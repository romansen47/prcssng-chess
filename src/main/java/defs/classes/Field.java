package defs.classes;

import chess.Main;
import conf.Config;
import defs.enums.BlackWhite;
import defs.interfaces.IBlackWhite;
import defs.interfaces.IDraw;
import defs.interfaces.ISpiel;

public class Field implements IBlackWhite, IDraw, ISpiel {

	final private int i;
	final private int j;
	final private BlackWhite col;

	private Piece piece = null;

	public Field(BlackWhite col, int i, int j) {
		this.col = col;
		this.i = i;
		this.j = j;
	}

	public int getI() {
		return 7 - i;
	}

	public int getJ() {
		return j;
	}

	@Override
	public BlackWhite getCol() {
		return col;
	}

	public Piece getFigur() {
		return piece;
	}

	public void setFigur(Piece piece) {
		this.piece = piece;
	}

	@Override
	public int getColAsInt() {
		if (getCol() == BlackWhite.WHITE) {
			return 250;
		}
		return 100;
	}

	@Override
	public void draw(Main main) {
		int size = Config.Size;
		main.noStroke();
		main.fill(getColAsInt());
		main.rect(size * getJ(), size * getI(), size, size);
		if (getFigur() != null) {
			getFigur().draw(main);
		}
	}

	public int getPosI(Main main) {
		int PosI = (main.mouseX - main.mouseX % Config.Size) / Config.Size;
		return PosI;
	}

	public int getPosJ(Main main) {
		int PosJ = (main.mouseY - main.mouseY % Config.Size) / Config.Size;
		return PosJ;
	}

	@Override
	public String toString() {
		if (this.getFigur() != null) {
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
		str += 8 - this.getI();
		return str;
	}

	// for testing purposes, to be removed...
//	public void setCol(Color col) {
//		this.col=col;
//	}

}
