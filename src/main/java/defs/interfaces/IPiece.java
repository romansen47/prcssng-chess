package defs.interfaces;

import java.util.ArrayList;
import java.util.List;

import chess.Main;
import defs.classes.Field;
import defs.classes.Piece;
import defs.classes.Player;
import defs.enums.Colors;
import defs.enums.Ids;
import pieces.King;
import pieces.Pawn;
import processing.core.PImage;

public interface IPiece extends IRefs {

	default List<IMove> convertFieldsToMoves(List<Field> input){
		List<IMove> ans=new ArrayList<>();
		for (Field fld:input){
			ans.add(getMove(fld));
		}
		return ans;
	}
	
	default List<Field> convertMovesToFields(List<IMove> input){
		List<Field> ans=new ArrayList<>();
		for (IMove move:input){
			ans.add(move.getNext());
		}
		return ans;
	}
	
	/**
	 * @return returns the list of possible moves
	 */
	List<IMove> getPossibleMoves();

	default List<Field> getPossibleFields(){
		return convertMovesToFields(getPossibleMoves());
	}
	
	default List<Field> getFieldsOfInterest(Player pl){
		List<Field> fields=new ArrayList<>();
		List<IPiece> pieces=pl.getPieces();
		for (IPiece piece:pieces) {
			if (piece.getPossibleFields().contains(getField())) {
				fields.add(piece.getField());
			}
		}
		return fields;
	}
	
	default List<IMove> getPossibleMovesOfInterest() {
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
		IPiece piece=getReferee().getMarked().getPiece();
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
	
	default Player getPlayer() {
		if (getCol()==Colors.WHITE) {
			return getGame().getWhite();
		}
		return getGame().getBlack();
	}

	default Player getOpponent() {
		if (getCol()==Colors.WHITE) {
			return getGame().getBlack();
		}
		return getGame().getWhite();
	}

	default public int getPosI() {
		return getField().getI();
	}

	default public int getPosJ() {
		return getField().getJ();
	}
	
	public PImage getImage();
	
	/**
	 * Setter for the pimage
	 * @param image the pimage
	 */
	public void setImage(PImage image);

	/**
	 * Getter for the field
	 * @return the field
	 */
	public Field getField();

	/**
	 * Setter for the field
	 * @param field the field
	 */
	public void setField(Field field);

	/**
	 * Getter for the color
	 * @return the color
	 */
	public Colors getCol();
	
	/**
	 * Creator of a move
	 * @param field the field to move on
	 * @return the created move
	 */
	public IMove getMove(Field field);
	
	/**
	 * Draws the piece
	 * 
	 * @param main the main papplet
	 */
	public void draw(Main main);

	public void die();
	
	Ids getId();

	void reset();

	Field getFirstField();

	
}
