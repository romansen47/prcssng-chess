package defs.classes;

import chess.Game;
import defs.enums.Colors;
import defs.enums.Ids;
import defs.interfaces.IGame;

public class Move implements IGame {

	final Colors col;
	final Field prev;
	final private Ids prevId;
	final Field next;
	final private Ids nextId;

	public Ids getPrevId() {
		return prevId;
	}

	public Ids getNextId() {
		return nextId;
	}

	@Override
	public String toString() {
		String str = "";
		// str+=super.toString()+": ";
		str += prevId;
		if (nextId != null) {
			str += " -> " + nextId + ":  ";
		}
		str += "(" + prev.toChessNotation() + ":" + next.toChessNotation() + ")";
		System.out.println("");
		return str;
	}

	public Move(Piece fig, Field fld) {
		col = fig.getCol();
		prev = fig.getField();
		prevId = fig.id;
		next = fld;
		if (next.getPiece() != null) {
			nextId = next.getPiece().id;
		} else {
			nextId = null;
		}
	}

	public boolean isValid(Game game) {
		if (prev.getPiece().getPossibleMoves().contains(this.next)) {
			return true;
		}
		return false;
	}

	public void execute(Game game) {
		Piece fig1 = prev.getPiece();
		Piece fig2 = next.getPiece();
		prev.setPiece(null);
		fig1.setField(next);
		next.setPiece(fig1);
		if (fig2 != null) {
			getSpiel().getOtherPlayer().getPieces().remove(fig2);
			getSpiel().getOtherPlayer().getDeadPieces().add(fig2);
		}
		Game.getReferee().setMarked(null);
	}

}
