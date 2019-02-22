package defs.classes;

import java.util.ArrayList;
import java.util.List;

import chess.Game;
import chess.Main;
import conf.Config;
import defs.enums.Colors;
import figuren.Bauer;

public class Drawer {

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
			if (getSpiel().getField(main.getPosJ(), main.getPosI()).getPiece() != null
					&& getSpiel().getField(main.getPosJ(), main.getPosI()).getPiece().getCol() == getPlayer().getCol()) {
				getReferee().setMarked(getSpiel().getField(main.getPosJ(), main.getPosI()));
			}
		} else {
			getReferee().setMarked2(getSpiel().getField(main.getPosJ(), main.getPosI()));
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
			drawMarkedFields(getReferee().getMarked().getPiece().getAttackers(getOtherPlayer()),Colors.RED);			

			Piece piece=getReferee().getMarked().getPiece();
			Colors col=Colors.WHITE;
			if (piece.getCol()==col) {
				col=Colors.BLACK;
			}
			Field field=piece.getField();
			Piece fakepiece=new Bauer(col, field);
			field.setPiece(fakepiece);
			getPlayer().getPieces().remove(piece);
			getPlayer().getPieces().add(fakepiece);			
			drawMarkedFields(piece.getAttackers(getPlayer()),Colors.BLUE);
			getPlayer().getPieces().remove(fakepiece);
			getPlayer().getPieces().add(piece);
			field.setPiece(piece);
		
			List<Field> tmp=new ArrayList<Field>();
			tmp.add(getReferee().getMarked());
			drawMarkedFields(tmp,Colors.YELLOW);
			
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

	private static Referee getReferee() {
		return Referee.getInstance();
	}

	private Game getSpiel() {
		return Main.getSpiel();
	}
}
