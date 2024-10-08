package Exercise02;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Animacion extends JFrame {

    private BackgroundPanel backgroundPanel;
    private JLabel animationJLabel;
    private JSlider slider;
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
    private ImageIcon conteinImage;
    private int chanceImage = 0;

    public Animacion(){
        setBounds(500, 100, 400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Animacion con control de velocidad.");

        paneles();
        sliderCreation();
        isAnimation(); 
    }

    private void paneles(){
        backgroundPanel = new BackgroundPanel("Image/Fondo.png");
        backgroundPanel.setLayout(null);
        this.getContentPane().add(backgroundPanel);
    }

    private void sliderCreation(){
        slider = new JSlider(JSlider.HORIZONTAL, 10, 1000, 500);
        slider.setInverted(true);
        slider.setBounds(80, 250, 230, 50);
        slider.setMajorTickSpacing(160);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        backgroundPanel.add(slider);
    }

    private void isAnimation(){
        animationJLabel = new JLabel();
        animationJLabel.setBounds(138, 20, 108, 210);
        timer = new Timer(slider.getValue(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateImage();
            }
            
        });
        
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                timer.setDelay(slider.getValue());
            }
        });


        backgroundPanel.add(animationJLabel);
        timer.start();
    }
    
    private void updateImage(){
        conteinImage = new ImageIcon(isImages[chanceImage]);
        Image imagen  = conteinImage.getImage().getScaledInstance(108, 210, Image.SCALE_SMOOTH);
        animationJLabel.setIcon(new ImageIcon(imagen));
        chanceImage++;

        if(chanceImage >= isImages.length){
            chanceImage = 0;
        }
    }
}