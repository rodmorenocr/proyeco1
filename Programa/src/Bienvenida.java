import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
//import java.io.*;//Inportamos la libreria para trabajar con ficheros.
//import java.util.Objects;
//import java.util.Scanner;//Inportamos la libreria scanner para utilizar entrada de datos por teclado.
public class Bienvenida extends JFrame implements ActionListener{

  private JTextField textfield1;
  private JLabel label1, label2, label3, label4, label5;
  private JButton boton1;

  public Bienvenida(){
    setLayout(null);
    setTitle("Bienvenido");
    getContentPane().setBackground(new Color(255, 255, 255));
    //setIconImage(new ImageIcon(getClass().getResource("images/icon.png")).getImage());
    //ImageIcon imagen = new ImageIcon("C:/Users/Dell/Desktop/JAVA/PRUEBA_P1/src/images/1.png");
    //ImageIcon imagen = new ImageIcon("images/logo-coca.png");
    /*label1 = new JLabel(imagen);
    label1.setBounds(25,35,350,450);
    add(label1);*/

    //ImageIcon imagen2 = new ImageIcon("C:/Users/Dell/Desktop/JAVA/PRUEBA_P1/src/images/1.png");
    label5 = new JLabel("Aura Boutique");
    label5.setFont(new Font("Bradley Hand ITC", 3, 32));
    label5.setBounds(400,10,500,100);
    label5.setForeground(new Color(0, 0, 0, 255));
    add(label5);

    label2 = new JLabel("Sistema de Control Acceso");
    label2.setBounds(400,135,300,100);
    label2.setFont(new Font("Andale Mono", 3, 18));
    label2.setForeground(new Color(0, 0, 0, 255));
    add(label2);

    label3 = new JLabel("Ingrese su nombre");
    label3.setBounds(400,212,200,30);
    label3.setFont(new Font("Andale Mono", 1, 12));
    label3.setForeground(new Color(0, 0, 0));
    add(label3);

    label4 = new JLabel("�2017 Aura Boutique Company");
    label4.setBounds(400,375,300,30);
    label4.setFont(new Font("Andale Mono", 1, 12));
    label4.setForeground(new Color(0, 0, 0));
    add(label4);

    textfield1 = new JTextField ();
    textfield1.setBounds(400,240,255,25);
    textfield1.setBackground(new Color(224,224,224));
    textfield1.setFont(new Font("Andale Mono", 1, 14));
    textfield1.setForeground(new Color(255,0,0));
    add(textfield1);

    boton1 = new JButton("Ingresar");
    boton1.setBounds(400,280,100,30);
    boton1.setBackground(new Color(255,255,255));
    boton1.setFont(new Font("Andale Mono", 1, 14));
    boton1.setForeground(new Color(255,0,0));
    boton1.addActionListener(this);
    add(boton1);
    String imagen1="boutique1.png";       
    
    System.out.println(busquedad(imagen1));
    ImageIcon imagen = new ImageIcon(imagen1);
    label1 = new JLabel(imagen);
    label1.setBounds(0,0,350,450);
    add(label1);
  }



   public void actionPerformed(ActionEvent e){
     if(e.getSource() == boton1){
      Menu menu = new Menu(this, true);
      menu.setVisible(true);  
     }

   }

   
   public String busquedad (String datos) {
    String separador = System.getProperty("file.separator");
    String escritorio = System.getProperty("desktop");
    String rutafichero = System.getProperty("user.home") + separador+escritorio+separador+datos; // Creamos la variable donde guardamos la ruta del fichero.
    return rutafichero;
}


   public static void main(String args[]){
     Bienvenida ventanabienvenida = new Bienvenida();
     ventanabienvenida.setBounds(0,0,700,450);
     ventanabienvenida.setVisible(true);
     ventanabienvenida.setResizable(false);
     ventanabienvenida.setLocationRelativeTo(null);
   }
}