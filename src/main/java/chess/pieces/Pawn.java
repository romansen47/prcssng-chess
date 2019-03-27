package chess.pieces;

import java.util.ArrayList;
import java.util.List;

import chess.Config;
import chess.Drawer;
import chess.moves.EnPassant;
import chess.moves.IMove;
import chess.moves.Promotion;
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
		super(Ids.PAWN, col, field, 30);
	}

	/**
	 * @param field the field to move on
	 * @return returns the move. returns subtype enpassant in case of validity
	 */
	@Override
	public IMove getMove(Field field) {
		final IPiece fig = getGame().getField(getPosI(), field.getJ()).getPiece();
		if (lastMoveValidForEnPassant(fig)) {
			Field fld;
			if (fig.getPosI() == 3) {
				fld = getReferee().getGame().getChessboard()[2][fig.getPosJ()];
			} else {
				fld = getReferee().getGame().getChessboard()[5][fig.getPosJ()];
			}
			return new EnPassant(this, fld);
		} else if (field.getI() == 0 || field.getI() == 7) {
			final Ids id = drawer.choose();
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
		if (fig == null || getGame().getMoveList().isEmpty()) {
			return false;
		} else {
			lastMove = getGame().getMoveList().get(getGame().getMoveList().size() - 1);
			movedTwoFields = Math.abs(lastMove.getPrev().getI() - lastMove.getNext().getI()) == 2;
		}
		final boolean isPawn = fig instanceof Pawn;
		final boolean differentColor = fig.getCol() != getCol();
		final boolean lastMovedIsPawn = lastMove.getPiece() == fig;
		return isPawn && differentColor && lastMovedIsPawn && movedTwoFields;
	}

	@Override
	public List<IMove> getSimpleMoves() {
		final List<IMove> lst = new ArrayList<>();
		if (getCol() == Colors.WHITE) {
			lst.addAll(getSimpleMovesForWhite());
		} else {
			lst.addAll(getSimpleMovesForBlack());
		}
		return lst;
	}

	private List<IMove> getSimpleMovesForWhite() {
		final List<IMove> lst = new ArrayList<>();
		if (getField().getI() < Config.GAMESIZE) {
			lst.addAll(getSimpleMoves(true));
		}
		return lst;
	}

	private List<IMove> getSimpleMovesForBlack() {
		final List<IMove> lst = new ArrayList<>();
		if (getField().getI() > 0) {
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
		if ((getPosJ() < Config.GAMESIZE) && (getGame().getField(getPosI() + factor, getPosJ() + 1).getPiece() != null)
				&& (getGame().getField(getPosI() + factor, getPosJ() + 1).getPiece().getCol() == color)) {
			lst.add(getGame().getField(getPosI() + factor, getPosJ() + 1));
		}
		if ((getPosJ() > 0) && (getGame().getField(getPosI() + factor, getPosJ() - 1).getPiece() != null)
				&& (getGame().getField(getPosI() + factor, getPosJ() - 1).getPiece().getCol() == color)) {
			lst.add(getGame().getField(getPosI() + factor, getPosJ() - 1));
		}
		if (getGame().getField(getPosI() + factor, getPosJ()).getPiece() == null) {
			lst.add(getGame().getField(getPosI() + factor, getPosJ()));
			if (getField().getI() == getFirstField().getI()) {
				if (getGame().getField(getPosI() + 2 * factor, getPosJ()).getPiece() == null) {
					lst.add(getGame().getField(getPosI() + 2 * factor, getPosJ()));
				}
			}
		}
		return convertFieldsToMoves(lst);
	}

	@Override
	public List<IMove> getSpecialMoves() {
		final List<Field> lst = new ArrayList<>();
		final IMove move = getOpponent().getLastMove();
		if ((move != null) && (move.getPiece().getId() == Ids.PAWN) && (getPosI() == 4) && (move.getPrev().getI() == 6)
				&& (move.getNext().getI() == 4)) {
			lst.add(getGame().getField(5, move.getNext().getJ()));
		}
		if ((move != null) && (move.getPiece().getId() == Ids.PAWN) && (getPosI() == 3) && (move.getPrev().getI() == 1)
				&& (move.getNext().getI() == 3)) {
			lst.add(getGame().getField(2, move.getNext().getJ()));
		}
		final List<IMove> list = new ArrayList<>();
		for (final Field fld : lst) {
			list.add(getMove(fld));
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
