package figuren;

import java.util.ArrayList;
import java.util.List;

import chess.Main;
import conf.Config;
import defs.classes.Field;
import defs.classes.Piece;
import defs.enums.Colors;
import defs.enums.Ids;

public class Koenig extends Piece {

	public Koenig(Colors col, Field field) throws Exception{
		super(Ids.Koenig, col, field);
	}

	@Override
	public List<Field> getPossibleMoves() {
		List<Field> lst = new ArrayList<Field>();
		lst.add(this.getField());
		int tempI = 7 - getField().getI();
		int tempJ = getField().getJ();
		if (tempI - 1 >= 0) {
			checkForValidity(getSpiel().getField(tempI - 1, tempJ), lst);
			if (tempI - 1 >= 0) {
				checkForValidity(getSpiel().getField(tempI - 1, tempJ - 1), lst);
			}
			if (tempI + 1 <= 7) {
				checkForValidity(getSpiel().getField(tempI - 1, tempJ + 1), lst);
			}
		}
		if (tempI + 1 <= 7) {
			checkForValidity(getSpiel().getField(tempI + 1, tempJ), lst);
			if (tempI - 1 >= 0) {
				checkForValidity(getSpiel().getField(tempI + 1, tempJ - 1), lst);
			}
			if (tempI + 1 <= 7) {
				checkForValidity(getSpiel().getField(tempI + 1, tempJ + 1), lst);
			}
		}
		if (tempJ + 1 <= 7) {
			checkForValidity(getSpiel().getField(tempI, tempJ + 1), lst);
		}
		if (tempJ - 1 >= 0) {
			checkForValidity(getSpiel().getField(tempI, tempJ - 1), lst);
		}

		return lst;

	}

	@Override
	public boolean checkForValidity(Field fld, List<Field> lst) {
		if (fld.getPiece() != null && fld.getPiece().getCol() == getCol()) {
			return false;
		}
		lst.add(fld);
		return true;
	}

	@Override
	public void draw(Main main) {
		main.fill(getColAsInt());
		final int size = Config.Size;
		if (col == Colors.WHITE) {
			main.image(main.getWhiteKing(), size * getField().getJ(), size * getField().getI(), size, size);
		} else {
			main.image(main.getBlackKing(), size * getField().getJ(), size * getField().getI(), size, size);
		}
	}

}
