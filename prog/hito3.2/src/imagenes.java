import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class imagenes implements ActionListener {
    private  JComboBox<String> comboBox;
    private JLabel labelFoto;

    public imagenes(JComboBox cb, JLabel imagensLabel) {
        this.comboBox = cb;
        this.labelFoto = imagensLabel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //obtiene el nommbre de la imagen
        String nombreMeme = (String) comboBox.getSelectedItem();
        System.out.println(nombreMeme);
        //cargar la imagen
        ImageIcon imageIcon = new ImageIcon("./argazkiak/" + nombreMeme);
        Image foto = imageIcon.getImage();
        Image newMeme = foto.getScaledInstance(200,200,Image.SCALE_DEFAULT);
        ImageIcon newMemeIcon = new ImageIcon(newMeme);

        // meter la imagen en la label
        labelFoto.setIcon(newMemeIcon);

    }

}
