package chess.moves;

import chess.pieces.IPiece;
import chess.pieces.King;
import chess.pieces.Rook;
import defs.enums.Ids;

/**
 *
 * @author roman
 *
 *         Subtype of Move.
 */
public class Castling extends Move {

	/**
	 * Treating a castling like a (friendly)
	 * beat. Opponent is always a rook.
	 */
	private final Rook rook;

	public Castling(King king, Rook rook) {
		super(king.getField(), rook.getField());
		this.rook = rook;
	}

	@Override
	public void execute() {
		final IPiece king = getPiece();
		if ((rook.getId() == Ids.ROOK) && (king.getCol() == rook.getCol())) {
			int	k	= getPrev().getJ();
			int	r	= getNext().getJ();
			if (k > r) {
				r	= k - 1;
				k	= r - 1;
			} else {
				r	= k + 1;
				k	= r + 1;
			}
			getPrev().setPiece(null);
			king.setField(getGame().getField(getPrev().getI(), k));
			getGame().getField(getPrev().getI(), k).setPiece(king);
			getNext().setPiece(null);
			rook.setField(getGame().getField(getPrev().getI(), r));
			rook.getField().setPiece(getRook());
		}
		((King) king).setValidForCastling(false);
		getGame().getMoveList().add(this);
		getReferee().switchMainPlayer();
	}

	@Override
	public String toString() {
		String str = "0-0: ";
		if (Math.abs(getPrev().getJ() - getNext().getJ()) > 3) {
			str = "0-" + str;
		}
		return str;
	}

	/**
	 * @return the rook
	 */
	Rook getRook() {
		return rook;
	}
}
