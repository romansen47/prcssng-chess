package chess;

import defs.classes.Game;
import defs.classes.Piece;
import defs.enums.Ids;
import pieces.King;
import pieces.Rook;

public class Castling extends Move{

	final Rook rook;
	
	public Castling(King king, Rook rook) {
		super(king, rook.getField());
		this.rook=rook;
	}

	@Override
	public void execute(Game game) {
		Piece piece=next.getPiece();
		if (piece.id==Ids.Turm && fig.getCol()==next.getPiece().getCol()) {
			int k=prev.getJ();
			int r=next.getJ();
			if (k>r) {
				r=k-1;
				k=r-1;
			}
			else {
				r=k+1;
				k=r+1;
			}
			prev.setPiece(null);
			fig.setField(getSpiel().getField(7-prev.getI(), k));
			getSpiel().getField(prev.getI(), k).setPiece(fig);
			next.setPiece(null);
			rook.setField(getSpiel().getField(7-prev.getI(), r));
			getSpiel().getField(prev.getI(), r).setPiece(rook);
			
		}
		getReferee().switchMainPlayer();
	}
}
