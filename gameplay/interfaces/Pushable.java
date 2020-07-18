package gameplay.interfaces;

import gameplay.classes.MovingEntity;

import java.util.List;


public interface Pushable extends Collidable {
    
    default void calculatePush(MovingEntity self, List<MovingEntity> entities) {

        for(MovingEntity me : entities) {
            calculatePush(self, me);
        }
    }

    default void calculatePush(MovingEntity self, MovingEntity me) {

        if(collideEntity(self, me)){

            int centerX = self.posX+self.sizeX/2;
            int centerY = self.posY+self.sizeY/2;

            if(me.posX < centerX && centerX < me.posX+me.sizeX) {
                if(centerY < me.posY+me.sizeY/2) self.speedY += -me.pushY;
                else self.speedY += me.pushY;
            }
            if(me.posY < centerY && centerY < me.posY+me.sizeY) {
                if(centerX < me.posX+me.sizeX/2) self.speedX += -me.pushX;
                else self.speedX += me.pushX;
            }
        }
    }
}