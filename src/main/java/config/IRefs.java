package config;

import chess.ConcreteChess;
import defs.classes.Game;
import defs.classes.Referee;

/**
 *
 * @author RoMansen
 *
 *         This interfaces provides
 *         default getters for static
 *         singleton instances
 */
public interface IRefs {

	/**
	 * Getter for the drawer
	 *
	 * @param main the Main instance
	 * @return static instance of drawer
	 */
	default Drawer getDrawer(ConcreteChess main) {
		return Drawer.getInstance(main);
	}

	/**
	 * Getter for the game
	 *
	 * @return static instance of game
	 */
	default Game getGame() {
		return Game.getInstance();
	}

	/**
	 * Getter for the Referee
	 *
	 * @return static instance of Referee
	 */
	default Referee getReferee() {
		return Referee.getInstance();
	}

}
