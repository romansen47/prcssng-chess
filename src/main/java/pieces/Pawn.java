package pieces;

import java.util.ArrayList;
import java.util.List;

import defs.classes.Castling;
import defs.classes.EnPassant;
import defs.classes.Field;
import defs.classes.Move;
import defs.classes.Piece;
import defs.enums.Colors;
import defs.enums.Ids;
import defs.interfaces.IMove;
import defs.interfaces.IPiece;

public class Pawn extends Piece {

	public Pawn(Colors col, Field field) {
		super(Ids.Bauer, col, field);
	}

	@Override
	public List<IMove> getPossibleMoves() {
		List<Field> lst = new ArrayList<>();
		lst.add(this.getField());
		if (this.getCol() == Colors.WHITE) {
			if (getField().getI() < 7 ) {
				if (getPosJ() < 7 && getSpiel().getField(getPosI()+1, getPosJ() + 1).getPiece() != null
						&& getSpiel().getField(getPosI()+1, getPosJ() + 1).getPiece().getCol() == Colors.BLACK) {
					lst.add(getSpiel().getField(getPosI()+1, getPosJ() + 1));
				}
				if (getPosJ() > 0 && getSpiel().getField(getPosI()+1, getPosJ() - 1).getPiece() != null
						&& getSpiel().getField(getPosI()+1, getPosJ() - 1).getPiece().getCol() == Colors.BLACK) {
					lst.add(getSpiel().getField(getPosI()+1, getPosJ() - 1));
				}
				if (getSpiel().getField(getPosI()+1, getPosJ()).getPiece() == null) {
					lst.add(getSpiel().getField(getPosI()+1, getPosJ()));
					if (getField().getI() == 1) {
						if (getSpiel().getField(getPosI()+2, getPosJ()).getPiece() == null) {
							lst.add(getSpiel().getField(getPosI()+2, getPosJ()));
						}
					}
				}
			}
		} 
		else {
			if (getField().getI() > 0) {
				if (getPosJ() < 7 && getSpiel().getField(getPosI()-1, getPosJ() + 1).getPiece() != null
						&& getSpiel().getField(getPosI()-1, getPosJ() + 1).getPiece().getCol() == Colors.WHITE) {
					lst.add(getSpiel().getField(getPosI()-1, getPosJ() + 1));
				}
				if (getPosJ() > 0 && getSpiel().getField(getPosI()-1, getPosJ() - 1).getPiece() != null
						&& getSpiel().getField(getPosI()-1, getPosJ() - 1).getPiece().getCol() == Colors.WHITE) {
					lst.add(getSpiel().getField(getPosI()-1, getPosJ() - 1));
				}
				if (getSpiel().getField(getPosI()-1, getPosJ()).getPiece() == null) {
					lst.add(getSpiel().getField(getPosI()-1, getPosJ()));
					if (getField().getI() == 6) {
						if (getSpiel().getField(getPosI()-2, getPosJ()).getPiece() == null) {
							lst.add(getSpiel().getField(getPosI()-2, getPosJ()));
						}
					}
				}
			}
		}
		IMove move=this.getOpponent().getLastMove();
		if (move!=null && move.getFig().getId()==Ids.Bauer && getPosI()==4) {
			if (move.getPrev().getI()==6 && move.getNext().getI()==4) {
				lst.add(getSpiel().getField(5,move.getNext().getJ()));
			}
		}
		if (move!=null && move.getFig().getId()==Ids.Bauer && getPosI()==3) {
			if (move.getPrev().getI()==1 && move.getNext().getI()==3) {
				lst.add(getSpiel().getField(2,move.getNext().getJ()));
			}
		}
		
		List<IMove> list=new ArrayList<>();
		for (Field fld:lst){
			list.add(getMove(fld));
		}
		
		return list;
	}
	
	/**
	 * @param field the field to move on
	 * @return returns the move. returns subtype enpassant in case of validity
	 */
	@Override
	public IMove getMove(Field field) {
		IPiece fig=getSpiel().getField(getPosI(),field.getJ()).getPiece();
		if (fig!=null && fig instanceof Pawn && fig.getCol()!=getCol()) {
			return new EnPassant(this,(Pawn)fig,field);
		}
		return super.getMove(field);
	}


}
