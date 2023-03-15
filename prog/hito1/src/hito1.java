import javax.swing.*;
import javax.swing.JPanel;
import java.awt.*;

public class hito1 extends JFrame {
    private JPanel panelN;
    private JPanel panelS;
    private JPanel panelE;

    private JPanel panelC;
    private JLabel label;
    private JRadioButton array[];
    private JButton but1,but2;
    private JCheckBox check1,check2;
    private JRadioButton rb1,rb2,rb3;


    public hito1(){
        this.setLayout(new BorderLayout());

        //panel norte
        this.panelN = new JPanel();
        this.check1 = new JCheckBox("check 1");
        this.check2 = new JCheckBox("check 2");
        panelN.add(check1);
        panelN.add(check2);


        //panel este
        this.array  = new JRadioButton[3];
        this.panelE = new JPanel();
        panelE.setPreferredSize(new Dimension(250,0));
        panelE.setLayout(new BoxLayout(panelE, BoxLayout.Y_AXIS));
        panelE.add(Box.createVerticalGlue());
        panelE.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.array[0] = new JRadioButton("Opcion 1", true);
        this.array[1] = new JRadioButton("Opcion 2");
        this.array[2] = new JRadioButton("Opcion 3");
        ButtonGroup rbGroup = new ButtonGroup();
        for (int i = 0; i < array.length; i++) {
            rbGroup.add(array[i]);
        }
        panelE.add(array[0]);
        panelE.add(array[1]);
        panelE.add(array[2]);
        panelE.add(Box.createVerticalGlue());

        //panel sur
        this.panelS = new JPanel();
        this.panelS.setPreferredSize(new Dimension(0,50));
        panelS.setLayout(new BoxLayout(panelS, BoxLayout.LINE_AXIS));
        this.but1 = new JButton("boton 1");
        this.but2 = new JButton("boton 2");
        panelS.add(but1);
        panelS.add(but2);

        //panel central
        this.panelC = new JPanel(new GridLayout(2,2));
        ImageIcon image = new ImageIcon("descarga.jpg");
        JLabel label1 = new JLabel(image);
        JLabel label2 = new JLabel(image);
        JLabel label3 = new JLabel(image);
        JLabel label4 = new JLabel(image);
        panelC.add(label1);
        panelC.add(label2);
        panelC.add(label3);
        panelC.add(label4);

        //agregar componentes al contenedoor principal con BorderLayout
        this.add(panelN, BorderLayout.NORTH);
        this.add(panelE, BorderLayout.EAST);
        this.add(panelS, BorderLayout.SOUTH);
        this.add(panelC, BorderLayout.CENTER);

        //display
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
