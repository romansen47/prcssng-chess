package chess.pieces;

import chess.Config;
import chess.IDraw;
import chess.IRefs;
import chess.Main;
import chess.moves.IMove;
import chess.moves.Move;
import defs.classes.Field;
import defs.enums.Colors;
import defs.enums.Ids;
import defs.interfaces.IValidityChecker;
import processing.core.PImage;

/**
 *
 * @author roman
 *
 *         The blueprint for the pieces
 */
public abstract class Piece implements IPiece, IValidityChecker, IDraw, IRefs {

	private final int value;

	/**
	 * the color
	 */
	private final Colors col;

	/**
	 * the field. nullable
	 */
	private Field field;

	private final Field firstField;

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
	public Piece(Ids id, Colors col, Field field, int value) {
		this.id = id;
		this.col = col;
		setField(field);
		firstField = field;
		this.value = value;
	}

	@Override
	public void draw(Main main) {
		final int size = Config.SIZE;
		main.image(getImage(), (float) size * getField().getJ(), (float) size * getField().getI(), size, size);
	}

	@Override
	public Colors getCol() {
		return col;
	}

	@Override
	public Field getField() {
		return field;
	}

	@Override
	public Field getFirstField() {
		return firstField;
	}

	@Override
	public Ids getId() {
		return id;
	}

	@Override
	public PImage getImage() {
		return image;
	}

	@Override
	public IMove getMove(Field field) {
		return new Move(getField(), field);
	}

	@Override
	public int getPosI() {
		return getField().getI();
	}

	@Override
	public int getPosJ() {
		return getField().getJ();
	}

	@Override
	public void reset() {
		if (getField() != null) {
			getField().setPiece(this);
		}
		setField(getFirstField());
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
		return getCol() + " " + getId();
	}

	/**
	 * @return the value
	 */
	@Override
	public int getValue() {
		return value;
	}

}
