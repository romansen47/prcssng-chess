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

	/**
	 * Constructor
	 *
	 * @param col   the color
	 * @param field the field
	 */
	public Pawn(Colors col, Field field) {
		super(Ids.PAWN, col, field);
	}

	/**
	 * @param field the field to move on
	 * @return returns the move. returns subtype enpassant in case of validity
	 */
	@Override
	public IMove getMove(Field field) {
		final IPiece fig = this.getGame().getField(this.getPosI(), field.getJ()).getPiece();
		if ((fig instanceof Pawn) && (fig.getCol() != this.getCol())) {
			return new EnPassant(this, (Pawn) fig, field);
		}
		return super.getMove(field);
	}

	@Override
	public List<IMove> getSimpleMoves() {
		final List<IMove> lst = new ArrayList<>();
		if (this.getCol() == Colors.WHITE) {
			lst.addAll(getSimpleMovesForWhite());
		} else {
			lst.addAll(getSimpleMovesForBlack());
		}
		return lst;
	}

	private List<IMove> getSimpleMovesForWhite(){
		final List<IMove> lst = new ArrayList<>();
		if (this.getField().getI() < Config.GAMESIZE) {
			lst.addAll(getSimpleMoves(true));
		}
		return lst;
	}
	
	private List<IMove> getSimpleMovesForBlack(){
		final List<IMove> lst = new ArrayList<>();
		if (this.getField().getI() > 0) {
			lst.addAll(getSimpleMoves(false));
		}
		return lst;
	}
	
	private List<IMove> getSimpleMoves(boolean iswhite){
		final List<Field> lst = new ArrayList<>();
		Colors color=Colors.BLACK;
		int factor=1;
		if (!iswhite) {
			factor=-1;
			color=Colors.WHITE;
		}
		if ((this.getPosJ() < Config.GAMESIZE)
				&& (this.getGame().getField(this.getPosI() +factor, this.getPosJ() + 1).getPiece() != null)
				&& (this.getGame().getField(this.getPosI() +factor, this.getPosJ() + 1).getPiece()
						.getCol() == color)) {
			lst.add(this.getGame().getField(this.getPosI() +factor, this.getPosJ() + 1));
		}
		if ((this.getPosJ() > 0)
				&& (this.getGame().getField(this.getPosI() +factor, this.getPosJ() - 1).getPiece() != null)
				&& (this.getGame().getField(this.getPosI() +factor, this.getPosJ() - 1).getPiece()
						.getCol() == color)) {
			lst.add(this.getGame().getField(this.getPosI() +factor, this.getPosJ() - 1));
		}
		if (this.getGame().getField(this.getPosI() +factor, this.getPosJ()).getPiece() == null) {
			lst.add(this.getGame().getField(this.getPosI() +factor, this.getPosJ()));
			if (this.getField().getI() == this.getFirstField().getI()) {
				if (this.getGame().getField(this.getPosI() +2*factor, this.getPosJ()).getPiece() == null) {
					lst.add(this.getGame().getField(this.getPosI() +2*factor, this.getPosJ()));
				}
			}
		}
		return convertFieldsToMoves(lst);
	}

	
	
	@Override
	public List<IMove> getSpecialMoves() {
		final List<Field> lst = new ArrayList<>();
		final IMove move = this.getOpponent().getLastMove();
		if ((move != null) && (move.getFig().getId() == Ids.PAWN) && (this.getPosI() == 4)
				&& (move.getPrev().getI() == 6) && (move.getNext().getI() == 4)) {
			lst.add(this.getGame().getField(5, move.getNext().getJ()));
		}
		if ((move != null) && (move.getFig().getId() == Ids.PAWN) && (this.getPosI() == 3)
				&& (move.getPrev().getI() == 1) && (move.getNext().getI() == 3)) {
			lst.add(this.getGame().getField(2, move.getNext().getJ()));
		}
		final List<IMove> list = new ArrayList<>();
		for (final Field fld : lst) {
			list.add(this.getMove(fld));
		}
		return list;
	}

}
