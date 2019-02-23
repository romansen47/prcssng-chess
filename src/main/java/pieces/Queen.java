package pieces;

import java.util.List;

import defs.classes.Field;
import defs.classes.Piece;
import defs.enums.Colors;
import defs.enums.Ids;

public class Queen extends Piece {

	public Queen(Colors col, Field field){
		super(Ids.Dame, col, field);
	}

	@Override
	public List<Field> getPossibleMoves() {
		Rook tmprook=new Rook(this.getCol(), this.getField());
		this.getField().setPiece(tmprook);
		List<Field> lst1 = tmprook.getPossibleMoves();
		Bishop tmpbish=new Bishop(this.getCol(), this.getField());
		this.getField().setPiece(tmpbish);
		List<Field> lst2 = tmpbish.getPossibleMoves();
		lst1.addAll(lst2);
		this.getField().setPiece(this);
		return lst1;
	}

}
