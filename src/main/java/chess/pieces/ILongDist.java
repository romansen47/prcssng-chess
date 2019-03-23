package chess.pieces;

import java.util.ArrayList;
import java.util.List;

import chess.Config;
import chess.moves.IMove;
import defs.classes.Field;
import defs.interfaces.IValidityChecker;

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
		tmpList.add(getField());
		int i = 1;
		while (((getPosI() + (i * direction[0])) >= 0) && ((getPosI() + (i * direction[0])) <= Config.GAMESIZE)
				&& ((getPosJ() + (i * direction[1])) >= 0) && ((getPosJ() + (i * direction[1])) <= Config.GAMESIZE)
				&& checkIfOccupiedByFriend(
						getGame().getField(getPosI() + (i * direction[0]), getPosJ() + (i * direction[1])), tmpList)) {
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
		final List<IMove> tmp = convertFieldsToMoves(longDistCheck());
		tmp.remove(null);
		return tmp;
	}

	/**
	 * creates an empty list and fills it with valid moves along possible directions
	 *
	 * @return list of possible moves
	 */
	default List<Field> longDistCheck() {
		final List<Field> lst = createList();
		checkDirections(lst);
		lst.remove(getField());
		return lst;
	}

}
