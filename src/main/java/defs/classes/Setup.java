package defs.classes;

import java.util.ArrayList;
import java.util.List;

import chess.Config;
import chess.IRefs;
import chess.ISetupAndRun;
import chess.Main;
import chess.pieces.IPiece;
import defs.enums.Colors;
import defs.enums.Ids;
import defs.players.IPlayer;
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
		setupSurface();

		// setup for the players
		setupPlayers();

		// create pieces
		initiatePieces(getMain().getPath());

		// this.getReferee().setMarked(Game.getPlayer().getKing().getField());

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
		return main;
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
		IPlayer pl = Game.getWhite();
		if (col == Colors.BLACK) {
			pl = Game.getBlack();
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

		getMain().setWhiteKing(getMain().loadImage(path + "white_king.png"));
		initiateRelevant(Ids.KING, Colors.WHITE, getMain().getWhiteKing());

		getMain().setBlackKing(getMain().loadImage(path + "black_king.png"));
		initiateRelevant(Ids.KING, Colors.BLACK, getMain().getBlackKing());

		getMain().setWhiteQueen(getMain().loadImage(path + "white_queen.png"));
		initiateRelevant(Ids.QUEEN, Colors.WHITE, getMain().getWhiteQueen());

		getMain().setBlackQueen(getMain().loadImage(path + "black_queen.png"));
		initiateRelevant(Ids.QUEEN, Colors.BLACK, getMain().getBlackQueen());

		getMain().setWhiteKnight(getMain().loadImage(path + "white_knight.png"));
		initiateRelevant(Ids.KNIGHT, Colors.WHITE, getMain().getWhiteKnight());

		getMain().setBlackKnight(getMain().loadImage(path + "black_knight.png"));
		initiateRelevant(Ids.KNIGHT, Colors.BLACK, getMain().getBlackKnight());

		getMain().setWhiteBishop(getMain().loadImage(path + "white_bishop.png"));
		initiateRelevant(Ids.BISHOP, Colors.WHITE, getMain().getWhiteBishop());

		getMain().setBlackBishop(getMain().loadImage(path + "black_bishop.png"));
		initiateRelevant(Ids.BISHOP, Colors.BLACK, getMain().getBlackBishop());

		getMain().setWhiteTower(getMain().loadImage(path + "white_rook.png"));
		initiateRelevant(Ids.ROOK, Colors.WHITE, getMain().getWhiteTower());

		getMain().setBlackTower(getMain().loadImage(path + "black_rook.png"));
		initiateRelevant(Ids.ROOK, Colors.BLACK, getMain().getBlackTower());

		getMain().setWhitePawn(getMain().loadImage(path + "white_pawn.png"));
		initiateRelevant(Ids.PAWN, Colors.WHITE, getMain().getWhitePawn());

		getMain().setBlackPawn(getMain().loadImage(path + "black_pawn.png"));
		initiateRelevant(Ids.PAWN, Colors.BLACK, getMain().getBlackPawn());

	}

	/**
	 * Assignes an image to a set of pieces of same kind
	 *
	 * @param id    the id corresponding to which the pieces will be selected
	 * @param col   the col corresponding to which the pieces will be selected
	 * @param image the image to be assigned
	 */
	public void initiateRelevant(Ids id, Colors col, PImage image) {
		getImageForAllRelevant(getPiecesOfSameKind(id, col), image);
	}

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
		getMain().background(255);
		getMain().frameRate(60);
		getMain().getSurface().setResizable(true);
		getMain().getSurface().setSize(15 * Config.SIZE, 8 * Config.SIZE);
		getMain().getSurface().setLocation((getMain().displayWidth - getMain().width) >> 1,
				(getMain().displayHeight - getMain().height) >> 1);
	}

}