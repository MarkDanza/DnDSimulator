/**
 * The type of Piece that is controlled by the user; Players are also
 * Attackers.
 *
 * @author Mark Danza
 */
public class Player extends Attacker {
    /**
     * Creates a new Player piece.
     * @param name the name of this player
     * @param board the GameBoard the simulation is taking place on
     * @param weapon the Weapon this player uses to make attacks
     */
    public Player(String name, GameBoard board, Weapon weapon) {
        super(name, board, weapon);
    }

    /**
     * The character representing a Player piece is 'P'.
     * @return the character representing this piece
     */
    @Override
    public char toChar() {
        return 'P';
    }
}
