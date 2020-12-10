package config;

import chess.ConcreteChess;
import defs.classes.Field;
import defs.classes.Game;
import defs.classes.Setup;
import defs.players.IPlayer;
import processing.core.PImage;
import processing.template.impl.Gui;

public abstract class AbstractChess extends Gui implements IRefs, IMain {

	/**
	 * a drawer instance
	 */
	protected final ISetupAndRun drawer = Drawer.getInstance(this);

	/**
	 * a setup instance
	 */
	protected final ISetupAndRun setup = Setup.getInstance(this);

	protected static boolean restore = true;

	/**
	 * the main class
	 */
	protected static final String MAINCLASS = "chess.ConcreteChess";

	/**
	 * the path for where the images are stored
	 */
	protected static final String PATH = "";

	protected static String SAVEDTIMELINEPATH = "target/TimeLine.xml";

	/**
	 * True, if the frame should be redrawn
	 */
	protected boolean redrawCustom = true;

	/**
	 * image for the piece
	 */
	protected PImage blackBishop;

	/**
	 * image for the piece
	 */
	protected PImage blackKing;

	/**
	 * image for the piece
	 */
	protected PImage blackKnight;

	/**
	 * image for the piece
	 */
	protected PImage blackPawn;

	/**
	 * image for the piece
	 */
	protected PImage blackQueen;

	/**
	 * image for the piece
	 */
	protected PImage blackTower;

	/**
	 * image for the piece
	 */
	protected PImage whiteBishop;

	/**
	 * image for the piece
	 */
	protected PImage whiteKing;

	/**
	 * image for the piece
	 */
	protected PImage whiteKnight;

	/**
	 * image for the piece
	 */
	protected PImage whitePawn;

	/**
	 * image for the piece
	 */
	protected PImage whiteQueen;

	/**
	 * image for the piece
	 */
	protected PImage whiteTower;

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
		return ConcreteChess.PATH;
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
		ConcreteChess.restore = restore;
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
		ConcreteChess.SAVEDTIMELINEPATH = sAVEDTIMELINEPATH;
	}

	/**
	 * @return the drawer
	 */
	@Override
	public ISetupAndRun getDrawer() {
		return drawer;
	}

}
