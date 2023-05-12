import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

public class PictureViewer extends JFrame {
    private JPanel pFotografos,pPhotoAfter, pAward, pRemove;
    private JLabel lFotografos, lFecha, lArgazkiak;

    private JComboBox fotografos;
    private JXDatePicker fecha;
    private JList<Picture> lista;
    private JButton bAward, bRemove;

    private DBkonexion conexion;

    public PictureViewer(){
        setTitle("Photography");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000,750));
        setLayout(new GridLayout(3,2));

        //labels
        this.lFotografos = new JLabel("Photographer: ");
        this.lFecha = new JLabel("Photos after: ");
        this.lArgazkiak = new JLabel();

        //BotonAward
        bAward = new JButton("AWARD");

        //BotonRemove
        bRemove = new JButton("REMOVE");

        //combobox
        this.fotografos = new JComboBox<>(new DefaultComboBoxModel<>(DBkonexion.cargarFotografos().toArray(new Photographer[]{})));

        //JList
        this.lista = new JList<>();

        //createVisitMap

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
        //botoia Award clikatzerakoan  eskatuko diogu nahi ditugun bisitak eta deituko dio awardUpdateri
        bAward.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int bisitakSartu = Integer.parseInt(JOptionPane.showInputDialog(null, "Zenbat bisitak nahi dituzu sartu?"));

                conexion = new DBkonexion();
                conexion.awardUpdate(bisitakSartu);

            }
        });
        //botoa remove clikatzerakoann deituko dio ezabatu metdodoari DBkonexioan
        bRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conexion = new DBkonexion();
                conexion.ezabatu();
            }
        });

        //fecha
        this.fecha = new JXDatePicker();

        //window
        this.pAward = new JPanel();
        pAward.add(bAward);

        this.pRemove = new JPanel();
        pRemove.add(bRemove);

        this.pFotografos = new JPanel();
        pFotografos.add(lFotografos);
        pFotografos.add(fotografos);

        this.pPhotoAfter = new JPanel();
        pPhotoAfter.add(lFecha);
        pPhotoAfter.add(fecha);

        this.add(pAward);
        this.add(pRemove);
        this.add(pFotografos);
        this.add(pPhotoAfter);
        this.add(this.lista);
        this.add(this.lArgazkiak);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    //emen metodoa erabikltzen dugu argazkiak kargatzeko
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
