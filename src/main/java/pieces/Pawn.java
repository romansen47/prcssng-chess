package pieces;

import java.util.ArrayList;
import java.util.List;

import conf.Config;
import defs.classes.EnPassant;
import defs.classes.Field;
import defs.classes.Piece;
import defs.enums.Colors;
import defs.enums.Ids;
import defs.interfaces.IMove;
import defs.interfaces.IPiece;

public class Pawn extends Piece {

	public Pawn(Colors col, Field field) {
		super(Ids.PAWN, col, field);
	}

	@Override
	public List<IMove> getPossibleMoves() {
		List<Field> lst = new ArrayList<>();
		// lst.add(this.getField());
		if (this.getCol() == Colors.WHITE) {
			if (getField().getI() < Config.GAMESIZE) {
				if (getPosJ() < Config.GAMESIZE && getGame().getField(getPosI() + 1, getPosJ() + 1).getPiece() != null
						&& getGame().getField(getPosI() + 1, getPosJ() + 1).getPiece().getCol() == Colors.BLACK) {
					lst.add(getGame().getField(getPosI() + 1, getPosJ() + 1));
				}
				if (getPosJ() > 0 && getGame().getField(getPosI() + 1, getPosJ() - 1).getPiece() != null
						&& getGame().getField(getPosI() + 1, getPosJ() - 1).getPiece().getCol() == Colors.BLACK) {
					lst.add(getGame().getField(getPosI() + 1, getPosJ() - 1));
				}
				if (getGame().getField(getPosI() + 1, getPosJ()).getPiece() == null) {
					lst.add(getGame().getField(getPosI() + 1, getPosJ()));
					if (getField().getI() == 1) {
						if (getGame().getField(getPosI() + 2, getPosJ()).getPiece() == null) {
							lst.add(getGame().getField(getPosI() + 2, getPosJ()));
						}
					}
				}
			}
		} else {
			if (getField().getI() > 0) {
				if (getPosJ() < Config.GAMESIZE && getGame().getField(getPosI() - 1, getPosJ() + 1).getPiece() != null
						&& getGame().getField(getPosI() - 1, getPosJ() + 1).getPiece().getCol() == Colors.WHITE) {
					lst.add(getGame().getField(getPosI() - 1, getPosJ() + 1));
				}
				if (getPosJ() > 0 && getGame().getField(getPosI() - 1, getPosJ() - 1).getPiece() != null
						&& getGame().getField(getPosI() - 1, getPosJ() - 1).getPiece().getCol() == Colors.WHITE) {
					lst.add(getGame().getField(getPosI() - 1, getPosJ() - 1));
				}
				if (getGame().getField(getPosI() - 1, getPosJ()).getPiece() == null) {
					lst.add(getGame().getField(getPosI() - 1, getPosJ()));
					if (getField().getI() == 6) {
						if (getGame().getField(getPosI() - 2, getPosJ()).getPiece() == null) {
							lst.add(getGame().getField(getPosI() - 2, getPosJ()));
						}
					}
				}
			}
		}
		IMove move = this.getOpponent().getLastMove();
		if (move != null && move.getFig().getId() == Ids.PAWN && getPosI() == 4) {
			if (move.getPrev().getI() == 6 && move.getNext().getI() == 4) {
				lst.add(getGame().getField(5, move.getNext().getJ()));
			}
		}
		if (move != null && move.getFig().getId() == Ids.PAWN && getPosI() == 3) {
			if (move.getPrev().getI() == 1 && move.getNext().getI() == 3) {
				lst.add(getGame().getField(2, move.getNext().getJ()));
			}
		}

		List<IMove> list = new ArrayList<>();
		for (Field fld : lst) {
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
		IPiece fig = getGame().getField(getPosI(), field.getJ()).getPiece();
		if (fig instanceof Pawn && fig.getCol() != getCol()) {
			return new EnPassant(this, (Pawn) fig, field);
		}
		return super.getMove(field);
	}

}
