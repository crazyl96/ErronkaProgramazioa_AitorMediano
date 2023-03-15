import javax.swing.*;
import javax.swing.JPanel;
import java.awt.*;

public class windowsExample extends JFrame {
    private JPanel panel1;
    private JPanel panel2;
    private JButton bnt1,bnt2,bnt3,bnt4,bnt5,bnt6,bnt7,bnt8,bnt9;

    public windowsExample(){
        this.setLayout(new BorderLayout());
        this.panel1 = new JPanel();
        this.panel2 = new JPanel();

        this.panel2.setLayout(new GridLayout(3,3,10,20));
        this.bnt1 = new JButton("Botoi 1");
        this.bnt2 = new JButton("Botoi 2");
        this.bnt3 = new JButton("Botoi 3");
        this.bnt4 = new JButton("Botoi 4");
        this.bnt5 = new JButton("Botoi 5");
        this.bnt6 = new JButton("Botoi 6");
        this.bnt7 = new JButton("Botoi 7");
        this.bnt8 = new JButton("Botoi 8");
        this.bnt9 = new JButton("Botoi 9");

        this.add(panel1,BorderLayout.NORTH);
        this.add(panel2, BorderLayout.SOUTH);

        panel1.add(bnt1);
        panel1.add(bnt2);
        panel2.add(bnt3);
        panel2.add(bnt4);
        panel2.add(bnt5);
        panel2.add(bnt6);
        panel2.add(bnt7);
        panel2.add(bnt8);
        panel2.add(bnt9);


    }

}
