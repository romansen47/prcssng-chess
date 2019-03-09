package conf;

import java.util.ArrayList;
import java.util.List;

import artint.RandomPlayer;
import defs.classes.Field;
import defs.classes.Game;
import defs.enums.State;
import defs.interfaces.IMove;
import defs.interfaces.IPiece;
import defs.interfaces.IRefs;
import pieces.King;

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
		Game.getPlayer().getMoveList().add(move);
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
	 * Check what situation we have after this move
	 */
	public void checkState() {
		King king = Game.getPlayer().getKing();
		List<IMove> moves = king.getAllMovesToAvoidChess();
		if (moves.isEmpty()) {
			if (king.getState() == State.REGULAR) {
				king.setState(State.MATE);
			} else {
				king.setState(State.STALEMATE);
			}
		}
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
		if(Game.getPlayer() instanceof RandomPlayer) {
			return ((RandomPlayer)Game.getPlayer()).randomMove();
		}
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
			getGame().getMoveList().add(move);
			Game.getPlayer().getMoveList().add(move);
			move.execute();
			this.getReferee().setMarked(null);
		}
	}

	/**
	 * Resets the pieces to their fields in the beginning of a match
	 */
	private void resetPieces() {
		for (final IPiece piece : Game.getWhite().getAllPieces()) {
			piece.reset();
		}
		for (final IPiece piece : Game.getBlack().getAllPieces()) {
			piece.reset();
		}
	}

	/**
	 * Resets the game and execute all moves, but not the last one.
	 */
	public void rewindLastMove() {
		Game.setPlayer(Game.getWhite());
		final Timeline timeLine = getGame().getMoveList();
		this.resetPieces();
		final int last = timeLine.size() - 1;
		if (last >= 0) {
			timeLine.remove(last);
			for (final IMove move : timeLine) {
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
			Game.getInstance();
			if (marked.getPiece().getCol() == Game.getPlayer().getCol()) {
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
		Game.getInstance();
		if (Game.getPlayer() == Game.getWhite()) {
			Game.getInstance();
			Game.setPlayer(Game.getBlack());
		} else {
			Game.setPlayer(Game.getWhite());
		}
	}

}
