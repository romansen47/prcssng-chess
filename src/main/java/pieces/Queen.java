package pieces;

import java.util.List;

import defs.classes.Field;
import defs.classes.Piece;
import defs.enums.Colors;
import defs.enums.Ids;
import defs.interfaces.ILongDist;

public class Queen extends Piece implements ILongDist{

	public Queen(Colors col, Field field){
		super(Ids.Queen, col, field);
	}

	@Override
	public void checkDirections(List<Field> lst) {
		
		checkDirection(lst, new int[] {1,1});
		checkDirection(lst, new int[] {-1,1});
		checkDirection(lst, new int[] {1,-1});
		checkDirection(lst, new int[] {-1,-1});

		checkDirection(lst, new int[] {1,0});
		checkDirection(lst, new int[] {-1,0});
		checkDirection(lst, new int[] {0,1});
		checkDirection(lst, new int[] {0,-1});
		
	}

}
