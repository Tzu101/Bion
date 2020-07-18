package gameplay.classes;

import gameplay.interfaces.*;

import java.util.List;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Player extends MovingEntity implements KeyListener, Pushable {

    public Player(Color color, int posX, int posY, int sizeX, int sizeY, float speedAcc, float speedMax, float push) {
        super(color, posX, posY, sizeX, sizeY, speedAcc, speedAcc, speedMax, speedMax, push, push);
    }
    
    public void update(List<Entity> entities, List<MovingEntity> movingEntities, PatternEntity p) {

        claculateMovement();
        calculatePush(this, movingEntities);
        calculatePush(this, p);
        calculateCollide(this, entities);
        newPosition();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        if(e.getKeyCode() == KeyEvent.VK_A) left = true;
        else if(e.getKeyCode() == KeyEvent.VK_D) right = true;
        else if(e.getKeyCode() == KeyEvent.VK_W) up = true;
        else if(e.getKeyCode() == KeyEvent.VK_S) down = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        if(e.getKeyCode() == KeyEvent.VK_A) super.left = false;
        else if(e.getKeyCode() == KeyEvent.VK_D) right = false;
        else if(e.getKeyCode() == KeyEvent.VK_W) up = false;
        else if(e.getKeyCode() == KeyEvent.VK_S) down = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {}  // Not needed
}