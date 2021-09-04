/**
 * Stores data about the pieces on the GameBoard, which represent characters
 * or other things in the simulation. This abstract superclass defines the
 * minimum amount of data a piece must have to be a functional part of the
 * game (aside from toChar(), which must be implemented by children of Piece).
 *
 * @author Mark Danza
 */
public abstract class Piece {
    /** a name for the user to identify this piece */
    private String name;
    /** reference to the GameBoard containing this piece */
    private GameBoard board;
    /** the location of this piece on the GameBoard */
    private Coords location;

    /**
     * Creates a new Piece with a name and a reference to the GameBoard
     * being used by the Session. The location of the Piece is null until
     * it is placed on the board using GameBoard.place().
     * @param name the name of the piece
     * @param board the GameBoard the simulation is taking place on
     */
    public Piece(String name, GameBoard board){
        this.name = name;
        this.board = board;
        this.location = null;
    }

    /**
     * Getter for this piece's location.
     * @return this piece's location on the board
     */
    public Coords getLocation() {
        return location;
    }

    /**
     * Setter for this piece's location.
     * @param location the new location the piece will have on the board
     */
    public void setLocation(Coords location) {
        this.location = location;
    }

    /**
     * Getter for this piece's name.
     * @return the name of this piece
     */
    public String getName() {
        return this.name;
    }

    /**
     * Used to represent pieces as ASCII characters in a PTUI. Each type of
     * Piece is represented by a different character.
     * @return a character which will act as a visual representation of this
     * piece for the user
     */
    public abstract char toChar();
}
