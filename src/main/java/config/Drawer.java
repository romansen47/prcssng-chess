package config;

import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.xml.bind.JAXBException;

import chess.ConcreteChess;
import chess.game.moves.IMove;
import chess.game.moves.PrintableTimeline;
import chess.game.moves.Timeline;
import chess.game.pieces.IPiece;
import chess.game.pieces.impl.Pawn;
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

	final JFileChooser chooser = new JFileChooser();

	/**
	 * true if match is to be restarted
	 */
	private boolean startup = true;

	/**
	 * map containing all possible valid moves for each piece
	 */
	private Map<IPiece, List<IMove>> allPossibleMoves = null;

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
	public void restoreFromXml(String path) {
		if (main.isRestore()) {
			Game.setPlayer(Game.getWhite());
			try {
				new PrintableTimeline().restoreFromXml(path);
			} catch (final JAXBException e) {
				System.err.println("Failed to load timeline from xml. Does the file exist?");
			}
		}
	}

	/**
	 * method to restore a match from a separate xml file
	 */
	public void restoreFromXml() {
		try {
			int ans = chooser.showOpenDialog(null);
			String path = chooser.getSelectedFile().getPath();
			if (ans == JFileChooser.APPROVE_OPTION) {
				System.out.println("Die zu öffnende Datei ist: " + path);
			}
			restoreFromXml(path);
		} catch (Exception e) {
		}
	}

	/**
	 * the correct method to prepare the start of the match
	 */
	public void startUp() {
		if (isStartup()) {
			((ConcreteChess) getMain()).background(255);
			createAllMoves();
			drawChessboard(getAllPossibleMoves(), getAllAttackers(), getAllSupporters());
			setStartup(false);
		}
	}

	/**
	 * creates the move
	 *
	 * @param cl true on mouse button
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
	 * @throws Exception from marshaller when loading fails
	 */
	public void processMove() {
		checkForMate();
		if (move != null) {
			// save move to list and statistics
			getReferee().processMove(move);
			setAllPossibleMoves(null);
			setAllAttackers(null);
			setAllSupporters(null);
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
	 * @throws Exception from (un)marshaller
	 */
	public void checkForPressedKey() throws Exception {
		if ((((ConcreteChess) getMain()).pressed() == 1)) {
			if (((ConcreteChess) getMain()).key == 'r') {
				getReferee().rewindLastMove();
			}
			if (((ConcreteChess) getMain()).key == 'c') {
				Timeline.getInstance().clear();
				getReferee().rePlayGame(Timeline.getInstance());
			}
			if (((ConcreteChess) getMain()).key == 's') {
				new PrintableTimeline().toXml();
			}
			if (((ConcreteChess) getMain()).key == 'l') {
				getReferee().reset();
				move = null;
				main.setRestore(true);
			}
			((ConcreteChess) getMain()).background(255);
			drawChessboard(getAllPossibleMoves(), getAllAttackers(), getAllSupporters());
			move = null;
		}
	}

	/**
	 * draw everything if clicked or non-trivial move exists
	 *
	 * @param cl true on mouse button hit
	 */
	public void drawChessboardPiecesAndMarks(boolean cl) {
		if (cl || move != null) {
			createAllMoves();
			((ConcreteChess) getMain()).background(255);
			drawChessboard(getAllPossibleMoves(), getAllAttackers(), getAllSupporters());
			move = null;
		}
	}

	public void createAllMoves() {
		setAllPossibleMoves(getReferee().createPossibleValidMovesForActivePieces());
		setAllAttackers(getReferee().createAttackersOnActivePieces());
		setAllSupporters(getReferee().createSupportersOfActivePieces(getAllPossibleMoves()));
	}

	/**
	 * the main method executed whithin the main draw loop
	 *
	 * @throws Exception file does not exist
	 */
	@Override
	public void execute() throws Exception {

		// restore, if Main.restore is true
		if (main.isRestore()) {
			restoreFromXml();
			main.setRestore(false);
		}

		// start up correctly, if startup is true
		startUp();

		// check keyboard for interaction
		checkForPressedKey();

		// check mouse for interaction
		final boolean cl = checkForClick();

		// mark field and create next move, if clicked
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
	 * @param mn the main papplet object
	 * @return drawer instance
	 */
	public static Drawer getInstance(IMain mn) {
		if (Drawer.instance == null) {
			main = mn;
			final Drawer inst = new Drawer();
			if (Pawn.getDrawer() == null) {
				Pawn.setDrawer(inst);
			}
			return Drawer.instance = inst;
		}
		return Drawer.instance;
	}

	/**
	 * instance getter
	 *
	 * @return drawer instance
	 */
	public static Drawer getInstance() {
		if (main != null) {
			getInstance(main);
		}
		return Drawer.instance;
	}

	/**
	 * main instance is needed, since the drawer should be able to "draw" things.
	 */
	private static IMain main;

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
		return ((ConcreteChess) getMain()).clicked() == 1;
	}

	/**
	 * Draws the chess board. First draws the grid.
	 *
	 * @param allPossibleMoves map containing all possible moves for all players
	 * @param allAttackers     map containing all attackers
	 * @param allSupporters    map containing all supporters
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
				((ConcreteChess) getMain()).stroke(0);
				((ConcreteChess) getMain()).strokeWeight(3);
				final float tmp = 8 * (float) Config.getInstance().SIZE;
				((ConcreteChess) getMain()).line(0, 0, tmp, 0);
				((ConcreteChess) getMain()).line(0, 0, 0, tmp);
				((ConcreteChess) getMain()).line(tmp, 0, tmp, tmp);
				((ConcreteChess) getMain()).line(0, tmp, tmp, tmp);
			}
		}
	}

	// Chessboard

	/**
	 * draws all marked fields
	 *
	 * @param allPossibleMoves map containing all possible moves for all players
	 * @param allAttackers     map containing all attackers
	 * @param allSupporters    map containing all supporters
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
	 * @param fields the marked fields
	 * @param tmp    the colors for the fields
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
				getGame().getField(i, j).draw(((ConcreteChess) getMain()));
			}
		}
	}

	/**
	 * Draws the timeline
	 */
	private void drawTimeLine() {
		final Timeline tl = getGame().getMoveList();
		((ConcreteChess) getMain()).textSize(20);
		((ConcreteChess) getMain()).fill(0);
		((ConcreteChess) getMain()).text("Timeline:", (Config.GAMESIZE + 2) * (float) Config.getInstance().SIZE,
				Config.getInstance().SIZE);
		((ConcreteChess) getMain()).textSize(18);
		int i = 2;
		for (final String str : tl.toStr()) {
			((ConcreteChess) getMain()).text(str,
					(((float) Config.getInstance().SIZE) / 4) + ((Config.GAMESIZE + 1) * Config.getInstance().SIZE),
					(float) Config.getInstance().SIZE + (i++ * 30));
		}
	}

	/**
	 * Draws the timeline
	 */
	private void drawServiceWindow() {
		// TODO: Create, whatever it was.
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
	 * @param fld   the field to draw
	 * @param red   how much red?
	 * @param green how much green?
	 * @param blue  how much blue?
	 * @param pos   position of the mark within the field
	 */
	private void drawColoredField(Field fld, int red, int green, int blue, int pos) {
		((ConcreteChess) getMain()).stroke(red, green, blue);
		final int size = Config.getInstance().SIZE;
		final int thickness = 5 - pos;
		((ConcreteChess) getMain()).strokeWeight(thickness);
		((ConcreteChess) getMain()).noFill();
		((ConcreteChess) getMain()).rect((((fld.getJ() + 1) * size) - size) + (float) thickness,
				(((fld.getI() + 1) * (float) size) - size) + thickness, (float) size - (2 * thickness),
				(float) size - (2 * thickness));
	}

	/**
	 * Concrete drawing of a field mark
	 *
	 * @param fld the field to draw the mark for
	 * @param col the color
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
	 * @param clicked tells whether click has been performed
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
	public static IMain getMain() {
		return main;
	}

	/**
	 * @return the startup
	 */
	private boolean isStartup() {
		return startup;
	}

	/**
	 * @param startup the startup to set
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
	public Map<IPiece, List<IMove>> getAllPossibleMoves() {
		return allPossibleMoves;
	}

	/**
	 * @param allPossibleMoves the allPossibleMoves to set
	 */
	public void setAllPossibleMoves(Map<IPiece, List<IMove>> allPossibleMoves) {
		this.allPossibleMoves = allPossibleMoves;
	}

	public List<Path> ls(String directory) {
		Path dir = FileSystems.getDefault().getPath(directory);
		return ls(dir);
	}

	public static List<Path> ls(Path dir) {
		List<Path> paths = new ArrayList<>();
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
			for (Path file : stream) {
				paths.add(file.getFileName());
			}
		} catch (Exception e) {
			// IOException can never be thrown by the iteration.
			// In this snippet, it can only be thrown by newDirectoryStream.
			System.err.println(e);
		}
		return paths;
	}

	/**
	 * @return the allAttackers
	 */
	public Map<IPiece, List<IMove>> getAllAttackers() {
		return allAttackers;
	}

	/**
	 * @param allAttackers the allAttackers to set
	 */
	public void setAllAttackers(Map<IPiece, List<IMove>> allAttackers) {
		this.allAttackers = allAttackers;
	}

	/**
	 * @return the allSupporters
	 */
	public Map<IPiece, List<IMove>> getAllSupporters() {
		return allSupporters;
	}

	/**
	 * @param allSupporters the allSupporters to set
	 */
	public void setAllSupporters(Map<IPiece, List<IMove>> allSupporters) {
		this.allSupporters = allSupporters;
	}

}
