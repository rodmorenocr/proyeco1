import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class Horario extends JDialog {

    // --- MIEMBROS DE LA CLASE ---
    private static String nombreUsuario;
    private long startTime;
    private long accumulatedTime = 0;

    // Componentes de la UI
    private NavigationPanel navigationPanel;
    private JPanel contentPanel;
    private JLabel headerLabel;
    private JLabel timeLabel;
    private JLabel computedTimeLabel;
    private Timer clockTimer;
    private Timer workTimer;

    public Horario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.nombreUsuario = Bienvenido.nombreUsuario;
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Horario");
    }

    private void initComponents() {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        // 1. Crear componentes principales
        headerLabel = new JLabel(new ImageIcon(getClass().getResource("/images/nominas/Foto_Cabezera.png")));
        navigationPanel = new NavigationPanel();
        contentPanel = createHorarioContentPanel();

        // 2. Añadir componentes a la ventana
        getContentPane().add(headerLabel, BorderLayout.NORTH);
        getContentPane().add(navigationPanel, BorderLayout.WEST);
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        // 3. Iniciar el reloj en tiempo real
        startRealTimeClock();
        
        pack();
    }
    
    private JPanel createHorarioContentPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // --- Panel de Título y Reloj ---
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setOpaque(false);
        
        JLabel titleLabel = new JLabel("Horario de trabajo");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        
        timeLabel = new JLabel("00:00:00", SwingConstants.RIGHT);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        
        titlePanel.add(titleLabel, BorderLayout.WEST);
        titlePanel.add(timeLabel, BorderLayout.EAST);

        // --- Panel de Botones de Jornada ---
        JPanel jornadaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jornadaPanel.setOpaque(false);
        JButton startButton = new JButton("Iniciar jornada");
        JButton stopButton = new JButton("Finalizar jornada");
        startButton.addActionListener(e -> startWorkTimer());
        stopButton.addActionListener(e -> stopWorkTimer());
        jornadaPanel.add(startButton);
        jornadaPanel.add(stopButton);

        // --- Panel de Tiempo Computado ---
        JPanel computedTimePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        computedTimePanel.setOpaque(false);
        computedTimeLabel = new JLabel("00:00:00");
        computedTimeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        computedTimePanel.add(new JLabel("Tiempo computado:"));
        computedTimePanel.add(computedTimeLabel);
        
        // --- Imagen del Calendario ---
        JLabel calendarImage = new JLabel(new ImageIcon(getClass().getResource("/images/nominas/Foto_calendario.png")));
        
        // --- Ensamblaje ---
        JPanel topContainer = new JPanel();
        topContainer.setOpaque(false);
        topContainer.setLayout(new BoxLayout(topContainer, BoxLayout.Y_AXIS));
        topContainer.add(titlePanel);
        topContainer.add(jornadaPanel);
        topContainer.add(computedTimePanel);

        panel.add(topContainer, BorderLayout.NORTH);
        panel.add(calendarImage, BorderLayout.CENTER);

        return panel;
    }

    private void startRealTimeClock() {
        clockTimer = new Timer(1000, e -> {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            timeLabel.setText(sdf.format(new Date()));
        });
        clockTimer.start();
    }

    private void startWorkTimer() {
        if (workTimer != null && workTimer.isRunning()) return; // Evita iniciar múltiples veces
        startTime = System.currentTimeMillis();
        workTimer = new Timer(1000, e -> updateComputedTime());
        workTimer.start();
    }

    private void stopWorkTimer() {
        if (workTimer == null || !workTimer.isRunning()) return; // No se puede parar si no está iniciado
        workTimer.stop();
        accumulatedTime += System.currentTimeMillis() - startTime;
        updateComputedTime(); // Actualiza una última vez
    }

    private void updateComputedTime() {
        long elapsedTime = accumulatedTime;
        if (workTimer != null && workTimer.isRunning()) {
            elapsedTime += System.currentTimeMillis() - startTime;
        }
        
        long hours = (elapsedTime / 3600000);
        long minutes = (elapsedTime / 60000) % 60;
        long seconds = (elapsedTime / 1000) % 60;
        computedTimeLabel.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
    }
}