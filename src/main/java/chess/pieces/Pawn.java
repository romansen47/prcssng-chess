package chess.pieces;

import java.util.ArrayList;
import java.util.List;

import chess.Config;
import chess.Drawer;
import chess.moves.EnPassant;
import chess.moves.IMove;
import chess.moves.Move;
import defs.classes.Field;
import defs.enums.Colors;
import defs.enums.Ids;

public class Pawn extends Piece {

	private static Drawer drawer;

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
		if (lastMoveValidForEnPassant(fig)) {
			Field fld;
			if (fig.getPosI() == 3) {
				fld = getReferee().getGame().getChessboard()[2][fig.getPosJ()];
			} else {
				fld = getReferee().getGame().getChessboard()[5][fig.getPosJ()];
			}
			return new EnPassant(this, fld);
		} else if (field.getI() == 0 || field.getI() == 7) {
			Ids id = drawer.choose();
			return new Promotion(getField(), field, id);
		}
		return super.getMove(field);
	}

	private boolean lastMoveValidForEnPassant(IPiece fig) {
		
		/**
		 * get the last move, if exists
		 */
		boolean movedTwoFields;
		IMove lastMove;
		if (fig==null || getGame().getMoveList().isEmpty()) {
			return false;
		}
		else {
			lastMove=getGame().getMoveList().get(getGame().getMoveList().size()-1);
			movedTwoFields=Math.abs(lastMove.getPrev().getI()-lastMove.getNext().getI())==2;
		}
		boolean isPawn=fig instanceof Pawn;
		boolean differentColor= fig.getCol() != this.getCol();
		boolean lastMovedIsPawn=lastMove.getPiece()==fig;
		return isPawn&& differentColor && lastMovedIsPawn && movedTwoFields;
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

	private List<IMove> getSimpleMovesForWhite() {
		final List<IMove> lst = new ArrayList<>();
		if (this.getField().getI() < Config.GAMESIZE) {
			lst.addAll(getSimpleMoves(true));
		}
		return lst;
	}

	private List<IMove> getSimpleMovesForBlack() {
		final List<IMove> lst = new ArrayList<>();
		if (this.getField().getI() > 0) {
			lst.addAll(getSimpleMoves(false));
		}
		return lst;
	}

	private List<IMove> getSimpleMoves(boolean iswhite) {
		final List<Field> lst = new ArrayList<>();
		Colors color = Colors.BLACK;
		int factor = 1;
		if (!iswhite) {
			factor = -1;
			color = Colors.WHITE;
		}
		if ((this.getPosJ() < Config.GAMESIZE)
				&& (this.getGame().getField(this.getPosI() + factor, this.getPosJ() + 1).getPiece() != null)
				&& (this.getGame().getField(this.getPosI() + factor, this.getPosJ() + 1).getPiece()
						.getCol() == color)) {
			lst.add(this.getGame().getField(this.getPosI() + factor, this.getPosJ() + 1));
		}
		if ((this.getPosJ() > 0)
				&& (this.getGame().getField(this.getPosI() + factor, this.getPosJ() - 1).getPiece() != null)
				&& (this.getGame().getField(this.getPosI() + factor, this.getPosJ() - 1).getPiece()
						.getCol() == color)) {
			lst.add(this.getGame().getField(this.getPosI() + factor, this.getPosJ() - 1));
		}
		if (this.getGame().getField(this.getPosI() + factor, this.getPosJ()).getPiece() == null) {
			lst.add(this.getGame().getField(this.getPosI() + factor, this.getPosJ()));
			if (this.getField().getI() == this.getFirstField().getI()) {
				if (this.getGame().getField(this.getPosI() + 2 * factor, this.getPosJ()).getPiece() == null) {
					lst.add(this.getGame().getField(this.getPosI() + 2 * factor, this.getPosJ()));
				}
			}
		}
		return convertFieldsToMoves(lst);
	}

	@Override
	public List<IMove> getSpecialMoves() {
		final List<Field> lst = new ArrayList<>();
		final IMove move = this.getOpponent().getLastMove();
		if ((move != null) && (move.getPiece().getId() == Ids.PAWN) && (this.getPosI() == 4)
				&& (move.getPrev().getI() == 6) && (move.getNext().getI() == 4)) {
			lst.add(this.getGame().getField(5, move.getNext().getJ()));
		}
		if ((move != null) && (move.getPiece().getId() == Ids.PAWN) && (this.getPosI() == 3)
				&& (move.getPrev().getI() == 1) && (move.getNext().getI() == 3)) {
			lst.add(this.getGame().getField(2, move.getNext().getJ()));
		}
		final List<IMove> list = new ArrayList<>();
		for (final Field fld : lst) {
			list.add(this.getMove(fld));
		}
		return list;
	}

	/**
	 * @return the drawer
	 */
	public static Drawer getDrawer() {
		return drawer;
	}

	/**
	 * @param drawer the drawer to set
	 */
	public static void setDrawer(Drawer drawer) {
		Pawn.drawer = drawer;
	}

}
