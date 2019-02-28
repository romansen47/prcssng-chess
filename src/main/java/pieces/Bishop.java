package pieces;

import java.util.ArrayList;
import java.util.List;

import chess.Move;
import defs.classes.Field;
import defs.classes.Piece;
import defs.enums.Colors;
import defs.enums.Ids;
import defs.interfaces.ILongDist;

public class Bishop extends Piece implements ILongDist{

	public Bishop(Colors col, Field field){
		super(Ids.Laeufer, col, field);
	}

	@Override
	public List<Field> createList(){
		List<Field> lst = new ArrayList<Field>();
		lst.add(this.getField());
		return lst;
	}
	
	@Override
	public List<Move> getPossibleMoves() {
		return convertFieldsToMoves(longDistCheck());
	}
	

	public void checkDirections(List<Field> lst,List<Field> lst1,List<Field> lst2,List<Field> lst3,List<Field> lst4) {
		int i = 1;
		while (getPosI() + i <= 7 && getPosJ() + i <= 7
				&& checkForValidity(getSpiel().getField( getPosI() + i, getPosJ() + i), lst1)) {
			lst.add(getSpiel().getField( getPosI() + i, getPosJ() + i));
			i += 1;
		}
		i = 1;
		while (getPosI() + i <= 7 && getPosJ() - i >= 0
				&& checkForValidity(getSpiel().getField(getPosI() + i, getPosJ() - i), lst2)) {
			lst.add(getSpiel().getField( getPosI() + i, getPosJ() - i));
			i += 1;
		}
		i = 1;
		while (getPosI() - i >= 0 && getPosJ() + i <= 7
				&& checkForValidity(getSpiel().getField(getPosI() - i, getPosJ() + i), lst3)) {
			lst.add(getSpiel().getField(getPosI() - i, getPosJ() + i));
			i += 1;
		}
		i = 1;
		while (getPosI() - i >= 0 && getPosJ() - i >= 0
				&& checkForValidity(getSpiel().getField( getPosI() - i, getPosJ() - i), lst4)) {
			lst.add(getSpiel().getField(getPosI() - i, getPosJ() - i));
			i += 1;
		}
	}

}
