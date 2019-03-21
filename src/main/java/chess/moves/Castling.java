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
	 * Treating a castling like a (friendly) beat. Opponent is always a rook.
	 */
	private final Rook rook;

	public Castling(King king, Rook rook) {
		super(king.getField(), rook.getField());
		this.rook = rook;
	}

	@Override
	public void execute() {
		IPiece king = this.getPiece();
		if ((rook.getId() == Ids.ROOK) && (king.getCol() == rook.getCol())) {
			int k = this.getPrev().getJ();
			int r = this.getNext().getJ();
			if (k > r) {
				r = k - 1;
				k = r - 1;
			} else {
				r = k + 1;
				k = r + 1;
			}
			this.getPrev().setPiece(null);
			king.setField(this.getGame().getField(this.getPrev().getI(), k));
			this.getGame().getField(this.getPrev().getI(), k).setPiece(king);
			this.getNext().setPiece(null);
			rook.setField(this.getGame().getField(this.getPrev().getI(), r));
			rook.getField().setPiece(this.getRook());
		}
		((King) king).setValidForCastling(false);
		getGame().getMoveList().add(this);
		this.getReferee().switchMainPlayer();
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
