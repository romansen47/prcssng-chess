package defs.classes;

import defs.enums.Colors;
import defs.enums.Ids;
import defs.enums.State;
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
public class Move implements IMove, IRefs {

	/**
	 * private attributes
	 */
	private final Colors col;
	private final Piece fig;
	private final Field next;
	private final Ids nextId;
	private final Field prev;
	private final Ids prevId;

	/**
	 * Constructor for a move
	 *
	 * @param fig the initial piece to move
	 * @param fld the field, fig is to be moved to
	 */
	public Move(Piece fig, Field fld) {
		this.fig = fig;
		this.col = fig.getCol();
		this.prev = fig.getField();
		this.prevId = fig.getId();
		this.next = fld;
		if (this.getNext().getPiece() != null) {
			this.nextId = this.getNext().getPiece().getId();
		} else {
			this.nextId = null;
		}
	}

	private boolean checkForChess() {
		return !this.getFig().getOpponent().getKing().getAttackers().isEmpty();
	}

	/**
	 * default implementation
	 */
	@Override
	public void execute() {
		final IPiece fig1 = this.fig;
		final IPiece fig2 = this.getNext().getPiece();
		this.getPrev().setPiece(null);
		fig1.setField(this.getNext());
		this.getNext().setPiece(fig1);
		if (fig2 != null) {
			getGame().getOpponent().getPieces().remove(fig2);
			getGame().getOpponent().getDeadPieces().add(fig2);
		}
		if (this.fig instanceof King) {
			((King) this.fig).setValidForCastling(false);
		}
		if (this.checkForChess()) {
			final King opKing = fig1.getOpponent().getKing();
			opKing.setState(State.CHESS);
		}
		this.getReferee().switchMainPlayer();
	}

	@Override
	public Colors getCol() {
		return this.col;
	}

	@Override
	public Piece getFig() {
		return this.fig;
	}

	@Override
	public Field getNext() {
		return this.next;
	}

	@Override
	public Ids getNextId() {
		return this.nextId;
	}

	@Override
	public Field getPrev() {
		return this.prev;
	}

	@Override
	public Ids getPrevId() {
		return this.prevId;
	}

	/**
	 * @return String in order to present the move
	 */
	@Override
	public String toString() {
		String str = "";
		str += this.getPrevId().toString().substring(0, 1);
		if (this.getNextId() != null) {
			str += this.getPrev().toChessNotation() + "x" + this.getNextId().toString().substring(0, 1)+this.getNext().toChessNotation();
		}
		else {
			str += this.getPrev().toChessNotation() + "-" + this.getNext().toChessNotation();
		}
		return str;
	}

}
