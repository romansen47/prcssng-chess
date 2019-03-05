package artint;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import defs.classes.Field;
import defs.classes.Player;
import defs.enums.Colors;
import defs.interfaces.IMove;
import defs.interfaces.IPiece;

public class RandomPlayer extends Player{

	final Random random=new Random();
	public RandomPlayer() {
		super(Colors.BLACK);
	}

	public IMove randomMove() {
		List<IMove> moves=new ArrayList<>();
		for (IPiece piece:getPieces()){
			List<IMove> tmpmoves=piece.getPossibleMoves();
			for (IMove move:tmpmoves){
				if (move.getNext()!=piece.getField()){
					moves.addAll(tmpmoves);
				}
			}
		}
		int i=random.nextInt(moves.size()-1);
		return moves.get(i);
	}
	
}
