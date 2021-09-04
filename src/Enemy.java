/**
 * The type of Piece that is controlled by the simulation; Enemies are
 * also Attackers.
 *
 * @author Mark Danza
 */
public class Enemy extends Attacker {
    /**
     * Creates a new Enemy piece.
     * @param name the name of this enemy
     * @param board the GameBoard the simulation is taking place on
     * @param weapon the Weapon this enemy uses to make attacks
     */
    public Enemy(String name, GameBoard board, Weapon weapon) {
        super(name, board, weapon);
    }

    /**
     * The character representing an Enemy piece is 'E'.
     * @return the character representing this piece
     */
    @Override
    public char toChar() {
        return 'E';
    }
}
