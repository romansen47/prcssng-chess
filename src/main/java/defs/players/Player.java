package defs.players;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import chess.game.moves.IMove;
import chess.game.pieces.IPiece;
import chess.game.pieces.impl.King;
import config.Config;
import defs.classes.Game;
import defs.enums.Colors;

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
	 * a list of all players of one color.
	 * this list stays allways the same. no
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
			king = new King(Colors.WHITE, Player.game.getField(0, 4));
		} else {
			king = new King(Colors.BLACK, Player.game.getField(Config.GAMESIZE, 4));
		}
		initialGeneration();
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
		return getGame().getMoveList();
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
