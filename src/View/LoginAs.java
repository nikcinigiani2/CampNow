package View;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginAs extends JFrame {

    //TODO finire...
    public LoginAs(){
        setupWindow();
        JPanel mainPanel = createMainPanel();
        add(mainPanel);
        setVisible(true);
    }
    private void setupWindow() {
        setTitle("Login As");
        setSize(500, 400);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    private JPanel createMainPanel(){
        JPanel mainPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("CAMPNOW", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        add(titleLabel, BorderLayout.NORTH);

        JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
        separator.setPreferredSize(new Dimension(10, 0));
        add(separator, BorderLayout.CENTER);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(1, 2)); // 2 colonne, 1 riga
        add(centerPanel, BorderLayout.CENTER);
        centerPanel.setBorder(new LineBorder(Color.BLACK, 2));

        JPanel clubPanel = createClubPanel();

        centerPanel.add(clubPanel);

        JPanel userPanel = createUserPanel();

        centerPanel.add(userPanel);

        mainPanel.add(centerPanel);

        return mainPanel;
    }

    private JPanel createClubPanel() {

        JPanel clubPanel = new JPanel();
        clubPanel.setLayout(new BoxLayout(clubPanel, BoxLayout.Y_AXIS));
        JLabel clubLabel = new JLabel("CLUB", JLabel.CENTER);
        JButton clubLoginButton = new JButton("Login");
        JButton clubRegisterButton = new JButton("Register");

        clubPanel.setBorder(new LineBorder(Color.BLACK, 2));
        clubPanel.add(clubLabel, BorderLayout.CENTER);
        clubPanel.add(clubLoginButton, BorderLayout.CENTER);
        clubPanel.add(clubRegisterButton, BorderLayout.CENTER);

        return clubPanel;
    }

    private JPanel createUserPanel() {
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));
        JLabel userLabel = new JLabel("USER", JLabel.CENTER);
        JButton userLoginButton = new JButton("Login");
        JButton userRegisterButton = new JButton("Register");

        userPanel.setBorder(new LineBorder(Color.BLACK, 2));
        userPanel.add(userLabel, BorderLayout.CENTER);
        userPanel.add(userLoginButton, BorderLayout.CENTER);
        userPanel.add(userRegisterButton, BorderLayout.CENTER);

        return userPanel;
    }
}