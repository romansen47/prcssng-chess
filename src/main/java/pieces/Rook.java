package pieces;

import java.util.List;

import defs.classes.Field;
import defs.classes.Piece;
import defs.enums.Colors;
import defs.enums.Ids;
import defs.interfaces.ILongDist;
import defs.interfaces.IMove;
import defs.interfaces.IPiece;

public class Rook extends Piece implements ILongDist {

	public Rook(Colors col, Field field) {
		super(Ids.ROOK, col, field);
	}

	@Override
	public void checkDirections(List<Field> lst) {
		checkDirection(lst, new int[] { 1, 0 });
		checkDirection(lst, new int[] { -1, 0 });
		checkDirection(lst, new int[] { 0, 1 });
		checkDirection(lst, new int[] { 0, -1 });
	}

	public boolean isValidForCastling() {
		boolean hasBeenMoved = false;
		for (IMove move : getPlayer().getMoveList()) {
			IPiece fig = move.getFig();
			if (fig instanceof Rook && (Rook) fig == this) {
				hasBeenMoved = true;
			}
		}
		King king = getOpponent().getKing();
		boolean validForCastling = king.isValidForCastling();
		king.setValidForCastling(false);
		List<Field> attackers = getAttackers();
		king.setValidForCastling(validForCastling);
		return attackers.isEmpty() && !hasBeenMoved;
	}

}
