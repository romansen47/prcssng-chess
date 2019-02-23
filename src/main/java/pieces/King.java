package pieces;

import java.util.ArrayList;
import java.util.List;

import chess.Castling;
import chess.Main;
import chess.Move;
import conf.Config;
import defs.classes.Field;
import defs.classes.Piece;
import defs.classes.Player;
import defs.enums.Colors;
import defs.enums.Ids;

public class King extends Piece {
	
	private boolean isValidForCastling=true;

	public King(Colors col, Field field){
		super(Ids.Koenig, col, field);
	}


	/**
	 * @return returns the list of possible moves excluding the castling moves
	 */
	@Override
	public List<Field> getPossibleMoves() {
		List<Field> lst=getPossibleMovesOfInterest();
		int tempI = getField().getI();
		int tempJ = getField().getJ();
		if (tempJ + 2 <= 7 && getSpiel().getField(tempI, tempJ + 1).getPiece()==null
				&& isValidForCastling(getSpiel().getField(tempI, tempJ + 2))) {
			lst.add(getSpiel().getField(tempI, tempJ + 2));
		}
		if (tempJ - 2 >= 0 && getSpiel().getField(tempI, tempJ - 1).getPiece()==null
				&& isValidForCastling(getSpiel().getField(tempI, tempJ - 2))) {
			lst.add(getSpiel().getField(tempI, tempJ - 2));
		}
		
		return lst;
	}
	
	/**
	 *  Must be rewritten in order to avoid cycles
	 */
	public List<Field> getPossibleMovesOfInterest() {
		List<Field> lst = new ArrayList<Field>();
		lst.add(this.getField());
		int tempI = getField().getI();
		int tempJ = getField().getJ();
		if (tempI - 1 >= 0) {
			checkForValidity(getSpiel().getField(tempI - 1, tempJ), lst);
			if (tempJ - 1 >= 0) {
				checkForValidity(getSpiel().getField(tempI - 1, tempJ - 1), lst);
			}
			if (tempJ + 1 <= 7) {
				checkForValidity(getSpiel().getField(tempI - 1, tempJ + 1), lst);
			}
		}
		if (tempI + 1 <= 7) {
			checkForValidity(getSpiel().getField(tempI + 1, tempJ), lst);
			if (tempJ - 1 >= 0) {
				checkForValidity(getSpiel().getField(tempI + 1, tempJ - 1), lst);
			}
			if (tempJ + 1 <= 7) {
				checkForValidity(getSpiel().getField(tempI + 1, tempJ + 1), lst);
			}
		}
		if (tempJ + 1 <= 7) {
			checkForValidity(getSpiel().getField(tempI, tempJ + 1), lst);
		}
		if (tempJ - 1 >= 0) {
			checkForValidity(getSpiel().getField(tempI, tempJ - 1), lst);
		}

		
		return lst;

	}

	@Override
	public  List<Field> getAttackers() {
		Player pl=getOpponent();
		List<Field> fields=new ArrayList<Field>();
		List<Piece> pieces=pl.getPieces();
		for (Piece piece:pieces) {
			if (!(piece instanceof King)) {
				if (piece.getPossibleMoves().contains(getField())) {
					fields.add(piece.getField());
				}
			}
			else {
				if (((King)piece).getPossibleMovesOfInterest().contains(getField())) {
					fields.add(piece.getField());
				}
			}
		}
		return fields;
	}
	
	/**
	 * @param fld the field to be checked for validity
	 * @param lst the list of fields already validated
	 * 
	 * @return 	returns true, if field is valid and adds field to the list of valid fields. 
	 * 			returns false and does nothing, otherwise.
	 */
	@Override
	public boolean checkForValidity(Field fld, List<Field> lst) {
		if (fld.getPiece() != null && fld.getPiece().getCol() == getCol()) {
			return false;
		}
		lst.add(fld);
		return true;
	}

	/**
	 * 
	 * @return returns, whether king already has been moved or checked
	 */
	public boolean isValidForCastling() {
		return isValidForCastling;
	}
	
	/**
	 * 
	 * @param field the field to be checked for validity
	 * @return returns, whether the field contains a rook of same color, whether rook has already been attacked
	 * 			and if one of the fields in between is under enemy attack.
	 */
	public boolean isValidForCastling(Field field) {
		if (isValidForCastling) {
			Rook rook=getRook(field);
			if (rook!=null && rook.isValidForCastling()){
				List<Field> fields=getFieldsInBetween(this.getField(),rook.getField());
				List<Field> allAttackedFields=getAllAttackedFields();
				for (Field fld:fields) {
					if(allAttackedFields.contains(fld)) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}
	
	public List<Field> getAllAttackedFields(){
		List<Field> fields=new ArrayList<Field>();
		for (Piece piece:getOpponent().getPieces()) {
			fields.addAll(piece.getPossibleMovesOfInterest());
		}
		return fields;
	}
	
	public List<Field> getFieldsInBetween(Field fld1,Field fld2) {
		int k=fld1.getJ();
		int r=fld2.getJ();
		if (r<=k) {
			return getFieldsInBetween(fld2,fld1);
		}
		List<Field> fields=new ArrayList<Field>();
		for (int i=1;i<r-k;i++) {
			fields.add(getSpiel().getField(fld1.getI(), k+i));
		}
		return fields;
	}

	public Rook getRook(Field field) {
		Rook rook=null;
		if (isValidForCastling) {
			int k=this.getPosJ();
			int r=field.getJ();
			if (Math.abs(r-k)>1) {
				if (r<k) {
					r=0;
				}
				else {
					r=7;
				}
			}
			Field tmpField=getSpiel().getField(getPosI(), r);
			if (tmpField.getPiece()!=null && tmpField.getPiece().id==Ids.Turm 
					&& tmpField.getPiece().getCol()==getCol()) {
				rook=(Rook)tmpField.getPiece();
			}
		}
		return rook;
	}
	
	/**
	 * 
	 * @return returns, whether king is under enemy attack
	 */
	public boolean isChecked() {
		return !getAttackers().isEmpty();
	}
	
	/**
	 * @param field the field to move on
	 * @return returns the move. returns subtype castling in case of validity
	 */
	@Override
	public Move getMove(Field field) {
		Rook rook=getRook(field);
		if (rook!=null) {
			return new Castling(this,rook);
		}
		return super.getMove(field);
	}

}
