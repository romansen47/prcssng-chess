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
		final List<IMove> ans = new ArrayList<>();
		for (final Field fld : input) {
			ans.add(this.getMove(fld));
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
		final List<Field> ans = new ArrayList<>();
		for (final IMove move : input) {
			ans.add(move.getNext());
		}
		return ans;
	}

	/**
	 * creates an empty list of fields
	 * 
	 * @return an empty list of fields
	 */
	default List<Field> createList() {
		final List<Field> lst = new ArrayList<>();
		return lst;
	}

	/**
	 * Pieces can die, when they are hit. what happens then?
	 */
	default void die() {
		this.getField().setPiece(null);
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
		return this.getSpecialFields(this.getOpponent());
	}

	/**
	 * Getter for the color
	 * 
	 * @return the color
	 */
	Colors getCol();

	/**
	 * Getter for the field
	 * 
	 * @return the field
	 */
	Field getField();

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
	PImage getImage();

	/**
	 * Creator of a move
	 * 
	 * @param field the field to move on
	 * @return the created move
	 */
	IMove getMove(Field field);

	/**
	 * getter for the opponent
	 * 
	 * @return the opponent of the player the piece belongs to
	 */
	default Player getOpponent() {
		if (this.getCol() == Colors.WHITE) {
			return this.getGame().getBlack();
		}
		return this.getGame().getWhite();
	}

	/**
	 * getter for the player the piece belongs to. NOT the acting player
	 * 
	 * @return the player the piece belongs to
	 */
	default Player getOwner() {
		if (this.getCol() == Colors.WHITE) {
			return this.getGame().getWhite();
		}
		return this.getGame().getBlack();
	}

	/**
	 * the piece does not have any posotion but the underlying field does
	 * 
	 * @return the row coordinate of the field the piece is standing on
	 */
	default int getPosI() {
		return this.getField().getI();
	}

	/**
	 * the piece does not have any posotion but the underlying field does
	 * 
	 * @return the column coordinate of the field the piece is standing on
	 */
	default int getPosJ() {
		return this.getField().getJ();
	}

	/**
	 * get possible moves as a list of fields
	 * 
	 * @return list of fields
	 */
	default List<Field> getPossibleFields() {
		return this.convertMovesToFields(this.getPossibleMoves());
	}

	/**
	 * @return returns the list of possible moves
	 */
	default List<IMove> getPossibleMoves() {
		return getSimpleMoves();
	}

	/**
	 * The list of simple moves the piece can perform. Castling and EnPassant are
	 * excluded!
	 * 
	 * @return the list of simple moves the piece can perform.
	 */
	List<IMove> getSimpleMoves();

	/**
	 * 
	 * @param pl the player
	 * @return list including the special moves
	 */
	default List<Field> getSpecialFields(Player pl) {
		final List<Field> fields = new ArrayList<>();
		final List<IPiece> pieces = pl.getPieces();
		for (final IPiece piece : pieces) {
			King king = null;
			boolean castling = true;
			if (piece instanceof King) {
				king = (King) piece;
				castling = king.isValidForCastling();
				king.setValidForCastling(false);
			}
			if (piece.getPossibleFields().contains(this.getField())) {
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
		return new ArrayList<>();
	}

	/**
	 * 
	 * @return List of fields with friends on them guarding the piece
	 */
	default List<Field> getSupporters() {
		final IPiece piece = this;// getReferee().getMarked().getPiece();
		Colors col = Colors.WHITE;
		if (piece.getCol() == col) {
			col = Colors.BLACK;
		}
		final Field field = piece.getField();
		final Piece fakepiece = new Pawn(col, field);
		field.setPiece(fakepiece);
		this.getOwner().getPieces().remove(piece);
		this.getOwner().getPieces().add(fakepiece);
		final List<Field> tmpFields = piece.getSpecialFields(this.getOwner());
		this.getOwner().getPieces().remove(fakepiece);
		this.getOwner().getPieces().add(piece);
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
	void setField(Field field);

	/**
	 * Setter for the pimage
	 * 
	 * @param image the pimage
	 */
	void setImage(PImage image);
}
