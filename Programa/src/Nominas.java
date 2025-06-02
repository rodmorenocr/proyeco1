import java.awt.*;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;


public class Nominas extends JDialog {
    
    // --- MIEMBROS DE LA CLASE ---
    private static String nombreUsuario;
    private int arbol_seleccionado = 0;

    // Componentes de la UI
    private NavigationPanel navigationPanel;
    private JPanel contentPanel;
    private JLabel headerLabel;
    private JLabel imageLabel;
    private JTree jTree1;

    public Nominas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.nombreUsuario = Bienvenido.nombreUsuario;
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Nóminas");
    }

    private void initComponents() {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout()); // Layout principal

        // 1. Crear e instanciar los componentes principales
        headerLabel = new JLabel(new ImageIcon(getClass().getResource("/images/nominas/Foto_Cabezera.png")));
        navigationPanel = new NavigationPanel();
        contentPanel = createNominasContentPanel(); // Panel solo con el contenido de nóminas

        // 2. Añadir los paneles a la ventana
        getContentPane().add(headerLabel, BorderLayout.NORTH);
        getContentPane().add(navigationPanel, BorderLayout.WEST);
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        // 3. Ajustar el tamaño de la ventana
        pack();
    }

    /**
     * Crea y devuelve el panel que contiene solo la lógica de las nóminas (árbol, imagen, etc.).
     * @return El panel de contenido.
     */
    private JPanel createNominasContentPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10)); // Usamos BorderLayout para el contenido
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Añadir márgenes
        panel.setBackground(Color.WHITE);

        // --- Panel Superior con Título y Búsqueda ---
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setOpaque(false);

        JLabel titleLabel = new JLabel("Recibo de nóminas");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        
        JTextField searchField = new JTextField("Buscar");
        searchField.setMaximumSize(new Dimension(Integer.MAX_VALUE, searchField.getPreferredSize().height));

        topPanel.add(titleLabel);
        topPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        topPanel.add(searchField);

        // --- Componentes Principales ---
        jTree1 = createConfiguredTree();
        JScrollPane treeScrollPane = new JScrollPane(jTree1);
        treeScrollPane.setPreferredSize(new Dimension(250, 0));

        imageLabel = new JLabel("Seleccione una nómina para visualizar.", SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.TOP);
        
        // --- Botón de Descargar ---
        JButton downloadButton = new JButton("Descargar");
        downloadButton.addActionListener(this::downloadButtonActionPerformed);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setOpaque(false);
        buttonPanel.add(downloadButton);

        // Añadir componentes al panel principal de contenido
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(treeScrollPane, BorderLayout.WEST);
        panel.add(imageLabel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JTree createConfiguredTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("WorkSpace");
        String[] meses = {"Nómina Diciembre", "Nómina Noviembre", "Nómina Octubre"};
        String[] fechas = {"01/12/2024-31/12/2024", "01/11/2024-30/11/2024", "01/10/2024-31/10/2024"};

        for (int i = 0; i < meses.length; i++) {
            DefaultMutableTreeNode mesNode = new DefaultMutableTreeNode(meses[i]);
            mesNode.add(new DefaultMutableTreeNode(fechas[i]));
            root.add(mesNode);
        }

        JTree tree = new JTree(root);
        tree.addTreeSelectionListener(this::jTree1ValueChanged);
        return tree;
    }

   private void jTree1ValueChanged(javax.swing.event.TreeSelectionEvent evt) {
    DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree1.getLastSelectedPathComponent();

    // Si no es un nodo hoja (un mes), no hacer nada
    if (node == null || !node.isLeaf()) {
        imageLabel.setIcon(null);
        imageLabel.setText("Seleccione una nómina para visualizar.");
        return;
    }
    
    // Obtenemos los datos del nodo padre (el mes) y el abuelo (el año)
    DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) node.getParent();
    String textoMes = parentNode.toString(); // Ej: "Nómina Diciembre"
    
    // Extraemos el mes y el año (esto depende de cómo construyes el árbol)
    // Asumiremos la estructura estática que tienes ahora
    int mes = 0;
    int anio = 2024; // Asumimos 2024, ajústalo si es necesario

    if (textoMes.contains("Diciembre")) mes = 12;
    else if (textoMes.contains("Noviembre")) mes = 11;
    else if (textoMes.contains("Octubre")) mes = 10;
    // Añade más meses si los tienes en tu árbol estático
    
    if (mes == 0) return; // Mes no reconocido

    try {
        // Construimos la URL a nuestro NUEVO script file_server.php
        String urlString = String.format(
            "https://auraboutique.info/wp-content/themes/divi-child/file_server.php?dni=%s&mes=%d&anio=%d",
            Menu.dnib, // El DNI del usuario logueado
            mes,
            anio
        );

        URL urlDelServidorDeArchivos = new URL(urlString);

        // ImageIO lee la imagen directamente del stream que le envía el PHP
        Image imagen = ImageIO.read(urlDelServidorDeArchivos);

        if (imagen != null) {
            imageLabel.setText("");
            imageLabel.setIcon(new ImageIcon(imagen));
        } else {
             imageLabel.setIcon(null);
             imageLabel.setText("Error: archivo recibido no es una imagen válida.");
        }

    } catch (Exception e) {
        imageLabel.setIcon(null);
        imageLabel.setText("No se pudo cargar la imagen.");
        System.err.println("Error al cargar imagen desde el file_server: " + e.getMessage());
        e.printStackTrace();
    }
    
    // El revalidate/repaint sigue siendo una buena práctica
    contentPanel.revalidate();
    contentPanel.repaint();
}
    private void downloadButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (arbol_seleccionado == 0 || imageLabel.getIcon() == null) {
            JOptionPane.showMessageDialog(this, "No hay ninguna nómina seleccionada para la descarga.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String fileName = "";
        switch (arbol_seleccionado) {
            case 1 -> fileName = "nomina_diciembre.png";
            case 2 -> fileName = "nomina_octubre.png";
            case 3 -> fileName = "nomina_noviembre.png";
        }

        // Lógica para guardar la imagen (simplificada)
        JOptionPane.showMessageDialog(this, "Nómina '" + fileName + "' descargada en el escritorio.", "Descarga Completa", JOptionPane.INFORMATION_MESSAGE);
    }
}
