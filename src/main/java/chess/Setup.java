package chess;

import java.util.ArrayList;
import java.util.List;

import conf.Config;
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
 *         The functionality of the main setup method is being outsourced to an
 *         own (singleton) class
 */
public class Setup implements IRefs, ISetupAndRun {

	/**
	 * static intstance
	 */
	private static Setup instance = null;

	/**
	 * Getter for instance
	 * 
	 * @param main the Main(PApplet) object.
	 * @return the static instance
	 */
	public static Setup getInstance(Main main) {
		if (Setup.instance == null) {
			return new Setup(main);
		}
		return Setup.instance;
	}

	/**
	 * This is necessary, since main instance is needed to initiate the pieces
	 */
	private final Main main;

	/**
	 * Constructor
	 * 
	 * @param main the papplet object
	 */
	private Setup(Main main) {
		this.main = main;
	}

	/**
	 * the substituted main "setup" method
	 */
	@Override
	public void execute() {

		// setup the surface
		this.setupSurface();

		// setup for the players
		this.setupPlayers();

		// create pieces
		this.initiatePieces(this.getMain().getPath());

		this.getReferee();

	}

	// Preparations

	/**
	 * Assignes an image to a set of pieces
	 * 
	 * @param pieces list of pieces
	 * @param image  to be assigned
	 */
	public void getImageForAllRelevant(List<IPiece> pieces, PImage image) {
		for (final IPiece piece : pieces) {
			piece.setImage(image);
		}
	}

	/**
	 * @return the main
	 */
	public Main getMain() {
		return this.main;
	}

	/**
	 * Returns e.g. all black rooks.
	 * 
	 * @param id  the id the pieces in the returned list should have
	 * @param col the color the pieces in the returned list should have
	 * @return the list of pieces of same color and id
	 */
	public List<IPiece> getPiecesOfSameKind(Ids id, Colors col) {
		final List<IPiece> pieces = new ArrayList<>();
		Player pl = this.getGame().getWhite();
		if (col == Colors.BLACK) {
			pl = this.getGame().getBlack();
		}
		for (final IPiece piece : pl.getPieces()) {
			if (piece.getId() == id) {
				pieces.add(piece);
			}
		}
		return pieces;
	}

	/**
	 * Initiates the pimage objects and assignes them to the pieces
	 * 
	 * @param path the path where the images are stored
	 */
	final void initiatePieces(String path) {

		this.getMain().setWhiteKing(this.getMain().loadImage(path + "white_king.png"));
		this.initiateRelevant(Ids.KING, Colors.WHITE, this.getMain().getWhiteKing());

		this.getMain().setBlackKing(this.getMain().loadImage(path + "black_king.png"));
		this.initiateRelevant(Ids.KING, Colors.BLACK, this.getMain().getBlackKing());

		this.getMain().setWhiteQueen(this.getMain().loadImage(path + "white_queen.png"));
		this.initiateRelevant(Ids.QUEEN, Colors.WHITE, this.getMain().getWhiteQueen());

		this.getMain().setBlackQueen(this.getMain().loadImage(path + "black_queen.png"));
		this.initiateRelevant(Ids.QUEEN, Colors.BLACK, this.getMain().getBlackQueen());

		this.getMain().setWhiteKnight(this.getMain().loadImage(path + "white_knight.png"));
		this.initiateRelevant(Ids.KNIGHT, Colors.WHITE, this.getMain().getWhiteKnight());

		this.getMain().setBlackKnight(this.getMain().loadImage(path + "black_knight.png"));
		this.initiateRelevant(Ids.KNIGHT, Colors.BLACK, this.getMain().getBlackKnight());

		this.getMain().setWhiteBishop(this.getMain().loadImage(path + "white_bishop.png"));
		this.initiateRelevant(Ids.BISHOP, Colors.WHITE, this.getMain().getWhiteBishop());

		this.getMain().setBlackBishop(this.getMain().loadImage(path + "black_bishop.png"));
		this.initiateRelevant(Ids.BISHOP, Colors.BLACK, this.getMain().getBlackBishop());

		this.getMain().setWhiteTower(this.getMain().loadImage(path + "white_rook.png"));
		this.initiateRelevant(Ids.ROOK, Colors.WHITE, this.getMain().getWhiteTower());

		this.getMain().setBlackTower(this.getMain().loadImage(path + "black_rook.png"));
		this.initiateRelevant(Ids.ROOK, Colors.BLACK, this.getMain().getBlackTower());

		this.getMain().setWhitePawn(this.getMain().loadImage(path + "white_pawn.png"));
		this.initiateRelevant(Ids.PAWN, Colors.WHITE, this.getMain().getWhitePawn());

		this.getMain().setBlackPawn(this.getMain().loadImage(path + "black_pawn.png"));
		this.initiateRelevant(Ids.PAWN, Colors.BLACK, this.getMain().getBlackPawn());

	}

	/**
	 * Assignes an image to a set of pieces of same kind
	 * 
	 * @param id    the id corresponding to which the pieces will be selected
	 * @param col   the col corresponding to which the pieces will be selected
	 * @param image the image to be assigned
	 */
	public void initiateRelevant(Ids id, Colors col, PImage image) {
		this.getImageForAllRelevant(this.getPiecesOfSameKind(id, col), image);
	}

	/**
	 * Setup players
	 */
	final void setupPlayers() {
		this.getGame().setup();
	}

	/**
	 * Setup surface-attribute of papplet instance
	 */
	final void setupSurface() {

		this.getMain().background(255);
		this.getMain().frameRate(60);
		this.getMain().getSurface().setResizable(true);
		this.getMain().getSurface().setSize(15 * Config.SIZE, 8 * Config.SIZE);
		this.getMain().getSurface().setLocation(this.getMain().displayWidth - this.getMain().width >> 1,
				this.getMain().displayHeight - this.getMain().height >> 1);

	}

}