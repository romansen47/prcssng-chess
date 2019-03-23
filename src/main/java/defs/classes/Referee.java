package defs.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.IRefs;
import chess.moves.IMove;
import chess.moves.Timeline;
import chess.pieces.IPiece;
import chess.pieces.King;
import defs.enums.State;
import defs.players.artint.RandomPlayer;

/**
 *
 * @author Ro Mansen
 *
 *         Referee class, modelled as a singleton class. This class performs the
 *         main logical steps.
 */
public final class Referee implements IRefs {

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
		final King king = Game.getPlayer().getKing();
		move.execute();
		ans = !king.isChecked();
		rewindLastMove();
		// this.setMarked(move.getPrev());
		return ans;
	}

	/**
	 * Check what situation we have after this move
	 */
	public void checkState() {
		final King king = Game.getPlayer().getKing();
		final List<IMove> moves = king.getAllMovesToAvoidChess();
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
	 * @param allPossibleMoves takes an element from possibleMovesMap
	 *
	 * @return a move in case of validity. null otherwise
	 */
	public IMove getMove(Map<IPiece, List<IMove>> allPossibleMoves) {
		if (Game.getPlayer() instanceof RandomPlayer) {
			return ((RandomPlayer) Game.getPlayer()).randomMove();
		}
		if (isMarked2() && (getMarked().getPiece() != null)) {
			final IPiece piece = getMarked().getPiece();
			final List<IMove> moves = allPossibleMoves.get(piece);
			final List<Field> lst = new ArrayList<>();
			for (final IMove move : moves) {
				lst.add(move.getNext());
			}
			if (lst.contains(getMarked2())) {
				return getMarked().getPiece().getMove(getMarked2());
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
		final List<IMove> ans = new ArrayList<>();
		for (final IMove move : moves) {
			final IMove mv = getValidMove(move);
			if (mv != null) {
				ans.add(mv);
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
		// this.setMarked2(null);
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
			move.execute();
			getReferee().setMarked(null);
		}
	}

	/**
	 * Resets the pieces to their fields in the beginning of a match
	 */
	private void resetPieces() {
		Game.getPlayer().getPieces().clear();
		Game.getOpponent().getPieces().clear();
		Game.getPlayer().getDeadPieces().clear();
		Game.getOpponent().getDeadPieces().clear();
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
		final Timeline timeLine = (Timeline) getGame().getMoveList().clone();
		reset();
		timeLine.remove(timeLine.size() - 1);
		rePlayGame(timeLine);
	}

	public void rePlayGame(Timeline timeLine) {
		reset();
		for (final IMove move : timeLine) {
			move.execute();
		}
	}

	private void resetFields() {
		for (final Field[] row : getGame().getChessboard()) {
			for (final Field fld : row) {
				fld.reset();
			}
		}
	}

	public void reset() {
		resetFields();
		resetPieces();
		getGame().getMoveList().clear();

	}

	/**
	 * Setter for marked field
	 *
	 * @param marked the marked field
	 */
	public void setMarked(Field marked) {
		if ((marked == null) || (marked.getPiece() == null)) {
			this.marked = null;
			marked2 = null;
		} else {
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
		Game.getInstance();
		if (Game.getPlayer() == Game.getWhite()) {
			Game.getInstance();
			Game.setPlayer(Game.getBlack());
		} else {
			Game.setPlayer(Game.getWhite());
		}
	}

	public Map<IPiece, List<IMove>> createPossibleMovesForActivePieces() {
		final Map<IPiece, List<IMove>> possibleMovesMap = new HashMap<IPiece, List<IMove>>();
		for (final IPiece piece : Game.getPlayer().getPieces()) {
			possibleMovesMap.put(piece, piece.getPossibleMoves());
		}
		return possibleMovesMap;
	}

	public Map<IPiece, List<IMove>> createPossibleValidMovesForActivePieces() {
		final Map<IPiece, List<IMove>> validMoves = new HashMap<>();
		final Map<IPiece, List<IMove>> possibleMoves = createPossibleMovesForActivePieces();
		for (final IPiece piece : possibleMoves.keySet()) {
			validMoves.put(piece, getValidMoves(possibleMoves.get(piece)));
		}
		return validMoves;
	}

	public Map<IPiece, List<IMove>> createAttackersOnActivePieces() {
		final Map<IPiece, List<IMove>> possibleMovesMap = new HashMap<IPiece, List<IMove>>();
		for (final IPiece piece : Game.getPlayer().getPieces()) {
			possibleMovesMap.put(piece, piece.convertFieldsToMoves(piece.getAttackers()));
		}
		return possibleMovesMap;
	}

	public Map<IPiece, List<IMove>> createSupportersOfActivePieces(Map<IPiece, List<IMove>> possibleMovesMap) {
//		List<IPiece> pieces = Game.getPlayer().getAllPieces();
//		for (IPiece piece : pieces) {
//			if (Game.getPlayer().getPieces().contains(piece)) {
//				List<IMove> movelist = piece.convertFieldsToMoves(piece.getSupporters());
//				if (movelist != null) {
//					possibleMovesMap.put(piece, movelist);
//				} else {
//					possibleMovesMap.put(piece, new ArrayList<IMove>());
//				}
//			}
//		}
		return possibleMovesMap;
	}

}
