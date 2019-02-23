package chess;

import java.util.ArrayList;
import java.util.List;

import conf.Config;
import conf.Referee;
import defs.classes.Piece;
import defs.classes.Player;
import defs.enums.Colors;
import defs.enums.Ids;
import defs.interfaces.IRefs;
import defs.interfaces.ISetupAndRun;
import processing.core.PImage;

public class Setup implements IRefs,ISetupAndRun{

	final Main main;

	private static Setup instance=null;
	
	public static Setup getInstance(Main main) {
		if (instance==null) {
			return new Setup(main);
		}
		return instance;
	}
	
	private Setup(Main main) {
		this.main=main;
	}
	
	public void execute() {

		// setup the surface
		setupSurface();

		// setup for the players
		setupPlayers();
		
		// create pieces
		initiatePieces(main.getPath());
		
		getReferee();
	
	}
	
	// Preparations
	
	final void setupPlayers() {
		getSpiel().setup();
	}
	
	final void setupSurface() {
		
		main.background(255);
		main.frameRate(60);
		main.getSurface().setResizable(true);
		main.getSurface().setSize(12 * Config.Size, 8 * Config.Size);
		main.getSurface().setLocation(main.displayWidth - main.width >> 1, main.displayHeight - main.height >> 1);
		
	}
	
	public List<Piece> getPiecesOfSameKind(Ids id,Colors col){
		List<Piece> pieces=new ArrayList<Piece>();
		Player pl = getSpiel().getWhite();
		if (col == Colors.BLACK) {
			pl=getSpiel().getBlack();
		}
		for (Piece piece:pl.getPieces()) {
			if (piece.id==id) {
				pieces.add(piece);
			}
		}
		return pieces;
	}
	
	public void getImageForAllRelevant(List<Piece> pieces,PImage image) {
		for (Piece piece:pieces) {
			piece.setImage(image);
		}
	}
	
	public void initiateRelevant(Ids id,Colors col,PImage image) {
		getImageForAllRelevant(getPiecesOfSameKind(id,col),image);
	}
	
	final void initiatePieces(String path) {
		
		main.setWhiteKing(main.loadImage(path + "white_king.png"));
		initiateRelevant(Ids.Koenig,Colors.WHITE,main.getWhiteKing());
		
		main.setBlackKing(main.loadImage(path + "black_king.png"));
		initiateRelevant(Ids.Koenig,Colors.BLACK,main.getBlackKing());
		
		main.setWhiteQueen(main.loadImage(path + "white_queen.png"));
		initiateRelevant(Ids.Dame,Colors.WHITE,main.getWhiteQueen());
		
		main.setBlackQueen(main.loadImage(path + "black_queen.png"));
		initiateRelevant(Ids.Dame,Colors.BLACK,main.getBlackQueen());
		
		main.setWhiteKnight(main.loadImage(path + "white_knight.png"));
		initiateRelevant(Ids.Springer,Colors.WHITE,main.getWhiteKnight());
		
		main.setBlackKnight(main.loadImage(path + "black_knight.png"));
		initiateRelevant(Ids.Springer,Colors.BLACK,main.getBlackKnight());
		
		main.setWhiteBishop(main.loadImage(path + "white_bishop.png"));
		initiateRelevant(Ids.Laeufer,Colors.WHITE,main.getWhiteBishop());
		
		main.setBlackBishop(main.loadImage(path + "black_bishop.png"));
		initiateRelevant(Ids.Laeufer,Colors.BLACK,main.getBlackBishop());
		
		main.setWhiteTower(main.loadImage(path + "white_rook.png"));
		initiateRelevant(Ids.Turm,Colors.WHITE,main.getWhiteTower());
		
		main.setBlackTower(main.loadImage(path + "black_rook.png"));
		initiateRelevant(Ids.Turm,Colors.BLACK,main.getBlackTower());
		
		main.setWhitePawn(main.loadImage(path + "white_pawn.png"));
		initiateRelevant(Ids.Bauer,Colors.WHITE,main.getWhitePawn());
		
		main.setBlackPawn(main.loadImage(path + "black_pawn.png"));
		initiateRelevant(Ids.Bauer,Colors.BLACK,main.getBlackPawn());
		
	}

}