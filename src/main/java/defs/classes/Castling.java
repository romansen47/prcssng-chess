package defs.classes;

import defs.enums.Ids;
import defs.interfaces.IPiece;
import pieces.King;
import pieces.Rook;

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
	final Rook rook;

	public Castling(King king, Rook rook) {
		super(king, rook.getField());
		this.rook = rook;
	}

	@Override
	public void execute() {
		final IPiece piece = this.getNext().getPiece();
		if ((piece.getId() == Ids.ROOK) && (this.getFig().getCol() == this.getNext().getPiece().getCol())) {
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
			this.getFig().setField(this.getGame().getField(this.getPrev().getI(), k));
			this.getGame().getField(this.getPrev().getI(), k).setPiece(this.getFig());
			this.getNext().setPiece(null);
			this.rook.setField(this.getGame().getField(this.getPrev().getI(), r));
			this.getGame().getField(this.getPrev().getI(), r).setPiece(this.rook);
		}
		((King) this.getFig()).setValidForCastling(false);
		this.getReferee().switchMainPlayer();
	}
}
