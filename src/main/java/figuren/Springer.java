package figuren;

import java.util.ArrayList;
import java.util.List;

import chess.Main;
import conf.Config;
import defs.classes.Field;
import defs.classes.Piece;
import defs.enums.Colors;
import defs.enums.Ids;

public class Springer extends Piece {

	public Springer(Colors col, Field field){
		super(Ids.Springer, col, field);
	}

	@Override
	public List<Field> getPossibleMoves() {
		List<Field> lst = new ArrayList<Field>();
		lst.add(this.getField());
		int i = 7 - getField().getI();
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
		
		return lst;
	}

	// TODO: An das Interface IValidityChecker anpassen
	public boolean checkForValidity(int tmpI, int tmpJ) {
		if (getSpiel().getField(tmpI, tmpJ).getPiece() != null) {
			return getCol() != getSpiel().getField(tmpI, tmpJ).getPiece().getCol();
		}
		return true;
	}

	@Override
	public void draw(Main main) {
		main.fill(getColAsInt());
		final int size = Config.Size;
		if (col == Colors.WHITE) {
			main.image(main.getWhiteKnight(), size * getField().getJ(), size * getField().getI(), size, size);
		} else {
			main.image(main.getBlackKnight(), size * getField().getJ(), size * getField().getI(), size, size);
		}
	}

}
