package defs.classes;

import chess.ConcreteChess;
import chess.game.pieces.IPiece;
import config.Config;
import config.IDraw;
import config.IRefs;
import defs.enums.Colors;
import defs.interfaces.IColors;

/**
 *
 * @author roman
 *
 *         Implementation of field class
 */
public class Field implements IColors, IDraw, IRefs {

	/**
	 * the color of the field
	 */
	private final Colors col;

	/**
	 * the vertical coordinate
	 */
	private final int i;

	/**
	 * the horizontal coordinate
	 */
	private final int j;

	/**
	 * the piece on the field. nullable
	 */
	private IPiece piece = null;

	public Field() {
		i = 0;
		j = 0;
		col = null;
	}

	/**
	 * Constructor of field.
	 *
	 * @param col the color of the field
	 * @param i   the vertical coordinate
	 * @param j   the horizontal coordinate
	 */
	public Field(Colors col, int i, int j) {
		this.col = col;
		this.i = i;
		this.j = j;
	}

	/*
	 * + (non-Javadoc)
	 *
	 * @see defs.interfaces.IDraw#draw(chess. Main)
	 */
	@Override
	public void draw(ConcreteChess main) {
		final int size = Config.getInstance().SIZE;
		main.noStroke();
		main.fill(getColAsInt());
		main.rect((float) size * getJ(), (float) size * getI(), size, size);
		if (getPiece() != null && getPiece().getField() != null) {
			getPiece().draw(main);
		}
	}

	/**
	 * Getter for the color
	 */
	@Override
	public Colors getCol() {
		return col;
	}

	/**
	 * We need some different colors, since the fields should not be only black and
	 * white. In detail, e.e. white should rather be grey
	 *
	 * @return the corresponding color value as integer
	 */
	public int getColAsInt() {
		if (getCol() == Colors.WHITE) {
			return 230;
		}
		return 100;
	}

	/**
	 * Getter for i
	 *
	 * @return vertical coordinate
	 */
	public int getI() {
		return i;
	}

	/**
	 * Getter for j
	 *
	 * @return horizontal coordinate
	 */
	public int getJ() {
		return j;
	}

	/**
	 * Getter for the piece on the field
	 *
	 * @return the piece
	 */
	public IPiece getPiece() {
		return piece;
	}

	/**
	 * The mouse position in chess game's native coordinates
	 *
	 * @param main the main papplet
	 * @return the vertical position
	 */
	public int getPosI(ConcreteChess main) {
		return (main.mouseX - (main.mouseX % Config.getInstance().SIZE)) / Config.getInstance().SIZE;
	}

	/**
	 * The mouse position in chess game's native coordinates
	 *
	 * @param main the main papplet
	 * @return the horizontal position
	 */
	public int getPosJ(ConcreteChess main) {
		return (main.mouseY - (main.mouseY % Config.getInstance().SIZE)) / Config.getInstance().SIZE;
	}

	/**
	 * Setter for piece
	 *
	 * @param piece the piece on the field
	 */
	public void setPiece(IPiece piece) {
		this.piece = piece;
	}

	/**
	 * Implementation of chess notation
	 *
	 * @return the string in chess notation
	 */
	public String toChessNotation() {
		final StringBuilder strBuilder = new StringBuilder();
		final char name = "ABCDEFGH".charAt(getJ());
		strBuilder.append(name);
		strBuilder.append(1 + getI());
		return strBuilder.toString();
	}

	/**
	 * own method for presentation
	 */
	@Override
	public String toString() {
		String ans = "(" + toChessNotation() + "): " + getCol() + " FIELD";
		if (getPiece() != null) {
			ans += " (" + getPiece().toString() + ")";
		}
		return ans;
	}

	public void reset() {
		setPiece(null);
	}

}
