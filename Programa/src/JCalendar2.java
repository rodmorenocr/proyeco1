
import com.toedter.calendar.JCalendar;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class JCalendar2 extends JDialog {

    private JCalendar jcFecha;
    private JButton jbAceptar, jbLimpiar, jbAhora;
  //  private JComboBox<String> jcHora, jcMinutos;
    private int dia, mes, year, diaSemana, hora, minutos;
    String nombreDia, nombreMes, horaCadena, minutosCadena, fechaCompleta, horaCompleta;
    boolean selecciono;
    
    // constructor para crear un JCalendar con la fecha actual del sistema
    public JCalendar2(JFrame jf) {
        super(jf, "Calendario", true);
        set(); // asignar atributos con valores por defecto
        crearGUI(); 
    }
    
    /* 
    Constructor para crear un JCalendar con una fecha establecida
    La fecha que se desea establecer debe estar en formato "dia;mes;anio;hora;minutos"
    dia, mes, hora, minutos con 2 digitos. hora en formato militar
    anio con 4 digitos    
    Por ej: para establecer la fecha de 7 de marzo de 1979 a las 3:30 pm, 
    la cadena debe ser: "07;03;1979;15;30"
    */
    public JCalendar2(JFrame jf, String fecha) {
        super(jf, "Selector de Fecha", true);          
        set(); // asignar atributos con valores por defecto
        crearGUI();       
        
        selecciono = true;
        String tokens[] = fecha.split(";");
        Calendar calendario = new GregorianCalendar(
                Integer.parseInt(tokens[2]),        //convertir el año en entero
                Integer.parseInt(tokens[1]) - 1,    //convertir el mes en entero
                Integer.parseInt(tokens[0]));       //convertir el dia en entero
                
        jcFecha.setDate(calendario.getTime());  //asignar la fecha al jcFecha
        
      // jcHora.setSelectedItem(tokens[3]);      //asignar la hora al combobox
      //  jcMinutos.setSelectedItem(tokens[4]);   //asignar los minutos al combobox
        
        dia = calendario.get(Calendar.DATE);
        mes = calendario.get(Calendar.MONTH) + 1;
        year = calendario.get(Calendar.YEAR);
        diaSemana = calendario.get(Calendar.DAY_OF_WEEK);

      //  hora = Integer.parseInt((String) jcHora.getSelectedItem());
      //  minutos = Integer.parseInt((String) jcMinutos.getSelectedItem());
      //  horaCadena = (String) jcHora.getSelectedItem();
      //  minutosCadena = (String) jcMinutos.getSelectedItem();
        
        setNombreDia();
        setNombreMes();
    }
    
    public void set(){
        dia = mes = year = diaSemana = hora = minutos = 0;
        nombreDia = nombreMes = horaCadena = minutosCadena = fechaCompleta = horaCompleta = "";
        selecciono = false;
    }
    
    public void crearGUI(){
        // crear el JDialog
        setSize(315, 330);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        // asignar icono en la barra de titulo del JDialog
        Image icon = new ImageIcon(getClass().getResource("images/calendar-icon.png")).getImage();
        setIconImage(icon);
        
        // crear los componentes para ser adicionados el JDialog
        jcFecha = new JCalendar();// crear el JCalendar con la fecha actual del sistema
        jcFecha.setBounds(30, 10, 240, 200);// Ubicar y agregar al panel             
        add(jcFecha);
        /*
        jcHora = new JComboBox<String>();
        for(int i = 0; i < 24; i++){
            if(i < 10) jcHora.addItem("0" + i);
            else jcHora.addItem("" + i);
        }
        jcHora.setBounds(97, 215, 48, 25);
        add(jcHora);*/
        
      /*  JLabel jl = new JLabel("***************************");
        jl.setBounds(152, 215, 20, 25);
        add(jl);
        
        jcMinutos = new JComboBox<String>();
        for(int i = 0; i < 60; i++){
            if(i < 10) jcMinutos.addItem("0" + i);
            else jcMinutos.addItem("" + i);
        }
        jcMinutos.setBounds(162, 215, 48, 25);
        add(jcMinutos);


       */

        jbAceptar = new JButton("Solicitar día");
        jbAceptar.setBounds(20, 248, 150, 25);// Ubicar y agregar al panel
        jbAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evento_jbAceptar();
            }
        });
        add(jbAceptar);
        /*
        jbAhora = new JButton("Ahora");
        jbAhora.setBounds(110, 248, 80, 25);// Ubicar y agregar al panel
        jbAhora.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evento_jbAhora();
            }
        });
        add(jbAhora);
        */
        
        jbLimpiar = new JButton("Volver");
        jbLimpiar.setBounds(195, 248, 85, 25);// Ubicar y agregar al panel
        jbLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evento_jbLimpiar();
            }
        });
        add(jbLimpiar);
    }

    private void evento_jbAceptar() {        
        selecciono = true;
        Calendar calendario = jcFecha.getCalendar();
        dia = calendario.get(Calendar.DATE);
        mes = calendario.get(Calendar.MONTH) + 1;
        year = calendario.get(Calendar.YEAR);
        diaSemana = calendario.get(Calendar.DAY_OF_WEEK);
      //  hora = Integer.parseInt((String) jcHora.getSelectedItem());
      //  minutos = Integer.parseInt((String) jcMinutos.getSelectedItem());
      //  horaCadena = (String) jcHora.getSelectedItem();
      //  minutosCadena = (String) jcMinutos.getSelectedItem();
        
        setNombreDia();
        setNombreMes();
        
        setVisible(false);
    }

 /*   private void evento_jbAhora() {
        jcFecha.setDate(new Date());// asignar la fecha actual del sistema al JCalendar
        
        // obtener la fecha actual del sistema
        Calendar actual = Calendar.getInstance();
        int hora = actual.get(Calendar.HOUR_OF_DAY); // obtener la hora del dia
        int min =   actual.get(Calendar.MINUTE); // obtener los minutos del dia
        
        // asignar la hora al combobox
        if(hora < 10) jcHora.setSelectedItem("0" + hora);
        else jcHora.setSelectedItem(hora + "");
        
        // asignar los minutos al combobox
        if(min < 10) jcMinutos.setSelectedItem("0" + min);
        else jcMinutos.setSelectedItem(min + "");        
    }
    */

    
    private void evento_jbLimpiar() {
        set(); // asignar atributos con valores por defecto
     //   evento_jbAhora();
        setVisible(false);
    }

    public void setNombreDia(){
    	switch(diaSemana){
	    case 1: nombreDia = "Domingo";	break;
	    case 2: nombreDia = "Lunes"; 	break;
	    case 3: nombreDia = "Martes"; 	break;
	    case 4: nombreDia = "Miércoles"; 	break;
	    case 5: nombreDia = "Jueves"; 	break;
	    case 6: nombreDia = "Viernes"; 	break;
	    case 7: nombreDia = "Sábado"; 	break;
    	}	
    }
    
    public void setNombreMes(){
    	switch(mes){
	    case 1:  nombreMes = "Enero"; 	break;
	    case 2:  nombreMes = "Febrero"; 	break;
	    case 3:  nombreMes = "Marzo"; 	break;
	    case 4:  nombreMes = "Abril"; 	break;
	    case 5:  nombreMes = "Mayo"; 	break;
	    case 6:  nombreMes = "Junio"; 	break;
	    case 7:  nombreMes = "Julio"; 	break;
	    case 8:  nombreMes = "Agosto"; 	break;
	    case 9:  nombreMes = "Septiembre"; 	break;
	    case 10: nombreMes = "Octubre"; 	break;
	    case 11: nombreMes = "Noviembre"; 	break;
	    case 12: nombreMes = "Diciembre"; 	break;
    	}	
    }	
            
    public void setFecha(int tipo){
        fechaCompleta = "";
        switch (tipo) {
            case 1: // fecha en formato DD/MM/YYYY (2 digitos para el dia y el mes. 4 digitos para el anio)
                if(dia < 10) fechaCompleta+="0" + dia + "/";
                else fechaCompleta+= dia + "/";
                
                if(mes < 10) fechaCompleta+="0" + mes + "/";
                else fechaCompleta+= mes + "/";
                
                fechaCompleta+= year;
                break;
            case 2: // fecha en formato YYYY/MM/DD (4 digitos para el anio. 2 digitos para el mes y el dia)
                fechaCompleta+= year + "/";
                
                if(mes < 10) fechaCompleta+="0" + mes + "/";
                else fechaCompleta+= mes + "/";
                
                if(dia < 10) fechaCompleta+="0" + dia;
                else fechaCompleta+= dia;
                break; 
            case 3: // fecha en formato MM/DD/YYYY (2 digitos para el mes y el dia. 4 digitos para el anio)
                if(mes < 10) fechaCompleta+="0" + mes + "/";
                else fechaCompleta+= mes + "/";
                
                if(dia < 10) fechaCompleta+="0" + dia + "/";
                else fechaCompleta+= dia + "/";
                
                fechaCompleta+= year;
                break; 
            default:
                fechaCompleta = "FORMATO INVALIDO";
        }        
    }
    
    
    public int getDia(){ return dia; }
    public int getMes(){ return mes; }
    public int getYear(){ return year; }
    public int getHora(){ return hora; }
    public int getMinutos(){ return minutos; }
    public int getDiaSemana(){ return diaSemana; }    
    public String getNombreDia(){ return nombreDia; }
    public String getNombreMes(){ return nombreMes; }
    public String getHoraCadena(){ return horaCadena; }
    public String getMinutosCadena(){ return minutosCadena; } 
    public String getHoraCompleta(){ return horaCadena + ":" + minutosCadena; }
    public String getFechaCompleta(int tipo){
        if(selecciono) setFecha(tipo);
        else fechaCompleta = "";
        return fechaCompleta;
    }
}
