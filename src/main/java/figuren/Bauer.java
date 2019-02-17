package figuren;

import java.util.ArrayList;
import java.util.List;

import chess.Main;
import conf.Config;
import defs.classes.Field;
import defs.classes.Piece;
import defs.enums.BlackWhite;
import defs.enums.Ids;

public class Bauer extends Piece {

	public Bauer(BlackWhite col, Field field) {
		super(Ids.Bauer, col, field);
	}

	@Override
	public List<Field> getPossibleMoves() {
		List<Field> lst = new ArrayList<Field>();
		lst.add(this.getField());
		if (this.getCol() == BlackWhite.WHITE) {
			if (getField().getI() > 1) {
				if (getPosJ() < 6 && getSpiel().getField(8 - getPosI(), getPosJ() + 1).getFigur() != null
				        && getSpiel().getField(8 - getPosI(), getPosJ() + 1).getFigur().getCol() == BlackWhite.BLACK) {
					lst.add(getSpiel().getField(8 - getPosI(), getPosJ() + 1));
				}
				if (getPosJ() > 0 && getSpiel().getField(8 - getPosI(), getPosJ() - 1).getFigur() != null
				        && getSpiel().getField(8 - getPosI(), getPosJ() - 1).getFigur().getCol() == BlackWhite.BLACK) {
					lst.add(getSpiel().getField(8 - getPosI(), getPosJ() - 1));
				}
				if (getSpiel().getField(8 - getPosI(), getPosJ()).getFigur() == null) {
					lst.add(getSpiel().getField(8 - getPosI(), getPosJ()));
					if (getField().getI() == 6) {
						if (getSpiel().getField(9 - getPosI(), getPosJ()).getFigur() == null) {
							lst.add(getSpiel().getField(9 - getPosI(), getPosJ()));
						}
					}
				}
			}
		} else {
			if (getField().getI() < 6) {
				if (getPosJ() < 6 && getSpiel().getField(8 - getPosI(), getPosJ() + 1).getFigur() != null
				        && getSpiel().getField(8 - getPosI(), getPosJ() + 1).getFigur().getCol() == BlackWhite.WHITE) {
					lst.add(getSpiel().getField(6 - getPosI(), getPosJ() + 1));
				}
				if (getPosJ() > 0 && getSpiel().getField(8 - getPosI(), getPosJ() - 1).getFigur() != null
				        && getSpiel().getField(8 - getPosI(), getPosJ() - 1).getFigur().getCol() == BlackWhite.WHITE) {
					lst.add(getSpiel().getField(6 - getPosI(), getPosJ() - 1));
				}
				if (getSpiel().getField(6 - getPosI(), getPosJ()).getFigur() == null) {
					lst.add(getSpiel().getField(6 - getPosI(), getPosJ()));
					if (getField().getI() == 1) {
						if (getSpiel().getField(5 - getPosI(), getPosJ()).getFigur() == null) {
							lst.add(getSpiel().getField(5 - getPosI(), getPosJ()));
						}
					}
				}
			}
		}
		return lst;
	}

	@Override
	public void draw(Main main) {
		main.fill(getColAsInt());
		final int size = Config.Size;
		if (col == BlackWhite.WHITE) {
			main.image(main.getWhitePawn(), size * getField().getJ(), size * getField().getI(), size, size);
		} else {
			main.image(main.getBlackPawn(), size * getField().getJ(), size * getField().getI(), size, size);
		}
	}

}
