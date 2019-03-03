package defs.classes;

import java.util.ArrayList;
import java.util.List;

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
 * The main information about the game is saved to a sequence of "moves"
 */
public class Move implements IMove,IRefs {

	private final Colors col;
	private final Piece fig;
	private final Field prev;
	private final Ids prevId;
	private final Field next;
	private final Ids nextId;

	/**
	 * @return String in order to present the move
	 */
	@Override
	public String toString() {
		String str = "";
		// str+=super.toString()+": ";
		str += getPrevId();
		if (getNextId() != null) {
			str += " -> " + getNextId() + ":  ";
		}
		str += "(" + getPrev().toChessNotation() + ":" + getNext().toChessNotation() + ")";
		return str;
	}

	/**
	 * Constructor for a move
	 * 
	 * @param fig the initial piece to move
	 * @param fld the field, fig is to be moved to
	 */
	public Move(Piece fig, Field fld) {
		this.fig=fig;
		col = fig.getCol();
		prev = fig.getField();
		prevId = fig.getId();
		next = fld;
		if (getNext().getPiece() != null) {
			nextId = getNext().getPiece().getId();
		} else {
			nextId = null;
		}
	}
	
	/**
	 * Execution of a move. Recalculates new positions.
	 */
	@Override
	public void execute() {
		IPiece fig1 = fig;
		IPiece fig2 = getNext().getPiece();
		getPrev().setPiece(null);
		fig1.setField(getNext());
		getNext().setPiece(fig1);
		if (fig2 != null) {
			getGame().getOtherPlayer().getPieces().remove(fig2);
			getGame().getOtherPlayer().getDeadPieces().add(fig2);
		}
		getReferee().setMarked(null);
		if (fig instanceof King) {
			((King) fig).setValidForCastling(false);
		}
		if (checkForChess()) {
			King opKing=fig1.getOpponent().getKing();
			opKing.setState(State.Chess);
		}
		getReferee().switchMainPlayer();
	}

	private boolean checkForChess() {
		return !getFig().getOpponent().getKing().getAttackers().isEmpty();
	}

	@Override
	public Colors getCol() {
		return col;
	}

	@Override
	public Piece getFig() {
		return fig;
	}

	@Override
	public Field getPrev() {
		return prev;
	}

	@Override
	public Ids getPrevId() {
		return prevId;
	}

	@Override
	public Field getNext() {
		return next;
	}

	@Override
	public Ids getNextId() {
		return nextId;
	}

}
