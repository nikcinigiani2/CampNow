package View;

import javax.swing.*;
import java.awt.*;

public class HomeUser extends StandardView{

    public HomeUser(){
        setupWindow();
        JPanel mainPanel = createMainPanel();
        add(mainPanel);
        setVisible(true);
    }

    @Override
    protected void setupWindow() {
        setTitle("CampNow");
        setSize(900, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    protected JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Titolo in alto
        JLabel titleLabel = new JLabel("Home", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        return mainPanel;
    }

    //TODO andare avanti...

}
