
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Cronometro extends JFrame {

    private JPanel panel;
    private JLabel tiempoLabel;

    public Cronometro(){

        setBounds(500, 100, 400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Calculadora.");

        IniciarComp();

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

        this.getContentPane().add(panel);
    }

}
