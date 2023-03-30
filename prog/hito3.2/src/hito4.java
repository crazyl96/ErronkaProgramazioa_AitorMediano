import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class hito4 extends JFrame implements ActionListener {
    private static final String kArgazkiak = "argazkiak";
    private JPanel panelE, panelW,panelS;
    private JLabel imagensLabel;
    private JButton b1;
    private JComboBox<String> cb;
    private JCheckBox checkBox;
    private ImageIcon ic;
    private JTextArea textArea;

    public hito4(){
        setTitle("4ª mugarria");
        this.setSize(800,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //Panles
        panelW = new JPanel();//izquieda
        panelE = new JPanel();//derecha
        panelS = new JPanel();


        //Panel W
        //fotos
        this.ic = new ImageIcon("./argazkiak/meme2.png");
        Image foto = this.ic.getImage();
        Image newMeme = foto.getScaledInstance(200,200,Image.SCALE_SMOOTH);
        ImageIcon newMemeIcon = new ImageIcon(newMeme);
        this.imagensLabel = new JLabel(newMemeIcon);
        //combobox
        this.cb = new JComboBox<String>();
        load_combo();
        this.cb.addActionListener(new imagenes(this.cb,this.imagensLabel));

        //checkbox
        this.checkBox = new JCheckBox("save your comment",true);
        //meter las cosas en el panelW
        panelW.add(cb);
        panelW.add(imagensLabel);
        panelW.add(checkBox);

        //PanelE
        //textos
        this.textArea = new JTextArea();
        this.textArea.setPreferredSize(new Dimension(300,30));
        //meter las cosas en el panelE
        panelE.add(textArea, BorderLayout.SOUTH);
        //panelS
        //boton
        this.b1 = new JButton("SAVE");
        this.b1.addActionListener(new ButtonSave());
        //meter las cosas en el panelS
        panelS.add(b1);
        //meter los paneles
        this.add(panelW, BorderLayout.WEST);
        this.add(panelE, BorderLayout.EAST);
        this.add(panelS, BorderLayout.SOUTH);
        //display
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void load_combo() {
        File fitxategia = new File("./"+ this.kArgazkiak);
        File[] karpetak = fitxategia.listFiles();
        for(File karpeta : karpetak){
            if(karpeta.isFile()){
                cb.addItem(karpeta.getName());
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public class ButtonSave implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedImage = (String) cb.getSelectedItem();
            String comment = textArea.getText();
            OutputStream outputStream = null;
            if(checkBox.isSelected()){
                String fileName = selectedImage.substring(0,selectedImage.lastIndexOf("."))+ ".txt";
                try{
                    outputStream = new FileOutputStream(fileName,true);
                    outputStream.write(comment.getBytes());
                    outputStream.write("\n".getBytes());
                }catch (FileNotFoundException exception){
                    System.out.println("ez da aurkitu fitxeroa");
                }catch (IOException exception){
                    System.out.println("...");
                }finally {
                    try {
                        outputStream.close();
                    }catch (IOException exception){
                        System.out.println("uwu");
                    }
                }
            }
        }
    }

}
