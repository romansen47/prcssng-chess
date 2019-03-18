package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import conf.Config;
import conf.Timeline;
import defs.classes.Field;
import defs.classes.Game;
import defs.enums.Colors;
import defs.interfaces.IMove;
import defs.interfaces.IPiece;
import defs.interfaces.IPlayer;
import defs.interfaces.ISetupAndRun;
import defs.players.artint.RandomPlayer;

/**
 *
 * @author roman
 *
 *         Class for handling the main draw-function, constructed as a singleton
 *         class.
 */
public class Drawer implements ISetupAndRun {

	private Map<IPiece, List<IMove>> allPossibleMoves = null;// new HashMap<IPiece,List<IMove>>();
	private Map<IPiece, List<IMove>> allAttackers = null;// new HashMap<IPiece,List<IMove>>();
	private Map<IPiece, List<IMove>> allSupporters = null;// new HashMap<IPiece,List<IMove>>();

	private IMove move = null;

	/**
	 * the main method executed whithin the main draw loop
	 * 
	 * @throws Exception
	 */
	@Override
	public void execute() throws Exception {

		// check for interaction and mark field, if clicked
		final boolean cl = this.checkForClick();
		this.setMark(cl);

		if (move != null) {
			move = null;
		}

		if (Game.getPlayer() instanceof RandomPlayer) {
			move = ((RandomPlayer) Game.getPlayer()).randomMove();
		} else {
			if (cl) {
				// use information on interaction to create next move
				move = this.getReferee().getMove(allPossibleMoves);
			}
		}
		if (move != null) {
			// save move to list and statistics
			this.getReferee().processMove(move);
			allPossibleMoves = null;
			allAttackers = null;
			allSupporters = null;
		}
		if ((this.main.pressed() == 1) && (this.main.key == 'r')) {
			this.getReferee().rewindLastMove();
			// this.main.background(255);
			// this.getGame().getReferee().setMarked(null);
			// this.getGame().getReferee().setMarked2(null);
		}
		if (cl || move != null) {
			// Draw the complete chess board
			this.main.setRedraw(true);

			allPossibleMoves = this.getReferee().createPossibleValidMovesForActivePieces();
			allAttackers = this.getReferee().createAttackersOnActivePieces();
			allSupporters = this.getReferee().createSupportersOfActivePieces();
			// getReferee().checkState();
		}
		this.drawChessboard(allPossibleMoves, allAttackers, allSupporters);

	}

	/**
	 * Implementation as a singleton class
	 */
	private static Drawer instance = null;

	/**
	 * instance getter
	 *
	 * @param main the main papplet object
	 * @return drawer instance
	 */
	public static Drawer getInstance(Main main) {
		if (Drawer.instance == null) {
			return new Drawer(main);
		}
		return Drawer.instance;
	}

	/**
	 * main instance is needed, since the drawer should be able to "draw" things.
	 */
	final Main main;

	/**
	 * private constructor.
	 *
	 * @param main the main {@inheritDoc}PApplet object.
	 */
	private Drawer(Main main) {
		this.main = main;
	}

	/**
	 * Do we have a click?
	 *
	 * @return whether click has been performed.
	 */
	public boolean checkForClick() {
		return this.main.clicked() == 1;
	}

	/**
	 * Draws the chess board. First draws the grid.
	 * 
	 * @throws Exception
	 */
	public void drawChessboard(Map<IPiece, List<IMove>> allPossibleMoves, Map<IPiece, List<IMove>> allAttackers,
			Map<IPiece, List<IMove>> allSupporters) throws Exception {
		this.drawGrid();
		this.drawPieces();
		this.drawMarked(allPossibleMoves, allAttackers, allSupporters);
		this.drawTimeLine();
	}

	/**
	 * draws the grid
	 */
	public void drawGrid() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				this.main.stroke(0);
				this.main.strokeWeight(3);
				final float tmp = 8 * (float) Config.SIZE;
				this.main.line(0, 0, tmp, 0);
				this.main.line(0, 0, 0, tmp);
				this.main.line(tmp, 0, tmp, tmp);
				this.main.line(0, tmp, tmp, tmp);
			}
		}
	}

	// Chessboard

	/**
	 * draws all marked fields
	 */
	public void drawMarked(Map<IPiece, List<IMove>> allPossibleMoves, Map<IPiece, List<IMove>> allAttackers,
			Map<IPiece, List<IMove>> allSupporters) {
		if ((this.getReferee().getMarked() != null) && (this.getReferee().getMarked().getPiece() != null)) {
			Field tmp = this.getReferee().getMarked();
			IPiece piece = tmp.getPiece();
			this.drawMarkedFields(piece.convertMovesToFields((allPossibleMoves.get(piece))), Colors.GREEN);
			this.drawMarkedFields(piece.convertMovesToFields(allAttackers.get(piece)), Colors.RED);
			this.drawMarkedFields(piece.convertMovesToFields(allSupporters.get(piece)), Colors.BLUE);
			final List<Field> pos = new ArrayList<>();
			pos.add(tmp);
			this.drawMarkedFields(pos, Colors.YELLOW);
			this.getReferee().setMarked(tmp);
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
				this.mark(fld, tmp);
			}
		}
	}

	/**
	 * draws the pieces
	 */
	public void drawPieces() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				getGame().getField(i, j).draw(this.main);
			}
		}
	}

	/**
	 * Draws the timeline
	 * 
	 * @throws Exception
	 */
	private void drawTimeLine() throws Exception {
		final Timeline tl = this.getGame().getMoveList();
		this.main.textSize(32);
		this.main.fill(0);
		this.main.text("Timeline:", (Config.GAMESIZE + 2) * (float) Config.SIZE, Config.SIZE);
		this.main.textSize(18);
		int i = 2;
		for (final String str : tl.toStr()) {
			this.main.text(str, (((float) Config.SIZE) / 4) + ((Config.GAMESIZE + 1) * Config.SIZE),
					(float) Config.SIZE + (i++ * 30));
		}
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
	 * Concrete drawing of a field mark
	 *
	 * @param fld the field to draw the mark for
	 * @param col the color
	 */
	public void mark(Field fld, Colors col) {

		final int thickness = 5;
		this.main.strokeWeight(thickness);
		final int size = Config.SIZE;
		switch (col) {
		case RED:
			this.main.stroke(255, 0, 0);
			this.main.noFill();
			this.main.rect((((fld.getJ() + 1) * size) - size) + (float) thickness,
					(((fld.getI() + 1) * (float) size) - size) + thickness, (float) size - (2 * thickness),
					(float) size - (2 * thickness));
			break;
		case BLUE:
			this.main.stroke(0, 0, 255);
			this.main.noFill();
			this.main.rect((((fld.getJ() + 1) * (float) size) - size) + (2 * thickness),
					(((fld.getI() + 1) * (float) size) - size) + (2 * thickness), (float) size - (4 * thickness),
					(float) size - (4 * thickness));
			break;
		case YELLOW:
			this.main.stroke(255, 255, 0);
			this.main.noFill();
			this.main.rect((((fld.getJ() + 1) * (float) size) - size) + thickness,
					(((fld.getI() + 1) * (float) size) - size) + thickness, (float) size - (2 * thickness),
					(float) size - (2 * thickness));
			this.main.rect((((fld.getJ() + 1) * (float) size) - size) + (2 * thickness),
					(((fld.getI() + 1) * (float) size) - size) + (2 * thickness), (float) size - (4 * thickness),
					(float) size - (4 * thickness));
			this.main.rect(((fld.getJ() + 1) * (float) size) - size, ((fld.getI() + 1) * (float) size) - size, size,
					size);
			break;
		default:
			this.main.stroke(0, 255, 0);
			this.main.noFill();
			this.main.rect(((fld.getJ() + 1) * (float) size) - size, ((fld.getI() + 1) * (float) size) - size, size,
					size);
			break;

		}
	}

	/**
	 * Marks the field, which has been cicked on
	 *
	 * @param clicked tells whether click has been performed
	 */
	public void setMark(boolean clicked) {
		if (!clicked || (clicked && this.main.getPosJ() > Config.GAMESIZE)) {
			return;
		}
		if (!this.getReferee().isMarked()) {
			final int i = Config.GAMESIZE - this.main.getPosI();
			final int j = this.main.getPosJ();
			if ((i >= 0) && (i <= Config.GAMESIZE) && (j >= 0) && (j <= Config.GAMESIZE)
					&& (this.getGame().getField(i, j).getPiece() != null)
					&& (this.getGame().getField(Config.GAMESIZE - this.main.getPosI(), this.main.getPosJ()).getPiece()
							.getCol() == this.getPlayer().getCol())) {
				this.getReferee()
						.setMarked(this.getGame().getField(Config.GAMESIZE - this.main.getPosI(), this.main.getPosJ()));
			}
		} else {
			this.getReferee()
					.setMarked2(this.getGame().getField(Config.GAMESIZE - this.main.getPosI(), this.main.getPosJ()));
		}
	}

}
