package defs.players;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import conf.Config;
import defs.classes.Game;
import defs.enums.Colors;
import defs.interfaces.IMove;
import defs.interfaces.IPiece;
import defs.interfaces.IPlayer;
import pieces.King;

/**
 *
 * @author roman
 *
 */
public class Player implements IPlayer {

	/**
	 * The game
	 */
	private static Game game = Game.getInstance();

	/**
	 * a list of all players of one color. this list stays allways the same. no
	 * pieces are to be added or removed!
	 */
	private final List<IPiece> allPieces = new ArrayList<>();

	/**
	 * player's color
	 */
	protected final Colors col;

	/**
	 * set of dead pieces
	 */
	private final List<IPiece> deadPieces = new ArrayList<>();

	/**
	 * The king
	 */
	private final King king;

	/**
	 * List of player's moves
	 */
	private final List<IMove> moveList = new ArrayList<>();

	/**
	 * Player's set of pieces
	 */
	private final List<IPiece> pieces = new ArrayList<>();

	/**
	 * Constructor for the player
	 *
	 * @param col the player's color
	 */
	public Player(Colors col) {
		this.col = col;
		if (col == Colors.WHITE) {
			this.king = new King(Colors.WHITE, Player.game.getField(0, 4));
		} else {
			this.king = new King(Colors.BLACK, Player.game.getField(Config.GAMESIZE, 4));
		}
		this.initialGeneration();
	}

	@Override
	public List<IPiece> getAllPieces() {
		return allPieces;
	}

	@Override
	public Colors getCol() {
		return col;
	}

	@Override
	public List<IPiece> getDeadPieces() {
		return deadPieces;
	}

	@Override
	public King getKing() {
		return king;
	}

	@Override
	public List<IMove> getMoveList() {
		return moveList;
	}

	@Override
	public List<IPiece> getPieces() {
		return pieces;
	}

	@Override
	public Random getRandom() {
		return null;
	}
}
