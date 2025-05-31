
import javax.swing.*;
import java.awt.*;

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


/*
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Envio_Solicitud extends JFrame {

    public JLabel jLabel , jlabel5;

    public Envio_Solicitud(){

        setLayout(null);
        jLabel = new JLabel("Solicitud Enviada");
        jLabel.setBounds(80,50,100,30);
        jLabel.setFont(new Font("Andale Mono", 1, 12));
        jLabel.setForeground(new Color(0, 0, 0));
        add(jLabel);

        jlabel5 = new JLabel("©2017 Aura Boutique Company");
        jlabel5.setBounds(50,120,300,30);
        jlabel5.setFont(new Font("Andale Mono", 1, 12));
        jlabel5.setForeground(new Color(0, 0, 0));
        add(jlabel5);

    }
}*/
