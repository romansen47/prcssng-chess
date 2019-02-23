package defs.classes;

import chess.Main;
import conf.Config;
import defs.enums.Colors;
import defs.interfaces.IColors;
import defs.interfaces.IDraw;
import defs.interfaces.IRefs;

public class Field implements IColors, IDraw, IRefs {

	final private int i;
	final private int j;
	final private Colors col;

	private Piece piece = null;

	public Field(Colors col, int i, int j) {
		this.col = col;
		this.i = i;
		this.j = j;
	}

	public int getI() {
		return i;
	}

	public int getJ() {
		return j;
	}

	@Override
	public Colors getCol() {
		return col;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	@Override
	public int getColAsInt() {
		if (getCol() == Colors.WHITE) {
			return 250;
		}
		return 100;
	}

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

	public int getPosI(Main main) {
		return (main.mouseX - main.mouseX % Config.Size) / Config.Size;
	}

	public int getPosJ(Main main) {
		return  (main.mouseY - main.mouseY % Config.Size) / Config.Size;
	}

	@Override
	public String toString() {
		if (this.getPiece() != null) {
			return super.toString();
		} else {
			return "";
		}
	}

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
		}
		str += 1+this.getI();
		return str;
	}

	// for testing purposes, to be removed...
//	public void setCol(Color col) {
//		this.col=col;
//	}

}
