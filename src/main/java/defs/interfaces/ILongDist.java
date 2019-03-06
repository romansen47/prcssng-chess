package defs.interfaces;

import java.util.ArrayList;
import java.util.List;

import conf.Config;
import defs.classes.Field;

/**
 * 
 * @author RoMansen
 * 
 *         the pieces queen, rook and bishop need specal checks!
 *
 */
public interface ILongDist extends IPiece, IValidityChecker {

	/**
	 * method to check ONE direction, e.g. south
	 * 
	 * @param lst       the list to be filled with possible moves
	 * @param direction the direction to check
	 */
	public default void checkDirection(List<Field> lst, int[] direction) {
		List<Field> tmpList = new ArrayList<>();
		tmpList.add(getField());
		int i = 1;
		while (getPosI() + i * direction[0] >= 0 && getPosI() + i * direction[0] <= Config.GAMESIZE
				&& getPosJ() + i * direction[1] >= 0 && getPosJ() + i * direction[1] <= Config.GAMESIZE
				&& checkIfOccupiedByFriend(
						getGame().getField(getPosI() + i * direction[0], getPosJ() + i * direction[1]), tmpList)) {
			i += 1;
		}
		tmpList.remove(0);
		lst.addAll(tmpList);
	}

	/**
	 * 
	 * @param lst an empty list, which will be filled with possible moves.
	 */
	public void checkDirections(List<Field> lst);

	@Override
	public Field getField();

	@Override
	int getPosI();

	@Override
	int getPosJ();

	@Override
	default List<IMove> getPossibleMoves() {
		List<IMove> tmp = convertFieldsToMoves(longDistCheck());
		tmp.remove(null);
		return tmp;
	}

	/**
	 * creates an empty list and fills it with valid moves along possible directions
	 * 
	 * @return
	 */
	default List<Field> longDistCheck() {
		List<Field> lst = createList();
		checkDirections(lst);
		lst.remove(getField());
		return lst;
	}

}
