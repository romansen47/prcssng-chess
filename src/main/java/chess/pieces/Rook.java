package chess.pieces;

import java.util.List;

import chess.moves.IMove;
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
		super(Ids.ROOK, col, field);
	}

	@Override
	public void checkDirections(List<Field> lst) {
		this.checkDirection(lst, new int[] { 1, 0 });
		this.checkDirection(lst, new int[] { -1, 0 });
		this.checkDirection(lst, new int[] { 0, 1 });
		this.checkDirection(lst, new int[] { 0, -1 });
	}

	/**
	 * computes whether rook already has been moved
	 *
	 * @return whether rook is still valid for castling
	 */
	public boolean isValidForCastling() {
		boolean hasBeenMoved = false;
		for (final IMove move : this.getOwner().getMoveList()) {
			final IPiece fig = move.getPrev().getPiece();
			if ((fig instanceof Rook) && ((Rook) fig == this)) {
				hasBeenMoved = true;
			}
		}
		final King king = this.getOpponent().getKing();
		final boolean validForCastling = king.isValidForCastling();
		king.setValidForCastling(false);
		final List<Field> attackers = this.getAttackers();
		king.setValidForCastling(validForCastling);
		return attackers.isEmpty() && !hasBeenMoved;
	}

}
