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
		if (Referee.instance == null) {
			Referee.instance = new Referee();
			return Referee.instance;
		}
		return Referee.instance;
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
	 * Getter for marked field
	 * 
	 * @return the field that has been marked
	 */
	public Field getMarked() {
		return this.marked;
	}

	/**
	 * Getter for second marked field
	 * 
	 * @return the field that has been marked
	 */
	public Field getMarked2() {
		return this.marked2;
	}

	/**
	 * Constructs a move and checks for validity
	 * 
	 * @return a move in case of validity. null otherwise
	 */
	public IMove getMove() {
		if (this.isMarked2() && this.getMarked().getPiece() != null) {
			final IPiece piece = this.getMarked().getPiece();
			if (piece.convertMovesToFields(piece.getPossibleMoves()).contains(this.getMarked2())) {
				final IMove move = this.getMarked().getPiece().getMove(this.getMarked2());
				this.setMarked2(null);
				this.setMarked(null);
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
		if (this.checkForValidity(move)) {
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
		final List<IMove> ans = new ArrayList<>();
		for (final IMove move : moves) {
			if (this.checkForValidity(move)) {
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
		if (this.getMarked() != null) {
			return true;
		}
		this.setMarked2(null);
		return false;
	}

	/**
	 * Checks whether two fields have already been marked
	 * 
	 * @return true, if a second field has been marked
	 */
	public boolean isMarked2() {
		if (this.getMarked() != null) {
			return (this.getMarked2() != null);
		}
		this.setMarked2(null);
		return false;
	}

	/**
	 * adds the move to the movelists, prints the move and switches the active
	 * player
	 * 
	 * @param move the given move
	 */
	public void processMove(IMove move) {
		if (this.getValidMove(move) != null) {
			this.getGame().getMoveList().add(move);
			this.getGame().getPlayer().getMoveList().add(move);
			/**
			 * Print move: System.out.println((getGame().getMoveList()).toStr());
			 */
			move.execute();
			this.getReferee().setMarked(null);
		}
	}

	/**
	 * Resets the game and execute all moves, but not the last one.
	 */
	public void rewindLastMove() {
		this.getGame().setPlayer(this.getGame().getWhite());
		final Timeline tl = this.getGame().getMoveList();
		this.resetPieces();
		final int last = tl.size() - 1;
		if (last >= 0) {
			tl.remove(last);
			for (final IMove move : tl) {
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
			this.marked2 = null;
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
		if (marked2 != this.getMarked()) {
			this.marked2 = marked2;
		} else {
			this.setMarked(null);
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
		this.getGame().getMoveList().add(move);
		this.getGame().getPlayer().getMoveList().add(move);
		move.execute();
		for (final IPiece piece : move.getFig().getOpponent().getPieces()) {
			if (piece.getPossibleFields().contains(move.getFig().getOwner().getKing().getField())) {
				ans = false;
			}
		}
		this.rewindLastMove();
		this.setMarked(move.getPrev());
		return ans;
	}

	/**
	 * Resets the pieces to their fields in the beginning of a match
	 */
	private void resetPieces() {
		for (final IPiece piece : this.getGame().getWhite().getAllPieces()) {
			piece.reset();
		}
		for (final IPiece piece : this.getGame().getBlack().getAllPieces()) {
			piece.reset();
		}
	}

}
