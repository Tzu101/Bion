package gameplay.classes;

import java.awt.Color;


public class PatternEntity extends MovingEntity {

    private int[][] pattern;
    private int step=0;

    public PatternEntity(Color color, int posX, int posY, int sizeX, int sizeY, float speedAcc, float speedMax, float push, int[][] pattern) {
        super(color, posX, posY, sizeX, sizeY, speedAcc, speedAcc, speedMax, speedMax, push, push);
        this.pattern = pattern;
    }

    public void update() {

        patternMovement();
        claculateMovement();
        newPosition();
    }

    public void patternMovement() {

        int centerX = posX + sizeX/2;
        int centerY = posY + sizeY/2;

        int targetX = pattern[step][0];
        int targetY = pattern[step][1];

        if(centerX > targetX) left = true;
        else left = false;
            
        if(centerX < targetX) right = true;
        else right = false;

        if(centerY > targetY) up = true;
        else up = false;

        if(centerY < targetY) down = true;
        else down = false;

        if(targetX == centerX && targetY == centerY) {
            if(step < pattern.length-1) step++;
            else step = 0;
        }
    }
    @Override
    public void claculateMovement() {

        super.claculateMovement();

        int targetDistX = pattern[step][0] - (posX + sizeX/2);
        int targetDistY = pattern[step][1] - (posY + sizeY/2);

        if(speedX < 0 && targetDistX < 0 && speedX < targetDistX) speedX = targetDistX;
        else if(speedX > 0 && targetDistX > 0 && speedX > targetDistX) speedX = targetDistX;

        if(speedY < 0 && targetDistY < 0 && speedY < targetDistY) speedY = targetDistY;
        else if(speedY > 0 && targetDistY > 0 && speedY > targetDistY) speedY = targetDistY;
    }
}