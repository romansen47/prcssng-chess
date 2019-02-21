package defs.classes;

import defs.enums.Colors;
import defs.enums.Ids;
import defs.interfaces.IDraw;
import defs.interfaces.IPiece;
import defs.interfaces.IGame;
import defs.interfaces.IValidityChecker;

abstract public class Piece implements IPiece, IValidityChecker, IDraw, IGame {
 
	public final Ids id;
	public final Colors col;
	private Field field;

	public Piece(Ids id, Colors col, Field field) throws Exception {
		if (col!=Colors.WHITE && col!=Colors.BLACK) {
			throw new Exception("Figuren dürfen nur schwarz oder weiss sein!");
		}
		this.id = id;
		this.col = col;
		this.setField(field);
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

}
