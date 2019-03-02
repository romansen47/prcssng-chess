package defs.classes;

import chess.Main;
import conf.Config;
import defs.enums.Colors;
import defs.enums.Ids;
import defs.interfaces.IDraw;
import defs.interfaces.IMove;
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
	private final Ids id;
	
	/**
	 * the color
	 */
	private final Colors col;
	
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

	
	
	public PImage getImage() {
		return image;
	}
	
	public void setImage(PImage image) {
		this.image=image;
	}

	@Override
	public Field getField() {
		return field;
	}

	@Override
	public void setField(Field field) {
		this.field = field;
		if (field != null) {
			this.field.setPiece(this);
		}
	}

	@Override
	public Colors getCol() {
		return col;
	}
	
	@Override
	public IMove getMove(Field field) {
		return new Move(this,field);
	}
	
	@Override
	public void draw(Main main) {
		final int size = Config.SIZE;
		main.image(getImage(), size * getField().getJ(), size * getField().getI(), size, size);
	}

	public void die() {
		getField().setPiece(null);
		this.getPlayer().getPieces().remove(this);
	}
	
	@Override
	public Ids getId() {
		return id;
	}
	
	@Override
	public int getPosI() {
		return getField().getI();
	}
	
	@Override
	public int getPosJ() {
		return getField().getJ();
	}

}
