package defs.classes;

import java.util.ArrayList;
import java.util.List;

import chess.Config;
import chess.IMain;
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
	public static Setup getInstance(IMain main) {
		if (Setup.instance == null) {
			return new Setup(main);
		}
		return Setup.instance;
	}

	/**
	 * This is necessary, since main instance is needed to initiate the pieces
	 */
	private final IMain main;

	/**
	 * Constructor
	 *
	 * @param main the papplet object
	 */
	private Setup(IMain main) {
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
		initiatePieces(((Main) getMain()).getPath());

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
	public IMain getMain() {
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

		((Main) getMain()).setWhiteKing(((Main) getMain()).loadImage(path + "white_king.png"));
		initiateRelevant(Ids.KING, Colors.WHITE, ((Main) getMain()).getWhiteKing());

		((Main) getMain()).setBlackKing(((Main) getMain()).loadImage(path + "black_king.png"));
		initiateRelevant(Ids.KING, Colors.BLACK, ((Main) getMain()).getBlackKing());

		((Main) getMain()).setWhiteQueen(((Main) getMain()).loadImage(path + "white_queen.png"));
		initiateRelevant(Ids.QUEEN, Colors.WHITE, ((Main) getMain()).getWhiteQueen());

		((Main) getMain()).setBlackQueen(((Main) getMain()).loadImage(path + "black_queen.png"));
		initiateRelevant(Ids.QUEEN, Colors.BLACK, ((Main) getMain()).getBlackQueen());

		((Main) getMain()).setWhiteKnight(((Main) getMain()).loadImage(path + "white_knight.png"));
		initiateRelevant(Ids.KNIGHT, Colors.WHITE, ((Main) getMain()).getWhiteKnight());

		((Main) getMain()).setBlackKnight(((Main) getMain()).loadImage(path + "black_knight.png"));
		initiateRelevant(Ids.KNIGHT, Colors.BLACK, ((Main) getMain()).getBlackKnight());

		((Main) getMain()).setWhiteBishop(((Main) getMain()).loadImage(path + "white_bishop.png"));
		initiateRelevant(Ids.BISHOP, Colors.WHITE, ((Main) getMain()).getWhiteBishop());

		((Main) getMain()).setBlackBishop(((Main) getMain()).loadImage(path + "black_bishop.png"));
		initiateRelevant(Ids.BISHOP, Colors.BLACK, ((Main) getMain()).getBlackBishop());

		((Main) getMain()).setWhiteTower(((Main) getMain()).loadImage(path + "white_rook.png"));
		initiateRelevant(Ids.ROOK, Colors.WHITE, ((Main) getMain()).getWhiteTower());

		((Main) getMain()).setBlackTower(((Main) getMain()).loadImage(path + "black_rook.png"));
		initiateRelevant(Ids.ROOK, Colors.BLACK, ((Main) getMain()).getBlackTower());

		((Main) getMain()).setWhitePawn(((Main) getMain()).loadImage(path + "white_pawn.png"));
		initiateRelevant(Ids.PAWN, Colors.WHITE, ((Main) getMain()).getWhitePawn());

		((Main) getMain()).setBlackPawn(((Main) getMain()).loadImage(path + "black_pawn.png"));
		initiateRelevant(Ids.PAWN, Colors.BLACK, ((Main) getMain()).getBlackPawn());

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
		((Main) getMain()).getSurface().setResizable(true);
		((Main) getMain()).getSurface().setSize(15 * Config.SIZE, 8 * Config.SIZE);
		((Main) getMain()).background(255);
		((Main) getMain()).frameRate(60);
		((Main) getMain()).getSurface().setLocation((((Main) getMain()).displayWidth - ((Main) getMain()).width) >> 1,
				(((Main) getMain()).displayHeight - ((Main) getMain()).height) >> 1);
	}

}