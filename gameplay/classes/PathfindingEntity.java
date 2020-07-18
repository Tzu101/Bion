package gameplay.classes;

import utility.Pathfinder;
import gameplay.interfaces.*;

import java.util.List;

import java.awt.Color;


public class PathfindingEntity extends MovingEntity implements Pushable {

    private int pathfindTime = 20;
    private int pathfindCounter = 20;
    private Pathfinder.Point path;

    public PathfindingEntity(Color color, int posX, int posY, int sizeX, int sizeY, float speedAcc, float speedMax, float push) {
        super(color, posX, posY, sizeX, sizeY, speedAcc, speedAcc, speedMax, speedMax, push, push);
    }
    
    public void update(List<Entity> obstacles, List<MovingEntity> entities, int[][] map, Player player, PatternEntity p) {

        pathfind(map, player);
        claculateMovement();
        calculatePush(this, player);
        calculatePush(this, entities);
        calculatePush(this, p);
        calculateCollide(this, obstacles);
        newPosition();        
    }

    public void pathfind(int[][] map, Player player) {

        int centerX = posX + sizeX/2;
        int centerY = posY + sizeY/2;

        int targetX,targetY;

        if(path == null) {
            targetX = player.posX + player.sizeX/2;
            targetY = player.posY + player.sizeY/2;
        }
        else {
            targetX = path.x()*32 + 16;
            targetY = path.y()*32 + 16;

            if(Math.abs(targetX - centerX) < sizeX && Math.abs(targetY - centerY) < sizeY) path = path.n();
        }

        if(centerX > targetX) left = true;
        else left = false;
            
        if(centerX < targetX) right = true;
        else right = false;

        if(centerY > targetY) up = true;
        else up = false;

        if(centerY < targetY) down = true;
        else down = false;

        if(pathfindCounter >= pathfindTime) {
            pathfindCounter = 0;
            path = Pathfinder.reversePath(Pathfinder.findEndPoint(map, posX/32, posY/32, player.posX/32, player.posY/32, 1000));
        }
        else pathfindCounter++;
    }
}