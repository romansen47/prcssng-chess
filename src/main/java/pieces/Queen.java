package pieces;

import java.util.List;

import defs.classes.Field;
import defs.classes.Move;
import defs.classes.Piece;
import defs.enums.Colors;
import defs.enums.Ids;
import defs.interfaces.IMove;

public class Queen extends Piece {

	public Queen(Colors col, Field field){
		super(Ids.Dame, col, field);
	}

	@Override
	public List<IMove> getPossibleMoves() {
		Rook tmprook=new Rook(this.getCol(), this.getField());
		this.getField().setPiece(tmprook);
		List<IMove> lst1 = tmprook.getPossibleMoves();
		Bishop tmpbish=new Bishop(this.getCol(), this.getField());
		this.getField().setPiece(tmpbish);
		List<IMove> lst2 = tmpbish.getPossibleMoves();
		lst1.addAll(lst2);
		this.getField().setPiece(this);
		return lst1;
	}

}
