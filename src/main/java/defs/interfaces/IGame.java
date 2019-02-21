package defs.interfaces;

import chess.Game;

public interface IGame {

	default Game getSpiel() {
		return Game.getInstance();
	}

}
