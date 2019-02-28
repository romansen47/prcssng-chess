package pieces;

import java.util.ArrayList;
import java.util.List;

import chess.Move;
import defs.classes.Field;
import defs.classes.Piece;
import defs.enums.Colors;
import defs.enums.Ids;
import defs.interfaces.ILongDist;

public class Rook extends Piece implements ILongDist{
	
	public Rook(Colors col, Field field){
		super(Ids.Turm, col, field);
	}

	@Override
	public List<Move> getPossibleMoves() {
		return convertFieldsToMoves(longDistCheck());
	}
	
	public void checkDirections(List<Field> lst,List<Field> lst1,List<Field> lst2,List<Field> lst3,List<Field> lst4) {
		int i = 1;
		while (getPosI() + i <= 7 && checkForValidity(getSpiel().getField( getPosI() +i, getPosJ()), lst1)) {
			i += 1;
		}
		i = 1;
		while (getPosI() - i >= 0 && checkForValidity(getSpiel().getField( getPosI() - i, getPosJ()), lst2)) {
			i += 1;
		}
		i = 1;
		while (getPosJ() + i <= 7 && checkForValidity(getSpiel().getField( getPosI(), getPosJ() + i), lst3)) {
			i += 1;
		}
		i = 1;
		while (getPosJ() - i >= 0 && checkForValidity(getSpiel().getField( getPosI(), getPosJ() - i), lst4)) {
			i += 1;
		}
	}

	public boolean isValidForCastling() {
		return getAttackers().isEmpty();
	}

}
