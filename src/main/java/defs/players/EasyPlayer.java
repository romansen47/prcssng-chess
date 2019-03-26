package defs.players;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import chess.moves.IMove;
import chess.pieces.IPiece;
import defs.classes.Game;
import defs.enums.Colors;
import defs.players.artint.RandomPlayer;

public class EasyPlayer extends RandomPlayer {

	final int depth;

	public EasyPlayer(Colors col, int depth) {
		super(col);
		this.depth = depth;
	}

	@Override
	public IMove randomMove() {
		final ArrayList<IMove> moves = new ArrayList<>();
		for (final IPiece piece : getPieces()) {
			final List<IMove> tmpmoves = piece.getPossibleMoves();
			for (final IMove move : tmpmoves) {
				if (move.getNext() != piece.getField()) {
					moves.addAll(tmpmoves);
				}
			}
		}
		final ArrayList<IMove> newMoves = IPlayer.removeDuplicates(moves);
		evaluateMoveList(moves);
		return newMoves.get(0);
	}

	private class SortByEval implements Comparator<IMove> {
		// Used for sorting in ascending order
		// of
		// roll number
		@Override
		public int compare(IMove a, IMove b) {
			return evaluate(a) - evaluate(b);
		}
	}

	private void evaluateMoveList(ArrayList<IMove> moves) {
		Collections.sort(moves, new SortByEval());
	}

	public int evaluate(IMove a) {
		a.execute();
		getReferee().switchMainPlayer();
		Map<IPiece, List<IMove>>	possibleMoves	= getReferee().createPossibleValidMovesForActivePieces();
		List<IMove>					movesToList		= new ArrayList<>();
		for (IPiece piece : possibleMoves.keySet()) {
			movesToList.addAll(possibleMoves.get(piece));
		}
		int			AmountOfPossibleMovesAfterExecution	= movesToList.size();
		int			DifferenceConcPieces				= getPieces().size() - Game.getOpponent().getPieces().size();
		final int	ans									= AmountOfPossibleMovesAfterExecution + DifferenceConcPieces;
		getReferee().rewindLastMove();
		return ans;
	}
}
