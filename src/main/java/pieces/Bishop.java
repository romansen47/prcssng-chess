package pieces;

import java.util.ArrayList;
import java.util.List;

import defs.classes.Field;
import defs.classes.Piece;
import defs.enums.Colors;
import defs.enums.Ids;
import defs.interfaces.ILongDist;

public class Bishop extends Piece implements ILongDist{

	public Bishop(Colors col, Field field){
		super(Ids.BISHOP, col, field);
	}

	@Override
	public List<Field> createList(){
		List<Field> lst = new ArrayList<Field>();
		lst.add(this.getField());
		return lst;
	}
	
	@Override
	public void checkDirections(List<Field> lst) {
			checkDirection(lst, new int[] {1,1});
			checkDirection(lst, new int[] {-1,1});
			checkDirection(lst, new int[] {1,-1});
			checkDirection(lst, new int[] {-1,-1});
	}

}
