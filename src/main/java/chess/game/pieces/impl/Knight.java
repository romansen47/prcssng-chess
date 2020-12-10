package chess.game.pieces.impl;

import java.util.ArrayList;
import java.util.List;

import chess.game.moves.IMove;
import config.Config;
import defs.classes.Field;
import defs.enums.Colors;
import defs.enums.Ids;

public class Knight extends Piece {

	/**
	 * Constructor
	 *
	 * @param col   the color
	 * @param field the field
	 */
	public Knight(Colors col, Field field) {
		super(Ids.KNIGHT, col, field, 50);
	}

	// TODO: An das Interface
	// IValidityChecker anpassen
	public boolean checkForValidity(int tmpI, int tmpJ) {
		if (getGame().getField(tmpI, tmpJ).getPiece() != null) {
			return getCol() != getGame().getField(tmpI, tmpJ).getPiece().getCol();
		}
		return true;
	}

	@Override
	public List<IMove> getSimpleMoves() {
		final List<Field> lst = new ArrayList<>();
		final int i = getField().getI();
		final int j = getField().getJ();
		checkJump(i + 1, j + 2, lst);
		checkJump(i + 1, j - 2, lst);
		checkJump(i - 1, j + 2, lst);
		checkJump(i - 1, j - 2, lst);
		checkJump(i + 2, j + 1, lst);
		checkJump(i + 2, j - 1, lst);
		checkJump(i - 2, j + 1, lst);
		checkJump(i - 2, j - 1, lst);
		lst.remove(null);
		return convertFieldsToMoves(lst);
	}

	public void checkJump(int alpha, int beta, List<Field> lst) {
		if ((alpha >= 0) && (beta >= 0) && (alpha <= Config.GAMESIZE) && (beta <= Config.GAMESIZE)
				&& checkForValidity(alpha, beta)) {
			lst.add(getGame().getField(alpha, beta));
		}
	}

}
