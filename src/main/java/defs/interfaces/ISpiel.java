package defs.interfaces;

import chess.Game;

public interface ISpiel {

	default Game getSpiel() {
		return Game.getInstance();
	}

}
