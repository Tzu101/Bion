package gameplay.classes;

import java.awt.Color;


public class MovingEntity extends Entity {

    private float speedAccX, speedAccY;
    private float speedMaxX, speedMaxY;

    public float pushX, pushY;
    public float speedX=0, speedY=0;
    public boolean left=false, right=false, up=false, down=false;

    public MovingEntity(Color color, int posX, int posY, int sizeX, int sizeY, float speedAccX, float speedAccY, float speedMaxX, float speedMaxY, float pushX, float pushY) {
        super(color, posX, posY, sizeX, sizeY);
        this.speedAccX = speedAccX;
        this.speedAccY = speedAccY;
        this.speedMaxX = speedMaxX;
        this.speedMaxY = speedMaxY;
        this.pushX = pushX;
        this.pushY = pushY;
    }

    public void newPosition() {
        posX += speedX;
        posY += speedY;
    }

    public void claculateMovement() {
        if(left && !(speedX < -speedMaxX)) {
            if(speedX > -speedMaxX + speedAccX) speedX += -speedAccX;
            else speedX = -speedMaxX;
        }
        else if(speedX < 0){
            if(speedX < -speedAccX) speedX += speedAccX;
            else speedX = 0;
        }

        if(right && !(speedX > speedMaxX)) {
            if(speedX < speedMaxX - speedAccX) speedX += speedAccX;
            else speedX = speedMaxX;
        }
        else if(speedX > 0){
            if(speedX > speedAccX) speedX += -speedAccX;
            else speedX = 0;
        }

        if(up && !(speedY < -speedMaxY)) {
            if(speedY > -speedMaxY + speedAccY) speedY += -speedAccY;
            else speedY = -speedMaxY;
        }
        else if(speedY < 0){
            if(speedY < -speedAccY) speedY += speedAccY;
            else speedY = 0;
        }

        if(down && !(speedY > speedMaxY)) {
            if(speedY < speedMaxY - speedAccY) speedY += speedAccY;
            else speedY = speedMaxY;
        }
        else if(speedY > 0){
            if(speedY > speedAccY) speedY += -speedAccY;
            else speedY = 0;
        }
    }
}