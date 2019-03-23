package chess.pieces;

import java.util.ArrayList;
import java.util.List;

import defs.classes.Field;
import defs.enums.Colors;
import defs.enums.Ids;

public class Queen extends Piece implements ILongDist {

	/**
	 * Constructor
	 *
	 * @param col   the color
	 * @param field the field
	 */
	public Queen(Colors col, Field field) {
		super(Ids.QUEEN, col, field);
	}

	@Override
	public void checkDirections(List<Field> lst) {
		checkDirection(lst, new int[] { 1, 1 });
		checkDirection(lst, new int[] { -1, 1 });
		checkDirection(lst, new int[] { 1, -1 });
		checkDirection(lst, new int[] { -1, -1 });
		checkDirection(lst, new int[] { 1, 0 });
		checkDirection(lst, new int[] { -1, 0 });
		checkDirection(lst, new int[] { 0, 1 });
		checkDirection(lst, new int[] { 0, -1 });

	}

	@Override
	public List<Field> createList() {
		final List<Field> lst = new ArrayList<>();
		lst.add(getField());
		return lst;
	}

}
