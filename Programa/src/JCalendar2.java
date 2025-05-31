
import com.toedter.calendar.JCalendar;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class JCalendar2 extends JDialog {

    private JCalendar jcFecha;
    private JButton jbAceptar, jbLimpiar;
    private JLabel jlFechaHoraActual;
    private int dia, mes, year, diaSemana, hora, minutos;
    String nombreDia, nombreMes, horaCadena, minutosCadena, fechaCompleta, horaCompleta;
    boolean selecciono;

    public JCalendar2(JFrame jf) {
        super(jf, "Calendario", true);
        set();
        crearGUI();
    }

    public JCalendar2(JFrame jf, String fecha) {
        super(jf, "Selector de Fecha", true);
        set();
        crearGUI();

        selecciono = true;
        String[] tokens = fecha.split(";");
        Calendar calendario = new GregorianCalendar(
                Integer.parseInt(tokens[2]),
                Integer.parseInt(tokens[1]) - 1,
                Integer.parseInt(tokens[0])
        );

        jcFecha.setDate(calendario.getTime());

        dia = calendario.get(Calendar.DATE);
        mes = calendario.get(Calendar.MONTH) + 1;
        year = calendario.get(Calendar.YEAR);
        diaSemana = calendario.get(Calendar.DAY_OF_WEEK);

        setNombreDia();
        setNombreMes();
    }

    public void set() {
        dia = mes = year = diaSemana = hora = minutos = 0;
        nombreDia = nombreMes = horaCadena = minutosCadena = fechaCompleta = horaCompleta = "";
        selecciono = false;
    }

    public void crearGUI() {
        setSize(315, 330);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        Image icon = new ImageIcon(getClass().getResource("images/calendar-icon.png")).getImage();
        setIconImage(icon);

        jcFecha = new JCalendar();
        jcFecha.setBounds(30, 40, 240, 200);
        jcFecha.setMinSelectableDate(new Date()); // Bloquear fechas pasadas
        add(jcFecha);

        // Mostrar hora y fecha actual
        jlFechaHoraActual = new JLabel();
        jlFechaHoraActual.setBounds(10, 10, 300, 20);
        actualizarFechaHoraActual();
        add(jlFechaHoraActual);

        jbAceptar = new JButton("Solicitar día");
        jbAceptar.setBounds(20, 248, 150, 25);
        jbAceptar.addActionListener(this::evento_jbAceptar);
        add(jbAceptar);

        jbLimpiar = new JButton("Volver");
        jbLimpiar.setBounds(195, 248, 85, 25);
        jbLimpiar.addActionListener(this::evento_jbLimpiar);
        add(jbLimpiar);
    }

    private void actualizarFechaHoraActual() {
        Calendar now = Calendar.getInstance();
        Date fecha = now.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy - HH:mm");
        jlFechaHoraActual.setText(sdf.format(fecha));
    }

    private void evento_jbAceptar(ActionEvent e) {
        selecciono = true;
        Calendar calendario = jcFecha.getCalendar();
        dia = calendario.get(Calendar.DATE);
        mes = calendario.get(Calendar.MONTH) + 1;
        year = calendario.get(Calendar.YEAR);
        diaSemana = calendario.get(Calendar.DAY_OF_WEEK);
        hora = calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE);

        setNombreDia();
        setNombreMes();
        setHoraCompleta();

        setVisible(false);
    }

    private void setHoraCompleta() {
        horaCadena = String.format("%02d", hora);
        minutosCadena = String.format("%02d", minutos);
        horaCompleta = horaCadena + ":" + minutosCadena;
    }

    private void evento_jbLimpiar(ActionEvent e) {
        set();
        setVisible(false);
    }

    public void setNombreDia() {
        switch (diaSemana) {
            case 1 -> nombreDia = "Domingo";
            case 2 -> nombreDia = "Lunes";
            case 3 -> nombreDia = "Martes";
            case 4 -> nombreDia = "Miércoles";
            case 5 -> nombreDia = "Jueves";
            case 6 -> nombreDia = "Viernes";
            case 7 -> nombreDia = "Sábado";
        }
    }

    public void setNombreMes() {
        switch (mes) {
            case 1 -> nombreMes = "Enero";
            case 2 -> nombreMes = "Febrero";
            case 3 -> nombreMes = "Marzo";
            case 4 -> nombreMes = "Abril";
            case 5 -> nombreMes = "Mayo";
            case 6 -> nombreMes = "Junio";
            case 7 -> nombreMes = "Julio";
            case 8 -> nombreMes = "Agosto";
            case 9 -> nombreMes = "Septiembre";
            case 10 -> nombreMes = "Octubre";
            case 11 -> nombreMes = "Noviembre";
            case 12 -> nombreMes = "Diciembre";
        }
    }

    public void setFecha(int tipo) {
        fechaCompleta = switch (tipo) {
            case 1 -> String.format("%02d/%02d/%04d", dia, mes, year);
            case 2 -> String.format("%04d/%02d/%02d", year, mes, dia);
            case 3 -> String.format("%02d/%02d/%04d", mes, dia, year);
            default -> "FORMATO INVALIDO";
        };
    }

    public int getDia() { return dia; }
    public int getMes() { return mes; }
    public int getYear() { return year; }
    public int getHora() { return hora; }
    public int getMinutos() { return minutos; }
    public int getDiaSemana() { return diaSemana; }
    public String getNombreDia() { return nombreDia; }
    public String getNombreMes() { return nombreMes; }
    public String getHoraCadena() { return horaCadena; }
    public String getMinutosCadena() { return minutosCadena; }
    public String getHoraCompleta() { return horaCompleta; }
    public String getFechaCompleta(int tipo) {
        if (selecciono) setFecha(tipo);
        else fechaCompleta = "";
        return fechaCompleta;
    }
}
