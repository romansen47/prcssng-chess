package pieces;

import java.util.ArrayList;
import java.util.List;

import chess.Castling;
import chess.EnPassant;
import chess.Move;
import defs.classes.Field;
import defs.classes.Piece;
import defs.enums.Colors;
import defs.enums.Ids;

public class Pawn extends Piece {

	public Pawn(Colors col, Field field) {
		super(Ids.Bauer, col, field);
	}

	@Override
	public List<Move> getPossibleMoves() {
		List<Field> lst = new ArrayList<Field>();
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
		Move move=this.getOpponent().getLastMove();
		if (move!=null && move.fig.id==Ids.Bauer && getPosI()==4) {
			if (move.prev.getI()==6 && move.next.getI()==4) {
				lst.add(getSpiel().getField(5,move.next.getJ()));
			}
		}
		if (move!=null && move.fig.id==Ids.Bauer && getPosI()==3) {
			if (move.prev.getI()==1 && move.next.getI()==3) {
				lst.add(getSpiel().getField(2,move.next.getJ()));
			}
		}
		
		List<Move> list=new ArrayList<Move>();
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
	public Move getMove(Field field) {
		Pawn nextPawn=(Pawn)getSpiel().getField(getPosI(),field.getJ()).getPiece();
		if (nextPawn!=null && nextPawn.getCol()!=getCol()) {
			return new EnPassant(this,nextPawn,field);
		}
		return super.getMove(field);
	}


}
