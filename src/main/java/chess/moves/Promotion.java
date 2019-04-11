/**
 *
 */
package chess.moves;

import chess.Drawer;
import chess.pieces.IPiece;
import chess.pieces.Pawn;
import defs.classes.Field;
import defs.enums.Colors;
import defs.enums.Ids;

/**
 * @author ro
 *
 */
public class Promotion extends Move {

	final public IPiece pawn;

	/**
	 * TODO: Constructor
	 */
	public Promotion(Field prev, Field next, Ids id) {
		super(prev, next);
		pawn = getPiece();
	}

	@Override
	public void execute() {
		super.execute();
		IPiece newQueen = ((Pawn) pawn).getPromotedQueen();
		newQueen.setField(getNext());
		pawn.getOwner().getPieces().add(newQueen);
		pawn.die();
		getNext().setPiece(newQueen);
		if (getPiece().getCol() == Colors.WHITE) {
			newQueen.setImage(Drawer.getMain().getWhiteQueen());
		} else {
			newQueen.setImage(Drawer.getMain().getBlackQueen());
		}
	}
}
