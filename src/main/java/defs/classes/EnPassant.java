package defs.classes;

import pieces.Pawn;

public class EnPassant extends Move {

	/**
	 * The piece we move is a pawn. for sure.
	 */
	final Pawn pawn;

	/**
	 * Constructor of an EnPassant
	 * 
	 * @param fig  first pawn
	 * @param fig2 second pawn
	 * @param fld  the field of the moving pawn
	 */
	public EnPassant(Pawn fig, Pawn fig2, Field fld) {
		super(fig, fld);
		this.pawn = fig2;
	}

	@Override
	public void execute() {
		this.pawn.die();
		super.execute();
	}

}
