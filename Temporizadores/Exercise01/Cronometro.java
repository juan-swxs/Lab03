package Exercise01;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Cronometro extends JFrame {

    private JLabel tiempoLabel;
    private JLabel tiempoLabel2;
    private JPanel panel;
    private JTextField alarmaTextField;
    private Timer timer;
    private int segundos = 0;
    private int alarmaSegundos = -1;
    private boolean alarmaActiva = false;

    public Cronometro(){

        setBounds(500, 100, 340, 240);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Cronometro con alarma.");
        Paneles();
    }

    private void Paneles(){
        panel = new JPanel();
        panel.setLayout(null);
        tiempoLabel = new JLabel("00:00:00");
        tiempoLabel.setFont(new Font("Serif", Font.BOLD, 34));
        tiempoLabel.setBounds(100, 32, 195, 50); 
        panel.add(tiempoLabel);

        alarmaTextField = new JTextField(10); 
        alarmaTextField.setBounds(210, 97, 100, 24);
        panel.add(alarmaTextField);

        tiempoLabel2 = new JLabel("Tiempo para primera alarma (s) :");
        tiempoLabel2.setFont(new Font("Serif", Font.BOLD, 12));
        tiempoLabel2.setBounds(30, 80, 200, 50); 
        panel.add(tiempoLabel2);

        JButton configurarAlarmaBtn = new JButton("Iniciar Alarma");
        configurarAlarmaBtn.setBounds(85, 140, 170, 27);
        panel.add(configurarAlarmaBtn);

        configurarAlarmaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarTimer();
                configuralarma();
            }
        });

        this.getContentPane().add(panel);
    }
       
    private void iniciarTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                segundos++;
                actualizarTiempo();
                if(alarmaActiva && segundos == alarmaSegundos){
                    panel.setBackground(Color.GREEN);
                    reproducirSonido("Sounds/TimeSoun.wav");
                }
            }
        });

        timer.start();
    }


    private void actualizarTiempo() {
        int horas = segundos / 3600;
        int minutos = (segundos % 3600) / 60;
        int seg = segundos % 60;

        String tiempoFormat = String.format("%02d:%02d:%02d", horas, minutos, seg);
        tiempoLabel.setText(tiempoFormat);
    }

    private void configuralarma(){
        try {
            int segundosAlarma = Integer.parseInt(alarmaTextField.getText());
            alarmaSegundos = segundosAlarma;
            alarmaActiva = true;
        } catch (NumberFormatException ex) {
            System.out.println("Por favor, introduce un número válido para la alarma.");
        }

    }

    private void reproducirSonido(String archivoSonido) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(archivoSonido).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error al reproducir el sonido.");
            ex.printStackTrace();
        }
    }

}
    

