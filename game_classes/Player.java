package game_classes;

import java.util.List;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Player extends Entity implements KeyListener {

    private float speedX=0, speedY=0;
    private float speedAcc, speedMax;

    public boolean left=false, right=false, up=false, down=false;

    public Player(Color color, int posX, int posY, int sizeX, int sizeY, int healthMax, List<Entity> group, float speedAcc, float speedMax) {
        super(color, posX, posY, sizeX, sizeY, healthMax, group);
        this.speedAcc = speedAcc;
        this.speedMax = speedMax;    }
    
    public void update(List<Obstacle> obstacles) {

        if(left && !(speedX < -speedMax)) {
            if(speedX > -speedMax + speedAcc) speedX += -speedAcc;
            else speedX = -speedMax;
        }
        else if(speedX < 0){
            if(speedX < -speedAcc) speedX += speedAcc;
            else speedX = 0;
        }

        if(right && !(speedX > speedMax)) {
            if(speedX < speedMax - speedAcc) speedX += speedAcc;
            else speedX = speedMax;
        }
        else if(speedX > 0){
            if(speedX > speedAcc) speedX += -speedAcc;
            else speedX = 0;
        }

        if(up && !(speedY < -speedMax)) {
            if(speedY > -speedMax + speedAcc) speedY += -speedAcc;
            else speedY = -speedMax;
        }
        else if(speedY < 0){
            if(speedY < -speedAcc) speedY += speedAcc;
            else speedY = 0;
        }

        if(down && !(speedY > speedMax)) {
            if(speedY < speedMax - speedAcc) speedY += speedAcc;
            else speedY = speedMax;
        }
        else if(speedY > 0){
            if(speedY > speedAcc) speedY += -speedAcc;
            else speedY = 0;
        }

        int moveX = (int)speedX;
        int moveY = (int)speedY;

        if(speedX < 0) {
            Integer leftCollideDist = collideLeft(obstacles);
            if(leftCollideDist != null && speedX < -leftCollideDist) moveX = -leftCollideDist;
        }
        else if(speedX > 0) {
            Integer rightCollideDist = collideRight(obstacles);
            if(rightCollideDist != null && speedX > rightCollideDist) moveX = rightCollideDist;
        }

        if(speedY < 0) {
            Integer upleftCollideDist = collideUp(obstacles);
            if(upleftCollideDist != null && speedY < -upleftCollideDist) moveY = -upleftCollideDist;
        }
        else if(speedY > 0) {
            Integer downCollideDist = collideDown(obstacles);
            if(downCollideDist != null && speedY > downCollideDist) moveY = downCollideDist;
        }

        super.move(moveX, moveY);
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
        
        if(e.getKeyCode() == KeyEvent.VK_A) left = false;
        else if(e.getKeyCode() == KeyEvent.VK_D) right = false;
        else if(e.getKeyCode() == KeyEvent.VK_W) up = false;
        else if(e.getKeyCode() == KeyEvent.VK_S) down = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {}  // Not needed
}