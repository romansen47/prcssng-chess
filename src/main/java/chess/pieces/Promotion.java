/**
 *
 */
package chess.pieces;

import chess.moves.IMove;
import chess.moves.Move;
import defs.classes.Field;
import defs.enums.Ids;

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
