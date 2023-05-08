import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.util.List;

public class PictureViewer extends JFrame {
    private JPanel pFotografos,pPhotoAfter;
    private JLabel lFotografos, lFecha, lArgazkiak;

    private JComboBox fotografos;
    private JXDatePicker fecha;
    private JList<Picture> lista;

    public PictureViewer(){
        setTitle("Photography");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000,750));
        setLayout(new GridLayout(2,2));
        //labels
        this.lFotografos = new JLabel("Photographer: ");
        this.lFecha = new JLabel("Photos after: ");
        this.lArgazkiak = new JLabel();


        //combobox
        this.fotografos = new JComboBox<>(new DefaultComboBoxModel<>(DBkonexion.cargarFotografos().toArray(new Photographer[]{})));

        //JList
        this.lista = new JList<>();

        //fotografoak aukeratzekoan argazkiak kargatzea
        fotografos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarImagenes();
            }
        });

        lista.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                    Picture imagenSeleccion = lista.getSelectedValue();
                    ImageIcon imageIcon = new ImageIcon(imagenSeleccion.getFitxategia());
                    Image image = imageIcon.getImage().getScaledInstance(250,250, Image.SCALE_SMOOTH);
                    ImageIcon scaladoImageIcon = new ImageIcon(image);
                    lArgazkiak.setIcon(scaladoImageIcon);
                    imagenSeleccion.incrementVisitas();
                }
            }
        });

        //fecha
        this.fecha = new JXDatePicker();

        //window
        this.pFotografos = new JPanel();
        pFotografos.add(lFotografos);
        pFotografos.add(fotografos);

        this.pPhotoAfter = new JPanel();
        pPhotoAfter.add(lFecha);
        pPhotoAfter.add(fecha);

        this.add(pFotografos);
        this.add(pPhotoAfter);
        this.add(this.lista);
        this.add(this.lArgazkiak);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void cargarImagenes(){
        Photographer fotografo = (Photographer) fotografos.getSelectedItem();
        Date date = fecha.getDate();
        List<Picture> argazkiak = DBkonexion.cargarImagen(fotografo.getId(), date);
        DefaultListModel<Picture> model = new DefaultListModel<>();
        for (Picture argazkia : argazkiak){
            model.addElement(argazkia);
        }
        lista.setModel(model);
    }
    public static void main(String[] args) {
        new PictureViewer();

    }
}
