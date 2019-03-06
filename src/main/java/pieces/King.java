package pieces;

import java.util.ArrayList;
import java.util.List;

import conf.Config;
import defs.classes.Castling;
import defs.classes.Field;
import defs.classes.Piece;
import defs.classes.Player;
import defs.enums.Colors;
import defs.enums.Ids;
import defs.enums.State;
import defs.interfaces.IMove;
import defs.interfaces.IPiece;

public class King extends Piece {

	/**
	 * King carries around the information if he is valid for castling
	 */
	private boolean isValidForCastling = true;

	/**
	 * king has a state
	 */
	private State state = State.Plain;

	/**
	 * Constructor
	 * 
	 * @param col   the color
	 * @param field the field
	 */
	public King(Colors col, Field field) {
		super(Ids.KING, col, field);
	}

	/**
	 * @param fld the field to be checked for validity
	 * @param lst the list of fields already validated
	 * 
	 * @return returns true, if field is valid and adds field to the list of valid
	 *         fields. returns false and does nothing, otherwise.
	 */
	@Override
	public boolean checkIfOccupiedByFriend(Field fld, List<Field> lst) {
		if (fld.getPiece() != null && fld.getPiece().getCol() == getCol()) {
			return false;
		}
		lst.add(fld);
		return true;
	}

	/**
	 * the forecast for the king which fields he may not enter
	 * 
	 * @return the fields the king may not enter
	 */
	public List<Field> getAllAttackedFields() {
		List<Field> fields = new ArrayList<>();
		for (IPiece piece : getOpponent().getPieces()) {
			fields.addAll(piece.convertMovesToFields(piece.getSpecialMoves()));
		}
		return fields;
	}

	@Override
	public List<Field> getAttackers() {
		Player pl = getOpponent();
		List<Field> fields = new ArrayList<>();
		List<IPiece> pieces = pl.getPieces();
		for (IPiece piece : pieces) {
			if (!(piece instanceof King) && piece.convertMovesToFields(piece.getPossibleMoves()).contains(getField())) {
				fields.add(piece.getField());
			}
		}
		return fields;
	}

	/**
	 * in a check for validity for a castling the fields between the king and the
	 * rook must not be being attacked
	 * 
	 * @param fld1 first field
	 * @param fld2 second field
	 * @return the fields in between
	 */
	public List<Field> getFieldsInBetween(Field fld1, Field fld2) {
		int k = fld1.getJ();
		int r = fld2.getJ();
		if (r <= k) {
			return getFieldsInBetween(fld2, fld1);
		}
		List<Field> fields = new ArrayList<>();
		for (int i = 1; i < r - k; i++) {
			fields.add(getGame().getField(fld1.getI(), k + i));
		}
		return fields;
	}

	/**
	 * @param field the field to move on
	 * @return returns the move. returns subtype castling in case of validity
	 */
	@Override
	public IMove getMove(Field field) {
		Rook rook = getRook(field);
		if (rook != null) {
			return new Castling(this, rook);
		}
		return super.getMove(field);
	}

	/**
	 * @return returns the list of possible moves excluding the castling moves
	 */
	@Override
	public List<IMove> getPossibleMoves() {
		List<IMove> lst = getSpecialMoves();
		int tempI = getField().getI();
		int tempJ = getField().getJ();
		if (isValidForCastling()) {
			if (tempJ + 2 <= Config.GAMESIZE && getGame().getField(tempI, tempJ + 1).getPiece() == null
					&& isValidForCastling(getGame().getField(tempI, tempJ + 2))) {
				lst.add(getMove(getGame().getField(tempI, tempJ + 2)));
			}
			if (tempJ - 2 >= 0 && getGame().getField(tempI, tempJ - 1).getPiece() == null
					&& isValidForCastling(getGame().getField(tempI, tempJ - 2))) {
				lst.add(getMove((getGame().getField(tempI, tempJ - 2))));
			}
		}
		lst.remove(null);
		return lst;
	}

	/**
	 * 
	 * @param field rooks field
	 * @return the rook
	 */
	public Rook getRook(Field field) {
		Rook rook = null;
		if (isValidForCastling()) {
			int k = this.getPosJ();
			int r = field.getJ();
			if (Math.abs(r - k) > 1) {
				if (r < k) {
					r = 0;
				} else {
					r = Config.GAMESIZE;
				}
			}
			Field tmpField = getGame().getField(getPosI(), r);
			if (tmpField.getPiece() != null && tmpField.getPiece().getId() == Ids.ROOK
					&& tmpField.getPiece().getCol() == getCol()) {
				rook = (Rook) tmpField.getPiece();
			}
		}
		return rook;
	}

	/**
	 * Must be rewritten in order to avoid cycles
	 */
	@Override
	public List<IMove> getSpecialMoves() {
		List<Field> lst = new ArrayList<>();
		int tempI = getField().getI();
		int tempJ = getField().getJ();
		if (tempI - 1 >= 0) {
			checkIfOccupiedByFriend(getGame().getField(tempI - 1, tempJ), lst);
			if (tempJ - 1 >= 0) {
				checkIfOccupiedByFriend(getGame().getField(tempI - 1, tempJ - 1), lst);
			}
			if (tempJ + 1 <= Config.GAMESIZE) {
				checkIfOccupiedByFriend(getGame().getField(tempI - 1, tempJ + 1), lst);
			}
		}
		if (tempI + 1 <= Config.GAMESIZE) {
			checkIfOccupiedByFriend(getGame().getField(tempI + 1, tempJ), lst);
			if (tempJ - 1 >= 0) {
				checkIfOccupiedByFriend(getGame().getField(tempI + 1, tempJ - 1), lst);
			}
			if (tempJ + 1 <= Config.GAMESIZE) {
				checkIfOccupiedByFriend(getGame().getField(tempI + 1, tempJ + 1), lst);
			}
		}
		if (tempJ + 1 <= Config.GAMESIZE) {
			checkIfOccupiedByFriend(getGame().getField(tempI, tempJ + 1), lst);
		}
		if (tempJ - 1 >= 0) {
			checkIfOccupiedByFriend(getGame().getField(tempI, tempJ - 1), lst);
		}
		return convertFieldsToMoves(lst);

	}

	/**
	 * getter for the state
	 * 
	 * @return the state
	 */
	public State getState() {
		return state;
	}

	/**
	 * 
	 * @return returns, whether king is under enemy attack
	 */
	public boolean isChecked() {
		return !getAttackers().isEmpty();
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
	 * @return returns, whether the field contains a rook of same color, whether
	 *         rook has already been attacked and if one of the fields in between is
	 *         under enemy attack.
	 */
	public boolean isValidForCastling(Field field) {
		Rook rook = getRook(field);
		if (rook != null && rook.isValidForCastling()) {
			List<Field> fields = getFieldsInBetween(this.getField(), rook.getField());
			for (Field fld : fields) {
				if (fld.getPiece() != null || getAllAttackedFields().contains(fld)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public void reset() {
		super.reset();
		setValidForCastling(true);
	}

	/**
	 * setter for the state
	 * 
	 * @param state the state
	 */
	public void setState(State state) {
		if (state == State.Chess) {
			setValidForCastling(false);
		}
		this.state = state;
	}

	/**
	 * setter for isValidForCastling
	 * 
	 * @param isValidForCastling isValidForCastling
	 */
	public void setValidForCastling(boolean isValidForCastling) {
		this.isValidForCastling = isValidForCastling;
	}
}
