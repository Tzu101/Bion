package utility;

import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Comparator;


public class Pathfinder {

    public static class Point implements Comparable<Point>{

        private int x;
        private int y;
        private Point next;
        private Point previous;

        public Point(int x, int y) {
            this(x, y, null);
        }

        public Point(int x, int y, Point previous) {
            this.x = x;
            this.y = y;
            this.previous = previous;
        }

        public int x() {
            return this.x;
        }
        public int y() {
            return this.y;
        }
        public Point n() {
            return this.next;
        }
        public Point p() {
            return this.previous;
        }
        public void next(Point p) {
            this.next = p;
        }

        public static Comparator<Point> CompareToEnd(Point stopPoint) {
            
            return (Point p1, Point p2) -> {
                if(p1.x() == p2.x() && p1.y() == p2.y()) return 0;
                return Pathfinder.distance(p1, stopPoint) - distance(p2, stopPoint);
            };
        }

        @Override
        public int compareTo(Point p) {

            if(this.x() == p.x() && this.y() == p.y()) return 0;
            else {
                int distance = this.x() - p.x() + this.y() - p.y();
                if(distance == 0) distance = -1;
                return distance;
            }
        }

        @Override
        public boolean equals(Object p) {
            if((p instanceof Point) && this.compareTo((Point)p) == 0) return true;
            return false; 
        }

        @Override
        public String toString() {
            return String.format("%d %d", this.x, this.y);
        }
    }

    private static class Pos {
        public int x;
        public int y;
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int distance(Point start, Point stop) {

        return (Math.abs(start.x() - stop.x()) + Math.abs(start.y() - stop.y()));
    }

    public static Point findEndPoint(int[][] map, int startX, int startY, int stopX, int stopY, int maxSearches) {

        if(map[stopY][stopX] == 1 || (startX == stopX && startY == stopY)) return null;

        int searches = 0;

        ArrayList<Point> availablePoints = new ArrayList<>();
        TreeSet<Point> usedPoints = new TreeSet<>();

        Point currentPoint = new Point(startX, startY);
        Point stopPoint = new Point(stopX, stopY);

        availablePoints.add(currentPoint);

        while(!(availablePoints.isEmpty()) && searches < maxSearches) {

            currentPoint = availablePoints.get(0);
            usedPoints.add(currentPoint);
            availablePoints.remove(currentPoint);

            ArrayList<Pos> newPositions = new ArrayList<>();
            boolean left=false, right=false, up=false, down = false;
            int cX=currentPoint.x(), cY = currentPoint.y();

            if(cX > 0 && map[cY][cX-1] == 0) left = true;
            if(cX < map[0].length-1 && map[cY][cX+1] == 0) right = true;
            if(cY > 0 && map[cY-1][cX] == 0) up = true;
            if(cY < map.length-1 && map[cY+1][cX] == 0) down = true;

            if(left) newPositions.add(new Pos(-1, 0));
            if(right) newPositions.add(new Pos(1, 0));
            if(up) newPositions.add(new Pos(0, -1));
            if(down) newPositions.add(new Pos(0, 1));

            if(left && up && map[cY-1][cX-1] == 0) newPositions.add(new Pos(-1, -1));
            if(left && down && map[cY+1][cX-1] == 0) newPositions.add(new Pos(-1, 1));
            if(right & up && map[cY-1][cX+1] == 0) newPositions.add(new Pos(1, -1));
            if(right && down && map[cY+1][cX+1] == 0) newPositions.add(new Pos(1, 1));

            for(Pos newPos : newPositions) {
                Point newPoint = new Point(cX+newPos.x, cY+newPos.y, currentPoint);
                if(newPoint.equals(stopPoint)) return newPoint;
                if(!usedPoints.contains(newPoint) && !availablePoints.contains(newPoint)) availablePoints.add(newPoint);
            }

            availablePoints.sort(Point.CompareToEnd(stopPoint));
            searches++;
        }
        return null;
    }

    public static Point reversePath(Point path) {

        Point lastPath = null;

        while(path != null) {
            path.next(lastPath);
            lastPath = path;
            path = path.p();
        }
        return lastPath;
    }
}