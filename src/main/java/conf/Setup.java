package conf;

import java.util.ArrayList;
import java.util.List;

import chess.Main;
import defs.classes.Player;
import defs.enums.Colors;
import defs.enums.Ids;
import defs.interfaces.IPiece;
import defs.interfaces.IRefs;
import defs.interfaces.ISetupAndRun;
import processing.core.PImage;

/**
 * 
 * @author roman
 *
 * The functionality of the main setup method is being outsourced to an own (singleton) class
 */
public class Setup implements IRefs,ISetupAndRun{

	/**
	 * This is necessary, since main instance is needed to initiate the pieces
	 */
	final Main main;

	private static Setup instance=null;
	
	public static Setup getInstance(Main main) {
		if (instance==null) {
			return new Setup(main);
		}
		return instance;
	}
	
	/**
	 * Constructor
	 * 
	 * @param main the papplet object
	 */
	private Setup(Main main) {
		this.main=main;
	}
	
	/**
	 * the substituted main "setup" method
	 */
	@Override
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
	
	/**
	 * Setup players
	 */
	final void setupPlayers() {
		getGame().setup();
	}
	
	/**
	 * Setup surface-attribute of papplet instance
	 */
	final void setupSurface() {
		
		main.background(255);
		main.frameRate(60);
		main.getSurface().setResizable(true);
		main.getSurface().setSize(15 * Config.SIZE, 8 * Config.SIZE);
		main.getSurface().setLocation(main.displayWidth - main.width >> 1, main.displayHeight - main.height >> 1);
		
	}
	
	/**
	 * Returns e.g. all black rooks.
	 * @param id the id the pieces in the returned list should have
	 * @param col the color the pieces in the returned list should have
	 * @return the list of pieces of same color and id
	 */
	public List<IPiece> getPiecesOfSameKind(Ids id,Colors col){
		List<IPiece> pieces=new ArrayList<>();
		Player pl = getGame().getWhite();
		if (col == Colors.BLACK) {
			pl=getGame().getBlack();
		}
		for (IPiece piece:pl.getPieces()) {
			if (piece.getId()==id) {
				pieces.add(piece);
			}
		}
		return pieces;
	}
	
	/**
	 * Assignes an image to a set of pieces
	 * @param pieces list of pieces
	 * @param image to be assigned
	 */
	public void getImageForAllRelevant(List<IPiece> pieces,PImage image) {
		for (IPiece piece:pieces) {
			piece.setImage(image);
		}
	}
	
	/**
	 * Assignes an image to a set of pieces of same kind
	 * @param id the id corresponding to which the pieces will be selected
	 * @param col the col corresponding to which the pieces will be selected
	 * @param image the image to be assigned
	 */
	public void initiateRelevant(Ids id,Colors col,PImage image) {
		getImageForAllRelevant(getPiecesOfSameKind(id,col),image);
	}
	
	/**
	 * Initiates the pimage objects and assignes them to the pieces 
	 * @param path the path where the images are stored
	 */
	final void initiatePieces(String path) {
		
		main.setWhiteKing(main.loadImage(path + "white_king.png"));
		initiateRelevant(Ids.KING,Colors.WHITE,main.getWhiteKing());
		
		main.setBlackKing(main.loadImage(path + "black_king.png"));
		initiateRelevant(Ids.KING,Colors.BLACK,main.getBlackKing());
		
		main.setWhiteQueen(main.loadImage(path + "white_queen.png"));
		initiateRelevant(Ids.QUEEN,Colors.WHITE,main.getWhiteQueen());
		
		main.setBlackQueen(main.loadImage(path + "black_queen.png"));
		initiateRelevant(Ids.QUEEN,Colors.BLACK,main.getBlackQueen());
		
		main.setWhiteKnight(main.loadImage(path + "white_knight.png"));
		initiateRelevant(Ids.KNIGHT,Colors.WHITE,main.getWhiteKnight());
		
		main.setBlackKnight(main.loadImage(path + "black_knight.png"));
		initiateRelevant(Ids.KNIGHT,Colors.BLACK,main.getBlackKnight());
		
		main.setWhiteBishop(main.loadImage(path + "white_bishop.png"));
		initiateRelevant(Ids.BISHOP,Colors.WHITE,main.getWhiteBishop());
		
		main.setBlackBishop(main.loadImage(path + "black_bishop.png"));
		initiateRelevant(Ids.BISHOP,Colors.BLACK,main.getBlackBishop());
		
		main.setWhiteTower(main.loadImage(path + "white_rook.png"));
		initiateRelevant(Ids.ROOK,Colors.WHITE,main.getWhiteTower());
		
		main.setBlackTower(main.loadImage(path + "black_rook.png"));
		initiateRelevant(Ids.ROOK,Colors.BLACK,main.getBlackTower());
		
		main.setWhitePawn(main.loadImage(path + "white_pawn.png"));
		initiateRelevant(Ids.PAWN,Colors.WHITE,main.getWhitePawn());
		
		main.setBlackPawn(main.loadImage(path + "black_pawn.png"));
		initiateRelevant(Ids.PAWN,Colors.BLACK,main.getBlackPawn());
		
	}

}