import java.util.Random;

/**
 * An object that stores information that will be used when
 * Attackers make attacks on the GameBoard. Every valid Attacker must
 * have a Weapon.
 *
 * @author Mark Danza
 */
public class Weapon {
    /** the name used to identify this weapon */
    private String name;
    /**
     * An integer representing the type of die used as this weapon's
     * damage die (e.g. 4 means a d4, 6 means a d6, etc.).
     */
    private int dmgDie;
    /** the range (in feet) from which this weapon can be used to attack */
    private int range;

    /**
     * Creates a new weapon with a specified name and damage die.
     * @param name the name of this weapon
     * @param dmgDie the number of sides on this weapon's damage die
     */
    public Weapon(String name, int dmgDie, int range) {
        this.name = name;
        this.dmgDie = dmgDie;
        this.range = range;
    }

    /**
     * Getter for the weapon's name.
     * @return this weapon's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for the attack range of this weapon.
     * @return this weapon's range (in feet)
     */
    public int getRange() {
        return range;
    }

    /**
     * Generates a random result for a damage roll of this weapon, as though
     * a die with the number of sides indicated by this.dmgDie were rolled.
     * @return a random number between 1 and the dmgDie value (inclusive)
     */
    public int rollDmg() {
        Random rand = new Random();
        return rand.nextInt(dmgDie) + 1; // nextInt produces a number between 0 (inclusive) and its argument (exclusive)
    }

    /** a predefined shortsword weapon */
    public static final Weapon shortsword = new Weapon("Shortsword", 6, 5);
}
