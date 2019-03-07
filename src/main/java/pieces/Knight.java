package pieces;

import java.util.ArrayList;
import java.util.List;

import conf.Config;
import defs.classes.Field;
import defs.classes.Piece;
import defs.enums.Colors;
import defs.enums.Ids;
import defs.interfaces.IMove;

public class Knight extends Piece {

	/**
	 * Constructor
	 * 
	 * @param col   the color
	 * @param field the field
	 */
	public Knight(Colors col, Field field) {
		super(Ids.KNIGHT, col, field);
	}

	// TODO: An das Interface IValidityChecker anpassen
	public boolean checkForValidity(int tmpI, int tmpJ) {
		if (this.getGame().getField(tmpI, tmpJ).getPiece() != null) {
			return this.getCol() != this.getGame().getField(tmpI, tmpJ).getPiece().getCol();
		}
		return true;
	}

	@Override
	public List<IMove> getPossibleMoves() {
		final List<Field> lst = new ArrayList<>();
		final int i = this.getField().getI();
		final int j = this.getField().getJ();
		if (i + 1 <= Config.GAMESIZE && j + 2 <= Config.GAMESIZE && this.checkForValidity(i + 1, j + 2)) {
			lst.add(this.getGame().getField(i + 1, j + 2));
		}
		if (i + 1 <= Config.GAMESIZE && j - 2 >= 0 && this.checkForValidity(i + 1, j - 2)) {
			lst.add(this.getGame().getField(i + 1, j - 2));
		}
		if (i - 1 >= 0 && j + 2 <= Config.GAMESIZE && this.checkForValidity(i - 1, j + 2)) {
			lst.add(this.getGame().getField(i - 1, j + 2));
		}
		if (i - 1 >= 0 && j - 2 >= 0 && this.checkForValidity(i - 1, j - 2)) {
			lst.add(this.getGame().getField(i - 1, j - 2));
		}
		if (i + 2 <= Config.GAMESIZE && j + 1 <= Config.GAMESIZE && this.checkForValidity(i + 2, j + 1)) {
			lst.add(this.getGame().getField(i + 2, j + 1));
		}
		if (i + 2 <= Config.GAMESIZE && j - 1 >= 0 && this.checkForValidity(i + 2, j - 1)) {
			lst.add(this.getGame().getField(i + 2, j - 1));
		}
		if (i - 2 >= 0 && j + 1 <= Config.GAMESIZE && this.checkForValidity(i - 2, j + 1)) {
			lst.add(this.getGame().getField(i - 2, j + 1));
		}
		if (i - 2 >= 0 && j - 1 >= 0 && this.checkForValidity(i - 2, j - 1)) {
			lst.add(this.getGame().getField(i - 2, j - 1));
		}
		lst.remove(null);
		return this.convertFieldsToMoves(lst);
	}

}
