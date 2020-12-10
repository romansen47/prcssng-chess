package chess.game.moves;

import chess.game.pieces.IPiece;
import defs.classes.Field;

public interface IMove {

	/**
	 * Execution of a move. Recalculates new positions.
	 */
	void execute();

//	/**
//	 * Getter for the color
//	 *
//	 * @return the color of the moved piece
//	 */
//	Colors getCol();

	/**
	 * Getter for the moved piece
	 *
	 * @return the moved piece
	 */
	IPiece getPiece();

	/**
	 * Getter for next, the field to move on
	 *
	 * @return the next field
	 */
	Field getNext();

	/**
	 * Getter for the Id of the piece on next field
	 *
	 * @return Id of the piece on next field
	 */
	// Ids getNextId();

	/**
	 * Getter for prev, the previous field
	 *
	 * @return the previous field
	 */
	Field getPrev();

	// void toXml() throws Exception;

	/**
	 * Getter for the Id of the piece on previous field
	 *
	 * @return Id of the piece on previous field
	 */
	// Ids getPrevId();

}
