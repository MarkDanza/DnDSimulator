import java.util.Random;

/**
 * A type of Piece which can take attack actions and be the target
 * of damage.
 *
 * @author Mark Danza
 */
public abstract class Attacker extends Piece {
    private static final int DEFAULT_MODIFIER = 2;
    private static final int DEFAULT_AC = 15;
    private static final int DEFAULT_MAXHP = 10;
    private static final int DEFAULT_SPEED = 30;
    /** the number of sides on a d20 */
    public static final int D20 = 20;

    /** the weapon this attacker uses to make attacks */
    private Weapon weapon;
    /** the attack modifier of this attacker */
    private int modifier;
    /** the armor class of this attacker */
    private int ac;
    /** the maximum HP of this attacker */
    private int maxHp;
    /** the current HP of this attacker */
    private int hp;
    /** the speed (in feet) of this attacker */
    private int speed;
    /** the xp this attacker has */
    private int xp;

    /**
     * Instantiates a new Attacker with a Weapon.
     * @param name the name of this attacker
     * @param board the GameBoard the simulation is taking place on
     * @param weapon the Weapon this attacker uses to make attacks
     */
    public Attacker(String name, GameBoard board, Weapon weapon) {
        super(name, board);
        this.weapon = weapon;
        this.modifier = DEFAULT_MODIFIER;
        this.ac = DEFAULT_AC;
        this.maxHp = DEFAULT_MAXHP;
        this.hp = DEFAULT_MAXHP;
        this.speed = DEFAULT_SPEED;
        this.xp = 0;
    }

    /**
     * Getter for this attacker's HP.
     * @return this attacker's current hp
     */
    public int getHP() {
        return hp;
    }

    /**
     * Getter for this attacker's xp. Used to transfer xp from killed attackers.
     * @return this attacker's current total xp
     */
    public int getXP() {
        return xp;
    }

    /**
     * Add to this attacker's xp total.
     * @param xp the amount of xp to add (use a negative value to subtract)
     */
    public void addXP(int xp) {
        this.xp += xp;
    }

    /**
     * Getter for this attacker's speed.
     * @return this attacker's speed (in feet)
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Getter for the information on this attacker's weapon.
     * @return the Weapon object belonging to this attacker
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /**
     * Handles an attack action where this Attacker attacks another
     * Attacker as the target.
     * @param target the target of the attack
     * @return true if the attack hit and damage was dealt, false otherwise
     */
    public boolean attack(Attacker target) {
        // roll to hit
        Random rand = new Random();
        int atkRoll = rand.nextInt(D20) + 1;
        if (atkRoll >= target.ac) {
            // roll damage
            int dmg = this.weapon.rollDmg();
            target.hp -= dmg;
            System.out.println("Attack hit and dealt " + dmg + " damage."); // TODO: move this output eventually
            return true;
        } else {
            System.out.println("Attack missed.");
            return false;
        }
    }

    @Override
    public abstract char toChar();
}
