package defs.classes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import defs.enums.Ids;
import defs.interfaces.IMove;
import defs.interfaces.IPiece;
import defs.interfaces.IRefs;
import pieces.King;

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
	private final IPiece fig;

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
		fig = null;
		chessNot = null;
	}

	/**
	 * Concrete constructor for a move
	 *
	 * @param prev the initial piece to move
	 * @param next the field, fig is to be moved to
	 */
	public Move(Field prev, Field next) {
		this.fig = prev.getPiece();
		this.prev = prev;
		this.next = next;
		chessNot = this.getString();
	}

	/**
	 * check if the move is a check for opponent's king
	 * 
	 * @return true if opponent's king is not chessed
	 */
	private boolean checkForChess() {
		return !this.next.getPiece().getOpponent().getKing().getAttackers().isEmpty();
	}

	/**
	 * default implementation
	 */
	@Override
	public void execute() {
		final IPiece fig1 = prev.getPiece();
		if (fig1 == null) {
			return;
		}
		final IPiece fig2 = next.getPiece();
		prev.setPiece(null);
		fig1.setField(this.getNext());
		this.getNext().setPiece(fig1);
		if (fig2 != null) {
			Game.getOpponent().getPieces().remove(fig2);
		}
		if (fig1 instanceof King) {
			((King) fig1).setValidForCastling(false);
		}
		if (this.checkForChess()) {
			final King opKing = fig1.getOpponent().getKing();
			// opKing.setState(State.CHESS);
		}
		getGame().getMoveList().add(this);
		this.getReferee().switchMainPlayer();
	}

	@Override
	public Field getNext() {
		return this.next;
	}

	@Override
	public Field getPrev() {
		return this.prev;
	}

	/**
	 * @return String in order to present the move
	 */
	public String getString() {
		String str = "";
		if (getFig() != null && getFig().getId() == Ids.KNIGHT) {
			str += getFig().getId().toString().substring(1, 2);
		} else {
			str += this.getFig().getId().toString().substring(0, 1);
		}
		if (this.next.getPiece() != null) {
			str += this.getPrev().toChessNotation() + "x" + this.next.getPiece().getId().toString().substring(0, 1)
					+ this.getNext().toChessNotation();
		} else {
			str += this.getPrev().toChessNotation() + "-" + this.getNext().toChessNotation();
		}
		return str;
	}

	/**
	 * @return the chessNot
	 */
	@Override
	public String toString() {
		return chessNot;
	}

	/**
	 * @return the fig
	 */
	@Override
	public IPiece getFig() {
		return fig;
	}

}
