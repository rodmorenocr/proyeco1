import java.awt.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.*;

public class UserProfile extends JDialog {

    // --- MIEMBROS DE LA CLASE ---
    private static String nombreUsuario;
    private String propertiesFilePath;

    // Componentes de la UI
    private NavigationPanel navigationPanel;
    private JPanel contentPanel;
    private JLabel headerLabel;
    
    // Campos del formulario
    private JTextPane nombrePane, apellidoPane, emailPane, puestoPane, telefonoPane,
                      direccionPane, codigoPostalPane, ciudadPane, provinciaPane, paisPane;

    public UserProfile(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.nombreUsuario = Bienvenido.nombreUsuario;
        this.propertiesFilePath = "Programa/src/usuarios/" + nombreUsuario + "/datos.properties"; // CUIDADO con la ruta
        
        initComponents();
        loadUserData(); // Cargar los datos después de crear los componentes
        setLocationRelativeTo(null);
        setTitle("Datos Personales");
    }

    private void initComponents() {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        // 1. Crear componentes principales
        headerLabel = new JLabel(new ImageIcon(getClass().getResource("/images/nominas/Foto_Cabezera.png")));
        navigationPanel = new NavigationPanel();
        contentPanel = createUserProfileContentPanel();

        // 2. Añadir componentes a la ventana
        getContentPane().add(headerLabel, BorderLayout.NORTH);
        getContentPane().add(navigationPanel, BorderLayout.WEST);
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        
        pack();
    }

    private JPanel createUserProfileContentPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        // Inicializar todos los TextPane
        nombrePane = new JTextPane();
        apellidoPane = new JTextPane();
        puestoPane = new JTextPane();
        emailPane = new JTextPane();
        telefonoPane = new JTextPane();
        direccionPane = new JTextPane();
        codigoPostalPane = new JTextPane();
        ciudadPane = new JTextPane();
        provinciaPane = new JTextPane();
        paisPane = new JTextPane();

        JLabel titleLabel = new JLabel("Datos personales");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        
        JButton saveButton = new JButton("Guardar");
        saveButton.addActionListener(e -> saveUserData());

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(e -> this.dispose());

        // --- Layout (GroupLayout) para el formulario ---
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        JLabel nombreLabel = new JLabel("Nombre:");
        JLabel apellidoLabel = new JLabel("Apellido:");
        JLabel puestoLabel = new JLabel("Puesto:");
        JLabel emailLabel = new JLabel("Mail:");
        JLabel telefonoLabel = new JLabel("Teléfono:");
        JLabel direccionLabel = new JLabel("Dirección:");
        JLabel cpLabel = new JLabel("Código Postal:");
        JLabel ciudadLabel = new JLabel("Ciudad:");
        JLabel provinciaLabel = new JLabel("Provincia:");
        JLabel paisLabel = new JLabel("País:");

        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(titleLabel)
                .addComponent(nombreLabel)
                .addComponent(nombrePane, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                .addComponent(emailLabel)
                .addComponent(emailPane, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                .addComponent(telefonoLabel)
                .addComponent(telefonoPane, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                .addComponent(cpLabel)
                .addComponent(codigoPostalPane, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                .addComponent(provinciaLabel)
                .addComponent(provinciaPane, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(saveButton)
                    .addComponent(cancelButton))
            )
            .addGap(20)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(apellidoLabel)
                .addComponent(apellidoPane, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                .addComponent(puestoLabel)
                .addComponent(puestoPane, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                .addComponent(direccionLabel)
                .addComponent(direccionPane, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                .addComponent(ciudadLabel)
                .addComponent(ciudadPane, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                .addComponent(paisLabel)
                .addComponent(paisPane, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
            )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addComponent(titleLabel)
            .addGap(20)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(nombreLabel)
                .addComponent(apellidoLabel))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(nombrePane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(apellidoPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addGap(10)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(emailLabel)
                .addComponent(puestoLabel))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(emailPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(puestoPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addGap(10)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(telefonoLabel)
                .addComponent(direccionLabel))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(telefonoPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(direccionPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addGap(10)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(cpLabel)
                .addComponent(ciudadLabel))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(codigoPostalPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(ciudadPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addGap(10)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(provinciaLabel)
                .addComponent(paisLabel))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(provinciaPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(paisPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addGap(30)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(saveButton)
                .addComponent(cancelButton))
        );

        return panel;
    }

    private void loadUserData() {
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream(propertiesFilePath)) {
            props.load(in);
            nombrePane.setText(props.getProperty("nombre", ""));
            apellidoPane.setText(props.getProperty("apellidos", ""));
            emailPane.setText(props.getProperty("email", ""));
            puestoPane.setText(props.getProperty("puesto", ""));
            telefonoPane.setText(props.getProperty("telefono", ""));
            direccionPane.setText(props.getProperty("direccion", ""));
            codigoPostalPane.setText(props.getProperty("codigo_postal", ""));
            ciudadPane.setText(props.getProperty("ciudad", ""));
            provinciaPane.setText(props.getProperty("provincia", ""));
            paisPane.setText(props.getProperty("pais", ""));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "No se pudo cargar el archivo de datos del usuario.", "Error de Lectura", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveUserData() {
        Properties props = new Properties();
        props.setProperty("nombre", nombrePane.getText());
        props.setProperty("apellidos", apellidoPane.getText());
        props.setProperty("email", emailPane.getText());
        props.setProperty("puesto", puestoPane.getText());
        props.setProperty("telefono", telefonoPane.getText());
        props.setProperty("direccion", direccionPane.getText());
        props.setProperty("codigo_postal", codigoPostalPane.getText());
        props.setProperty("ciudad", ciudadPane.getText());
        props.setProperty("provincia", provinciaPane.getText());
        props.setProperty("pais", paisPane.getText());
        
        try (FileOutputStream out = new FileOutputStream(propertiesFilePath)) {
            props.store(out, "Datos del usuario");
            JOptionPane.showMessageDialog(this, "Datos guardados correctamente.", "Guardado", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "No se pudo guardar el archivo de datos del usuario.", "Error de Escritura", JOptionPane.ERROR_MESSAGE);
        }
    }
}