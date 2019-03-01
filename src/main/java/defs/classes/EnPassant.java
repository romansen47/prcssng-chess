package defs.classes;

import pieces.Pawn;
import defs.classes.Move;

public class EnPassant extends Move{

	final Pawn pawn;
	
	public EnPassant(Pawn fig, Pawn fig2, Field fld) {
		super(fig, fld);
		pawn=fig2;
	}
	
	@Override
	public void execute() {
		pawn.die();
		super.execute();
	}

}
