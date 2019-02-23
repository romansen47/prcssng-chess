package conf;

import chess.Move;
import defs.classes.Field;
import defs.classes.Game;
import defs.interfaces.IRefs;

/**
 * 
 * @author roman
 *
 * Referee class, modelled as a singleton class. This class performs the main logical steps.
 */
public class Referee implements IRefs{

	/**
	 * The first field the referee recognizes as marked by the player
	 */
	private Field marked = null;
	

	/**
	 * The second field the referee recognizes as marked by the player
	 */
	private Field marked2 = null;

	protected static Referee instance = null;
	public static Referee getInstance() {
		if (instance == null) {
			instance = new Referee();
			return instance;
		}
		return instance;
	}
	private Referee() {
	}

	/**
	 * Getter for marked field
	 * @return the field that has been marked
	 */
	public Field getMarked() {
		return marked;
	}

	/**
	 * Setter for marked field
	 * @param marked the marked field
	 */
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

	/**
	 * Getter for second marked field
	 * @return the field that has been marked
	 */
	public Field getMarked2() {
		return marked2;
	}

	/**
	 * Setter for second marked field
	 * @param marked2 the marked field
	 */
	public void setMarked2(Field marked2) {
		if (marked2 != getMarked()) {
			this.marked2 = marked2;
		} else {
			setMarked(null);
		}
	}

	/**
	 * Checks whether a field has already been marked
	 * @return true, if a field has been marked
	 */
	public boolean isMarked() {
		if (getMarked() != null) {
			return true;
		}
		setMarked2(null);
		return false;
	}

	/**
	 * Checks whether two fields have already been marked
	 * @return true, if a second field has been marked
	 */
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

	/**
	 * Constructs a move and checks it for validity
	 * @return a move in case of validity. null otherwise
	 */
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

	/**
	 * After a move has been performed the players have to switch
	 */
	public void switchMainPlayer() {
		if (Game.getInstance().getPlayer() == Game.getInstance().getWhite()) {
			Game.getInstance().setPlayer(Game.getInstance().getBlack());
		} else {
			Game.getInstance().setPlayer(Game.getInstance().getWhite());
		}
	}

	/**
	 * adds the move to the movelists, prints the move and switches the active player
	 * 
	 * @param move the given move
	 */
	public void processMove(Move move) {
		if (move != null) {
			getSpiel().getMoveList().add(move);
			System.out.println((getSpiel().getMoveList()).toStr());
			move.execute();
		}
	}
	
}
