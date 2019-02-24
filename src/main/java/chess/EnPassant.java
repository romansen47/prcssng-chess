package chess;

import defs.classes.Field;
import pieces.Pawn;

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
