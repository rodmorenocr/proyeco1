
/* 
import javax.swing.*;
import java.awt.*;

public class Descarga_archivos extends JDialog {
    private JLabel jLabel;
    private JLabel jLabel5;

    public Descarga_archivos(String message) {
        setTitle("Nóminas");
        setLayout(new BorderLayout());
        setSize(300, 200);
        setLocationRelativeTo(null);

        jLabel = new JLabel("<html><body style='width: 200px; text-align: center;'>" + message + "</body></html>", SwingConstants.CENTER);
        jLabel.setFont(new Font("Andale Mono", Font.BOLD, 12));
        add(jLabel, BorderLayout.CENTER);
        
        jLabel5 = new JLabel("©2017 Aura Boutique Company", SwingConstants.CENTER);
        jLabel5.setFont(new Font("Andale Mono", Font.BOLD, 12));
        add(jLabel5, BorderLayout.SOUTH);
    }
}*/




import java.awt.*;
import javax.swing.JLabel;

public class Descarga_archivos extends javax.swing.JDialog {
    public JLabel jLabel, jlabel5;
    private String message;
    public Descarga_archivos(String message){
        this.message = message;
        setTitle("Nominas");
        setLayout(null);
        jLabel = new JLabel("<html><body style='width: 200px; padding: 10px; text-align: center;'>" + message + "</body></html>");
        jLabel.setBounds(20,50,220,30);
        jLabel.setFont(new Font("Andale Mono", 1, 12));
        jLabel.setForeground(new Color(0, 0, 0));
        add(jLabel);

        jlabel5 = new JLabel("©2017 Aura Boutique Company");
        jlabel5.setBounds(50,120,300,30);
        jlabel5.setFont(new Font("Andale Mono", 1, 12));
        jlabel5.setForeground(new Color(0, 0, 0));
        add(jlabel5);

    }
}


