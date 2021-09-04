import java.util.Scanner;

/**
 * The top-level implementation of the playable simulation.
 *
 * @author Mark Danza
 */
public class Session {
    /** a placeholder name for the user's Piece */
    private static final String DEFAULT_NAME = "Player";
    /** the default size of the GameBoard */
    private static final int DEFAULT_BOARD_SIZE = 10;
    /** the default weapon given to a player */
    private static final Weapon DEFAULT_WEAPON = Weapon.shortsword;

    /** reference to the Piece controlled by the user */
    private Piece player;
    /** the data structure the simulation takes place on */
    private GameBoard board;

    public Session(Piece player, GameBoard board) {
        this.player = player;
        this.board = board;
    }

    /**
     * Update the reference to the user's Piece.
     * @param player the Piece controlled by the user
     */
    private void setPlayer(Piece player) {
        this.player = player;
    }

    /**
     * Start and run the entire simulation: repeatedly print the board state,
     * then get a command from the user via standard input and process it.
     */
    private void runGame() {
        Scanner in = new Scanner(System.in);
        boolean running = true;
        while(true) {
            // print board
            System.out.println(this.board);
            // get input
            System.out.println("Make a move.");
            System.out.print("> ");
            String input = in.nextLine();
            // parse input
            String[] inputArgs = input.split(" ");
            String comm = inputArgs[0];
            switch (comm.toLowerCase()) {
                case "q":
                case "quit":
                    return;
                case "m":
                case "move": // TODO: refactor check # args, check valid coords, and creating coords after good input args steps that occur in move and attack (MVC?)
                    // check number of arguments
                    if (inputArgs.length != 5) {
                        System.out.println("Usage: move x1 y1 x2 y2");
                        break;
                    }
                    // check arguments for valid board coordinates
                    int max = this.board.getSize() - 1;
                    boolean goodCoords = true;
                    for (int i=1; i<5; i++) {
                        if (Integer.parseInt(inputArgs[i]) > max || Integer.parseInt(inputArgs[i]) < 0) {
                            goodCoords = false;
                        }
                    }
                    if (!goodCoords) {
                        System.out.println("Invalid coordinates.");
                        break;
                    }
                    // if execution reaches here, input arguments are good, so attempt the move
                    Coords start = new Coords(Integer.parseInt(inputArgs[1]), Integer.parseInt(inputArgs[2]));
                    Coords end = new Coords(Integer.parseInt(inputArgs[3]), Integer.parseInt(inputArgs[4]));
                    if (!board.move(start, end)) {
                        // bad move

                    } else {
                        System.out.println("Moved successfully.");
                    }
                    break;
                case "a":
                case "attack":
                    // check number of arguments
                    if (inputArgs.length != 5) {
                        System.out.println("Usage: attack x1 y1 x2 y2");
                        break;
                    }
                    // check arguments for valid board coordinates
                    max = this.board.getSize() - 1;
                    goodCoords = true;
                    for (int i=1; i<5; i++) {
                        if (Integer.parseInt(inputArgs[i]) > max || Integer.parseInt(inputArgs[i]) < 0) {
                            goodCoords = false;
                        }
                    }
                    if (!goodCoords) {
                        System.out.println("Invalid coordinates.");
                        break;
                    }
                    // if execution reaches here, input arguments are good, so attempt the attack
                    Coords source = new Coords(Integer.parseInt(inputArgs[1]), Integer.parseInt(inputArgs[2]));
                    Coords target = new Coords(Integer.parseInt(inputArgs[3]), Integer.parseInt(inputArgs[4]));
                    if (this.board.validateAttack(source, target)) {
                        this.board.observeAttack(source, target);
                    }
                    break;
                default:
                    System.out.println("Unrecognized command: " + input);
            }
        }
    }

    /**
     * Creates a GameBoard for the simulation, then creates a new session and
     * a Piece for the user to control, then runs the simulation.
     * @param args command line arguments: none
     */
    public static void main(String[] args) {
        // create board and player
        GameBoard board = new GameBoard(DEFAULT_BOARD_SIZE);
        Player player = new Player(DEFAULT_NAME, board, DEFAULT_WEAPON);
        // create session
        Session session = new Session(player, board);
        // place pieces
        session.board.place(player, new Coords(0, 0));
        Enemy goblin = new Enemy("Goblin", session.board, DEFAULT_WEAPON);
        session.board.place(goblin, new Coords(4, 0));
        // start session
        session.runGame();
    }
}
