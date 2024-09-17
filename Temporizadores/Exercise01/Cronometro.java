package Exercise01;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import java.awt.Image;

public class Cronometro extends JFrame {

    private JLabel tiempoLabel;
    private JLabel tiempoLabel2;
    private JPanel panel;
    private JPanel radioBJPanel;
    private JTextField alarmaTextField;
    private Timer timer;
    private String[] songs = {"TimeSoun.wav","Bedside.wav","Overs.wav"};
    private JRadioButton[] playList;
    private Clip clip;
    private int segundos = 0;
    private int alarmaSegundos = -1;
    private boolean alarmaActiva = false;
    private boolean inPause = true;
    private String selecSong = songs[0];
    private Random random;

    public Cronometro(){

        setBounds(500, 100, 340, 220);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Cronometro con alarma.");
        Paneles();
    }

    private void Paneles(){
        panel = new JPanel();
        panel.setLayout(null);
        tiempoLabel = new JLabel("00:00:00");
        tiempoLabel.setFont(new Font("Serif", Font.BOLD, 34));
        tiempoLabel.setBounds(100, 37, 195, 50); 
        panel.add(tiempoLabel);

        alarmaTextField = new JTextField(10); 
        alarmaTextField.setBounds(210, 97, 100, 24);
        panel.add(alarmaTextField);

        tiempoLabel2 = new JLabel("Tiempo para primera alarma (s):");
        tiempoLabel2.setFont(new Font("Serif", Font.BOLD, 13));
        tiempoLabel2.setBounds(21, 80, 200, 50); 
        panel.add(tiempoLabel2);

        ImageIcon icon = new ImageIcon("Image/Pause.jpg");
        Image imgScaled = icon.getImage().getScaledInstance(30, 27, Image.SCALE_SMOOTH);

        JButton configurarAlarmaCtn = new JButton(new ImageIcon(imgScaled));
        configurarAlarmaCtn.setBounds(50, 140, 30, 27);
        panel.add(configurarAlarmaCtn);

        configurarAlarmaCtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MethoPause();
            }
        });

        ImageIcon icon3Icon = new ImageIcon("Image/Repetir.jpg");
        Image imgScaled3 = icon3Icon.getImage().getScaledInstance(30, 27, Image.SCALE_SMOOTH);

        JButton configurarAlarmaDtn = new JButton(new ImageIcon(imgScaled3));
        configurarAlarmaDtn.setBounds(250, 140, 30, 27);
        panel.add(configurarAlarmaDtn);

        configurarAlarmaDtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                MethodRepeat();
            }
            
        });

        ImageIcon icon2 = new ImageIcon("Image/Reproductor.jpg");
        Image imgScaled2 = icon2.getImage().getScaledInstance(30, 27, Image.SCALE_SMOOTH);

        JButton configurarAlarmaBtn = new JButton(new ImageIcon(imgScaled2));
        configurarAlarmaBtn.setBounds(150, 140, 30, 27);
        panel.add(configurarAlarmaBtn);

        configurarAlarmaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarTimer();
                configuralarma();
            }
        });

        radioBJPanel = new JPanel();
        radioBJPanel.setBounds(8, 3, 300, 90);
        chooseSound();
        panel.add(radioBJPanel);


        this.getContentPane().add(panel);
    }
       
    private void iniciarTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                segundos++;
                actualizarTiempo();
                if(alarmaActiva && segundos == alarmaSegundos){
                    random = new Random();
                    Color colorRandom = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
                    panel.setBackground(colorRandom);
                    radioBJPanel.setBackground(colorRandom);
                    for(int n = 0; n < songs.length; n++){
                        playList[n].setBackground(colorRandom);
                    }
                    reproducirSonido("Sounds/" + selecSong);
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
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error al reproducir el sonido.");
        }
    }

    private void chooseSound(){
        playList = new JRadioButton[songs.length];
        ButtonGroup group = new ButtonGroup(); 

        for (int n = 0; n < songs.length; n++) {
            final int index = n;
            playList[n] = new JRadioButton(songs[n]);
            playList[n].setOpaque(true);
            playList[n].setFont(new Font("Serif", Font.BOLD, 11));
            playList[n].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selecSong = songs[index];
                }
            });
            radioBJPanel.add(playList[n]);
            group.add(playList[n]); 
        }
        
    }

    private void MethoPause(){
        inPause = !inPause;
        
        if(!inPause){
            timer.stop();
        }else{
            timer.start();
        }
    }

    private void MethodRepeat(){

        if (timer != null) {
            timer.stop();
        }
        
        segundos = 0;
        actualizarTiempo();
        
        alarmaTextField.setText("");
        
        alarmaSegundos = -1;
        alarmaActiva = false;
        
        panel.setBackground(null);
        for(int n = 0; n < songs.length; n++){
            playList[n].setBackground(null);
        }
        radioBJPanel.setBackground(null);
        clip.close();
    }

}
    

