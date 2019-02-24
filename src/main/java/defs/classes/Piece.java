package defs.classes;

import chess.Main;
import chess.Move;
import conf.Config;
import defs.enums.Colors;
import defs.enums.Ids;
import defs.interfaces.IDraw;
import defs.interfaces.IPiece;
import defs.interfaces.IRefs;
import defs.interfaces.IValidityChecker;
import processing.core.PImage;

/**
 * 
 * @author roman
 *
 * The blueprint for the pieces
 */
abstract public class Piece implements IPiece, IValidityChecker, IDraw, IRefs {
 
	/**
	 * Every piece has its own pimage
	 */
	private PImage image;
	
	/**
	 * The id
	 */
	public final Ids id;
	
	/**
	 * the color
	 */
	public final Colors col;
	
	/**
	 * the field. nullable
	 */
	private Field field;
	
	/**
	 * Constructor for piece
	 * @param id the id
	 * @param col the color
	 * @param field the field
	 */
	public Piece(Ids id, Colors col, Field field) {
		this.id = id;
		this.col = col;
		this.setField(field);
	}

	/**
	 * Getter for the pimage
	 * @return the corresponding pimage
	 */
	public PImage getImage() {
		return image;
	}
	
	/**
	 * Setter for the pimage
	 * @param image the pimage
	 */
	public void setImage(PImage image) {
		this.image=image;
	}

	/**
	 * Getter for the field
	 * @return the field
	 */
	@Override
	public Field getField() {
		return field;
	}

	/**
	 * Setter for the field
	 * @param field the field
	 */
	@Override
	public void setField(Field field) {
		this.field = field;
		if (field != null) {
			this.field.setPiece(this);
		}
	}

	/**
	 * Getter for the color
	 * @return the color
	 */
	@Override
	public Colors getCol() {
		return col;
	}
	
	/**
	 * Creator of a move
	 * @param field the field to move on
	 * @return the created move
	 */
	public Move getMove(Field field) {
		return new Move(this,field);
	}
	
	/**
	 * Draws the piece
	 * 
	 * @param main the main papplet
	 */
	@Override
	public void draw(Main main) {
		final int size = Config.Size;
		main.image(getImage(), size * getField().getJ(), size * getField().getI(), size, size);
	}

	public void die() {
		getField().setPiece(null);
		this.getPlayer().getPieces().remove(this);
	}

}
