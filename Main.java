import utility_classes.Window;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;


public class Main {

    public static void main(String[] args) {

        JFrame window = new JFrame("Title");

        int height = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
        int width = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;

        width = 500;
        height = 500;

        window.pack();
        window.setResizable(false);
        window.setSize(new Dimension(width , height));
            
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);

        window.add(new Window());
        window.setVisible(true);

        /*System.out.println(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height);
        System.out.println(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width);*/
        // GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(window);
    }
}
