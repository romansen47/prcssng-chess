package defs.classes;

import defs.enums.Colors;
import defs.enums.Ids;
import defs.interfaces.IMove;
import defs.interfaces.IPiece;
import defs.interfaces.IRefs;

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
		System.out.println("");
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
	 * Execution of a move. Recalculates new possitions.
	 */
	@Override
	public void execute() {
		
		IPiece fig1 = getPrev().getPiece();
		IPiece fig2 = getNext().getPiece();
		getPrev().setPiece(null);
		fig1.setField(getNext());
		getNext().setPiece(fig1);
		if (fig2 != null) {
			getSpiel().getOtherPlayer().getPieces().remove(fig2);
			getSpiel().getOtherPlayer().getDeadPieces().add(fig2);
		}
		getReferee().setMarked(null);
		// getSpiel().getPlayer().getMoveList().add(this);

		getReferee().switchMainPlayer();
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
