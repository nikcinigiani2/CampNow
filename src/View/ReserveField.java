package View;

import javax.swing.*;
import java.awt.*;

public class ReserveField extends StandardView{

    private int fieldId;


    public ReserveField(int fieldId){
        this.fieldId = fieldId;
        setupWindow();
        JPanel mainPanel = createMainPanel();
        add(mainPanel);
        setVisible(true);
    }

    @Override
    protected JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());



        return mainPanel;
    }

}
