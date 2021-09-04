/**
 * Wrapper class for the location data of a Piece on the GameBoard.
 *
 * @author Mark Danza
 */
public class Coords {
    /** an x-coordinate on the GameBoard */
    private int x;
    /** a y-coordinate on the GameBoard */
    private int y;

    /**
     * Creates a new Coords object representing a set of coordinates
     * in the Gameboard.
     * @param x an x-coordinate on the board
     * @param y a y-coordinate on the board
     */
    public Coords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Copy constructor for Coords. Values of -1 are given to both coordinates
     * if an attempt is made to copy an invalid object.
     * @param other the Coords to make a copy of
     */
    public Coords(Coords other) {
        if (other instanceof Coords) {
            this.x = other.x;
            this.y = other.y;
        } else {
            this.x = -1;
            this.y = -1;
        }
    }

    /**
     * Getter for the x-coordinate.
     * @return the x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Getter for the y-coordinate.
     * @return the y-coordinate
     */
    public int getY() {
        return y;
    }
}
