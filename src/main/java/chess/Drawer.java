package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;

import conf.Config;
import conf.PrintableTimeline;
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

	private boolean startup=true;
	/**
	 * the main method executed whithin the main draw loop
	 * 
	 * @throws Exception
	 */
	@Override
	public void execute() throws Exception {

		if (Main.isRestore()){
			Main.setRestore(false);
			try {
				Game.setPlayer(Game.getWhite());
				//Timeline tl =Timeline.getInstance();
				new PrintableTimeline().restoreFromXml();
				return;
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (startup){
			getMain().background(255);
			this.drawChessboard(allPossibleMoves, allAttackers, allSupporters);
			startup=false;
		}
		
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
		if ((getMain().pressed() == 1)){
			if (getMain().key == 'r'){
				this.getReferee().rewindLastMove();
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
				move=null;
				Main.setRestore(true);
			}
			getMain().background(255);
			this.drawChessboard(allPossibleMoves, allAttackers, allSupporters);
			move=null;
		}
		if (cl || move != null) {
			// Draw the complete chess board
			getMain().setRedraw(true);

			allPossibleMoves = this.getReferee().createPossibleValidMovesForActivePieces();
			allAttackers = this.getReferee().createAttackersOnActivePieces();
			allSupporters = this.getReferee().createSupportersOfActivePieces(allPossibleMoves);
			// getReferee().checkState();
			getMain().background(255);
			this.drawChessboard(allPossibleMoves, allAttackers, allSupporters);
		}
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
	private static Main main;

	/**
	 * private constructor.
	 *
	 * @param main the main {@inheritDoc}PApplet object.
	 */
	private Drawer(Main mn) {
		main = mn;
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
	 * @throws Exception
	 */
	public void drawChessboard(Map<IPiece, List<IMove>> allPossibleMoves, Map<IPiece, List<IMove>> allAttackers,
			Map<IPiece, List<IMove>> allSupporters) {
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
				getGame().getField(i, j).draw(getMain());
			}
		}
	}

	/**
	 * Draws the timeline
	 * 
	 * @throws Exception
	 */
	private void drawTimeLine() {
		final Timeline tl = this.getGame().getMoveList();
		getMain().textSize(32);
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
		getMain().strokeWeight(thickness);
		final int size = Config.SIZE;
		switch (col) {
		case RED:
			getMain().stroke(255, 0, 0);
			getMain().noFill();
			getMain().rect((((fld.getJ() + 1) * size) - size) + (float) thickness,
					(((fld.getI() + 1) * (float) size) - size) + thickness, (float) size - (2 * thickness),
					(float) size - (2 * thickness));
			break;
		case BLUE:
			getMain().stroke(0, 0, 255);
			getMain().noFill();
			getMain().rect((((fld.getJ() + 1) * (float) size) - size) + (2 * thickness),
					(((fld.getI() + 1) * (float) size) - size) + (2 * thickness), (float) size - (4 * thickness),
					(float) size - (4 * thickness));
			break;
		case YELLOW:
			getMain().stroke(255, 255, 0);
			getMain().noFill();
			getMain().rect((((fld.getJ() + 1) * (float) size) - size) + thickness,
					(((fld.getI() + 1) * (float) size) - size) + thickness, (float) size - (2 * thickness),
					(float) size - (2 * thickness));
			getMain().rect((((fld.getJ() + 1) * (float) size) - size) + (2 * thickness),
					(((fld.getI() + 1) * (float) size) - size) + (2 * thickness), (float) size - (4 * thickness),
					(float) size - (4 * thickness));
			getMain().rect(((fld.getJ() + 1) * (float) size) - size, ((fld.getI() + 1) * (float) size) - size, size,
					size);
			break;
		default:
			getMain().stroke(0, 255, 0);
			getMain().noFill();
			getMain().rect(((fld.getJ() + 1) * (float) size) - size, ((fld.getI() + 1) * (float) size) - size, size,
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
		if (!clicked || (clicked && getMain().getPosJ() > Config.GAMESIZE)) {
			return;
		}
		if (!this.getReferee().isMarked()) {
			final int i = Config.GAMESIZE - getMain().getPosI();
			final int j = getMain().getPosJ();
			if ((i >= 0) && (i <= Config.GAMESIZE) && (j >= 0) && (j <= Config.GAMESIZE)
					&& (this.getGame().getField(i, j).getPiece() != null)
					&& (this.getGame().getField(Config.GAMESIZE - getMain().getPosI(), getMain().getPosJ()).getPiece()
							.getCol() == this.getPlayer().getCol())) {
				this.getReferee()
						.setMarked(this.getGame().getField(Config.GAMESIZE - getMain().getPosI(), getMain().getPosJ()));
			}
		} else {
			this.getReferee()
					.setMarked2(this.getGame().getField(Config.GAMESIZE - getMain().getPosI(), getMain().getPosJ()));
		}
	}

	/**
	 * @return the main
	 */
	static Main getMain() {
		return main;
	}

}
