package obstacles;

import common.Location;

/**
 * Represents a fence on the map with a start and end location.
 */
public class Wall extends LocatableObstacle {
    private final Location end;

    /**
     * Constructs a new Fence object with the given start and end locations.
     * @param start The start location of the fence
     * @param end The end location of the fence
     */
    public Wall(Location start, Location end) {
        super(start);
        this.end = end;
    }

    @Override
    public boolean isLocationObstructed(int x, int y) {
        // Assuming `location` and `end` define opposite corners of the square
        return (
                (x >= Math.min(location.getX(), end.getX()) && x <= Math.max(location.getX(), end.getX())) &&
                        (y >= Math.min(location.getY(), end.getY()) && y <= Math.max(location.getY(), end.getY()))
        );
    }


    @Override
    public char getSymbol() {
        return ObstacleType.WALL.getSymbol();
    }

    public static Wall parse(String arg) {
        String[] parts = arg.split(",");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Wall must have 4 coordinates: startX,startY,endX,endY");
        }
        int startX = Integer.parseInt(parts[0]);
        int startY = Integer.parseInt(parts[1]);
        int endX = Integer.parseInt(parts[2]);
        int endY = Integer.parseInt(parts[3]);
        Location start = new Location(startX, startY);
        Location end = new Location(endX, endY);
        return new Wall(start, end);
    }
}