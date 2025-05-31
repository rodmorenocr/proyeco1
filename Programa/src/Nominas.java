import java.awt.*;
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
        if (node == null || !node.isLeaf()) {
            if (node != null && !node.isRoot()) { // Solo si no es la raíz
                String nodeName = node.toString();
                String imagePath = "";
                switch (nodeName) {
                    case "Nómina Diciembre" -> {
                        arbol_seleccionado = 1;
                        imagePath = "/usuarios/" + nombreUsuario + "/Nomina_Daniel1.png";
                    }
                    case "Nómina Noviembre" -> {
                        arbol_seleccionado = 3; // El orden era diferente en tu código original
                        imagePath = "/usuarios/" + nombreUsuario + "/Nomina_Daniel3.png";
                    }
                    case "Nómina Octubre" -> {
                        arbol_seleccionado = 2;
                        imagePath = "/usuarios/" + nombreUsuario + "/Nomina_Daniel2.png";
                    }
                    default -> arbol_seleccionado = 0;
                }
                if (!imagePath.isEmpty()) {
                    imageLabel.setText("");
                    imageLabel.setIcon(new ImageIcon(getClass().getResource(imagePath)));
                }
            }
            return;
        }
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
