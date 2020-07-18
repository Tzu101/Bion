package utility;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/* Informs the main program when a mouse button is pressed */
public class GetMousePress implements MouseListener {

    private Window window;

    public GetMousePress(Window window) {
        this.window = window;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
        if(e.getButton() == MouseEvent.BUTTON1) window.makeObstacle(e.getX() , e.getY());  // Left click
        if(e.getButton() == MouseEvent.BUTTON3) window.makeEnemy(e.getX() , e.getY());  // Right click
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Not needed
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Not needed
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Not needed
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Not needed
    }
}