package pieces;

import java.util.ArrayList;
import java.util.List;

import defs.classes.Field;
import defs.classes.Move;
import defs.classes.Piece;
import defs.enums.Colors;
import defs.enums.Ids;
import defs.interfaces.ILongDist;
import defs.interfaces.IMove;

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
	
	public void checkDirections(List<Field> lst) {
			checkDirection(lst, new int[] {1,1});
			checkDirection(lst, new int[] {-1,1});
			checkDirection(lst, new int[] {1,-1});
			checkDirection(lst, new int[] {-1,-1});
	}

}
