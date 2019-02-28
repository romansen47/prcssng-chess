package defs.classes;

import chess.Main;
import conf.Config;
import defs.enums.Colors;
import defs.interfaces.IColors;
import defs.interfaces.IDraw;
import defs.interfaces.IRefs;

/**
 * 
 * @author roman
 *
 * Implementation of field class
 */
public class Field implements IColors, IDraw, IRefs {

	/**
	 * the vertical coordinate
	 */
	private final int i;

	/**
	 * the horizontal coordinate
	 */
	private final int j;
	
	/**
	 * the color of the field
	 */
	private final Colors col;

	/**
	 * the piece on the field. nullable
	 */
	private Piece piece = null;

	/**
	 * Constructor of field.
	 * 
	 * @param col the color of the field
	 * @param i the vertical coordinate
	 * @param j the horizontal coordinate
	 */
	public Field(Colors col, int i, int j) {
		this.col = col;
		this.i = i;
		this.j = j;
	}

	/**
	 * Getter for i
	 * @return vertical coordinate
	 */
	public int getI() {
		return i;
	}

	/**
	 * Getter for j
	 * @return horizontal coordinate
	 */
	public int getJ() {
		return j;
	}

	/**
	 * Getter for the color
	 */
	@Override
	public Colors getCol() {
		return col;
	}

	/**
	 * Getter for the piece on the field
	 * @return the piece
	 */
	public Piece getPiece() {
		return piece;
	}

	/**
	 * Setter for piece
	 * @param piece the piece on the field
	 */
	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	/**
	 * We need some different colors, since the fields should not be only black and white. 
	 * In detail, e.e. white should rather be grey
	 * @return the corresponding color value as integer
	 */
	public int getColAsInt() {
		if (getCol() == Colors.WHITE) {
			return 230;
		}
		return 100;
	}

	/*+
	 * (non-Javadoc)
	 * @see defs.interfaces.IDraw#draw(chess.Main)
	 */
	@Override
	public void draw(Main main) {
		int size = Config.Size;
		main.noStroke();
		main.fill(getColAsInt());
		main.rect((float)size * getJ(), (float)size * getI(), size, size);
		if (getPiece() != null) {
			getPiece().draw(main);
		}
	}

	/**
	 * The mouse position in chess game's native coordinates
	 * @param main the main papplet
	 * @return the vertical position
	 */
	public int getPosI(Main main) {
		return (main.mouseX - main.mouseX % Config.Size) / Config.Size;
	}


	/**
	 * The mouse position in chess game's native coordinates
	 * @param main the main papplet
	 * @return the horizontal position
	 */
	public int getPosJ(Main main) {
		return  (main.mouseY - main.mouseY % Config.Size) / Config.Size;
	}

	/**
	 * own method for presentation
	 */
	@Override
	public String toString() {
		if (this.getPiece() != null) {
			return super.toString();
		} else {
			return "";
		}
	}

	/**
	 * Implementation of chess notation
	 * @return the string in chess notation
	 */
	public String toChessNotation() {
		String str = "";
		switch (this.getJ()) {
		case 0:
			str += "A";
			break;
		case 1:
			str += "B";
			break;
		case 2:
			str += "C";
			break;
		case 3:
			str += "D";
			break;
		case 4:
			str += "E";
			break;
		case 5:
			str += "F";
			break;
		case 6:
			str += "G";
			break;
		case 7:
			str += "H";
			break;
		default:
			break;
		}
		str += 1+this.getI();
		return str;
	}

}
