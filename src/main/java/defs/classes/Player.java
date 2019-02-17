package defs.classes;

import java.util.ArrayList;
import java.util.List;

import chess.Game;
import defs.enums.BlackWhite;
import figuren.Bauer;
import figuren.Dame;
import figuren.Koenig;
import figuren.Laeufer;
import figuren.Springer;
import figuren.Turm;

public class Player {

	private static Game game = null;
	private final BlackWhite col;
	final private List<Piece> Figuren = new ArrayList<Piece>();

	public List<Piece> getFiguren() {
		return Figuren;
	}

	final Koenig king;

	public Player(BlackWhite col) {
		game = Game.getInstance();
		this.col = col;
		if (col == BlackWhite.WHITE) {
			this.king = new Koenig(BlackWhite.WHITE, game.getField(0, 4));
		} else {
			this.king = new Koenig(BlackWhite.BLACK, game.getField(7, 4));
		}
		initialGeneration();
	}

	private void initialGeneration() {

		Figuren.add(king);
		int ersteReihe = 0;
		int zweiteReihe = 1;
		if (getCol() == BlackWhite.BLACK) {
			ersteReihe = 7;
			zweiteReihe = 6;
		}
		for (int j = 0; j < 8; j++) {
			Figuren.add(new Bauer(getCol(), game.getField(zweiteReihe, j)));
		}
		Figuren.add(new Springer(getCol(), game.getField(ersteReihe, 1)));
		Figuren.add(new Springer(getCol(), game.getField(ersteReihe, 6)));
		Figuren.add(new Laeufer(getCol(), game.getField(ersteReihe, 2)));
		Figuren.add(new Laeufer(getCol(), game.getField(ersteReihe, 5)));
		Figuren.add(new Turm(getCol(), game.getField(ersteReihe, 0)));
		Figuren.add(new Turm(getCol(), game.getField(ersteReihe, 7)));
		Figuren.add(new Dame(getCol(), game.getField(ersteReihe, 3)));

	}

	public BlackWhite getCol() {
		return col;
	}

}
