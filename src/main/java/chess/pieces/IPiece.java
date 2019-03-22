package chess.pieces;

import java.util.ArrayList;
import java.util.List;

import chess.IRefs;
import chess.Main;
import chess.moves.IMove;
import defs.classes.Field;
import defs.classes.Game;
import defs.enums.Colors;
import defs.enums.Ids;
import defs.players.IPlayer;
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
		return new ArrayList<>();
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
		List<Field> attackers = new ArrayList<>();
		for (IPiece piece : Game.getOpponent().getPieces()) {
			List<IMove> possibleMoves = piece.getSimpleMoves();
			for (IMove move : possibleMoves) {
				if (move.getNext() == this.getField()) {
					attackers.add(piece.getField());
				}
			}
		}
		return attackers;
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
	default IPlayer getOpponent() {
		if (this.getCol() == Colors.WHITE) {
			return Game.getBlack();
		}
		return Game.getWhite();
	}

	/**
	 * getter for the player the piece belongs to. NOT the acting player
	 *
	 * @return the player the piece belongs to
	 */
	default IPlayer getOwner() {
		if (this.getCol() == Colors.WHITE) {
			return Game.getWhite();
		}
		return Game.getBlack();
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
		final List<IMove> moves = getSimpleMoves();
		moves.addAll(getSpecialMoves());
		return moves;
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
	 * @param player the player
	 * @return list of special moves
	 */
	default List<Field> getSpecialFields(IPlayer player) {
		return new ArrayList<>();
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
		final IPiece piece = this;
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
