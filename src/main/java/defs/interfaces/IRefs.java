package defs.interfaces;

import chess.Drawer;
import chess.Main;
import conf.Referee;
import defs.classes.Game;

public interface IRefs {

	default Game getGame() {
		return Game.getInstance();
	}
	
	default Drawer getDrawer(Main main) {
		return Drawer.getInstance(main);
	}
	
	default Referee getReferee() {
		return Referee.getInstance();
	}

}
