package defs.classes;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import chess.Main;
import conf.Config;
import defs.enums.Colors;
import defs.interfaces.IColors;
import defs.interfaces.IDraw;
import defs.interfaces.IPiece;
import defs.interfaces.IRefs;

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
		i=0;
		j=0;
		col=null;
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
	 * @see defs.interfaces.IDraw#draw(chess.Main)
	 */
	@Override
	public void draw(Main main) {
		final int size = Config.SIZE;
		main.noStroke();
		main.fill(this.getColAsInt());
		main.rect((float) size * this.getJ(), (float) size * this.getI(), size, size);
		if (this.getPiece() != null) {
			this.getPiece().draw(main);
		}
	}

	/**
	 * Getter for the color
	 */
	@Override
	public Colors getCol() {
		return this.col;
	}

	/**
	 * We need some different colors, since the fields should not be only black and
	 * white. In detail, e.e. white should rather be grey
	 *
	 * @return the corresponding color value as integer
	 */
	public int getColAsInt() {
		if (this.getCol() == Colors.WHITE) {
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
		return this.i;
	}

	/**
	 * Getter for j
	 *
	 * @return horizontal coordinate
	 */
	public int getJ() {
		return this.j;
	}

	/**
	 * Getter for the piece on the field
	 *
	 * @return the piece
	 */
	public IPiece getPiece() {
		return this.piece;
	}

	/**
	 * The mouse position in chess game's native coordinates
	 *
	 * @param main the main papplet
	 * @return the vertical position
	 */
	public int getPosI(Main main) {
		return (main.mouseX - (main.mouseX % Config.SIZE)) / Config.SIZE;
	}

	/**
	 * The mouse position in chess game's native coordinates
	 *
	 * @param main the main papplet
	 * @return the horizontal position
	 */
	public int getPosJ(Main main) {
		return (main.mouseY - (main.mouseY % Config.SIZE)) / Config.SIZE;
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
		final StringBuilder strBuilder=new StringBuilder();
		char name= "ABCDEFGH".charAt(this.getJ());
		strBuilder.append(name);
		strBuilder.append(1 + this.getI());
		return strBuilder.toString();
	}

	/**
	 * own method for presentation
	 */
	@Override
	public String toString() {
		String ans = "(" + this.toChessNotation() + "): " + this.getCol() + " FIELD";
		if (this.getPiece() != null) {
			ans += " (" + this.getPiece().toString() + ")";
		}
		return ans;
	}

	public void reset() {
		this.setPiece(null);
	}

}
