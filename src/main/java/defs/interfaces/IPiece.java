package defs.interfaces;

import java.util.ArrayList;
import java.util.List;
import defs.classes.Field;
import defs.classes.Piece;
import defs.classes.Player;

public interface IPiece extends IGame {

	List<Field> getPossibleMoves();

	public Field getField();
	
	public void setField(Field field);

	default public List<Field> getAttackers(Player pl) {
		List<Field> fields=new ArrayList<Field>();
		List<Piece> pieces=pl.getPieces();
		for (Piece piece:pieces) {
			if (piece.getPossibleMoves().contains(getField())) {
				fields.add(piece.getField());
			}
		}
		//fields.remove(this.getField());
		return fields;
	}
	
	default public List<Field> getSupporters(Player pl) {
		List<Field> fields=new ArrayList<Field>();
		List<Piece> pieces=pl.getPieces();
		for (Piece piece:pieces) {
			if (piece.getPossibleMoves().contains(getField())) {
				fields.add(piece.getField());
			}
		}
		//fields.remove(this.getField());
		return fields;
	}

	default public int getPosI() {
		return getField().getI();
	}

	default public int getPosJ() {
		return getField().getJ();
	}
}
