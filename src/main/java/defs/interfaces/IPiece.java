package defs.interfaces;

import java.util.ArrayList;
import java.util.List;

import conf.Referee;
import defs.classes.Field;
import defs.classes.Piece;
import defs.classes.Player;
import defs.enums.Colors;
import pieces.Pawn;

public interface IPiece extends IRefs {

	/**
	 * @return returns the list of possible moves
	 */
	List<Field> getPossibleMoves();

	Field getField();
	
	void setField(Field field);

	default List<Field> getFieldsOfInterest(Player pl){
		List<Field> fields=new ArrayList<Field>();
		List<Piece> pieces=pl.getPieces();
		for (Piece piece:pieces) {
			if (piece.getPossibleMovesOfInterest().contains(getField())) {
				fields.add(piece.getField());
			}
		}
		return fields;
	}
	
	default List<Field> getPossibleMovesOfInterest() {
		return getPossibleMoves();
	}
	
	/**
	 * 
	 * @param pl the player 
	 * @return
	 */
	default List<Field> getAttackers() {
		return getFieldsOfInterest(getOpponent());
	}
	
	default List<Field> getSupporters(){
		Piece piece=getReferee().getMarked().getPiece();
		Colors col=Colors.WHITE;
		if (piece.getCol()==col) {
			col=Colors.BLACK;
		}
		Field field=piece.getField();
		Piece fakepiece=new Pawn(col, field);
		field.setPiece(fakepiece);
		getPlayer().getPieces().remove(piece);
		getPlayer().getPieces().add(fakepiece);			
		List<Field> tmpFields=piece.getFieldsOfInterest(getPlayer());
		getPlayer().getPieces().remove(fakepiece);
		getPlayer().getPieces().add(piece);
		field.setPiece(piece);
		return tmpFields;
	}
	
	Colors getCol();

	default Player getPlayer() {
		if (getCol()==Colors.WHITE) {
			return getSpiel().getWhite();
		}
		return getSpiel().getBlack();
	}

	default Player getOpponent() {
		if (getCol()==Colors.WHITE) {
			return getSpiel().getBlack();
		}
		return getSpiel().getWhite();
	}

	default public int getPosI() {
		return getField().getI();
	}

	default public int getPosJ() {
		return getField().getJ();
	}
}
