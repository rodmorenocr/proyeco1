
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Nominas extends JDialog {
    public Nominas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        this.setTitle("Interfaz de Nómina");
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("¡Hola, Nómina!");
        JButton button = new JButton("Haz clic aquí");

        button.addActionListener(e -> label.setText("¡Botón pulsado en Nómina!"));

        panel.add(label);
        panel.add(button);
        add(panel);
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
