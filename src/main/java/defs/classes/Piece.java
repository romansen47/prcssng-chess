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
 *         The blueprint for the pieces
 */
abstract public class Piece implements IPiece, IValidityChecker, IDraw, IRefs {

	/**
	 * the color
	 */
	private final Colors col;

	/**
	 * the field. nullable
	 */
	private Field field;

	final private Field firstField;

	/**
	 * The id
	 */
	private final Ids id;

	/**
	 * Every piece has its own pimage
	 */
	private PImage image;

	/**
	 * Constructor for piece
	 * 
	 * @param id    the id
	 * @param col   the color
	 * @param field the field
	 */
	public Piece(Ids id, Colors col, Field field) {
		this.id = id;
		this.col = col;
		this.setField(field);
		this.firstField = field;
	}

	@Override
	public void draw(Main main) {
		final int size = Config.SIZE;
		main.image(this.getImage(), size * this.getField().getJ(), size * this.getField().getI(), size, size);
	}

	@Override
	public Colors getCol() {
		return this.col;
	}

	@Override
	public Field getField() {
		return this.field;
	}

	@Override
	public Field getFirstField() {
		return this.firstField;
	}

	@Override
	public Ids getId() {
		return this.id;
	}

	@Override
	public PImage getImage() {
		return this.image;
	}

	@Override
	public IMove getMove(Field field) {
		return new Move(this, field);
	}

	@Override
	public int getPosI() {
		return this.getField().getI();
	}

	@Override
	public int getPosJ() {
		return this.getField().getJ();
	}

	@Override
	public void reset() {
		this.getField().setPiece(null);
		this.setField(this.getFirstField());
		this.getField().setPiece(this);
	}

	@Override
	public void setField(Field field) {
		this.field = field;
		if (field != null) {
			this.field.setPiece(this);
		}
	}

	@Override
	public void setImage(PImage image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return this.getCol() + " " + this.getId();
	}

}
