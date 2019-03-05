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
	 * player's color
	 */
	private final Colors col;
	
	final private List<IPiece> AllPieces = new ArrayList<>();;
	
	/**
	 * Player's set of pieces
	 */
	final private List<IPiece> Pieces = new ArrayList<>();
	
	/**
	 * set of dead pieces
	 */
	final private List<IPiece> deadPieces = new ArrayList<>();

	/**
	 * List of player's moves
	 */
	final private List<IMove> MoveList=new ArrayList<>();
	
	/**
	 * Getter of player's pieces
	 * @return list of player's pieces
	 */
	public List<IPiece> getPieces() {
		return Pieces;
	}

	/**
	 * The king
	 */
	private final King king;
	
	/**
	 * Constructor for the player
	 * @param col the player's color
	 */
	public Player(Colors col){
		game = Game.getInstance();
		this.col = col;
		if (col == Colors.WHITE) {
			this.king = new King(Colors.WHITE, game.getField(0, 4));
		} else {
			this.king = new King(Colors.BLACK, game.getField(Config.GAMESIZE, 4));
		}
		initialGeneration();
	}

	/**
	 * First setup
	 */
	private void initialGeneration() {

		Pieces.add(getKing());
		int ersteReihe = 0;
		int zweiteReihe = 1;
		if (getCol() == Colors.BLACK) {
			ersteReihe = Config.GAMESIZE;
			zweiteReihe = 6;
		}
		for (int j = 0; j < 8; j++) {
			Pieces.add(new Pawn(getCol(), game.getField(zweiteReihe, j)));
		}
		
		Pieces.add(new Knight(getCol(), game.getField(ersteReihe, 1)));
		Pieces.add(new Knight(getCol(), game.getField(ersteReihe, 6)));
		Pieces.add(new Bishop(getCol(), game.getField(ersteReihe, 2)));
		Pieces.add(new Bishop(getCol(), game.getField(ersteReihe, 5)));
		Pieces.add(new Rook(getCol(), game.getField(ersteReihe, 0)));
		Pieces.add(new Rook(getCol(), game.getField(ersteReihe, Config.GAMESIZE)));
		Pieces.add(new Queen(getCol(), game.getField(ersteReihe, 3)));
		AllPieces.addAll(Pieces);

	}

	/**
	 * Getter for color
	 * @return the color
	 */
	public Colors getCol() {
		return col;
	}

	/**
	 * getter for list of dead pieces
	 * @return the list of dead pieces
	 */
	public List<IPiece> getDeadPieces() {
		return deadPieces;
	}

	/**
	 * Getter for the list of moves
	 * @return MoveList
	 */
	public List<IMove> getMoveList() {
		return MoveList;
	}

	/**
	 * Getter for the king
	 * @return the king
	 */
	public King getKing() {
		return king;
	}
	
	public IMove getLastMove() {
		int n=getMoveList().size();
		if(n>0) {
			return getMoveList().get(n-1);
		}
		return null;
	}

	public List<IPiece> getAllPieces() {
		return AllPieces;
	}

}
