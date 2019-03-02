package defs.classes;

import defs.enums.Ids;
import defs.interfaces.IPiece;
import pieces.King;
import pieces.Rook;


/**
 * 
 * @author roman
 *
 * Subtype of Move.
 */
public class Castling extends Move{

	/**
	 * Treating a castling like a (friendly) beat. Opponent is always a rook.
	 */
	final Rook rook;
	
	public Castling(King king, Rook rook) {
		super(king, rook.getField());
		this.rook=rook;
	}

	@Override
	public void execute() {
		IPiece piece=getNext().getPiece();
		if (piece.getId()==Ids.Turm && getFig().getCol()==getNext().getPiece().getCol()) {
			int k=getPrev().getJ();
			int r=getNext().getJ();
			if (k>r) {
				r=k-1;
				k=r-1;
			}
			else {
				r=k+1;
				k=r+1;
			}
			getPrev().setPiece(null);
			getFig().setField(getGame().getField(getPrev().getI(), k));
			getGame().getField(getPrev().getI(), k).setPiece(getFig());
			getNext().setPiece(null);
			rook.setField(getGame().getField(getPrev().getI(), r));
			getGame().getField(getPrev().getI(), r).setPiece(rook);
		}
		((King)getFig()).setValidForCastling(false);
		getReferee().switchMainPlayer();
	}
}
