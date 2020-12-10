package defs.classes;

import java.util.ArrayList;
import java.util.List;

import chess.ConcreteChess;
import chess.game.pieces.IPiece;
import config.Config;
import config.IMain;
import config.IRefs;
import config.ISetupAndRun;
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
		initiatePieces(((ConcreteChess) getMain()).getPath());

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

		((ConcreteChess) getMain()).setWhiteKing(((ConcreteChess) getMain()).loadImage(path + "white_king.png"));
		initiateRelevant(Ids.KING, Colors.WHITE, ((ConcreteChess) getMain()).getWhiteKing());

		((ConcreteChess) getMain()).setBlackKing(((ConcreteChess) getMain()).loadImage(path + "black_king.png"));
		initiateRelevant(Ids.KING, Colors.BLACK, ((ConcreteChess) getMain()).getBlackKing());

		((ConcreteChess) getMain()).setWhiteQueen(((ConcreteChess) getMain()).loadImage(path + "white_queen.png"));
		initiateRelevant(Ids.QUEEN, Colors.WHITE, ((ConcreteChess) getMain()).getWhiteQueen());

		((ConcreteChess) getMain()).setBlackQueen(((ConcreteChess) getMain()).loadImage(path + "black_queen.png"));
		initiateRelevant(Ids.QUEEN, Colors.BLACK, ((ConcreteChess) getMain()).getBlackQueen());

		((ConcreteChess) getMain()).setWhiteKnight(((ConcreteChess) getMain()).loadImage(path + "white_knight.png"));
		initiateRelevant(Ids.KNIGHT, Colors.WHITE, ((ConcreteChess) getMain()).getWhiteKnight());

		((ConcreteChess) getMain()).setBlackKnight(((ConcreteChess) getMain()).loadImage(path + "black_knight.png"));
		initiateRelevant(Ids.KNIGHT, Colors.BLACK, ((ConcreteChess) getMain()).getBlackKnight());

		((ConcreteChess) getMain()).setWhiteBishop(((ConcreteChess) getMain()).loadImage(path + "white_bishop.png"));
		initiateRelevant(Ids.BISHOP, Colors.WHITE, ((ConcreteChess) getMain()).getWhiteBishop());

		((ConcreteChess) getMain()).setBlackBishop(((ConcreteChess) getMain()).loadImage(path + "black_bishop.png"));
		initiateRelevant(Ids.BISHOP, Colors.BLACK, ((ConcreteChess) getMain()).getBlackBishop());

		((ConcreteChess) getMain()).setWhiteTower(((ConcreteChess) getMain()).loadImage(path + "white_rook.png"));
		initiateRelevant(Ids.ROOK, Colors.WHITE, ((ConcreteChess) getMain()).getWhiteTower());

		((ConcreteChess) getMain()).setBlackTower(((ConcreteChess) getMain()).loadImage(path + "black_rook.png"));
		initiateRelevant(Ids.ROOK, Colors.BLACK, ((ConcreteChess) getMain()).getBlackTower());

		((ConcreteChess) getMain()).setWhitePawn(((ConcreteChess) getMain()).loadImage(path + "white_pawn.png"));
		initiateRelevant(Ids.PAWN, Colors.WHITE, ((ConcreteChess) getMain()).getWhitePawn());

		((ConcreteChess) getMain()).setBlackPawn(((ConcreteChess) getMain()).loadImage(path + "black_pawn.png"));
		initiateRelevant(Ids.PAWN, Colors.BLACK, ((ConcreteChess) getMain()).getBlackPawn());

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
		((ConcreteChess) getMain()).getSurface().setResizable(true);
		((ConcreteChess) getMain()).getSurface().setSize(15 * Config.getInstance().SIZE, 8 * Config.getInstance().SIZE);
		((ConcreteChess) getMain()).background(255);
		((ConcreteChess) getMain()).frameRate(60);
		((ConcreteChess) getMain()).getSurface().setLocation((((ConcreteChess) getMain()).displayWidth - ((ConcreteChess) getMain()).width) >> 1,
				(((ConcreteChess) getMain()).displayHeight - ((ConcreteChess) getMain()).height) >> 1);
	}

}