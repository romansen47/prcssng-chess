package defs.classes;

import chess.Main;
import chess.Move;
import conf.Config;
import defs.enums.Colors;
import defs.enums.Ids;
import defs.interfaces.IDraw;
import defs.interfaces.IPiece;
import defs.interfaces.IRefs;
import defs.interfaces.IValidityChecker;
import processing.core.PImage;

abstract public class Piece implements IPiece, IValidityChecker, IDraw, IRefs {
 
	private PImage image;
	public final Ids id;
	public final Colors col;
	private Field field;

	public Piece(Ids id, Colors col, Field field) {
//		if (col!=Colors.WHITE && col!=Colors.BLACK) {
//			throw new Exception("Figuren dürfen nur schwarz oder weiss sein!");
//		}
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
	
	public Move getMove(Field field) {
		return new Move(this,field);
	}
	

	@Override
	public void draw(Main main) {
		main.fill(getColAsInt());
		final int size = Config.Size;
		if (col == Colors.WHITE) {
			main.image(getImage(), size * getField().getJ(), size * getField().getI(), size, size);
		} else {
			main.image(getImage(), size * getField().getJ(), size * getField().getI(), size, size);
		}
	}


}
