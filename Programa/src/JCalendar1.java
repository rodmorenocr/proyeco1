
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class JCalendar1 extends JFrame {
    private JTextField jtFecha;
    private JCalendar2 calFull;
    
    public JCalendar1() {
        super("Mis Vacaciones");
        setSize(650, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        panel.add(new JLabel("Días de Vacaciones 2024:"));
        panel.add(new JLabel("25 días"));
        
        panel.add(new JLabel("Días Feriados 2024:"));
        panel.add(new JLabel("3 días"));
        
        panel.add(new JLabel("Solicitud:"));
        jtFecha = new JTextField("- / - / -");
        jtFecha.setEditable(false);
        panel.add(jtFecha);
        
        JButton jbSeleccFecha = new JButton(new ImageIcon(getClass().getResource("images/calendar-icon.png")));
        jbSeleccFecha.setToolTipText("Abrir calendario");
        jbSeleccFecha.addActionListener(e -> abrirCalendario());
        panel.add(jbSeleccFecha);
        
        JButton jbSolicitar = new JButton("Enviar Solicitud");
        jbSolicitar.addActionListener(this::enviarSolicitud);
        panel.add(jbSolicitar);
        
        add(panel, BorderLayout.CENTER);
        
        JLabel jLabelFooter = new JLabel("©2017 Aura Boutique Company", SwingConstants.CENTER);
        jLabelFooter.setFont(new Font("Andale Mono", Font.BOLD, 12));
        add(jLabelFooter, BorderLayout.SOUTH);
        
        calFull = new JCalendar2(this);
    }
    
    private void abrirCalendario() {
        calFull.setVisible(true);
        jtFecha.setText(calFull.getFechaCompleta(1));
    }
    
    private void enviarSolicitud(ActionEvent e) {
        Envio_Solicitud ventanaSolicitud = new Envio_Solicitud();
        ventanaSolicitud.setSize(290, 200);
        ventanaSolicitud.setLocationRelativeTo(null);
        ventanaSolicitud.setVisible(true);
        this.setVisible(false);
    }
}

