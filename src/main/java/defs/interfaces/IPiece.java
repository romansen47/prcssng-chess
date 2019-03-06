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

	/**
	 * Simple converter
	 * 
	 * @param input List of fields to convert to moves
	 * @return List of moves
	 */
	default List<IMove> convertFieldsToMoves(List<Field> input) {
		List<IMove> ans = new ArrayList<>();
		for (Field fld : input) {
			ans.add(getMove(fld));
		}
		return ans;
	}

	/**
	 * Simple converter
	 * 
	 * @param input List of moves to extract the fields from
	 * @return List of Fields
	 */
	default List<Field> convertMovesToFields(List<IMove> input) {
		List<Field> ans = new ArrayList<>();
		for (IMove move : input) {
			ans.add(move.getNext());
		}
		return ans;
	}

	/**
	 * creates an empty list of fields
	 * 
	 * @return
	 */
	default public List<Field> createList() {
		List<Field> lst = new ArrayList<Field>();
		return lst;
	}

	/**
	 * Pieces can die, when they are hit. what happens then?
	 */
	default void die() {
		getField().setPiece(null);
		this.getOwner().getPieces().remove(this);
	}

	/**
	 * Draws the piece
	 * 
	 * @param main the main papplet
	 */
	void draw(Main main);

	/**
	 * 
	 * @return returns list attacking fields
	 */
	default List<Field> getAttackers() {
		return getSpecialFields(getOpponent());
	}

	/**
	 * Getter for the color
	 * 
	 * @return the color
	 */
	public Colors getCol();

	/**
	 * Getter for the field
	 * 
	 * @return the field
	 */
	public Field getField();

	/**
	 * Getter for the field the piece stood on before the game started
	 * 
	 * @return firstField
	 */
	Field getFirstField();

	/**
	 * getter for the Id
	 * 
	 * @return the id
	 */
	Ids getId();

	/**
	 * Getter for pimage of the piece
	 * 
	 * @return the pimage
	 */
	public PImage getImage();

	/**
	 * Creator of a move
	 * 
	 * @param field the field to move on
	 * @return the created move
	 */
	public IMove getMove(Field field);

	/**
	 * getter for the opponent
	 * 
	 * @return the opponent of the player the piece belongs to
	 */
	default Player getOpponent() {
		if (getCol() == Colors.WHITE) {
			return getGame().getBlack();
		}
		return getGame().getWhite();
	}

	/**
	 * getter for the player the piece belongs to. NOT the acting player
	 * 
	 * @return
	 */
	default Player getOwner() {
		if (getCol() == Colors.WHITE) {
			return getGame().getWhite();
		}
		return getGame().getBlack();
	}

	/**
	 * the piece does not have any posotion but the underlying field does
	 * 
	 * @return the row coordinate of the field the piece is standing on
	 */
	default public int getPosI() {
		return getField().getI();
	}

	/**
	 * the piece does not have any posotion but the underlying field does
	 * 
	 * @return the column coordinate of the field the piece is standing on
	 */
	default public int getPosJ() {
		return getField().getJ();
	}

	/**
	 * get possible moves as a list of fields
	 * 
	 * @return list of fields
	 */
	default List<Field> getPossibleFields() {
		return convertMovesToFields(getPossibleMoves());
	}

	/**
	 * @return returns the list of possible moves
	 */
	List<IMove> getPossibleMoves();

	/**
	 * 
	 * @param pl
	 * @return
	 */
	default List<Field> getSpecialFields(Player pl) {
		List<Field> fields = new ArrayList<>();
		List<IPiece> pieces = pl.getPieces();
		for (IPiece piece : pieces) {
			King king = null;
			boolean castling = true;
			if (piece instanceof King) {
				king = (King) piece;
				castling = king.isValidForCastling();
				king.setValidForCastling(false);
			}
			if (piece.getPossibleFields().contains(getField())) {
				fields.add(piece.getField());
			}
			if (piece instanceof King) {
				king.setValidForCastling(castling);
			}
		}
		return fields;
	}

	/**
	 * get also special moves like castling or enpassant. the generic piece does not
	 * have any special moves
	 * 
	 * @return list of moves
	 */
	default List<IMove> getSpecialMoves() {
		return getPossibleMoves();
	}

	/**
	 * List of fields with friends on them guarding the piece
	 * 
	 * @return
	 */
	default List<Field> getSupporters() {
		IPiece piece = this;// getReferee().getMarked().getPiece();
		Colors col = Colors.WHITE;
		if (piece.getCol() == col) {
			col = Colors.BLACK;
		}
		Field field = piece.getField();
		Piece fakepiece = new Pawn(col, field);
		field.setPiece(fakepiece);
		getOwner().getPieces().remove(piece);
		getOwner().getPieces().add(fakepiece);
		List<Field> tmpFields = piece.getSpecialFields(getOwner());
		getOwner().getPieces().remove(fakepiece);
		getOwner().getPieces().add(piece);
		field.setPiece(piece);
		return tmpFields;
	}

	/**
	 * resets to the first move
	 */
	void reset();

	/**
	 * Setter for the field
	 * 
	 * @param field the field
	 */
	public void setField(Field field);

	/**
	 * Setter for the pimage
	 * 
	 * @param image the pimage
	 */
	public void setImage(PImage image);
}
