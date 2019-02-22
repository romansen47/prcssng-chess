package defs.classes;

import chess.Main;
import conf.Config;
import defs.interfaces.IGame;

public class Setup implements IGame{

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
	
	public final void execute() {

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
	
	final void initiatePieces(String path) {
		
		main.setWhiteKing(main.loadImage(path + "white_king.png"));
		main.setBlackKing(main.loadImage(path + "black_king.png"));
		main.setWhiteQueen(main.loadImage(path + "white_queen.png"));
		main.setBlackQueen(main.loadImage(path + "black_queen.png"));
		main.setWhiteKnight(main.loadImage(path + "white_knight.png"));
		main.setBlackKnight(main.loadImage(path + "black_knight.png"));
		main.setWhiteBishop(main.loadImage(path + "white_bishop.png"));
		main.setBlackBishop(main.loadImage(path + "black_bishop.png"));
		main.setWhiteTower(main.loadImage(path + "white_rook.png"));
		main.setBlackTower(main.loadImage(path + "black_rook.png"));
		main.setWhitePawn(main.loadImage(path + "white_pawn.png"));
		main.setBlackPawn(main.loadImage(path + "black_pawn.png"));
		
	}
	
	public static Referee getReferee() {
		return Main.getReferee();
	}

}