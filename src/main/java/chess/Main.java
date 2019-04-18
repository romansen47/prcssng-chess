package chess;

import defs.classes.Field;
import defs.classes.Game;
import defs.classes.Setup;
import defs.players.IPlayer;
import processing.core.PImage;
import processing.template.Gui;

public class Main extends Gui implements IRefs, IMain {

	private static boolean restore = true;
	/**
	 * the main class
	 */
	protected static final String MAINCLASS = "chess.Main";

	/**
	 * the path for where the images are stored
	 */
	static final String PATH = "";

	private static String SAVEDTIMELINEPATH = "target/TimeLine.xml";

	/**
	 * Getter for MainClass
	 *
	 * @return the main class as string
	 */
	@Override
	public String getMainClass() {
		return Main.MAINCLASS;
	}

	/**
	 * Getter for the acting player
	 *
	 * @return the acting player
	 */
	@Override
	public IPlayer getPlayer() {
		Game.getInstance();
		return Game.getPlayer();
	}

	/**
	 * Initial main method
	 *
	 * @param args some options
	 */
	public static void main(String[] args) {
		(new Gui()).run(Main.MAINCLASS);
	}

	/**
	 * True, if the frame should be redrawn
	 */
	private boolean redrawCustom = true;

	/**
	 * image for the piece
	 */
	private PImage blackBishop;
	/**
	 * image for the piece
	 */
	private PImage blackKing;
	/**
	 * image for the piece
	 */
	private PImage blackKnight;
	/**
	 * image for the piece
	 */
	private PImage blackPawn;
	/**
	 * image for the piece
	 */
	private PImage blackQueen;
	/**
	 * image for the piece
	 */
	private PImage blackTower;
	/**
	 * a drawer instance
	 */
	private final ISetupAndRun drawer = Drawer.getInstance(this);
	/**
	 * a setup instance
	 */
	protected final ISetupAndRun setup = Setup.getInstance(this);
	/**
	 * image for the piece
	 */
	private PImage whiteBishop;
	/**
	 * image for the piece
	 */
	private PImage whiteKing;
	/**
	 * image for the piece
	 */
	private PImage whiteKnight;
	/**
	 * image for the piece
	 */
	private PImage whitePawn;

	/**
	 * image for the piece
	 */
	private PImage whiteQueen;
	/**
	 * image for the piece
	 */
	private PImage whiteTower;

	/**
	 * functionality outsourced to Drawer class
	 */
	@Override
	public void draw() {
		if (getPlayer() != null) {
			try {
				getDrawer().execute();
			} catch (final Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				Thread.sleep(300);
				System.exit(0);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Getter for pimage
	 *
	 * @return pimage
	 */
	@Override
	public PImage getBlackBishop() {
		return blackBishop;
	}

	/**
	 * Getter for pimage
	 *
	 * @return pimage
	 */
	@Override
	public PImage getBlackKing() {
		return blackKing;
	}

	/**
	 * Getter for pimage
	 *
	 * @return pimage
	 */
	@Override
	public PImage getBlackKnight() {
		return blackKnight;
	}

	/**
	 * Getter for pimage
	 *
	 * @return pimage
	 */
	@Override
	public PImage getBlackPawn() {
		return blackPawn;
	}

	/**
	 * Getter for pimage
	 *
	 * @return pimage
	 */
	@Override
	public PImage getBlackQueen() {
		return blackQueen;
	}

	/**
	 * Getter for pimage
	 *
	 * @return pimage
	 */
	@Override
	public PImage getBlackTower() {
		return blackTower;
	}

	/**
	 * Getter for fields
	 *
	 * @param i the column number
	 * @param j the row number
	 * @return the field at position (i,j)
	 */
	@Override
	public Field getField(int i, int j) {
		return getGame().getField(i, j);
	}

	/**
	 * Getter for inactive player
	 *
	 * @return the inactive player
	 */
	@Override
	public IPlayer getOtherPlayer() {
		return Game.getInstance().getOpponent();
	}

	/**
	 * Getter for PATH
	 *
	 * @return the path to main class as string
	 */
	@Override
	public String getPath() {
		return Main.PATH;
	}

	/**
	 * Getter for Position
	 *
	 * @return the vertical position
	 */
	@Override
	public int getPosI() {
		return Config.GAMESIZE - ((mouseY - (mouseY % Config.getInstance().SIZE)) / Config.getInstance().SIZE);
	}

	/**
	 * Getter for Position
	 *
	 * @return the horizontal position
	 */
	@Override
	public int getPosJ() {
		return (mouseX - (mouseX % Config.getInstance().SIZE)) / Config.getInstance().SIZE;
	}

	/**
	 * Getter for pimage
	 *
	 * @return pimage
	 */
	@Override
	public PImage getWhiteBishop() {
		return whiteBishop;
	}

	/**
	 * Getter for pimage
	 *
	 * @return pimage
	 */
	@Override
	public PImage getWhiteKing() {
		return whiteKing;
	}

	/**
	 * Getter for pimage
	 *
	 * @return pimage
	 */
	@Override
	public PImage getWhiteKnight() {
		return whiteKnight;
	}

	/**
	 * Getter for pimage
	 *
	 * @return pimage
	 */
	@Override
	public PImage getWhitePawn() {
		return whitePawn;
	}

	/**
	 * Getter for pimage
	 *
	 * @return pimage
	 */
	@Override
	public PImage getWhiteQueen() {
		return whiteQueen;
	}

	/**
	 * Getter for pimage
	 *
	 * @return pimage
	 */
	@Override
	public PImage getWhiteTower() {
		return whiteTower;
	}

	/**
	 * Setter for pimage
	 *
	 * @param blackBishop pimage
	 */
	@Override
	public void setBlackBishop(PImage blackBishop) {
		this.blackBishop = blackBishop;
	}

	/**
	 * Setter for pimage
	 *
	 * @param blackKing pimage
	 */
	@Override
	public void setBlackKing(PImage blackKing) {
		this.blackKing = blackKing;
	}

	/**
	 * Setter for pimage
	 *
	 * @param blackKnight pimage
	 */
	@Override
	public void setBlackKnight(PImage blackKnight) {
		this.blackKnight = blackKnight;
	}

	/**
	 * Setter for pimage
	 *
	 * @param blackPawn pimage
	 */
	@Override
	public void setBlackPawn(PImage blackPawn) {
		this.blackPawn = blackPawn;
	}

	/**
	 * Setter for pimage
	 *
	 * @param blackQueen pimage
	 */
	@Override
	public void setBlackQueen(PImage blackQueen) {
		this.blackQueen = blackQueen;
	}

	/**
	 * Setter for pimage
	 *
	 * @param blackTower pimage
	 */
	@Override
	public void setBlackTower(PImage blackTower) {
		this.blackTower = blackTower;
	}

	@Override
	public void settings() {
		/**
		 * Nothing to do here...
		 */
	}

	/**
	 * functionality outsourced to Setup class
	 */
	@Override
	public void setup() {
		try {
			setup.execute();
		} catch (final Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.stroke(0);
	}

	/**
	 * Setter for pimage
	 *
	 * @param whiteBishop pimage
	 */
	@Override
	public void setWhiteBishop(PImage whiteBishop) {
		this.whiteBishop = whiteBishop;
	}

	/**
	 * Setter for pimage
	 *
	 * @param whiteKing pimage
	 */
	@Override
	public void setWhiteKing(PImage whiteKing) {
		this.whiteKing = whiteKing;
	}

	/**
	 * Setter for pimage
	 *
	 * @param whiteKnight pimage
	 */
	@Override
	public void setWhiteKnight(PImage whiteKnight) {
		this.whiteKnight = whiteKnight;
	}

	/**
	 * Setter for pimage
	 *
	 * @param whitePawn pimage
	 */
	@Override
	public void setWhitePawn(PImage whitePawn) {
		this.whitePawn = whitePawn;
	}

	/**
	 * Setter for pimage
	 *
	 * @param whiteQueen pimage
	 */
	@Override
	public void setWhiteQueen(PImage whiteQueen) {
		this.whiteQueen = whiteQueen;
	}

	/**
	 * Setter for pimage
	 *
	 * @param whiteTower pimage
	 */
	@Override
	public void setWhiteTower(PImage whiteTower) {
		this.whiteTower = whiteTower;
	}

	/**
	 * @return the redraw
	 */
	@Override
	public boolean isRedraw() {
		return redrawCustom;
	}

	/**
	 * @param redraw the redraw to set
	 */
	@Override
	public void setRedraw(boolean redraw) {
		redrawCustom = redraw;
	}

	/**
	 * @return the restore
	 */
	@Override
	public boolean isRestore() {
		return restore;
	}

	/**
	 * @param restore the restore to set
	 */
	@Override
	public void setRestore(boolean restore) {
		Main.restore = restore;
	}

	/**
	 * @return the sAVEDTIMELINEPATH
	 */
	@Override
	public String getSAVEDTIMELINEPATH() {
		return SAVEDTIMELINEPATH;
	}

	/**
	 * @param sAVEDTIMELINEPATH the sAVEDTIMELINEPATH to set
	 */
	@Override
	public void setSAVEDTIMELINEPATH(String sAVEDTIMELINEPATH) {
		Main.SAVEDTIMELINEPATH = sAVEDTIMELINEPATH;
	}

	/**
	 * @return the drawer
	 */
	@Override
	public ISetupAndRun getDrawer() {
		return drawer;
	}
}
