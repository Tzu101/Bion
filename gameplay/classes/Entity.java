package gameplay.classes;

import java.awt.Color;
import java.awt.Graphics;


public class Entity {
    
    private Color color;

    public int posX, posY;
    public int sizeX, sizeY;

    public Entity(Color color, int posX, int posY, int sizeX, int sizeY) {
        this.color = color;
        this.posX = posX;
        this.posY = posY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public void display(Graphics g) {
        g.setColor(color);
        g.fillRect(posX, posY, sizeX, sizeY);
        g.setColor(Color.GRAY);
        g.drawRect(posX, posY, sizeX, sizeY);
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        return false;
    }
}