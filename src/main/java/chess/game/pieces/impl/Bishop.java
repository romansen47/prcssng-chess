package chess.game.pieces.impl;

import java.util.ArrayList;
import java.util.List;

import chess.game.pieces.ILongDist;
import defs.classes.Field;
import defs.enums.Colors;
import defs.enums.Ids;

public class Bishop extends Piece implements ILongDist {

	/**
	 * Constructor
	 *
	 * @param col   the color of the bishop
	 * @param field the field of the bishop
	 */
	public Bishop(Colors col, Field field) {
		super(Ids.BISHOP, col, field, 70);
	}

	@Override
	public void checkDirections(List<Field> lst) {
		checkDirection(lst, new int[] { 1, 1 });
		checkDirection(lst, new int[] { -1, 1 });
		checkDirection(lst, new int[] { 1, -1 });
		checkDirection(lst, new int[] { -1, -1 });
	}

	@Override
	public List<Field> createList() {
		final List<Field> lst = new ArrayList<>();
		lst.add(getField());
		return lst;
	}

}
