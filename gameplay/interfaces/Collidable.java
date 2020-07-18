package gameplay.interfaces;

import gameplay.classes.Entity;
import gameplay.classes.MovingEntity;

import java.util.List;


public interface Collidable {
    
    default void calculateCollide(MovingEntity self, List<Entity> entities) {

        int mX1=0, mX2=0, mY1=0, mY2=0;

        if(self.speedX < 0) {
            Integer leftCollideDist = collideDistLeft(self, entities);
            if(leftCollideDist != null) {
                if(self.speedX < -leftCollideDist) self.speedX = -leftCollideDist;
                if(leftCollideDist == 0) self.speedX = 0;
            }
            mX1 = 0;
            mX2 = 1;
        }
        else if(self.speedX > 0) {
            Integer rightCollideDist = collideDistRight(self, entities);
            if(rightCollideDist != null) {
                if(self.speedX > rightCollideDist) self.speedX = rightCollideDist;
                if(rightCollideDist == 0) self.speedX = 0;
            }
            mX1 = 1;
            mX2 = 0;
        }

        if(self.speedY < 0) {
            Integer upleftCollideDist = collideDistUp(self, entities);
            if(upleftCollideDist != null) {
                if(self.speedY < -upleftCollideDist) self.speedY = -upleftCollideDist;
                if(upleftCollideDist == 0) self.speedY = 0;
            }
            mY1 = 0;
            mY2 = 1;
        }
        else if(self.speedY > 0) {
            Integer downCollideDist = collideDistDown(self, entities);
            if(downCollideDist != null) {
                if(self.speedY > downCollideDist) self.speedY = downCollideDist;
                if(downCollideDist == 0) self.speedY = 0;
            }
            mY1 = 1;
            mY2 = 0;
        }

        if(!(self.speedX == 0 || self.speedY == 0)) {
            Integer diagonalCollideDist = collideDistDiagonal(self, entities, mX1, mX2, mY1, mY2);
            if(diagonalCollideDist != null) {
                if(Math.abs(self.speedX) > diagonalCollideDist) self.speedX = Math.abs(self.speedX) / self.speedX * diagonalCollideDist;
                if(Math.abs(self.speedY) > diagonalCollideDist) self.speedY = Math.abs(self.speedY) / self.speedY * diagonalCollideDist;
            }
        }
    }

    default Integer collideDistDiagonal(MovingEntity self, List<Entity> entities, int mX1, int mX2, int mY1, int mY2) {

        Integer distToCollide = null;

        for(Entity e : entities) {
            int distX = Math.abs((self.posX + self.sizeX*mX1) - (e.posX + e.sizeX*mX2));
            int distY = Math.abs((self.posY + self.sizeY*mY1) - (e.posY + e.sizeY*mY2));
            if(distX == distY) {
                if(distToCollide == null || distX < distToCollide) distToCollide = distY;
            }
        }
        return distToCollide;
    }

    default Integer collideDistLeft(MovingEntity self, List<Entity> entities) {

        Integer distToCollide = null;

        for(Entity e : entities) {
            int dist = self.posX - e.posX - e.sizeX;
            if(dist >= 0) {
                if(collideInRange(self.posY, self.sizeY, e.posY, e.sizeY)) {
                    if(distToCollide == null || dist < distToCollide) distToCollide = dist;
                }
            }
        }
        return distToCollide;
    }

    default Integer collideDistRight(MovingEntity self, List<Entity> entities) {
        
        Integer distToCollide = null;

        for(Entity e : entities) {
            int dist = e.posX - self.posX - self.sizeX;
            if(dist >= 0) {
                if(collideInRange(self.posY, self.sizeY, e.posY, e.sizeY)) 
                    if(distToCollide == null || dist < distToCollide) distToCollide = dist;
            }
        }
        return distToCollide;
    }

    default Integer collideDistUp(MovingEntity self, List<Entity> entities) {
        
        Integer distToCollide = null;

        for(Entity e : entities) {
            int dist = self.posY - e.posY - e.sizeY;
            if(dist >= 0) {
                if(collideInRange(self.posX, self.sizeX, e.posX, e.sizeX)) 
                    if(distToCollide == null || dist < distToCollide) distToCollide = dist;
            }
        }
        return distToCollide;
    }

    default Integer collideDistDown(MovingEntity self, List<Entity> entities) {
        
        Integer distToCollide = null;

        for(Entity e : entities) {
            int dist = e.posY - self.posY - self.sizeY;
            if(dist >= 0) {
                if(collideInRange(self.posX, self.sizeX, e.posX, e.sizeX)) 
                    if(distToCollide == null || dist < distToCollide) distToCollide = dist;
            }
        }
        return distToCollide;
    }

    default boolean collideInRange(int ePos, int eSize, int oPos, int oSize) {
        return (ePos < oPos && oPos < ePos + eSize ||
                ePos < oPos + oSize && oPos + oSize < ePos + eSize ||
                (ePos >= oPos && ePos + eSize <= oPos + oSize));
    }

    default boolean collideEntity(MovingEntity self, Entity e) {

        if (!e.equals(self) &&
            self.posX < e.posX+e.sizeX && self.posX+self.sizeX > e.posX &&
            self.posY < e.posY+e.sizeY && self.posY+self.sizeY > e.posY ) return true;
        return false;
    }
}