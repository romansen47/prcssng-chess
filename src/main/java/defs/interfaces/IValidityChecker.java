package defs.interfaces;

import java.util.ArrayList;
import java.util.List;

import chess.moves.IMove;
import chess.pieces.IPiece;
import chess.pieces.King;
import defs.classes.Field;

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
		final Field last = lst.get(lst.size() - 1);
		if (((last.getPiece() == null) || (last.getPiece() == this)) && (fld.getPiece() == null)) {
			ans = true;
		}
		if (((last.getPiece() == null) || (last.getPiece() == this)) && (fld.getPiece() != null)
				&& (fld.getPiece().getCol() != this.getCol())) {
			ans = true;
		}
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
		if (move.getNext().getPiece() == move.getPrev().getPiece()) {
			return move;
		}
		final IPiece deadPiece = move.getNext().getPiece();
		move.getPrev().getPiece().setField(move.getNext());
		move.getNext().setPiece(move.getPrev().getPiece());
		move.getPrev().getPiece().getOpponent().getPieces().remove(deadPiece);
		move.getNext().setPiece(null);
		boolean ans = false;
		final List<Field> list = new ArrayList<>();
		for (final IPiece piece : move.getPrev().getPiece().getOpponent().getPieces()) {
			if (!(piece instanceof King)) {
				list.addAll(piece.getPossibleFields());
			}
		}
		if (!list.contains(move.getPrev().getPiece().getOwner().getKing().getField())) {
			ans = true;
		}
		move.getPrev().getPiece().setField(move.getPrev());
		move.getPrev().setPiece(move.getPrev().getPiece());
		move.getNext().setPiece(deadPiece);
		deadPiece.setField(move.getNext());
		move.getPrev().getPiece().getOpponent().getPieces().add(deadPiece);
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
		final List<IMove> validMoves = new ArrayList<>();
		for (final IMove move : moves) {
			validMoves.add(this.getValidMove(move));
		}
		return validMoves;
	}

}
