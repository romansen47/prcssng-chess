package chess.game.moves.impl;

import chess.game.pieces.impl.Pawn;
import defs.classes.Field;

public class EnPassant extends Move {

	/**
	 * the piece killed while moving to next
	 * field is a pawn.. for sure!
	 */
	final Pawn slaughtered;

	/**
	 * Constructor of an EnPassant
	 *
	 * @param fig first pawn
	 * @param fld second pawn
	 * @param fld the field of the moving
	 *            pawn
	 */
	public EnPassant(Pawn fig, Field fld) {
		super(fig.getField(), fld);
		slaughtered = ((Pawn) getGame().getChessboard()[fig.getPosI()][fld.getJ()].getPiece());
	}

	@Override
	public void execute() {
		Field fld;
		if (slaughtered.getPosI() == 3) {
			fld = getReferee().getGame().getChessboard()[2][slaughtered.getPosJ()];
		} else {
			fld = getReferee().getGame().getChessboard()[5][slaughtered.getPosJ()];
		}
		slaughtered.getField().setPiece(null);
		slaughtered.setField(fld);
		fld.setPiece(slaughtered);
		slaughtered.die();
		super.execute();
	}

}
