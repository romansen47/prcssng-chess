package defs.classes;

import java.util.ArrayList;
import java.util.List;

import chess.Move;
import defs.enums.Colors;
import pieces.Pawn;
import pieces.Queen;
import pieces.King;
import pieces.Knight;
import pieces.Bishop;
import pieces.Rook;

public class Player {

	private static Game game = null;
	private final Colors col;
	final private List<Piece> Pieces = new ArrayList<Piece>();
	final private List<Piece> deadPieces = new ArrayList<Piece>();

	final private List<Move> MoveList=new ArrayList<Move>();
	
	public List<Piece> getPieces() {
		return Pieces;
	}

	final King king;
	
	public Player(Colors col){
		game = Game.getInstance();
		this.col = col;
		if (col == Colors.WHITE) {
			this.king = new King(Colors.WHITE, game.getField(0, 4));
		} else {
			this.king = new King(Colors.BLACK, game.getField(7, 4));
		}
		initialGeneration();
	}

	private void initialGeneration() {

		Pieces.add(king);
		int ersteReihe = 0;
		int zweiteReihe = 1;
		if (getCol() == Colors.BLACK) {
			ersteReihe = 7;
			zweiteReihe = 6;
		}
		for (int j = 0; j < 8; j++) {
			Pieces.add(new Pawn(getCol(), game.getField(zweiteReihe, j)));
		}
		Pieces.add(new Bishop(getCol(), game.getField(ersteReihe, 1)));
		Pieces.add(new Bishop(getCol(), game.getField(ersteReihe, 6)));
		Pieces.add(new Knight(getCol(), game.getField(ersteReihe, 2)));
		Pieces.add(new Knight(getCol(), game.getField(ersteReihe, 5)));
		Pieces.add(new Rook(getCol(), game.getField(ersteReihe, 0)));
		Pieces.add(new Rook(getCol(), game.getField(ersteReihe, 7)));
		Pieces.add(new Queen(getCol(), game.getField(ersteReihe, 3)));

	}

	public Colors getCol() {
		return col;
	}

	public List<Piece> getDeadPieces() {
		return deadPieces;
	}

	public List<Move> getMoveList() {
		return MoveList;
	}

}
