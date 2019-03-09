package artint;

import java.util.Random;

import defs.classes.Player;
import defs.enums.Colors;

/**
 *
 * @author RoMansen
 *
 *         Implementation of a player moving randomly
 */

public class RandomPlayer extends Player {

	/**
	 * The random number generator
	 */
	final Random random = new Random();

	/**
	 * Constructor. The random Player is black
	 */
	public RandomPlayer(Colors col) {
		super(col);
	}

	@Override
	public final Random getRandom() {
		return random;
	}

}
