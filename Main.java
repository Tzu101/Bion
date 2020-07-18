import utility.Window;

import java.awt.Toolkit;
import java.awt.Dimension;

import javax.swing.JFrame;


public class Main {

    public static void main(String[] args) {

        JFrame window = new JFrame("Title");

        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = (int) ((double)width / 16.0 * 9.0);

        width = 32 * 32;
        height = 24 * 32;

        window.getContentPane().setPreferredSize(new Dimension(width, height));
        window.setResizable(false);
        window.pack();
        
            
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);

        window.add(new Window(width, height));
        window.setVisible(true);

        /*System.out.println(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height);
        System.out.println(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width);*/
        // GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(window);
    }
}
