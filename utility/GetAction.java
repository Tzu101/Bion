package utility;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/* Detects action events */
public class GetAction implements ActionListener {

    private Window window;

    public GetAction(Window window) {
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        window.mainLoop();
    }
}
