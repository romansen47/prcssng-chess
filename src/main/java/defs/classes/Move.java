package defs.classes;

import chess.Game;
import defs.enums.BlackWhite;
import defs.enums.Ids;
import defs.interfaces.ISpiel;

public class Move implements ISpiel {

	final BlackWhite col;
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
		if (next.getFigur() != null) {
			nextId = next.getFigur().id;
		} else {
			nextId = null;
		}
	}

	public boolean isValid(Game game) {
		if (prev.getFigur().getPossibleMoves().contains(this.next)) {
			return true;
		}
		return false;
	}

	public void execute(Game game) {
		Piece fig1 = prev.getFigur();
		Piece fig2 = next.getFigur();
		prev.setFigur(null);
		fig1.setField(next);
		if (fig2 != null) {
			fig2.setField(null);
		}
		Game.getReferee().setMarked(null);
	}

}
