/**
 * Stores an array of Pieces to simulate the board and controls the
 * moving and placement of pieces on that board. Also checks whether
 * proposed attack actions between Attackers on the board are valid.
 *
 * @author Mark Danza
 */
public class GameBoard {
    /**
     * The GameBoard contains a grid for the pieces to exist on,
     * represented as a 2D array of Piece objects. A value of null at
     * any location on the board indicates that there is no piece at
     * that location.
     */
    private Piece[][] grid;
    /** the size of one side of the square grid */
    private int size;
    /** the number of feet represented by one grid space */
    public static final int FEET_PER_TILE = 5;

    /**
     * Creates a new square GameBoard with a side length of size.
     * @param size the number of grid spaces on a side of the board
     */
    public GameBoard(int size) {
        this.grid = new Piece[size][size];
        this.size = size;
    }

    /**
     * Getter for the size of the board.
     * @return the size of the board
     */
    public int getSize() {
        return size;
    }

    /**
     * Moves the Attacker at a given starting location to a given end
     * location, if there is no other Piece at that end location.
     * @param start the initial location of the Piece to move
     * @param end the location to move the Piece at start to
     * @pre there must be a valid Attacker at the location given by start
     * @return true if the move was successful, false if the move failed
     */
    public boolean move(Coords start, Coords end) {
        // look for occupied start and empty end locations
        if (!(this.grid[start.getY()][start.getX()] instanceof Attacker) || this.grid[end.getY()][end.getX()] != null) {
            System.out.println("Cannot move: No piece at start location or occupied end location."); // TODO: move this output eventually
            return false;
        }
        // check that proposed move is within the mover's speed
        Attacker mover = (Attacker) this.grid[start.getY()][start.getX()];
        int distFt = dist(start, end) * FEET_PER_TILE; // proposed move distance (in feet)
        if (distFt > mover.getSpeed()) {
            System.out.println("Cannot move that far.");
            return false;
        }
        // make move if valid
        this.grid[start.getY()][start.getX()] = null;
        this.grid[end.getY()][end.getX()] = mover;
        return true;
    }

    /**
     * Returns the distance (in grid spaces) between any two grid locations
     * using the distance formula.
     * @param loc1 first grid location
     * @param loc2 second grid location
     * @return the distance in tiles between loc1 and loc2
     */
    public int dist(Coords loc1, Coords loc2) {
        return (int) Math.sqrt( Math.pow(loc2.getX()-loc1.getX(), 2) + Math.pow(loc2.getY()-loc1.getY(), 2) );
    }

    /**
     * Checks that a proposed attack action is valid by ensuring there
     * are Attackers at the source and target locations on the board,
     * and that the target is within range of the source's attack.
     * @param source the Attacker making the attack
     * @param target the target of the attack
     * @return true if the given attack is valid, false otherwise
     */
    public boolean validateAttack(Coords source, Coords target) {
        // validate that source and target coords hold Attacker objects
        if (!(this.grid[source.getY()][source.getX()] instanceof Attacker)
                || !(this.grid[target.getY()][target.getX()] instanceof Attacker)) {
            System.out.println("Invalid attack source or target."); // TODO: move this output eventually
            return false;
        }
        // heck that distance between source and target is within range of source's weapon
        int distFt = dist(source, target) * FEET_PER_TILE; // distance between source and target (in feet)
        Attacker atkerSource = (Attacker) this.grid[source.getY()][source.getX()]; // the source Attacker object
        if (distFt > atkerSource.getWeapon().getRange()) {
            System.out.println("Target out of attack range."); // TODO: move this output eventually
            return false;
        }
        // attack is valid - caller should call observeAttack() with the same source and target
        return true;
    }

    /**
     * Cause an attacker at a given source location to make an attack action
     * against an attacker at a given target location. Then removes the target
     * piece from the board if it was hit and killed.
     * @param source the location of the attacking Attacker
     * @param target the location of the target of the attack
     * @pre source must be a location on the board that contains an Attacker
     * @pre target must be a location on the board that contains an Attacker
     * @pre the attack action that will be caused must be valid by the tests
     * of validateAttack()
     */
    public void observeAttack(Coords source, Coords target) {
        Attacker atkerSource = (Attacker) this.grid[source.getY()][source.getX()];
        Attacker atkerTarget = (Attacker) this.grid[target.getY()][target.getX()];
        boolean hit = atkerSource.attack(atkerTarget);
        if (hit) {
            // if hit, remove the hit Attacker from the board if it has 0 hp
            if (atkerTarget.getHP() <= 0) {
                this.grid[target.getY()][target.getX()] = null;
                atkerSource.addXP(atkerTarget.getXP()); // transfer xp on kill
            }
        }
    }

    /**
     * Makes the board empty.
     */
    public void reset() {
        this.grid = new Piece[this.size][this.size];
    }

    /**
     * Places a given Piece at a given location on the board.
     * @param piece a Piece object to put on the board
     * @param location the Coords to place the new piece at
     * @pre there must not be another Piece already at the given location
     */
    public void place(Piece piece, Coords location) {
        this.grid[location.getY()][location.getX()] = piece;
        piece.setLocation(location);
    }

    /**
     * Builds an ASCII representation of the state of the GameBoard
     * useful for a PTUI.
     * @return a String representation of the entire board state
     */
    @Override
    public String toString() {
        StringBuilder board = new StringBuilder("   ");
        // top line
        for (int i=0; i<size; i++) {
            board.append("__");
        }
        board.append("\n");
        // all grid rows
        for (int row=0; row<size; row++) {
            board.append(row).append(" |");
            for (int col=0; col<size; col++) {
                // inserts '.' characters where there are nulls in the grid (unoccupied spaces)
                char boardChar = (grid[row][col] == null) ? '.' : grid[row][col].toChar();
                board.append(boardChar).append(" ");
            }
            board.append("|\n");
        }
        // bottom lines
        board.append("   ");
        for (int i=0; i<size; i++) {
            board.append("--");
        }
        board.append("\n").append("   ");
        for (int i=0; i<size; i++) {
            board.append(i).append(" ");
        }
        return board.toString();
    }
}
