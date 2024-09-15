package Exercise01;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Cronometro extends JFrame {

    private JLabel tiempoLabel;
    private JTextField alarmaTextField;
    private Timer timer;
    private int segundos = 0;
    private int alarmaSegundos = -1;
    private boolean alarmaActiva = false;


    public Cronometro(){

        setBounds(500, 100, 400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Cronometro con alarma.");

        IniciarComp();
        iniciarTimer();

    }

    private void IniciarComp(){
        Paneles();
    }

    private void Paneles(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints cuadricula  = new GridBagConstraints();
        cuadricula.insets = new Insets(10, 10, 10, 10);
        cuadricula.fill = GridBagConstraints.HORIZONTAL;
        cuadricula.gridx = 0;
        cuadricula.gridy = 0;
        cuadricula.gridwidth = 2;

        tiempoLabel = new JLabel("00:00:00");
        tiempoLabel.setFont(new Font("Serif", Font.BOLD, 30));
        tiempoLabel.setForeground(Color.BLACK);
        panel.add(tiempoLabel, cuadricula);

        cuadricula.gridy = 1;
        cuadricula.gridwidth = 1;

        panel.add(new JLabel("Tiempo para primera alarma (s):"), cuadricula);
        cuadricula.gridx = 1;
        alarmaTextField = new JTextField(10);
        panel.add(alarmaTextField, cuadricula);
        this.getContentPane().add(panel);
    }
      
    private void iniciarTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                segundos++;
                if(alarmaActiva && segundos == alarmaSegundos){
                    configuralarma();
                }
            }
        });

        timer.start();
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


}
    

