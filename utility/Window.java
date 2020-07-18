package utility;

import gameplay.classes.*;

import java.util.List;
import java.util.ArrayList;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.Timer;
import javax.swing.JPanel;


public class Window extends JPanel {

    private static final long serialVersionUID = 1L;  // just to hide the annoying warning

    private int windowWidth, windowHeight;

    private Timer mainLoopTimer;
    private int renderDelay = 50;

    /* ------------------------------------------------------------------ */

    private List<MovingEntity> entities = new ArrayList<>();
    private List<Entity> obstacles = new ArrayList<>();

    private Player p = new Player(Color.BLUE, 200, 200, 64, 64, (float)1, (float)5, 5F);

    private int[][] map;

    int[][] pattern = {{100, 100}, {600, 100}, {600, 600}, {100, 600}, {250, 250}};
    PatternEntity pa = new PatternEntity(Color.YELLOW, 10, 10, 64, 64, (float)5, (float)10, 5F, pattern);

    /* ------------------------------------------------------------------ */

    public Window(int windowWidth, int windowHeight) {

        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        this.mainLoopTimer = new Timer(this.renderDelay, new GetAction(this));
        this.mainLoopTimer.start();

        /* ------------------------------------------------------------------ */

        this.map = new int[windowHeight/32][windowWidth/32];

        Entity o1 = new Entity(Color.GREEN, 0, 0, 1, windowHeight);
        Entity o2 = new Entity(Color.GREEN, windowWidth-1, 0, 1, windowHeight);
        Entity o3 = new Entity(Color.GREEN, 0, 0, windowWidth, 1);
        Entity o4 = new Entity(Color.GREEN, 0, windowHeight-1, windowWidth, 1);
        
        obstacles.add(o1);
        obstacles.add(o2);
        obstacles.add(o3);
        obstacles.add(o4);

        /* ------------------------------------------------------------------ */

        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        addKeyListener(p);
        addMouseListener(new GetMousePress(this));
    }

    public void mainLoop() {

        p.update(obstacles, entities, pa);

        pa.update();

        for(MovingEntity e : entities) ((PathfindingEntity) e).update(obstacles, entities, map, p, pa);

        repaint();
    }

    public void paint(Graphics g) {

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, windowWidth, windowHeight);

        p.display(g);

        pa.display(g);

        for(MovingEntity e : entities) e.display(g);
        for(Entity o : obstacles) o.display(g);

        g.dispose();
    }

    public void makeObstacle(int x, int y) {
        map[y/32][x/32] = 1;
        Entity o = new Entity(Color.GREEN, x/32*32, y/32*32, 32, 32);
        obstacles.add(o);
    }

    public void makeEnemy(int x, int y) {
        PathfindingEntity e = new PathfindingEntity(Color.RED, x, y, 32, 32, 1F, 2F, 1F);
        entities.add(e);
    }
} 
