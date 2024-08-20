package obstacles;

import common.*;
import common.Location;

/**
 * Represents a camera on the map that obstructs all locations in its cone of vision.
 */
public class Laser extends LocatableObstacle {
    private final Direction direction;
    /**
     * Constructs a new Camera object with the given location and direction.
     * @param location The location of the camera
     * @param direction The direction of the camera
     */
    public Laser(Location location, Direction direction) {
        super(location);
        this.direction = direction;
    }

    @Override
    public boolean isLocationObstructed(int x, int y) {
        // Laser obstruct locations in a straight line from their position
        switch (direction) {
            case NORTH:
                return location.getY() >= y && location.getX() == x; // Line extending upwards
            case EAST:
                return location.getX() <= x && location.getY() == y; // Line extending to the right
            case SOUTH:
                return location.getY() <= y && location.getX() == x; // Line extending downwards
            case WEST:
                return location.getX() >= x && location.getY() == y; // Line extending to the left
            default:
                throw new IllegalStateException("Laser direction must be one of NORTH, EAST, SOUTH, WEST");
        }
    }

    @Override
    public char getSymbol() {
        return ObstacleType.LASER.getSymbol();
    }

    /**
     * Constructs a new Camera object from the given string argument.
     * @param arg The string argument in the format "x,y,direction" representing the location and direction of the camera
     * @return A new Camera object with the given location and direction
     */
    public static Laser parse(String arg) {
        String[] parts = arg.split(",");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Laser must have 3 inputs (x, y, direction)");
        }
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        Direction direction = Direction.parse(parts[2]);
        Location location = new Location(x, y);
        return new Laser(location, direction);
    }
}