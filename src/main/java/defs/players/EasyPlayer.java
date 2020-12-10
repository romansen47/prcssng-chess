package defs.players;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.game.moves.IMove;
import chess.game.pieces.IPiece;
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
		final List<IMove> tmpmoves = new ArrayList<>();
		for (final IPiece piece : getPieces()) {
			for (final IMove move : piece.getPossibleMoves()) {
				if (move.getNext() != piece.getField()) {
					tmpmoves.add(move);
				}
			}
		}
		final ArrayList<IMove> moves = new ArrayList<>();
		for (IMove move : tmpmoves) {
			if (getReferee().getValidMove(move) != null) {
				moves.add(move);
			}
		}
		final ArrayList<IMove> newMoves = IPlayer.removeDuplicates(moves);
		return evaluateMoveListAlternative(newMoves);
	}

	private IMove evaluateMoveListAlternative(ArrayList<IMove> moves) {
		Map<IMove, Integer> valuesMap = new HashMap<>();
		int max = 0;
		IMove moveTmp = null;
		for (IMove move : moves) {
			int value = evaluate(move);
			if (value > max) {
				max = value;
				moveTmp = move;
			}
			valuesMap.put(move, value);
		}
		return moveTmp;
	}

	public int evaluate(IMove a) {
		System.out.print("evaluating " + a.toString() + ": ");
		long time = System.currentTimeMillis();
		a.execute();
		getReferee().switchMainPlayer();
		Map<IPiece, List<IMove>> possibleMoves = getReferee().createPossibleValidMovesForActivePieces();
		List<IMove> movesToList = new ArrayList<>();
		for (IPiece piece : possibleMoves.keySet()) {
			movesToList.addAll(possibleMoves.get(piece));
		}
		int amountOfPossibleMovesAfterExecution = movesToList.size() * 50;
		final int ans = amountOfPossibleMovesAfterExecution + 100
				* (evaluatePieces(getPieces()) - evaluatePieces(getReferee().getGame().getOpponent().getPieces()));
		getReferee().rewindLastMove();
		System.out.println("done - " + (System.currentTimeMillis() - time) + " millis. Value: " + ans);
		return ans;
	}

	public int evaluatePieces(List<IPiece> list) {
		int ans = 0;
		for (IPiece piece : list) {
			ans = ans + piece.getValue();
		}
		return ans;
	}
}
