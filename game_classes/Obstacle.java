package game_classes;

import java.awt.Color;
import java.awt.Graphics;


public class Obstacle {
    
    private Color color;
    
    public int posX, posY;
    public int sizeX, sizeY;

    public Obstacle(Color color, int posX, int posY, int sizeX, int sizeY) {
        this.color = color;
        this.posX = posX;
        this.posY = posY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public void display(Graphics g) {
        g.setColor(color);
        g.fillRect(posX, posY, sizeX, sizeY);
    }
}