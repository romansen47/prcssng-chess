/**
 * 
 */
package pieces;

import defs.classes.Field;
import defs.classes.Move;
import defs.enums.Ids;
import defs.interfaces.IMove;

/**
 * @author ro
 *
 */
public class Promotion extends Move implements IMove {

	/**
	 * @param prev
	 * @param next
	 */
	public Promotion(Field prev, Field next, Ids id) {
		super(prev, next);
		// TODO Auto-generated constructor stub
	}

}
