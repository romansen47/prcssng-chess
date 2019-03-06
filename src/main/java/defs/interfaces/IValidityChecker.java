package defs.interfaces;

import java.util.ArrayList;
import java.util.List;

import defs.classes.Field;
import pieces.King;

public interface IValidityChecker extends IColors {

	/**
	 * 
	 * @param fld the field to check for validity
	 * @param lst the list of valid moves the converted field will be added to in
	 *            case for validity
	 * @return true, if valid, false otherwise
	 */
	default boolean checkIfOccupiedByFriend(Field fld, List<Field> lst) {
		boolean ans = false;
		Field last = lst.get(lst.size() - 1);
		if ((last.getPiece() == null || last.getPiece() == this) && fld.getPiece() == null)
			ans = true;
		if ((last.getPiece() == null || last.getPiece() == this) && fld.getPiece() != null
				&& fld.getPiece().getCol() != getCol())
			ans = true;
		if (ans) {
			lst.add(fld);
		}
		return ans;
	}

	/**
	 * Checks move for validity.
	 * 
	 * @param move the move to check
	 * @return the move, if after the move the own king is not being chessed by any
	 *         opponent piece. castling of the opponent is not checked as a threat!.
	 */
	default IMove getValidMove(IMove move) {
		if (move.getNext().getPiece() == move.getFig()) {
			return move;
		}
		IPiece deadPiece = move.getNext().getPiece();
		move.getFig().setField(move.getNext());
		move.getNext().setPiece(move.getFig());
		move.getFig().getOpponent().getPieces().remove(deadPiece);
		move.getNext().setPiece(null);
		boolean ans = false;
		List<Field> list = new ArrayList<>();
		for (IPiece piece : move.getFig().getOpponent().getPieces()) {
			if (!(piece instanceof King)) {
				list.addAll(piece.getPossibleFields());
			}
		}
		if (!list.contains(move.getFig().getOwner().getKing().getField())) {
			ans = true;
		}
		move.getFig().setField(move.getPrev());
		move.getPrev().setPiece(move.getFig());
		move.getNext().setPiece(deadPiece);
		deadPiece.setField(move.getNext());
		move.getFig().getOpponent().getPieces().add(deadPiece);
		if (ans) {
			return move;
		}
		return null;
	}

	/**
	 * Checks a list of moves for valid moves
	 * 
	 * @param moves list of moves to ckeck for validity
	 * @return the list with valid moves
	 */
	default List<IMove> getValidMoves(List<IMove> moves) {
		List<IMove> validMoves = new ArrayList<>();
		for (IMove move : moves) {
			validMoves.add(getValidMove(move));
		}
		return validMoves;
	}

}
