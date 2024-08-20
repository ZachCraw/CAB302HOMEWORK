package obstacles;

import common.Location;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a minefield on the map with multiple mines.
 */
public class Minefield extends LocatableObstacle {
    private final List<Location> mineLocations;

    /**
     * Constructs a new Minefield object with the given list of mine locations.
     * @param mineLocations The list of mine locations
     */
    public Minefield(List<Location> mineLocations) {
        super(mineLocations.isEmpty() ? null : mineLocations.get(0)); // Set initial location as the first mine location, or null if empty
        this.mineLocations = mineLocations;
    }

    @Override
    public boolean isLocationObstructed(int x, int y) {
        // A minefield obstructs all its mine locations
        for (Location mineLocation : mineLocations) {
            if (mineLocation.getX() == x && mineLocation.getY() == y) {
                return true;
            }
        }
        return false;
    }

    @Override
    public char getSymbol() {
        return ObstacleType.MINEFIELD.getSymbol(); // Define the symbol for a minefield in ObstacleType enum
    }

    /**
     * Constructs a new Minefield object from the given string argument.
     * @param arg The string argument in the format "x1,y1,x2,y2,..." representing the mine locations
     * @return A new Minefield object with the given mine locations
     */
    public static Minefield parse(String arg) {
        String[] parts = arg.split(",");
        if (parts.length % 2 != 0) {
            throw new IllegalArgumentException("Minefield must have an even number of inputs (x1,y1,x2,y2,...)");
        }

        List<Location> mineLocations = new ArrayList<>();
        for (int i = 0; i < parts.length; i += 2) {
            int x = Integer.parseInt(parts[i]);
            int y = Integer.parseInt(parts[i + 1]);
            mineLocations.add(new Location(x, y));
        }

        return new Minefield(mineLocations);
    }
}
