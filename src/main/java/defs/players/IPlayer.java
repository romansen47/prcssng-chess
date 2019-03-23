package defs.players;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import chess.Config;
import chess.IRefs;
import chess.moves.IMove;
import chess.pieces.Bishop;
import chess.pieces.IPiece;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Queen;
import chess.pieces.Rook;
import defs.enums.Colors;
import defs.enums.Ids;

public interface IPlayer extends IRefs {

	/**
	 * Get all pieces, the living and the dead
	 *
	 * @return allPieces
	 */
	List<IPiece> getAllPieces();

	/**
	 * Getter for color
	 *
	 * @return the color
	 */
	Colors getCol();

	/**
	 * getter for list of dead pieces
	 *
	 * @return the list of dead pieces
	 */
	List<IPiece> getDeadPieces();

	/**
	 * Getter for the king
	 *
	 * @return the king
	 */
	King getKing();

	/**
	 * method to get the last move the player performed
	 *
	 * @return the last move performed by the player
	 */
	default IMove getLastMove() {
		final int n = getMoveList().size();
		if (n > 0) {
			return getMoveList().get(n - 1);
		}
		return null;
	}

	/**
	 * Getter for the list of moves
	 *
	 * @return MoveList
	 */
	List<IMove> getMoveList();

	/**
	 * Getter of player's pieces
	 *
	 * @return list of player's pieces
	 */
	List<IPiece> getPieces();

	Random getRandom();

	/**
	 * First setup
	 */
	default void initialGeneration() {

		getPieces().add(getKing());
		int ersteReihe = 0;
		int zweiteReihe = 1;
		if (getCol() == Colors.BLACK) {
			ersteReihe = Config.GAMESIZE;
			zweiteReihe = 6;
		}
		for (int j = 0; j < 8; j++) {
			getPieces().add(new Pawn(getCol(), getGame().getField(zweiteReihe, j)));
		}

		getPieces().add(new Knight(getCol(), getGame().getField(ersteReihe, 1)));
		getPieces().add(new Knight(getCol(), getGame().getField(ersteReihe, 6)));
		getPieces().add(new Bishop(getCol(), getGame().getField(ersteReihe, 2)));
		getPieces().add(new Bishop(getCol(), getGame().getField(ersteReihe, 5)));
		getPieces().add(new Rook(getCol(), getGame().getField(ersteReihe, 0)));
		getPieces().add(new Rook(getCol(), getGame().getField(ersteReihe, Config.GAMESIZE)));
		getPieces().add(new Queen(getCol(), getGame().getField(ersteReihe, 3)));
		getAllPieces().addAll(getPieces());

	}

	/**
	 * Method to perform a random move. All possible moves are collected, random is
	 * used to choose one randomly
	 *
	 * @return a random valid move
	 */
	default IMove randomMove() {
		final ArrayList<IMove> moves = new ArrayList<>();
		for (final IPiece piece : getPieces()) {
			final List<IMove> tmpmoves = piece.getPossibleMoves();
			for (final IMove move : tmpmoves) {
				if (move.getNext() != piece.getField()) {
					moves.addAll(tmpmoves);
				}
			}
		}
		final ArrayList<IMove> newMoves = removeDuplicates(moves);
		final int i = getRandom().nextInt(newMoves.size() - 1);
		return newMoves.get(i);
	}

	// Function to remove duplicates from an ArrayList
	static ArrayList<IMove> removeDuplicates(ArrayList<IMove> moves) {

		// Create a new LinkedHashSet
		final Set<IMove> set = new LinkedHashSet<>();

		// Add the elements to set
		set.addAll(moves);

		// Clear the list
		moves.clear();

		// add the elements of set
		// with no duplicates to the list
		moves.addAll(set);

		// return the list
		return moves;
	}

	/**
	 * the player chooses the piece on promotion
	 *
	 * @return the id of the piece
	 */
	default Ids choose() {
//		List<Ids> ids=new ArrayList<>();
//		ids.add(Ids.BISHOP);
//		ids.add(Ids.KNIGHT);
//		ids.add(Ids.QUEEN);
//		ids.add(Ids.ROOK);
		return Ids.QUEEN;
	}

}
