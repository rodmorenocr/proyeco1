
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class JCalendar1 extends JFrame implements ActionListener {

    JLabel jlabel1 , jlabel2, jlabel3 ,  jlabel4 ,  jlabelResultado,jlabelImagen ,jlabel5,jlabelCuadro;
    JButton jbSeleccFecha,jbSolitar;
    JTextField jtFecha;
    JCalendar2 calFull;
    JTextArea textarea1;
    JScrollPane scrollpane1;

    public JCalendar1() {
        super("Mis Vacaciones");
        setSize(650, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        crearGUI();



        calFull = new JCalendar2(this);
       // calFull = new JCalendarFull(this, "07;03;1979;15;30");// "dia;mes;anio;hora;minutos"

        setVisible(true);

        ImageIcon imagen = new ImageIcon("C:/Users/Dell/Desktop/JAVA/proy1/src/images/boutique1.png");
        jlabelImagen = new JLabel(imagen);
        jlabelImagen.setBounds(10,15,300,320);
        add(jlabelImagen);
    }

    private void crearGUI() {




        JLabel jl = new JLabel("Ver Calendario:");
        jl.setBounds(370, 30, 150, 25);
        add(jl);
/*
        jtFecha = new JTextField();
        jtFecha = new JTextField("            -         ");// dia/mes/anio
        jtFecha.setBounds(170, 30, 80, 25);
        jtFecha.setEditable(false);
        add(jtFecha);

*/
        ImageIcon img = new ImageIcon(getClass().getResource("images/calendar-icon.png"));
        jbSeleccFecha = new JButton(img);
        jbSeleccFecha.setBounds(490, 30, 24, 24);
        jbSeleccFecha.setBorderPainted(false);
        jbSeleccFecha.setFocusPainted(false);
        jbSeleccFecha.setContentAreaFilled(false);
        jbSeleccFecha.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jbSeleccFecha.setToolTipText("Calendario");

        jbSeleccFecha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evento_jbSeleccFecha();
            }
        });
        add(jbSeleccFecha);



        jlabel1= new JLabel("Dias de Vacaciones 2024:");
        jlabel1.setBounds(330,100,180,25);
        add(jlabel1);

        jlabel2= new JLabel(" 25 dias ");
        jlabel2.setBounds(500,100,180,25);
        add(jlabel2);

        jlabel3= new JLabel("Dias Feriados 2024:  ");
        jlabel3.setBounds(330,130,180,25);
        add(jlabel3);

        jlabel4= new JLabel(" 3 dias ");
        jlabel4.setBounds(500,130,180,25);
        add(jlabel4);

        jlabelResultado = new JLabel("Solicitud:");
        jlabelResultado.setBounds(330,160,180,25);
        jlabelResultado.setFont(new Font("Andale Mono", 1, 12));
        add(jlabelResultado);

        jtFecha = new JTextField();
        jtFecha = new JTextField("            -         ");// dia/mes/anio
        jtFecha.setBounds(330, 190, 80, 25);
        jtFecha.setEditable(false);
        add(jtFecha);

        jbSolitar = new JButton("Enviar Solicitud");
        jbSolitar.setBounds(330, 230, 150, 25);
        jbSolitar.addActionListener(this);
        add(jbSolitar);

        jlabel5 = new JLabel("©2017 Aura Boutique Company");
        jlabel5.setBounds(350,300,300,30);
        jlabel5.setFont(new Font("Andale Mono", 1, 12));
        jlabel5.setForeground(new Color(0, 0, 0));
        add(jlabel5);

        }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbSolitar) {
            Envio_Solicitud ventanaSolicitud = new Envio_Solicitud();
            ventanaSolicitud.setBounds(0, 0, 290, 200);
            ventanaSolicitud.setVisible(true);
            ventanaSolicitud.setResizable(false);
            ventanaSolicitud.setLocationRelativeTo(null);
            this.setVisible(false);

        }
    }
    private void evento_jbSeleccFecha() {
        calFull.setVisible(true);
        jtFecha.setText(calFull.getFechaCompleta(1));
    }




    /*


        jbGuardar = new JButton("Ver datos Vacaciones del Usuario");
        jbGuardar.setBounds(110, 70, 120, 25);
        jbGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //evento_jbGuardar();
            }
        });
        add(jbGuardar);
    }

    private void evento_jbSeleccFecha() {
        calFull.setVisible(true);
        jtFecha.setText(calFull.getFechaCompleta(1));
    }
/*
    private void evento_jbGuardar() {
        verFechaFull();
    }*/


  /*  public void verFechaFull() {
        System.out.println("----------- Fecha seleccionada ------------");
        System.out.println("dia = " + calFull.getDia());
        System.out.println("mes = " + calFull.getMes());
        System.out.println("año = " + calFull.getYear());
        System.out.println("numero dia semana = " + calFull.getDiaSemana());
        System.out.println("nombre dia semana = " + calFull.getNombreDia());
        System.out.println("nombre mes = " + calFull.getNombreMes());
        System.out.println("hora numero = " + calFull.getHora());
        System.out.println("hora cadena = " + calFull.getHoraCadena());
        System.out.println("minutos numero = " + calFull.getMinutos());
        System.out.println("minutos cadena = " + calFull.getMinutosCadena());
        System.out.println("hora completa (formato militar) = " + calFull.getHoraCompleta());
    }*/

}
    
   /*public static void main(String[] args) {
        JCalendarDemo demo = new JCalendarDemo();
    }*/


