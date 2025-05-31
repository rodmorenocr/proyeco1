
import java.awt.*;
import javax.swing.*;

public class Envio_Solicitud extends JFrame {
    public Envio_Solicitud() {
        setTitle("Solicitud Enviada");
        setLayout(new BorderLayout());
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JLabel jLabel = new JLabel("Solicitud Enviada", SwingConstants.CENTER);
        jLabel.setFont(new Font("Andale Mono", Font.BOLD, 12));
        add(jLabel, BorderLayout.CENTER);
        
        JLabel jLabel5 = new JLabel("©2017 Aura Boutique Company", SwingConstants.CENTER);
        jLabel5.setFont(new Font("Andale Mono", Font.BOLD, 12));
        add(jLabel5, BorderLayout.SOUTH);
    }
}


