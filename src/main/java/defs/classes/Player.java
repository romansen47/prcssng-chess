package defs.classes;

import java.util.ArrayList;
import java.util.List;

import conf.Config;
import defs.enums.Colors;
import defs.interfaces.IMove;
import defs.interfaces.IPiece;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Queen;
import pieces.Rook;

/**
 * 
 * @author roman
 *
 */
public class Player {

	/**
	 * The game
	 */
	private static Game game = null;

	/**
	 * a list of all players of one color. this list stays allways the same. no
	 * pieces are to be added or removed!
	 */
	final private List<IPiece> AllPieces = new ArrayList<>();

	/**
	 * player's color
	 */
	private final Colors col;

	/**
	 * set of dead pieces
	 */
	final private List<IPiece> deadPieces = new ArrayList<>();

	/**
	 * The king
	 */
	private final King king;

	/**
	 * List of player's moves
	 */
	final private List<IMove> MoveList = new ArrayList<>();

	/**
	 * Player's set of pieces
	 */
	final private List<IPiece> Pieces = new ArrayList<>();

	/**
	 * Constructor for the player
	 * 
	 * @param col the player's color
	 */
	public Player(Colors col) {
		Player.game = Game.getInstance();
		this.col = col;
		if (col == Colors.WHITE) {
			this.king = new King(Colors.WHITE, Player.game.getField(0, 4));
		} else {
			this.king = new King(Colors.BLACK, Player.game.getField(Config.GAMESIZE, 4));
		}
		this.initialGeneration();
	}

	/**
	 * Get all pieces, the living and the dead
	 * 
	 * @return allPieces
	 */
	public List<IPiece> getAllPieces() {
		return this.AllPieces;
	}

	/**
	 * Getter for color
	 * 
	 * @return the color
	 */
	public Colors getCol() {
		return this.col;
	}

	/**
	 * getter for list of dead pieces
	 * 
	 * @return the list of dead pieces
	 */
	public List<IPiece> getDeadPieces() {
		return this.deadPieces;
	}

	/**
	 * Getter for the king
	 * 
	 * @return the king
	 */
	public King getKing() {
		return this.king;
	}

	/**
	 * method to get the last move the player performed
	 * 
	 * @return the last move performed by the player
	 */
	public IMove getLastMove() {
		final int n = this.getMoveList().size();
		if (n > 0) {
			return this.getMoveList().get(n - 1);
		}
		return null;
	}

	/**
	 * Getter for the list of moves
	 * 
	 * @return MoveList
	 */
	public List<IMove> getMoveList() {
		return this.MoveList;
	}

	/**
	 * Getter of player's pieces
	 * 
	 * @return list of player's pieces
	 */
	public List<IPiece> getPieces() {
		return this.Pieces;
	}

	/**
	 * First setup
	 */
	private void initialGeneration() {

		this.Pieces.add(this.getKing());
		int ersteReihe = 0;
		int zweiteReihe = 1;
		if (this.getCol() == Colors.BLACK) {
			ersteReihe = Config.GAMESIZE;
			zweiteReihe = 6;
		}
		for (int j = 0; j < 8; j++) {
			this.Pieces.add(new Pawn(this.getCol(), Player.game.getField(zweiteReihe, j)));
		}

		this.Pieces.add(new Knight(this.getCol(), Player.game.getField(ersteReihe, 1)));
		this.Pieces.add(new Knight(this.getCol(), Player.game.getField(ersteReihe, 6)));
		this.Pieces.add(new Bishop(this.getCol(), Player.game.getField(ersteReihe, 2)));
		this.Pieces.add(new Bishop(this.getCol(), Player.game.getField(ersteReihe, 5)));
		this.Pieces.add(new Rook(this.getCol(), Player.game.getField(ersteReihe, 0)));
		this.Pieces.add(new Rook(this.getCol(), Player.game.getField(ersteReihe, Config.GAMESIZE)));
		this.Pieces.add(new Queen(this.getCol(), Player.game.getField(ersteReihe, 3)));
		this.AllPieces.addAll(this.Pieces);

	}

}
