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
			if (getField().getI() < 7 ) {
				if (getPosJ() < 7 && getSpiel().getField(getPosI()+1, getPosJ() + 1).getPiece() != null
						&& getSpiel().getField(getPosI()+1, getPosJ() + 1).getPiece().getCol() == Colors.BLACK) {
					lst.add(getSpiel().getField(getPosI()+1, getPosJ() + 1));
				}
				if (getPosJ() > 0 && getSpiel().getField(getPosI()+1, getPosJ() - 1).getPiece() != null
						&& getSpiel().getField(getPosI()+1, getPosJ() - 1).getPiece().getCol() == Colors.BLACK) {
					lst.add(getSpiel().getField(getPosI()+1, getPosJ() - 1));
				}
				if (getSpiel().getField(getPosI()+1, getPosJ()).getPiece() == null) {
					lst.add(getSpiel().getField(getPosI()+1, getPosJ()));
					if (getField().getI() == 1) {
						if (getSpiel().getField(getPosI()+2, getPosJ()).getPiece() == null) {
							lst.add(getSpiel().getField(getPosI()+2, getPosJ()));
						}
					}
				}
			}
		} else {
			if (getField().getI() > 0) {
				if (getPosJ() < 7 && getSpiel().getField(getPosI()-1, getPosJ() + 1).getPiece() != null
						&& getSpiel().getField(getPosI()-1, getPosJ() + 1).getPiece().getCol() == Colors.WHITE) {
					lst.add(getSpiel().getField(getPosI()-1, getPosJ() + 1));
				}
				if (getPosJ() > 0 && getSpiel().getField(getPosI()-1, getPosJ() - 1).getPiece() != null
						&& getSpiel().getField(getPosI()-1, getPosJ() - 1).getPiece().getCol() == Colors.WHITE) {
					lst.add(getSpiel().getField(getPosI()-1, getPosJ() - 1));
				}
				if (getSpiel().getField(getPosI()-1, getPosJ()).getPiece() == null) {
					lst.add(getSpiel().getField(getPosI()-1, getPosJ()));
					if (getField().getI() == 6) {
						if (getSpiel().getField(getPosI()-2, getPosJ()).getPiece() == null) {
							lst.add(getSpiel().getField(getPosI()-2, getPosJ()));
						}
					}
				}
			}
		}
		return lst;
	}

}
