package defs.interfaces;

import chess.Main;

/**
 * 
 * @author RoMansen
 * 
 *         anyone who can draw is an IDrawer!
 *
 */

public interface IDraw {

	/**
	 * draws the piece
	 * 
	 * @param main gui object
	 */
	void draw(Main main);

}
