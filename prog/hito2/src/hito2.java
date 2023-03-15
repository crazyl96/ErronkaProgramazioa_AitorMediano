import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class hito2 extends JFrame implements ActionListener {
    private JComboBox<String> cb;
    private JTextArea textArea;
    private JPanel panelE;
    private JPanel panelW;
    private JScrollPane sp;
    private JPanel psc;
    public hito2(){
        super("Autatu artxiboa");

        this.setSize(800,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //PANEL W
        panelW = new JPanel();

        //creando los comboBox

        String[] artxiboak = {"python.txt","c.txt","java.txt"};

        this.cb = new JComboBox<>(artxiboak);
        cb.addActionListener(this);
        cb.setPreferredSize(new Dimension(400,30));
        //add(cb, BorderLayout.NORTH);
        panelW.add(cb);

        //crear boton de borrar
        JButton botonBorrar = new JButton("Borrar");
        botonBorrar.setPreferredSize(new Dimension(80,30));
        botonBorrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
            }
        });
        //add(botonBorrar, BorderLayout.WEST);
        panelW.add(botonBorrar);


        //PANEL E
        panelE = new JPanel();

        //text area
        textArea = new JTextArea(30,50);

        textArea.setEditable(true);
        //add(new JScrollPane(textArea), BorderLayout.CENTER);
        sp = new JScrollPane(textArea);



        //crear boton de cerrar
        JButton botonCerrar = new JButton("Cerrar");

        botonCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        //aqui hago un nuevo panel para crear un nuevo layout para juntal el textbox con el boton cerrar
        //para q no interfiera con el window.
        psc = new JPanel();
        psc.setLayout(new BoxLayout(psc, BoxLayout.Y_AXIS));
        psc.add(sp);
        psc.add(botonCerrar);

        panelE.add(psc,BorderLayout.CENTER);


        this.add(panelE, BorderLayout.EAST);
        this.add(panelW, BorderLayout.WEST);
        //display
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Obtener el nombre del archivo seleccionado en el JComboBox
        String nombreArchivo = (String) cb.getSelectedItem();

        // Intentar abrir el archivo y leer su contenido
        try {
            BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
            String linea;
            StringBuilder contenido = new StringBuilder();
            while ((linea = br.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
            br.close();

            // Mostrar el contenido del archivo en el JTextArea
            textArea.setText(contenido.toString());
        } catch (FileNotFoundException ex) {
            // Mostrar un mensaje de error si el archivo no existe.
            JOptionPane.showMessageDialog(this, "El archivo no existe", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
