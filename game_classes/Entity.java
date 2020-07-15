package game_classes;

import java.awt.Color;
import java.util.List;


public class Entity extends Obstacle {

    private int healthMax, healthCurrent;
    private List<Entity> group;

    public Entity(Color color, int posX, int posY, int sizeX, int sizeY, int healthMax, List<Entity> group) {
        super(color, posX, posY, sizeX, sizeY);
        this.healthMax = healthMax;
        this.healthCurrent = healthMax;
        this.group = group;
    }

    public void move(int moveX, int moveY) {
        super.posX += moveX;
        super.posY += moveY;
    }

    public void healthChange(int change) {
        healthCurrent += change;
        if(healthCurrent > healthMax) healthCurrent = healthMax;
        else if(healthCurrent <= 0) kill();
    }

    public void kill() {
        group.remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        return false;
    }

    public Integer collideLeft(List<Obstacle> obstacles) {

        Integer distToCollide = null;

        for(Obstacle o : obstacles) {
            int dist = this.posX - o.posX - o.sizeX;
            if(dist >= 0) {
                if(collideRange(this.posY, this.sizeY, o.posY, o.sizeY)) {
                    if(distToCollide == null || dist < distToCollide) distToCollide = dist;
                }
            }
        }
        return distToCollide;
    }

    public Integer collideRight(List<Obstacle> obstacles) {
        
        Integer distToCollide = null;

        for(Obstacle o : obstacles) {
            int dist = o.posX - this.posX - this.sizeX;
            if(dist >= 0) {
                if(collideRange(this.posY, this.sizeY, o.posY, o.sizeY)) 
                    if(distToCollide == null || dist < distToCollide) distToCollide = dist;
            }
        }
        return distToCollide;
    }

    public Integer collideUp(List<Obstacle> obstacles) {
        
        Integer distToCollide = null;

        for(Obstacle o : obstacles) {
            int dist = this.posY - o.posY - o.sizeY;
            if(dist >= 0) {
                if(collideRange(this.posX, this.sizeX, o.posX, o.sizeX)) 
                    if(distToCollide == null || dist < distToCollide) distToCollide = dist;
            }
        }
        return distToCollide;
    }

    public Integer collideDown(List<Obstacle> obstacles) {
        
        Integer distToCollide = null;

        for(Obstacle o : obstacles) {
            int dist = o.posY - this.posY - this.sizeY;
            if(dist >= 0) {
                if(collideRange(this.posX, this.sizeX, o.posX, o.sizeX)) 
                    if(distToCollide == null || dist < distToCollide) distToCollide = dist;
            }
        }
        return distToCollide;
    }

    public boolean collideRange(int ePos, int eSize, int oPos, int oSize) {
        return (ePos <= oPos && oPos <= ePos + eSize ||
                ePos <= oPos + oSize && oPos + oSize <= ePos + eSize ||
                (ePos >= oPos && ePos + eSize <= oPos + oSize));
    }
}