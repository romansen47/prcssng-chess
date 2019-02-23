package conf;

import chess.Move;
import defs.classes.Field;
import defs.classes.Game;
import defs.enums.Ids;
import defs.interfaces.IRefs;

public class Referee implements IRefs{

	protected static Referee instance = null;

	private Field marked = null;
	private Field marked2 = null;

	public static Referee getInstance() {
		if (instance == null) {
			instance = new Referee();
			return instance;
		}
		return instance;
	}

	private Referee() {
	}

	public Field getMarked() {
		return marked;
	}

	public void setMarked(Field marked) {
		if (marked == null || marked.getPiece() == null) {
			this.marked = null;
			return;
		} else {
			if (marked.getPiece().getCol() == Game.getInstance().getPlayer().getCol()) {
				this.marked = marked;
			}
		}
	}

	public Field getMarked2() {
		return marked2;
	}

	public void setMarked2(Field marked2) {
		if (marked2 != getMarked()) {
			this.marked2 = marked2;
		} else {
			setMarked(null);
		}
	}

	public boolean isMarked() {
		if (getMarked() != null) {
			return true;
		}
		setMarked2(null);
		return false;
	}

	public boolean isMarked2() {
		if (getMarked() != null) {
			if (getMarked2() != null) {
				return true;
			} else {
				return false;
			}
		}
		setMarked2(null);
		return false;
	}

	public Move getMove() {
		// TODO: auf "marked.getFigur()!=null" verzichten, statt dessen ueber die Setter
		// managen.
		if (!this.isMarked2() || getMarked().getPiece() == null) {
			return null;
		} else {
			if (getMarked().getPiece().getPossibleMoves().contains(getMarked2())) {
				Move move = getMarked().getPiece().getMove(getMarked2());
				setMarked2(null);
				setMarked(null);
				return move;
			}
			return null;
		}
	}

	public void switchMainPlayer() {
		if (Game.getInstance().getPlayer() == Game.getInstance().getWhite()) {
			Game.getInstance().setPlayer(Game.getInstance().getBlack());
		} else {
			Game.getInstance().setPlayer(Game.getInstance().getWhite());
		}
	}

	public void processMove(Move move) {
		if (move != null) {
			getSpiel().getZugListe().add(move);
			System.out.println((getSpiel().getZugListe()).toStr());
			move.execute(getSpiel());
		}
	}
	
	// TODO
	public void checkForChess() {
		
	}
	
	// TODO
	public void switchState() {
		
	}
}