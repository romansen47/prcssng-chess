package chess.moves;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import chess.IRefs;
import chess.pieces.IPiece;
import chess.pieces.King;
import defs.classes.Field;
import defs.classes.Game;
import defs.enums.Ids;
import defs.enums.State;

/**
 *
 * @author roman
 *
 *         The main information about the game is saved to a sequence of "moves"
 */
@XmlRootElement
public class Move implements IMove, IRefs {

	/**
	 * private attributes
	 */

	/**
	 * the piece to be moved
	 */
	private final IPiece piece;

	/**
	 * the field the piece stands on before move has been executed
	 */
	@XmlElement
	private final Field prev;

	/**
	 * the field after execution
	 */
	@XmlElement
	private final Field next;

	/**
	 * chess notation
	 */
	private final String chessNot;

	/**
	 * plain constructor
	 */
	public Move() {
		prev = null;
		next = null;
		piece = null;
		chessNot = null;
	}

	/**
	 * Concrete constructor for a move
	 *
	 * @param prev the initial piece to move
	 * @param next the field, fig is to be moved to
	 */
	public Move(Field prev, Field next) {
		piece = prev.getPiece();
		this.prev = prev;
		this.next = next;
		chessNot = getString();
	}

	/**
	 * check if the move is a check for opponent's king
	 *
	 * @return true if opponent's king is not chessed
	 */
	private boolean checkForChess() {
		return !next.getPiece().getOpponent().getKing().getAttackers().isEmpty();
	}

	/**
	 * default implementation of the execution of a simple move. to be extended for
	 * castling, en passant and promotion
	 */
	@Override
	public void execute() {
		final IPiece fig1 = prev.getPiece();
		if (fig1 == null) {
			return;
		}
		final IPiece fig2 = next.getPiece();
		prev.setPiece(null);
		fig1.setField(getNext());
		getNext().setPiece(fig1);
		if (fig2 != null) {
			Game.getInstance().getOpponent().getPieces().remove(fig2);
		}
//		if (fig1 instanceof King) {
//			((King) fig1).setValidForCastling(false);
//		}
		if (checkForChess()) {
			final King opKing = fig1.getOpponent().getKing();
			opKing.setState(State.CHESS);
		}
		getGame().getMoveList().add(this);
		getReferee().switchMainPlayer();
	}

	@Override
	public Field getNext() {
		return next;
	}

	@Override
	public Field getPrev() {
		return prev;
	}

	/**
	 * @return String in order to present the move in chess notation
	 */
	public String getString() {
		String str = "";
		if (getPiece() != null && getPiece().getId() == Ids.KNIGHT) {
			str += getPiece().getId().toString().substring(1, 2);
		} else {
			str += getPiece().getId().toString().substring(0, 1);
		}
		if (next.getPiece() != null) {
			str += getPrev().toChessNotation() + "x" + next.getPiece().getId().toString().substring(0, 1)
					+ getNext().toChessNotation();
		} else {
			str += getPrev().toChessNotation() + "-" + getNext().toChessNotation();
		}
		return str;
	}

	/**
	 * getter for chess notation
	 *
	 * @return the chessNot
	 */
	@Override
	public String toString() {
		return chessNot;
	}

	/**
	 * getter for the piece
	 *
	 * @return the piece
	 */
	@Override
	public IPiece getPiece() {
		return piece;
	}

}
