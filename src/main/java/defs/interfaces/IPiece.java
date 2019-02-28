package defs.interfaces;

import java.util.ArrayList;
import java.util.List;

import chess.Move;
import defs.classes.Field;
import defs.classes.Piece;
import defs.classes.Player;
import defs.enums.Colors;
import pieces.Pawn;

public interface IPiece extends IRefs {

	Move getMove(Field fld);
	
	default List<Move> convertFieldsToMoves(List<Field> input){
		List<Move> ans=new ArrayList<>();
		for (Field fld:input){
			ans.add(getMove(fld));
		}
		return ans;
	}
	
	default List<Field> convertMovesToFields(List<Move> input){
		List<Field> ans=new ArrayList<>();
		for (Move move:input){
			ans.add(move.next);
		}
		return ans;
	}
	
	/**
	 * @return returns the list of possible moves
	 */
	List<Move> getPossibleMoves();

	Field getField();
	
	void setField(Field field);

	default List<Field> getFieldsOfInterest(Player pl){
		List<Field> fields=new ArrayList<>();
		List<Piece> pieces=pl.getPieces();
		for (Piece piece:pieces) {
			if (piece.convertMovesToFields(piece.getPossibleMovesOfInterest()).contains(getField())) {
				fields.add(piece.getField());
			}
		}
		return fields;
	}
	
	default List<Move> getPossibleMovesOfInterest() {
		return getPossibleMoves();
	}
	
	/**
	 * 
	 * @return returns list attacking fields
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
