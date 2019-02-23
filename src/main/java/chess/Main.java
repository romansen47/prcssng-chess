package chess;

import conf.Config;
import defs.classes.Field;
import defs.classes.Game;
import defs.classes.Player;
import defs.interfaces.IRefs;
import processing.core.PImage;
import processing.template.Gui;

public class Main extends Gui implements IRefs{

	/**
	 * the main class
	 */
	final static String mainclass = "chess.Main";
	
	/**
	 * the path for where the images are stored
	 */
	final static String path ="";

	/**
	 * the images for the pieces
	 */
	private PImage whiteKing;
	private PImage blackKing;
	private PImage whiteQueen;
	private PImage blackQueen;
	private PImage whiteKnight;
	private PImage blackKnight;
	private PImage whiteBishop;
	private PImage blackBishop;
	private PImage whiteTower;
	private PImage blackTower;
	private PImage whitePawn;
	private PImage blackPawn;

	/**
	 * a setup instance
	 */
	private Setup setup=Setup.getInstance(this);
	
	/**
	 * a drawer instance
	 */
	private Drawer drawer=Drawer.getInstance(this);
	
	/**
	 * Initial main method
	 * @param args some options
	 */
	public static void main(String[] args) {
		(new Gui()).run(mainclass);
	}

	@Override
	public void settings() {
	}
	
	/**
	 * functionality outsourced to Setup class
	 */
	@Override
	public void setup() {
		setup.execute();		
		stroke(0);
	}

	/**
	 * functionality outsourced to Drawer class
	 */
	@Override
	public void draw() {
		drawer.execute();
	}
	
	/**
	 * Getter for inactive player
	 * @return the inactive player
	 */
	public Player getOtherPlayer() {
		return getSpiel().getOtherPlayer();
	}
	
	/**
	 * Getter for fields
	 * @param i the column number
	 * @param j the row number
	 * @return the field at position (i,j)
	 */
	public Field getField(int i, int j) {
		return getSpiel().getField(i, j);
	}

	/**
	 * Getter for Position
	 * @return the vertical position
	 */
	public int getPosI() {
		int PosI = 7-(mouseY - mouseY % Config.Size) / Config.Size;
		return PosI;
	}

	/**
	 * Getter for Position
	 * @return the horizontal position
	 */
	public int getPosJ() {
		int PosJ = (mouseX - mouseX % Config.Size) / Config.Size;
		return PosJ;
	}

	/**
	 * Getter for pimage
	 * @return pimage
	 */
	public PImage getWhiteKing() {
		return whiteKing;
	}

	public void setWhiteKing(PImage whiteKing) {
		this.whiteKing = whiteKing;
	}

	/**
	 * Getter for pimage
	 * @return pimage
	 */
	public PImage getBlackKing() {
		return blackKing;
	}

	public void setBlackKing(PImage blackKing) {
		this.blackKing = blackKing;
	}

	/**
	 * Getter for pimage
	 * @return pimage
	 */
	public PImage getWhiteQueen() {
		return whiteQueen;
	}

	public void setWhiteQueen(PImage whiteQueen) {
		this.whiteQueen = whiteQueen;
	}

	/**
	 * Getter for pimage
	 * @return pimage
	 */
	public PImage getBlackQueen() {
		return blackQueen;
	}

	public void setBlackQueen(PImage blackQueen) {
		this.blackQueen = blackQueen;
	}

	/**
	 * Getter for pimage
	 * @return pimage
	 */
	public PImage getWhiteKnight() {
		return whiteKnight;
	}

	public void setWhiteKnight(PImage whiteKnight) {
		this.whiteKnight = whiteKnight;
	}

	/**
	 * Getter for pimage
	 * @return pimage
	 */
	public PImage getBlackKnight() {
		return blackKnight;
	}

	public void setBlackKnight(PImage blackKnight) {
		this.blackKnight = blackKnight;
	}

	/**
	 * Getter for pimage
	 * @return pimage
	 */
	public PImage getWhiteBishop() {
		return whiteBishop;
	}

	public void setWhiteBishop(PImage whiteBishop) {
		this.whiteBishop = whiteBishop;
	}

	/**
	 * Getter for pimage
	 * @return pimage
	 */
	public PImage getBlackBishop() {
		return blackBishop;
	}

	public void setBlackBishop(PImage blackBishop) {
		this.blackBishop = blackBishop;
	}

	/**
	 * Getter for pimage
	 * @return pimage
	 */
	public PImage getWhiteTower() {
		return whiteTower;
	}

	public void setWhiteTower(PImage whiteTower) {
		this.whiteTower = whiteTower;
	}

	/**
	 * Getter for pimage
	 * @return pimage
	 */
	public PImage getBlackTower() {
		return blackTower;
	}

	public void setBlackTower(PImage blackTower) {
		this.blackTower = blackTower;
	}

	/**
	 * Getter for pimage
	 * @return pimage
	 */
	public PImage getWhitePawn() {
		return whitePawn;
	}

	public void setWhitePawn(PImage whitePawn) {
		this.whitePawn = whitePawn;
	}

	/**
	 * Getter for pimage
	 * @return pimage
	 */
	public PImage getBlackPawn() {
		return blackPawn;
	}

	public void setBlackPawn(PImage blackPawn) {
		this.blackPawn = blackPawn;
	}

	/**
	 * Getter for pimage
	 * @return pimage
	 */
	public static String getLoc() {
		return mainclass;
	}

	public String getPath() {
		return path;
	}

	/**
	 * Getter for pimage
	 * @return pimage
	 */
	public static Player getPlayer() {
		return Game.getInstance().getPlayer();
	}
}
