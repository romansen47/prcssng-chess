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
	default void checkDirection(List<Field> lst, int[] direction) {
		final List<Field> tmpList = new ArrayList<>();
		tmpList.add(this.getField());
		int i = 1;
		while (((this.getPosI() + (i * direction[0])) >= 0)
				&& ((this.getPosI() + (i * direction[0])) <= Config.GAMESIZE)
				&& ((this.getPosJ() + (i * direction[1])) >= 0)
				&& ((this.getPosJ() + (i * direction[1])) <= Config.GAMESIZE)
				&& this.checkIfOccupiedByFriend(this.getGame().getField(this.getPosI() + (i * direction[0]),
						this.getPosJ() + (i * direction[1])), tmpList)) {
			i += 1;
		}
		tmpList.remove(0);
		lst.addAll(tmpList);
	}

	/**
	 *
	 * @param lst an empty list, which will be filled with possible moves.
	 */
	void checkDirections(List<Field> lst);

	@Override
	Field getField();

	@Override
	int getPosI();

	@Override
	int getPosJ();

	@Override
	default List<IMove> getSimpleMoves() {
		final List<IMove> tmp = this.convertFieldsToMoves(this.longDistCheck());
		tmp.remove(null);
		return tmp;
	}

	/**
	 * creates an empty list and fills it with valid moves along possible directions
	 *
	 * @return list of possible moves
	 */
	default List<Field> longDistCheck() {
		final List<Field> lst = this.createList();
		this.checkDirections(lst);
		lst.remove(this.getField());
		return lst;
	}

}
