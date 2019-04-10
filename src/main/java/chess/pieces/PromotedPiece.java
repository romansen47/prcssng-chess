package chess.pieces;

import java.util.List;

import chess.moves.IMove;
import defs.classes.Field;
import defs.enums.Colors;
import defs.enums.Ids;

public class PromotedPiece extends Piece {

	public PromotedPiece(Ids id, Colors col, Field fld, int value) {
		super(id, col, null, value);
		setField(fld);
	}

	@Override
	public List<IMove> getSimpleMoves() {
		return ((IPiece) this).getSimpleMoves();
	}

}
