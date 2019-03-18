package chess;

import conf.Config;
import defs.classes.Field;
import defs.classes.Game;
import defs.interfaces.IPlayer;
import defs.interfaces.IRefs;
import defs.interfaces.ISetupAndRun;
import processing.core.PImage;
import processing.template.Gui;

public class Main extends Gui implements IRefs {

	/**
	 * the main class
	 */
	static final String MAINCLASS = "chess.Main";

	/**
	 * the path for where the images are stored
	 */
	static final String PATH = "";

	/**
	 * Getter for MainClass
	 *
	 * @return the main class as string
	 */
	public static String getMainClass() {
		return Main.MAINCLASS;
	}

	/**
	 * Getter for the acting player
	 *
	 * @return the acting player
	 */
	public static IPlayer getPlayer() {
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
	private boolean redraw=true;
	
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
	private final ISetupAndRun setup = Setup.getInstance(this);
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
		try {
			this.drawer.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Getter for pimage
	 *
	 * @return pimage
	 */
	public PImage getBlackBishop() {
		return this.blackBishop;
	}

	/**
	 * Getter for pimage
	 *
	 * @return pimage
	 */
	public PImage getBlackKing() {
		return this.blackKing;
	}

	/**
	 * Getter for pimage
	 *
	 * @return pimage
	 */
	public PImage getBlackKnight() {
		return this.blackKnight;
	}

	/**
	 * Getter for pimage
	 *
	 * @return pimage
	 */
	public PImage getBlackPawn() {
		return this.blackPawn;
	}

	/**
	 * Getter for pimage
	 *
	 * @return pimage
	 */
	public PImage getBlackQueen() {
		return this.blackQueen;
	}

	/**
	 * Getter for pimage
	 *
	 * @return pimage
	 */
	public PImage getBlackTower() {
		return this.blackTower;
	}

	/**
	 * Getter for fields
	 *
	 * @param i the column number
	 * @param j the row number
	 * @return the field at position (i,j)
	 */
	public Field getField(int i, int j) {
		return this.getGame().getField(i, j);
	}

	/**
	 * Getter for inactive player
	 *
	 * @return the inactive player
	 */
	public IPlayer getOtherPlayer() {
		return Game.getOpponent();
	}

	/**
	 * Getter for PATH
	 *
	 * @return the path to main class as string
	 */
	public String getPath() {
		return Main.PATH;
	}

	/**
	 * Getter for Position
	 *
	 * @return the vertical position
	 */
	public int getPosI() {
		return Config.GAMESIZE - ((this.mouseY - (this.mouseY % Config.SIZE)) / Config.SIZE);
	}

	/**
	 * Getter for Position
	 *
	 * @return the horizontal position
	 */
	public int getPosJ() {
		return (this.mouseX - (this.mouseX % Config.SIZE)) / Config.SIZE;
	}

	/**
	 * Getter for pimage
	 *
	 * @return pimage
	 */
	public PImage getWhiteBishop() {
		return this.whiteBishop;
	}

	/**
	 * Getter for pimage
	 *
	 * @return pimage
	 */
	public PImage getWhiteKing() {
		return this.whiteKing;
	}

	/**
	 * Getter for pimage
	 *
	 * @return pimage
	 */
	public PImage getWhiteKnight() {
		return this.whiteKnight;
	}

	/**
	 * Getter for pimage
	 *
	 * @return pimage
	 */
	public PImage getWhitePawn() {
		return this.whitePawn;
	}

	/**
	 * Getter for pimage
	 *
	 * @return pimage
	 */
	public PImage getWhiteQueen() {
		return this.whiteQueen;
	}

	/**
	 * Getter for pimage
	 *
	 * @return pimage
	 */
	public PImage getWhiteTower() {
		return this.whiteTower;
	}

	/**
	 * Setter for pimage
	 *
	 * @param blackBishop pimage
	 */
	public void setBlackBishop(PImage blackBishop) {
		this.blackBishop = blackBishop;
	}

	/**
	 * Setter for pimage
	 *
	 * @param blackKing pimage
	 */
	public void setBlackKing(PImage blackKing) {
		this.blackKing = blackKing;
	}

	/**
	 * Setter for pimage
	 *
	 * @param blackKnight pimage
	 */
	public void setBlackKnight(PImage blackKnight) {
		this.blackKnight = blackKnight;
	}

	/**
	 * Setter for pimage
	 *
	 * @param blackPawn pimage
	 */
	public void setBlackPawn(PImage blackPawn) {
		this.blackPawn = blackPawn;
	}

	/**
	 * Setter for pimage
	 *
	 * @param blackQueen pimage
	 */
	public void setBlackQueen(PImage blackQueen) {
		this.blackQueen = blackQueen;
	}

	/**
	 * Setter for pimage
	 *
	 * @param blackTower pimage
	 */
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
			this.setup.execute();
		} catch (Exception e) {
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
	public void setWhiteBishop(PImage whiteBishop) {
		this.whiteBishop = whiteBishop;
	}

	/**
	 * Setter for pimage
	 *
	 * @param whiteKing pimage
	 */
	public void setWhiteKing(PImage whiteKing) {
		this.whiteKing = whiteKing;
	}

	/**
	 * Setter for pimage
	 *
	 * @param whiteKnight pimage
	 */
	public void setWhiteKnight(PImage whiteKnight) {
		this.whiteKnight = whiteKnight;
	}

	/**
	 * Setter for pimage
	 *
	 * @param whitePawn pimage
	 */
	public void setWhitePawn(PImage whitePawn) {
		this.whitePawn = whitePawn;
	}

	/**
	 * Setter for pimage
	 *
	 * @param whiteQueen pimage
	 */
	public void setWhiteQueen(PImage whiteQueen) {
		this.whiteQueen = whiteQueen;
	}

	/**
	 * Setter for pimage
	 *
	 * @param whiteTower pimage
	 */
	public void setWhiteTower(PImage whiteTower) {
		this.whiteTower = whiteTower;
	}

	/**
	 * @return the redraw
	 */
	protected boolean isRedraw() {
		return redraw;
	}

	/**
	 * @param redraw the redraw to set
	 */
	protected void setRedraw(boolean redraw) {
		this.redraw = redraw;
	}
}
