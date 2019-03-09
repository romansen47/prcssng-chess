package defs.classes;

import pieces.Pawn;

public class EnPassant extends Move {

	/**
	 * the piece killed while moving to next field is a pawn.. for sure!
	 */
	final Pawn slaughtered;

	/**
	 * Constructor of an EnPassant
	 *
	 * @param fig  first pawn
	 * @param fig2 second pawn
	 * @param fld  the field of the moving pawn
	 */
	public EnPassant(Pawn fig, Pawn fig2, Field fld) {
		super(fig, fld);
		this.slaughtered = fig2;
	}

	@Override
	public void execute() {
		this.slaughtered.die();
		super.execute();
	}

}
