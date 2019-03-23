/**
 *
 */
package chess.moves;

import defs.classes.Field;
import defs.enums.Ids;

/**
 * @author ro
 *
 */
public class Promotion extends Move implements IMove {

	/**
	 * TODO: Constructor
	 */
	public Promotion(Field prev, Field next, Ids id) {
		super(prev, next);
	}

}
