package defs.interfaces;

import defs.classes.Field;
import defs.enums.Colors;
import defs.enums.Ids;

public interface IMove {

	/**
	 * Execution of a move. Recalculates new positions.
	 */
	void execute();

	/**
	 * Getter for the color
	 * 
	 * @return the color of the moved piece
	 */
	Colors getCol();

	/**
	 * Getter for the moved piece
	 * 
	 * @return
	 */
	IPiece getFig();

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
	Ids getNextId();

	/**
	 * Getter for prev, the previous field
	 * 
	 * @return the previous field
	 */
	Field getPrev();

	/**
	 * Getter for the Id of the piece on previous field
	 * 
	 * @return Id of the piece on previous field
	 */
	Ids getPrevId();

}
