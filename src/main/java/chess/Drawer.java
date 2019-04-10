package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;

import chess.moves.IMove;
import chess.moves.PrintableTimeline;
import chess.moves.Timeline;
import chess.pieces.IPiece;
import chess.pieces.Pawn;
import defs.classes.Field;
import defs.classes.Game;
import defs.enums.Colors;
import defs.enums.Ids;
import defs.players.IPlayer;
import defs.players.artint.RandomPlayer;

/**
 *
 * @author Ro Mansen
 *
 *         Class for handling the main draw-function, constructed as a singleton
 *         class.
 */
public final class Drawer implements ISetupAndRun {

	/**
	 * true if match is to be restarted
	 */
	private boolean startup = true;

	/**
	 * map containing all possible valid moves for each piece
	 */
	private static Map<IPiece, List<IMove>> allPossibleMoves = null;

	/**
	 * map containing all attackers for each piece
	 */
	private Map<IPiece, List<IMove>> allAttackers = null;

	/**
	 * map containing all supporters for each piece
	 */
	private Map<IPiece, List<IMove>> allSupporters = null;

	/**
	 * the move to be created
	 */
	private IMove move = null;

	/**
	 * method to restore a match from a separate xml file
	 */
	public void restoreFromXml() {
		if (Main.isRestore()) {
			Main.setRestore(false);
			try {
				Game.setPlayer(Game.getWhite());
				new PrintableTimeline().restoreFromXml();
				return;
			} catch (final JAXBException e) {
				System.err.println("Failed to load timeline from xml. Does the file exist?");
			}
		}
	}

	/**
	 * the correct method to prepare the start of the match
	 */
	public void startUp() {
		if (isStartup()) {
			getMain().background(255);
			createAllMoves();
			drawChessboard(getAllPossibleMoves(), allAttackers, allSupporters);
			setStartup(false);
		}
	}

	/**
	 * creates the move
	 *
	 * @param cl
	 *            true on mouse button
	 */
	public void checkForNewMove(boolean cl) {
		if (Game.getPlayer() instanceof RandomPlayer) {
			move = ((RandomPlayer) Game.getPlayer()).randomMove();
		} else {
			if (cl) {
				// use information on interaction to
				// create next move
				move = getReferee().getMove(getAllPossibleMoves());
			}
		}
	}

	/**
	 * resets the move maps end executes the move
	 *
	 * @throws Exception
	 *             from marshaller when loading fails
	 */
	public void processMove() throws Exception {
		checkForMate();
		if (move != null) {
			// save move to list and statistics
			getReferee().processMove(move);
			setAllPossibleMoves(null);
			allAttackers = null;
			allSupporters = null;
		}
	}

	private void checkForMate() {
		boolean mate = true;
		for (final Map.Entry<IPiece, List<IMove>> entry : getAllPossibleMoves().entrySet()) {
			if (!entry.getValue().isEmpty()) {
				mate = false;
			}
		}
		if (mate) {
			System.out.println("MATE!!!");
			Game.setPlayer(null);
		}
	}

	/**
	 * does various thing when r,s,c or l are hit
	 *
	 * @throws Exception
	 *             from (un)marshaller
	 */
	public void checkForPressedKey() throws Exception {
		if ((getMain().pressed() == 1)) {
			if (getMain().key == 'r') {
				getReferee().rewindLastMove();
			}
			if (getMain().key == 'c') {
				Timeline.getInstance().clear();
				getReferee().rePlayGame(Timeline.getInstance());
			}
			if (getMain().key == 's') {
				new PrintableTimeline().toXml();
			}
			if (getMain().key == 'l') {
				getReferee().reset();
				move = null;
				Main.setRestore(true);
			}
			getMain().background(255);
			drawChessboard(getAllPossibleMoves(), allAttackers, allSupporters);
			move = null;
		}
	}

	/**
	 * draw everything if clicked or non-trivial move exists
	 *
	 * @param cl
	 *            true on mouse button hit
	 */
	public void drawChessboardPiecesAndMarks(boolean cl) {
		if (cl || move != null) {
			createAllMoves();
			getMain().background(255);
			drawChessboard(getAllPossibleMoves(), allAttackers, allSupporters);
			move = null;
		}
	}

	private void createAllMoves() {
		setAllPossibleMoves(getReferee().createPossibleValidMovesForActivePieces());
		allAttackers = getReferee().createAttackersOnActivePieces();
		allSupporters = getReferee().createSupportersOfActivePieces(getAllPossibleMoves());
	}

	/**
	 * the main method executed whithin the main draw loop
	 *
	 * @throws Exception
	 *             file does not exist
	 */
	@Override
	public void execute() throws Exception {

		// restore, if Main.restore is true
		restoreFromXml();

		// start up correctly, if startup is
		// true
		startUp();

		// check keyboard for interaction
		checkForPressedKey();

		// check mouse for interaction
		final boolean cl = checkForClick();

		// mark field and create next move, if
		// clicked
		setMark(cl);
		checkForNewMove(cl);

		// process the move
		processMove();

		// create marks and draw everything
		drawChessboardPiecesAndMarks(cl);
	}

	/**
	 * Implementation as a singleton class
	 */
	private static Drawer instance = null;

	/**
	 * instance getter
	 *
	 * @param mn
	 *            the main papplet object
	 * @return drawer instance
	 */
	public static Drawer getInstance(Main mn) {
		if (Drawer.instance == null) {
			main = mn;
			final Drawer inst = new Drawer();
			if (Pawn.getDrawer() == null) {
				Pawn.setDrawer(inst);
			}
			return inst;
		}
		return Drawer.instance;
	}

	/**
	 * instance getter
	 *
	 * @return drawer instance
	 */
	public static Drawer getInstance() {
		return Drawer.instance;
	}

	/**
	 * main instance is needed, since the drawer should be able to "draw" things.
	 */
	private static Main main;

	/**
	 * private constructor.
	 */
	private Drawer() {
	}

	/**
	 * Do we have a click?
	 *
	 * @return whether click has been performed.
	 */
	public boolean checkForClick() {
		return getMain().clicked() == 1;
	}

	/**
	 * Draws the chess board. First draws the grid.
	 *
	 * @param allPossibleMoves
	 *            map containing all possible moves for all players
	 * @param allAttackers
	 *            map containing all attackers
	 * @param allSupporters
	 *            map containing all supporters
	 */
	public void drawChessboard(Map<IPiece, List<IMove>> allPossibleMoves, Map<IPiece, List<IMove>> allAttackers,
			Map<IPiece, List<IMove>> allSupporters) {
		drawGrid();
		drawPieces();
		drawMarked(allPossibleMoves, allAttackers, allSupporters);
		drawTimeLine();
		drawServiceWindow();
	}

	/**
	 * draws the grid
	 */
	public void drawGrid() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				getMain().stroke(0);
				getMain().strokeWeight(3);
				final float tmp = 8 * (float) Config.SIZE;
				getMain().line(0, 0, tmp, 0);
				getMain().line(0, 0, 0, tmp);
				getMain().line(tmp, 0, tmp, tmp);
				getMain().line(0, tmp, tmp, tmp);
			}
		}
	}

	// Chessboard

	/**
	 * draws all marked fields
	 *
	 * @param allPossibleMoves
	 *            map containing all possible moves for all players
	 * @param allAttackers
	 *            map containing all attackers
	 * @param allSupporters
	 *            map containing all supporters
	 */
	public void drawMarked(Map<IPiece, List<IMove>> allPossibleMoves, Map<IPiece, List<IMove>> allAttackers,
			Map<IPiece, List<IMove>> allSupporters) {
		if ((getReferee().getMarked() != null) && (getReferee().getMarked().getPiece() != null)) {
			final Field tmp = getReferee().getMarked();
			final IPiece piece = tmp.getPiece();
			drawMarkedFields(piece.convertMovesToFields((allPossibleMoves.get(piece))), Colors.GREEN);
			drawMarkedFields(piece.convertMovesToFields(allAttackers.get(piece)), Colors.RED);
			drawMarkedFields(piece.convertMovesToFields(allSupporters.get(piece)), Colors.BLUE);
			final List<Field> pos = new ArrayList<>();
			pos.add(tmp);
			drawMarkedFields(pos, Colors.YELLOW);
			getReferee().setMarked(tmp);
		}
	}

	/**
	 * draws specific marked fields
	 *
	 * @param fields
	 *            the marked fields
	 * @param tmp
	 *            the colors for the fields
	 */
	public void drawMarkedFields(List<Field> fields, Colors tmp) {
		if (!fields.isEmpty()) {
			for (final Field fld : fields) {
				mark(fld, tmp);
			}
		}
	}

	/**
	 * draws the pieces
	 */
	public void drawPieces() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				getGame().getField(i, j).draw(getMain());
			}
		}
	}

	/**
	 * Draws the timeline
	 */
	private void drawTimeLine() {
		final Timeline tl = getGame().getMoveList();
		getMain().textSize(24);
		getMain().fill(0);
		getMain().text("Timeline:", (Config.GAMESIZE + 2) * (float) Config.SIZE, Config.SIZE);
		getMain().textSize(18);
		int i = 2;
		for (final String str : tl.toStr()) {
			getMain().text(str, (((float) Config.SIZE) / 4) + ((Config.GAMESIZE + 1) * Config.SIZE),
					(float) Config.SIZE + (i++ * 30));
		}
	}

	/**
	 * Draws the timeline
	 */
	private void drawServiceWindow() {
		getMain().textSize(32);
		getMain().fill(255);
		getMain().rect((Config.GAMESIZE + 1) * (float) Config.SIZE, 4 * Config.SIZE, Config.SIZE, Config.SIZE);
	}

	/**
	 * getter for the active player
	 *
	 * @return the active player
	 */
	private IPlayer getPlayer() {
		getGame();
		return Game.getPlayer();
	}

	/**
	 * draws the concrete field with all marks
	 *
	 * @param fld
	 *            the field to draw
	 * @param red
	 *            how much red?
	 * @param green
	 *            how much green?
	 * @param blue
	 *            how much blue?
	 * @param pos
	 *            position of the mark within the field
	 */
	private void drawColoredField(Field fld, int red, int green, int blue, int pos) {
		getMain().stroke(red, green, blue);
		final int size = Config.SIZE;
		final int thickness = 5 - pos;
		getMain().strokeWeight(thickness);
		getMain().noFill();
		getMain().rect((((fld.getJ() + 1) * size) - size) + (float) thickness,
				(((fld.getI() + 1) * (float) size) - size) + thickness, (float) size - (2 * thickness),
				(float) size - (2 * thickness));
	}

	/**
	 * Concrete drawing of a field mark
	 *
	 * @param fld
	 *            the field to draw the mark for
	 * @param col
	 *            the color
	 */
	public void mark(Field fld, Colors col) {
		switch (col) {
		case RED:
			drawColoredField(fld, 255, 0, 0, 3);
			break;
		case BLUE:
			drawColoredField(fld, 0, 0, 255, 4);
			break;
		case YELLOW:
			drawColoredField(fld, 255, 255, 0, 2);
			break;
		default:
			drawColoredField(fld, 0, 255, 0, 1);
			break;

		}
	}

	/**
	 * Marks the field, which has been cicked on
	 *
	 * @param clicked
	 *            tells whether click has been performed
	 */
	public void setMark(boolean clicked) {
		if (!clicked || (getMain().getPosJ() > Config.GAMESIZE)) {
			return;
		}
		if (!getReferee().isMarked()) {
			final int i = Config.GAMESIZE - getMain().getPosI();
			final int j = getMain().getPosJ();
			if ((i >= 0) && (i <= Config.GAMESIZE) && (j >= 0) && (j <= Config.GAMESIZE)
					&& (getGame().getField(i, j).getPiece() != null)
					&& (getGame().getField(Config.GAMESIZE - getMain().getPosI(), getMain().getPosJ()).getPiece()
							.getCol() == getPlayer().getCol())) {
				getReferee().setMarked(getGame().getField(Config.GAMESIZE - getMain().getPosI(), getMain().getPosJ()));
			}
		} else {
			getReferee().setMarked2(getGame().getField(Config.GAMESIZE - getMain().getPosI(), getMain().getPosJ()));
		}
	}

	/**
	 * @return the main
	 */
	static Main getMain() {
		return main;
	}

	/**
	 * @return the startup
	 */
	private boolean isStartup() {
		return startup;
	}

	/**
	 * @param startup
	 *            the startup to set
	 */
	private void setStartup(boolean startup) {
		this.startup = startup;
	}

	/**
	 * The player should have the ability to choose from queen,bishop,knight or rook
	 *
	 * @return the chosen id
	 */
	public Ids choose() {
		return Game.getPlayer().choose();
	}

	/**
	 * @return the allPossibleMoves
	 */
	public static Map<IPiece, List<IMove>> getAllPossibleMoves() {
		return allPossibleMoves;
	}

	/**
	 * @param allPossibleMoves
	 *            the allPossibleMoves to set
	 */
	public static void setAllPossibleMoves(Map<IPiece, List<IMove>> allPossibleMoves) {
		Drawer.allPossibleMoves = allPossibleMoves;
	}

}
