import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;


public class Menu extends JFrame {

    private NavigationPanel navigationPanel; // Panel de navegación izquierdo (antes jPanel1)
    private JPanel contentPanel;             // Panel de contenido derecho (antes jPanel2)
    private JLabel headerLabel;              // Etiqueta para la imagen de cabecera

    // 2. Componentes específicos del 'contentPanel'
    private JButton jButton1, jButton2, jButton5, jButton12, jButton13, jButton14, jButton15, jButton16, jButton17;
    private JLabel jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8, jLabel9, jLabel10;

    // 3. Datos del usuario
    public static String fotoUsuario = "";
    private String nombreUsuario;

    // --- CONSTRUCTOR ---
    public Menu() {
        this.nombreUsuario = Bienvenido.nombreUsuario;
        System.out.println("Usuario en Menú: " + nombreUsuario);
        // Asigna la foto 
        Map<String, String> fotoUsuarios = new HashMap<>();
        fotoUsuarios.put("dani", "usuarios/dani/1.jpg");
        fotoUsuarios.put("marorthat", "usuarios/marorthat/1.jpg");
        fotoUsuarios.put("danjimfra", "usuarios/danjimfra/1.jpg");
        fotoUsuarios.put("rodmorcru", "usuarios/rodmorcru/1.jpg");
        fotoUsuarios.put("jospedlop", "usuarios/jospedlop/1.jpg");
        fotoUsuarios.put("jose", "usuarios/jose/1.jpg");
        fotoUsuarios.put("hecbarcre", "usuarios/hecbarcre/1.jpg");
        fotoUsuario = fotoUsuarios.getOrDefault(nombreUsuario, "");
        initComponents();
    }

    // --- MÉTODOS DE INICIALIZACIÓN DE LA UI ---

    /**
     * Método principal que organiza la ventana.
     * Usa BorderLayout para posicionar la cabecera, la navegación y el contenido.
     */
    private void initComponents() {
        // Configuración de la ventana principal
        setTitle("Menú Principal");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Usamos BorderLayout como gestor de layout principal
        getContentPane().setLayout(new BorderLayout(5, 5)); // 5px de espacio entre componentes

        // 1. Crear e instanciar el panel de cabecera
        headerLabel = new JLabel();
        headerLabel.setIcon(new ImageIcon(getClass().getResource("/images/Foto_Cabezera.png"))); 

        // 2. Crear e instanciar el panel de navegación reutilizable
        navigationPanel = new NavigationPanel();

        // 3. Crear e instanciar el panel de contenido principal
        contentPanel = createContentPanel();

        // 4. Añadir los paneles al JFrame en sus respectivas posiciones
        getContentPane().add(headerLabel, BorderLayout.NORTH);
        getContentPane().add(navigationPanel, BorderLayout.WEST);
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        // Ajusta el tamaño de la ventana al contenido y la centra
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Crea y devuelve el panel de contenido .
     * Toda la lógica de los iconos y botones de la derecha está aquí.
     * @return un JPanel configurado.
     */
    private JPanel createContentPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);

        // Instanciar todos los componentes del panel de contenido
        jLabel3 = createIconLabel("/images/iconos_menu/1.png");
        jLabel4 = createIconLabel("/images/iconos_menu/2.png");
        jLabel5 = createIconLabel("/images/iconos_menu/4.png");
        jLabel6 = createIconLabel("/images/iconos_menu/3.png");
        jLabel7 = createIconLabel("/images/iconos_menu/6.png");
        jLabel8 = createIconLabel("/images/iconos_menu/7.png");
        jLabel9 = createIconLabel("/images/iconos_menu/8.png");
        jLabel10 = createIconLabel("/images/iconos_menu/5.png");

        jButton1 = createContentButton("Mis datos personales", this::jButton1ActionPerformed);
        jButton2 = createContentButton("Mis nóminas", this::jButton2ActionPerformed);
        jButton5 = createContentButton("Mis cursos", this::jButton5ActionPerformed);
        jButton12 = createContentButton("Mi horario", this::jButton12ActionPerformed);
        jButton13 = createContentButton("Mis vacaciones", this::jButton13ActionPerformed);
        jButton14 = createContentButton("Email", this::jButton14ActionPerformed);
        jButton15 = createContentButton("Seguro médico", this::jButton15ActionPerformed);
        jButton16 = createContentButton("Configuración", this::jButton16ActionPerformed);
        
        jButton17 = new JButton("Cerrar sesión");
        jButton17.setFont(new Font("Arial", Font.BOLD, 14));
        jButton17.addActionListener(this::jButton17ActionPerformed);

        // --- Configuración del Layout (GroupLayout) para el panel de contenido ---
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                    .addComponent(jLabel7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton14, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel8, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton15, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton16, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton17, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)))
                    .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton12, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton13, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))))
                .addGap(67, 67, 67))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton13)
                            .addComponent(jButton12))
                        .addGap(36, 36, 36)
                        .addComponent(jLabel9, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5)
                    .addComponent(jButton15)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton14)
                        .addComponent(jButton16)))
                .addGap(62, 62, 62)
                .addComponent(jButton17, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        return panel;
    }

    // --- MÉTODOS AYUDANTES PARA CREAR COMPONENTES ---

    private JLabel createIconLabel(String imagePath) {
        JLabel label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setIcon(new ImageIcon(getClass().getResource(imagePath)));
        label.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        return label;
    }

    private JButton createContentButton(String text, java.awt.event.ActionListener listener) {
        JButton button = new JButton(text);
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", 0, 13));
        button.addActionListener(listener);
        return button;
    }
    
    // --- ACCIONES DE LOS BOTONES DEL PANEL DE CONTENIDO ---

    private void jButton1ActionPerformed(ActionEvent evt) {
        new UserProfile(this, false).setVisible(true);
    }

    private void jButton2ActionPerformed(ActionEvent evt) {
        new Nominas(this, false).setVisible(true);
    }

    private void jButton12ActionPerformed(ActionEvent evt) {
        new Horario(this, false).setVisible(true);
    }

    private void jButton13ActionPerformed(ActionEvent evt) {
        new JCalendar1().setVisible(true);
    }

    private void jButton17ActionPerformed(ActionEvent evt) {   
        this.dispose(); // Cierra esta ventana
    }

    // Acciones vacías para los botones no implementados
    private void jButton5ActionPerformed(ActionEvent evt) {}
    private void jButton14ActionPerformed(ActionEvent evt) {}
    private void jButton15ActionPerformed(ActionEvent evt) {}
    private void jButton16ActionPerformed(ActionEvent evt) {}
}