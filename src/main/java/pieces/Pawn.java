package pieces;

import java.util.ArrayList;
import java.util.List;

import chess.Main;
import conf.Config;
import defs.classes.Field;
import defs.classes.Piece;
import defs.enums.Colors;
import defs.enums.Ids;

public class Pawn extends Piece {

	public Pawn(Colors col, Field field) {
		super(Ids.Bauer, col, field);
	}

	@Override
	public List<Field> getPossibleMoves() {
		List<Field> lst = new ArrayList<Field>();
		lst.add(this.getField());
		if (this.getCol() == Colors.WHITE) {
			if (getField().getI() > 1) {
				if (getPosJ() < 6 && getSpiel().getField(8 - getPosI(), getPosJ() + 1).getPiece() != null
						&& getSpiel().getField(8 - getPosI(), getPosJ() + 1).getPiece().getCol() == Colors.BLACK) {
					lst.add(getSpiel().getField(8 - getPosI(), getPosJ() + 1));
				}
				if (getPosJ() > 0 && getSpiel().getField(8 - getPosI(), getPosJ() - 1).getPiece() != null
						&& getSpiel().getField(8 - getPosI(), getPosJ() - 1).getPiece().getCol() == Colors.BLACK) {
					lst.add(getSpiel().getField(8 - getPosI(), getPosJ() - 1));
				}
				if (getSpiel().getField(8 - getPosI(), getPosJ()).getPiece() == null) {
					lst.add(getSpiel().getField(8 - getPosI(), getPosJ()));
					if (getField().getI() == 6) {
						if (getSpiel().getField(9 - getPosI(), getPosJ()).getPiece() == null) {
							lst.add(getSpiel().getField(9 - getPosI(), getPosJ()));
						}
					}
				}
			}
		} else {
			if (getField().getI() < 7) {
				if (getPosJ() < 7 && getSpiel().getField(6 - getPosI(), getPosJ() + 1).getPiece() != null
						&& getSpiel().getField(6 - getPosI(), getPosJ() + 1).getPiece().getCol() == Colors.WHITE) {
					lst.add(getSpiel().getField(6 - getPosI(), getPosJ() + 1));
				}
				if (getPosJ() > 0 && getSpiel().getField(6 - getPosI(), getPosJ() - 1).getPiece() != null
						&& getSpiel().getField(6 - getPosI(), getPosJ() - 1).getPiece().getCol() == Colors.WHITE) {
					lst.add(getSpiel().getField(6 - getPosI(), getPosJ() - 1));
				}
				if (getSpiel().getField(6 - getPosI(), getPosJ()).getPiece() == null) {
					lst.add(getSpiel().getField(6 - getPosI(), getPosJ()));
					if (getField().getI() == 1) {
						if (getSpiel().getField(5 - getPosI(), getPosJ()).getPiece() == null) {
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
		if (col == Colors.WHITE) {
			main.image(main.getWhitePawn(), size * getField().getJ(), size * getField().getI(), size, size);
		} else {
			main.image(main.getBlackPawn(), size * getField().getJ(), size * getField().getI(), size, size);
		}
	}

}
