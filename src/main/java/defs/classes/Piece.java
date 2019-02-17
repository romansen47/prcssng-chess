package defs.classes;

import defs.enums.BlackWhite;
import defs.enums.Ids;
import defs.interfaces.IDraw;
import defs.interfaces.IPiece;
import defs.interfaces.ISpiel;
import defs.interfaces.IValidityChecker;

abstract public class Piece implements IPiece, IValidityChecker, IDraw, ISpiel {

	public final Ids id;
	public final BlackWhite col;
	private Field field;

	public Piece(Ids id, BlackWhite col, Field field) {
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
			this.field.setFigur(this);
		}
	}

	@Override
	public BlackWhite getCol() {
		return col;
	}

}
