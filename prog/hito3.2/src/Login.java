import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {
    public static void main(String[] args) {
        //Pasahitza
        String inputPassw = JOptionPane.showInputDialog("Jarri pasahitza");
        if (inputPassw == null || !inputPassw.equals("damocles")) {
            JOptionPane.showMessageDialog(null, "Pasahitza gaizki jarri duzu.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }else{
            new hito4();
        }

    }
}
