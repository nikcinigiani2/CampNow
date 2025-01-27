package View;

import javax.swing.*;

public abstract class StandardView extends JFrame{

    protected abstract void setupWindow();

    protected abstract JPanel createMainPanel();

    protected JToggleButton createButton(String title, ButtonGroup buttonGroup, Runnable action) {
        JToggleButton button = new JToggleButton(title);
        buttonGroup.add(button);
        if (action != null) {
            button.addActionListener(e -> action.run());
        }
        return button;
    }
}
