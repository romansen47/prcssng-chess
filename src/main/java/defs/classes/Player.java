package defs.classes;

import java.util.ArrayList;
import java.util.List;

import chess.Game;
import defs.enums.Colors;
import figuren.Bauer;
import figuren.Dame;
import figuren.Koenig;
import figuren.Laeufer;
import figuren.Springer;
import figuren.Turm;

public class Player {

	private static Game game = null;
	private final Colors col;
	final private List<Piece> Pieces = new ArrayList<Piece>();
	final private List<Piece> deadPieces = new ArrayList<Piece>();

	final private List<Move> MoveList=new ArrayList<Move>();
	
	public List<Piece> getPieces() {
		return Pieces;
	}

	final Koenig king;
	
	public Player(Colors col){
		game = Game.getInstance();
		this.col = col;
		if (col == Colors.WHITE) {
			this.king = new Koenig(Colors.WHITE, game.getField(0, 4));
		} else {
			this.king = new Koenig(Colors.BLACK, game.getField(7, 4));
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
			Pieces.add(new Bauer(getCol(), game.getField(zweiteReihe, j)));
		}
		Pieces.add(new Springer(getCol(), game.getField(ersteReihe, 1)));
		Pieces.add(new Springer(getCol(), game.getField(ersteReihe, 6)));
		Pieces.add(new Laeufer(getCol(), game.getField(ersteReihe, 2)));
		Pieces.add(new Laeufer(getCol(), game.getField(ersteReihe, 5)));
		Pieces.add(new Turm(getCol(), game.getField(ersteReihe, 0)));
		Pieces.add(new Turm(getCol(), game.getField(ersteReihe, 7)));
		Pieces.add(new Dame(getCol(), game.getField(ersteReihe, 3)));

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
