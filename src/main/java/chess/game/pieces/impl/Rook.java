package chess.game.pieces.impl;

import java.util.List;

import chess.game.moves.IMove;
import chess.game.pieces.ILongDist;
import chess.game.pieces.IPiece;
import defs.classes.Field;
import defs.enums.Colors;
import defs.enums.Ids;

public class Rook extends Piece implements ILongDist {

	/**
	 * Constructor
	 *
	 * @param col   the color
	 * @param field the field
	 */
	public Rook(Colors col, Field field) {
		super(Ids.ROOK, col, field, 300);
	}

	@Override
	public void checkDirections(List<Field> lst) {
		checkDirection(lst, new int[] { 1, 0 });
		checkDirection(lst, new int[] { -1, 0 });
		checkDirection(lst, new int[] { 0, 1 });
		checkDirection(lst, new int[] { 0, -1 });
	}

	/**
	 * computes whether rook already has been moved
	 *
	 * @return whether rook is still valid for castling
	 */
	public boolean isValidForCastling() {
		boolean hasBeenMoved = false;
		for (final IMove move : getOwner().getMoveList()) {
			final IPiece fig = move.getPrev().getPiece();
			if ((fig instanceof Rook) && ((Rook) fig == this)) {
				hasBeenMoved = true;
			}
		}
		return !hasBeenMoved;
	}

}
