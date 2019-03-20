package defs.players.artint;

import java.util.Random;

import defs.enums.Colors;
import defs.players.Player;

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
	 * 
	 * @param col the color
	 */
	public RandomPlayer(Colors col) {
		super(col);
	}

	@Override
	public final Random getRandom() {
		return random;
	}

}
