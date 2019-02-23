package chess;

import java.util.ArrayList;
import java.util.List;

import conf.Config;
import defs.classes.Field;
import defs.classes.Piece;
import defs.classes.Player;
import defs.enums.Colors;
import defs.interfaces.ISetupAndRun;
import pieces.Pawn;

public class Drawer implements ISetupAndRun{

	final Main main;
	
	private static Drawer instance=null;
	
	private Drawer(Main main) {
		this.main=main;
	}
	
	public static Drawer getInstance(Main main) {
		if (instance==null) {
			return new Drawer(main);
		}
		return instance;
	}
	
	public void execute() {
		
		// check for intaraction and mark field, if clicked
		setMark(checkForClick());
				
		// use information on interaction to create next move
		Move move = getReferee().getMove();
				
		// save move to list and statistics
		getReferee().processMove(move);

		// Draw the complete chess board
		drawChessboard();
				
	}

	public boolean checkForClick() {
		if (main.clicked() == 1) {
			return true;
		} 
		return false;
	}
	
	public void setMark(boolean clicked) {
		if (!clicked) {
			return;
		}
		if (!getReferee().isMarked()) {
			if (getSpiel().getField(7-main.getPosI(), main.getPosJ()).getPiece() != null
					&& getSpiel().getField(7-main.getPosI(), main.getPosJ()).getPiece().getCol() == getPlayer().getCol()) {
				getReferee().setMarked(getSpiel().getField(7-main.getPosI(), main.getPosJ()));
			}
		} else {
			getReferee().setMarked2(getSpiel().getField(7-main.getPosI(), main.getPosJ()));
		}
		main.loop();
	}
	

	// Chessboard
	
	public void drawChessboard() {
		
		drawGrid();
		drawMarked();
		
	}
	
	public void drawGrid() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (getReferee().getMarked() != null) {
					mark(getReferee().getMarked(),Colors.GREEN);
				}
				getSpiel().getField(i, j).draw(this.main);
				main.stroke(0);
				main.strokeWeight(3);
				main.line(0, 0, 8 * Config.Size, 0);
				main.line(0, 0, 0, 8 * Config.Size);
				main.line(8 * Config.Size, 0, 8 * Config.Size, 8 * Config.Size);
				main.line(0, 8 * Config.Size, 8 * Config.Size, 8 * Config.Size);
			}
		}
	}
	
	public void drawMarkedFields(List<Field> fields,Colors tmp) {
		if (!fields.isEmpty()) {
			for (Field fld : fields) {
				mark(fld,tmp);
			}
		}
	}
	
	public void drawMarked() {
		
		if (getReferee().getMarked() != null) {
			
			drawMarkedFields(getReferee().getMarked().getPiece().getPossibleMoves(),Colors.GREEN);
			drawMarkedFields(getReferee().getMarked().getPiece().getAttackers(),Colors.RED);			
			drawMarkedFields(getReferee().getMarked().getPiece().getSupporters(),Colors.BLUE);
			
			List<Field> pos=new ArrayList<Field>();
			pos.add(getReferee().getMarked());
			drawMarkedFields(pos,Colors.YELLOW);
			
		}
		
	}
	
	public void mark(Field fld,Colors col) {
		
		int thickness=5;
		main.strokeWeight(thickness);
		int size = Config.Size;
		switch(col){
			case RED:
				main.stroke(255,0,0);
				main.noFill();
				main.rect((fld.getJ()+1) * size-size+thickness, (fld.getI() + 1) * size-size+thickness, size-2*thickness,size-2*thickness);
				break;
			case BLUE:
				main.stroke(0,0,255);
				main.noFill();
				main.rect((fld.getJ()+1) * size-size+2*thickness, (fld.getI() + 1) * size-size+2*thickness, size-4*thickness,size-4*thickness);
				break;
			case YELLOW:
				main.stroke(255,255,0);
				main.noFill();
				main.rect((fld.getJ()+1) * size-size+thickness, (fld.getI() + 1) * size-size+thickness, size-2*thickness,size-2*thickness);
				main.rect((fld.getJ()+1) * size-size+2*thickness, (fld.getI() + 1) * size-size+2*thickness, size-4*thickness,size-4*thickness);
				main.rect((fld.getJ()+1) * size-size, (fld.getI() + 1) * size-size, size,size);
				break;
			default:
				main.stroke(0,255,0);
				main.noFill();
				main.rect((fld.getJ()+1) * size-size, (fld.getI() + 1) * size-size, size,size);
				break;
				
		}
	}
	
	private Player getOtherPlayer() {
		return getSpiel().getOtherPlayer();
	}

	private Player getPlayer() {
		return getSpiel().getPlayer();
	}

}
