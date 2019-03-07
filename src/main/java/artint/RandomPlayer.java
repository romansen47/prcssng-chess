package artint;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import defs.classes.Player;
import defs.enums.Colors;
import defs.interfaces.IMove;
import defs.interfaces.IPiece;

/**
 * 
 * @author ubuntu
 *
 *         Implementation of a player moving randomly
 */
public class RandomPlayer extends Player {

	/**
	 * The random number generator
	 */
	final Random random = new Random();

	/**
	 * Constructor. The random Player is black
	 */
	public RandomPlayer() {
		super(Colors.BLACK);
	}

	/**
	 * Method to perform a random move. All possible moves are collected, random is
	 * used to choose one randomly
	 * 
	 * @return a random valid move
	 */
	public IMove randomMove() {
		final List<IMove> moves = new ArrayList<>();
		for (final IPiece piece : this.getPieces()) {
			final List<IMove> tmpmoves = piece.getPossibleMoves();
			for (final IMove move : tmpmoves) {
				if (move.getNext() != piece.getField()) {
					moves.addAll(tmpmoves);
				}
			}
		}
		final int i = this.random.nextInt(moves.size() - 1);
		return moves.get(i);
	}

}
