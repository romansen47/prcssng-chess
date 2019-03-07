package pieces;

import java.util.ArrayList;
import java.util.List;

import defs.classes.Field;
import defs.classes.Piece;
import defs.enums.Colors;
import defs.enums.Ids;
import defs.interfaces.ILongDist;

public class Bishop extends Piece implements ILongDist {

	/**
	 * Constructor
	 * 
	 * @param col   the color of the bishop
	 * @param field the field of the bishop
	 */
	public Bishop(Colors col, Field field) {
		super(Ids.BISHOP, col, field);
	}

	@Override
	public void checkDirections(List<Field> lst) {
		this.checkDirection(lst, new int[] { 1, 1 });
		this.checkDirection(lst, new int[] { -1, 1 });
		this.checkDirection(lst, new int[] { 1, -1 });
		this.checkDirection(lst, new int[] { -1, -1 });
	}

	@Override
	public List<Field> createList() {
		final List<Field> lst = new ArrayList<>();
		lst.add(this.getField());
		return lst;
	}

}
