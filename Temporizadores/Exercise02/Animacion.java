package Exercise02;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Animacion extends JFrame {

    private JPanel panel;
    private JLabel animationJLabel;
    private String[] isImages = {
    "Image/man1.jpg",
    "Image/man2.jpg",
    "Image/man3.jpg",
    "Image/man4.jpg",
    "Image/man5.jpg",
    "Image/man6.jpg",
    "Image/man7.jpg",
    "Image/man8.jpg"};
    private Timer timer;

    public Animacion(){
        setBounds(500, 100, 400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Animacion con imagenes.");

        paneles();
        isAnimation();
    }

    private void paneles(){
        panel = new JPanel();
        panel.setLayout(null);
        
        this.getContentPane().add(panel);
    }

    private void isAnimation(){
        animationJLabel = new JLabel();
        animationJLabel.setBounds(138, 20, 108, 210);
        ImageIcon[] conteinImage = new ImageIcon[isImages.length];
        timer = new Timer(100, new ActionListener() {
            int chanceImage = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                if(chanceImage < isImages.length){
                    conteinImage[chanceImage] = new ImageIcon(isImages[chanceImage]);
                    Image imagen  = conteinImage[chanceImage].getImage().getScaledInstance(108, 210, Image.SCALE_SMOOTH);
                    animationJLabel.setIcon(new ImageIcon(imagen));
                    chanceImage++;
                }else{
                    chanceImage = 0;
                }
            }
            
        });
        panel.add(animationJLabel);
        timer.start();
    }
    
}
