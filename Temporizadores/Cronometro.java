
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Cronometro extends JFrame {

    private JPanel panel;

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
       panel = new JPanel();

       panel.setBackground(Color.DARK_GRAY);

       panel.setLayout(null);
       this.getContentPane().add(panel);
    }

}
