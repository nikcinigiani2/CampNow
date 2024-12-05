import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // Creazione del frame principale
        JFrame frame = new JFrame("Login Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLayout(new BorderLayout());

        // Creazione del pannello centrale
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField(15);
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField(15);
        JButton loginButton = new JButton("Login");
        JButton exitButton = new JButton("Exit");

        // Posizionamento dei componenti nel pannello
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(userLabel, gbc);

        gbc.gridx = 1;
        panel.add(userField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passLabel, gbc);

        gbc.gridx = 1;
        panel.add(passField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, gbc);

        gbc.gridy = 3;
        panel.add(exitButton, gbc);

        // Aggiunta del pannello al frame
        frame.add(panel, BorderLayout.CENTER);

        // Azione per il pulsante "Exit"
        exitButton.addActionListener(e -> System.exit(0));

        // Rendi visibile il frame
        frame.setLocationRelativeTo(null); // Centra la finestra sullo schermo
        frame.setVisible(true);




    }
}