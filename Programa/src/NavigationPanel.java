import java.awt.*;
import javax.swing.*;

public class NavigationPanel extends JPanel {

    // Declara todos los componentes del panel como miembros de la clase
    private JButton jButtonMail;
    private JButton jButtonDatosPersonales;
    private JButton jButtonNominas;
    private JButton jButtonHorario;
    private JButton jButtonVacaciones;
    private JButton jButtonCursos;
    private JButton jButtonSeguroMedico;
    private JButton jButtonNoticias;
    private JButton jButtonPerfil;
    private JTextField jTextFieldInicio;
    private JTextField jTextFieldBusqueda;
    private JLabel jLabelUserIcon;

    public NavigationPanel() {
        // Llama al método que inicializa y configura todos los componentes
        initComponents();
    }

    private void initComponents() {
        // --- Configuración del Panel Principal ---
        this.setBackground(new Color(68, 68, 68));

        // --- Creación de Componentes ---
        jLabelUserIcon = new JLabel();
        jTextFieldInicio = new JTextField();
        jButtonMail = new JButton();
        jButtonDatosPersonales = new JButton();
        jButtonNominas = new JButton();
        jButtonHorario = new JButton();
        jButtonVacaciones = new JButton();
        jButtonCursos = new JButton();
        jButtonSeguroMedico = new JButton();
        jButtonNoticias = new JButton();
        jTextFieldBusqueda = new JTextField();

        // --- Configuración del Icono de Usuario ---
        jLabelUserIcon.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelUserIcon.setIcon(new ImageIcon(getClass().getResource("/images/user.png"))); // Asegúrate que la ruta es correcta

        // --- Configuración del Campo "Inicio" ---
        jTextFieldInicio.setBackground(new Color(38, 38, 38));
        jTextFieldInicio.setFont(new Font("Arial", Font.BOLD, 16));
        jTextFieldInicio.setForeground(Color.WHITE);
        jTextFieldInicio.setHorizontalAlignment(JTextField.CENTER);
        jTextFieldInicio.setText("Inicio");
        jTextFieldInicio.setEditable(false);
        jTextFieldInicio.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        
        // --- Configuración de los Botones ---
        configureButton(jButtonMail, "Mail", e -> openMail());
        configureButton(jButtonDatosPersonales, "Mis datos personales", e -> openUserProfile());
        configureButton(jButtonNominas, "Mis nóminas", e -> openNominas());
        configureButton(jButtonHorario, "Mi horario", e -> openHorario());
        configureButton(jButtonVacaciones, "Mis vacaciones", e -> openVacaciones());
        configureButton(jButtonCursos, "Mis cursos", e -> { /* Acción para cursos */ });
        configureButton(jButtonSeguroMedico, "Seguro médico", e -> { /* Acción para seguro */ });
        configureButton(jButtonNoticias, "Noticias de la empresa", e -> { /* Acción para noticias */ });
        
        // --- Configuración del Campo de Búsqueda ---
        jTextFieldBusqueda.setBackground(new Color(38, 38, 38));
        jTextFieldBusqueda.setFont(new Font("Arial", 0, 13));
        jTextFieldBusqueda.setForeground(Color.WHITE);
        jTextFieldBusqueda.setText("Barra de búsqueda");
        jTextFieldBusqueda.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        
        // --- Layout del Panel (GroupLayout) ---
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelUserIcon, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                    .addComponent(jTextFieldInicio)
                    .addComponent(jButtonMail, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonDatosPersonales, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonNominas, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonHorario, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonVacaciones, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonCursos, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonSeguroMedico, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonNoticias, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldBusqueda))
                .addContainerGap())
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelUserIcon, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldInicio, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jButtonMail, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonDatosPersonales, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonNominas, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonHorario, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonVacaciones, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCursos, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSeguroMedico, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonNoticias, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldBusqueda, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
    }

    /**
     * Método ayudante para configurar los botones y evitar código repetido.
     */
    private void configureButton(JButton button, String text, java.awt.event.ActionListener action) {
        button.setText(text);
        button.setBackground(new Color(38, 38, 38));
        button.setFont(new Font("Arial", 0, 13));
        button.setForeground(Color.WHITE);
        button.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.addActionListener(action);
    }

    // --- Métodos de acción para los botones ---
    
    private Frame getParentFrame() {
        Component component = this;
        while (component != null && !(component instanceof Frame)) {
            component = component.getParent();
        }
        return (Frame) component;
    }

    private void openUserProfile() {
        // El segundo parámetro 'false' indica que no es un dialogo modal
        UserProfile userProfile = new UserProfile(getParentFrame(), false);
        userProfile.setVisible(true);
    }
    
    private void openNominas() {
        Nominas nominas = new Nominas(getParentFrame(), false);
        nominas.setVisible(true);
    }
    
    private void openHorario() {
        Horario horario = new Horario(getParentFrame(), false);
        horario.setVisible(true);
    }
    
    private void openVacaciones() {
        // JCalendar es un JFrame, no necesita un Frame padre en el constructor
        JCalendar1 calendar = new JCalendar1();
        calendar.setVisible(true);
    }
    
    private void openMail() {
        // Implementa la lógica para abrir la ventana de Mail
        JOptionPane.showMessageDialog(this, "Funcionalidad de Mail no implementada.");
    }
}