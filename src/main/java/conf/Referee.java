package conf;

import java.util.ArrayList;
import java.util.List;

import defs.classes.Field;
import defs.classes.Game;
import defs.interfaces.IMove;
import defs.interfaces.IPiece;
import defs.interfaces.IRefs;

/**
 * 
 * @author Ro Mansen
 *
 *         Referee class, modelled as a singleton class. This class performs the
 *         main logical steps.
 */
public class Referee implements IRefs {

	/**
	 * static instance of Referee
	 */
	protected static Referee instance = null;

	/**
	 * Getter for instance
	 * 
	 * @return static instance
	 */
	public static Referee getInstance() {
		if (instance == null) {
			instance = new Referee();
			return instance;
		}
		return instance;
	}

	/**
	 * The first field the referee recognizes as marked by the player
	 */
	private Field marked = null;

	/**
	 * The second field the referee recognizes as marked by the player
	 */
	private Field marked2 = null;

	/**
	 * private constructor
	 */
	private Referee() {
	}

	/**
	 * Validity check
	 * 
	 * @param move the move to check for validity
	 * @return true if move is valid, false otherwise
	 */
	private boolean checkForValidity(IMove move) {
		if (move == null) {
			return false;
		}
		boolean ans = true;
		getGame().getMoveList().add(move);
		getGame().getPlayer().getMoveList().add(move);
		move.execute();
		for (IPiece piece : move.getFig().getOpponent().getPieces()) {
			if (piece.getPossibleFields().contains(move.getFig().getOwner().getKing().getField())) {
				ans = false;
			}
		}
		rewindLastMove();
		setMarked(move.getPrev());
		return ans;
	}

	/**
	 * Getter for marked field
	 * 
	 * @return the field that has been marked
	 */
	public Field getMarked() {
		return marked;
	}

	/**
	 * Getter for second marked field
	 * 
	 * @return the field that has been marked
	 */
	public Field getMarked2() {
		return marked2;
	}

	/**
	 * Constructs a move and checks for validity
	 * 
	 * @return a move in case of validity. null otherwise
	 */
	public IMove getMove() {
		if (this.isMarked2() && getMarked().getPiece() != null) {
			IPiece piece = getMarked().getPiece();
			if (piece.convertMovesToFields(piece.getPossibleMoves()).contains(getMarked2())) {
				IMove move = getMarked().getPiece().getMove(getMarked2());
				setMarked2(null);
				setMarked(null);
				return move;
			}
		}
		return null;
	}

	/**
	 * Checks whether move is valid
	 * 
	 * @param move the move to check
	 * @return the move in case of validity, null otherwise
	 */
	public IMove getValidMove(IMove move) {
		if (checkForValidity(move)) {
			return move;
		}
		return null;
	}

	/**
	 * Checks a list of moves for valid moves
	 * 
	 * @param moves list of yet unchecked moves
	 * @return list of valid moves contained in the list
	 */
	public List<IMove> getValidMoves(List<IMove> moves) {
		List<IMove> ans = new ArrayList<>();
		for (IMove move : moves) {
			if (checkForValidity(move)) {
				ans.add(move);
			}
		}
		return ans;
	}

	/**
	 * Checks whether a field has already been marked
	 * 
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
	 * 
	 * @return true, if a second field has been marked
	 */
	public boolean isMarked2() {
		if (getMarked() != null) {
			return (getMarked2() != null);
		}
		setMarked2(null);
		return false;
	}

	/**
	 * adds the move to the movelists, prints the move and switches the active
	 * player
	 * 
	 * @param move the given move
	 */
	public void processMove(IMove move) {
		if (getValidMove(move) != null) {
			getGame().getMoveList().add(move);
			getGame().getPlayer().getMoveList().add(move);
			/**
			 * Print move: System.out.println((getGame().getMoveList()).toStr());
			 */
			move.execute();
		}
	}

	/**
	 * Resets the pieces to their fields in the beginning of a match
	 */
	private void resetPieces() {
		for (IPiece piece : getGame().getWhite().getAllPieces()) {
			piece.reset();
		}
		for (IPiece piece : getGame().getBlack().getAllPieces()) {
			piece.reset();
		}
	}

	/**
	 * Resets the game and execute all moves, but not the last one.
	 */
	public void rewindLastMove() {
		getGame().setPlayer(getGame().getWhite());
		Timeline tl = getGame().getMoveList();
		resetPieces();
		int last = tl.size() - 1;
		if (last >= 0) {
			tl.remove(last);
			for (IMove move : tl) {
				move.execute();
			}
		}
	}

	/**
	 * Setter for marked field
	 * 
	 * @param marked the marked field
	 */
	public void setMarked(Field marked) {
		if (marked == null || marked.getPiece() == null) {
			this.marked = null;
		} else {
			if (marked.getPiece().getCol() == Game.getInstance().getPlayer().getCol()) {
				this.marked = marked;
			}
		}
	}

	/**
	 * Setter for second marked field
	 * 
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
	 * After a move has been performed the players have to switch
	 */
	public void switchMainPlayer() {
		if (Game.getInstance().getPlayer() == Game.getInstance().getWhite()) {
			Game.getInstance().setPlayer(Game.getInstance().getBlack());
		} else {
			Game.getInstance().setPlayer(Game.getInstance().getWhite());
		}
	}

}
