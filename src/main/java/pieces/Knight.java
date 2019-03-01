package pieces;

import java.util.ArrayList;
import java.util.List;

import defs.classes.Field;
import defs.classes.Move;
import defs.classes.Piece;
import defs.enums.Colors;
import defs.enums.Ids;
import defs.interfaces.IMove;

public class Knight extends Piece {

	public Knight(Colors col, Field field){
		super(Ids.Springer, col, field);
	}

	@Override
	public List<IMove> getPossibleMoves() {
		List<Field> lst = new ArrayList<Field>();
		lst.add(this.getField());
		int i = getField().getI();
		int j = getField().getJ();
		if (i + 1 <= 7 && j + 2 <= 7 && checkForValidity(i + 1, j + 2)) {
			lst.add(getSpiel().getField(i + 1, j + 2));
		}
		if (i + 1 <= 7 && j - 2 >= 0 && checkForValidity(i + 1, j - 2)) {
			lst.add(getSpiel().getField(i + 1, j - 2));
		}
		if (i - 1 >= 0 && j + 2 <= 7 && checkForValidity(i - 1, j + 2)) {
			lst.add(getSpiel().getField(i - 1, j + 2));
		}
		if (i - 1 >= 0 && j - 2 >= 0 && checkForValidity(i - 1, j - 2)) {
			lst.add(getSpiel().getField(i - 1, j - 2));
		}
		if (i + 2 <= 7 && j + 1 <= 7 && checkForValidity(i + 2, j + 1)) {
			lst.add(getSpiel().getField(i + 2, j + 1));
		}
		if (i + 2 <= 7 && j - 1 >= 0 && checkForValidity(i + 2, j - 1)) {
			lst.add(getSpiel().getField(i + 2, j - 1));
		}
		if (i - 2 >= 0 && j + 1 <= 7 && checkForValidity(i - 2, j + 1)) {
			lst.add(getSpiel().getField(i - 2, j + 1));
		}
		if (i - 2 >= 0 && j - 1 >= 0 && checkForValidity(i - 2, j - 1)) {
			lst.add(getSpiel().getField(i - 2, j - 1));
		}
		
		return convertFieldsToMoves(lst);
	}

	// TODO: An das Interface IValidityChecker anpassen
	public boolean checkForValidity(int tmpI, int tmpJ) {
		if (getSpiel().getField(tmpI, tmpJ).getPiece() != null) {
			return getCol() != getSpiel().getField(tmpI, tmpJ).getPiece().getCol();
		}
		return true;
	}

}
