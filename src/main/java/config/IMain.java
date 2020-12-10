package config;

import defs.classes.Field;
import defs.players.IPlayer;
import processing.core.PImage;
import processing.template.abstr.IGuiTemplate;

public interface IMain extends IGuiTemplate {

	String getMainClass();

	/**
	 * Getter for the acting player
	 *
	 * @return the acting player
	 */
	IPlayer getPlayer();

	/**
	 * functionality outsourced to Drawer class
	 */
	@Override
	void draw();

	/**
	 * Getter for pimage
	 *
	 * @return pimage
	 */
	PImage getBlackBishop();

	/**
	 * Getter for pimage
	 *
	 * @return pimage
	 */
	PImage getBlackKing();

	/**
	 * Getter for pimage
	 *
	 * @return pimage
	 */
	PImage getBlackKnight();

	/**
	 * Getter for pimage
	 *
	 * @return pimage
	 */
	PImage getBlackPawn();

	/**
	 * Getter for pimage
	 *
	 * @return pimage
	 */
	PImage getBlackQueen();

	/**
	 * Getter for pimage
	 *
	 * @return pimage
	 */
	PImage getBlackTower();

	/**
	 * Getter for fields
	 *
	 * @param i the column number
	 * @param j the row number
	 * @return the field at position (i,j)
	 */
	Field getField(int i, int j);

	/**
	 * Getter for inactive player
	 *
	 * @return the inactive player
	 */
	IPlayer getOtherPlayer();

	/**
	 * Getter for PATH
	 *
	 * @return the path to main class as string
	 */
	String getPath();

	/**
	 * Getter for Position
	 *
	 * @return the vertical position
	 */
	int getPosI();

	/**
	 * Getter for Position
	 *
	 * @return the horizontal position
	 */
	int getPosJ();

	/**
	 * Getter for pimage
	 *
	 * @return pimage
	 */
	PImage getWhiteBishop();

	/**
	 * Getter for pimage
	 *
	 * @return pimage
	 */
	PImage getWhiteKing();

	/**
	 * Getter for pimage
	 *
	 * @return pimage
	 */
	PImage getWhiteKnight();

	/**
	 * Getter for pimage
	 *
	 * @return pimage
	 */
	PImage getWhitePawn();

	/**
	 * Getter for pimage
	 *
	 * @return pimage
	 */
	PImage getWhiteQueen();

	/**
	 * Getter for pimage
	 *
	 * @return pimage
	 */
	PImage getWhiteTower();

	/**
	 * Setter for pimage
	 *
	 * @param blackBishop pimage
	 */
	void setBlackBishop(PImage blackBishop);

	/**
	 * Setter for pimage
	 *
	 * @param blackKing pimage
	 */
	void setBlackKing(PImage blackKing);

	/**
	 * Setter for pimage
	 *
	 * @param blackKnight pimage
	 */
	void setBlackKnight(PImage blackKnight);

	/**
	 * Setter for pimage
	 *
	 * @param blackPawn pimage
	 */
	void setBlackPawn(PImage blackPawn);

	/**
	 * Setter for pimage
	 *
	 * @param blackQueen pimage
	 */
	void setBlackQueen(PImage blackQueen);

	/**
	 * Setter for pimage
	 *
	 * @param blackTower pimage
	 */
	void setBlackTower(PImage blackTower);

	/**
	 * functionality outsourced to Setup class
	 */
	@Override
	void setup();

	/**
	 * Setter for pimage
	 *
	 * @param whiteBishop pimage
	 */
	void setWhiteBishop(PImage whiteBishop);

	/**
	 * Setter for pimage
	 *
	 * @param whiteKing pimage
	 */
	void setWhiteKing(PImage whiteKing);

	/**
	 * Setter for pimage
	 *
	 * @param whiteKnight pimage
	 */
	void setWhiteKnight(PImage whiteKnight);

	/**
	 * Setter for pimage
	 *
	 * @param whitePawn pimage
	 */
	void setWhitePawn(PImage whitePawn);

	/**
	 * Setter for pimage
	 *
	 * @param whiteQueen pimage
	 */
	void setWhiteQueen(PImage whiteQueen);

	/**
	 * Setter for pimage
	 *
	 * @param whiteTower pimage
	 */
	void setWhiteTower(PImage whiteTower);

	/**
	 * @return the redraw
	 */
	boolean isRedraw();

	/**
	 * @param redraw the redraw to set
	 */
	void setRedraw(boolean redraw);

	/**
	 * @return the restore
	 */
	boolean isRestore();

	/**
	 * @param restore the restore to set
	 */
	void setRestore(boolean restore);

	/**
	 * @return the sAVEDTIMELINEPATH
	 */
	String getSAVEDTIMELINEPATH();

	/**
	 * @param sAVEDTIMELINEPATH the sAVEDTIMELINEPATH to set
	 */
	void setSAVEDTIMELINEPATH(String sAVEDTIMELINEPATH);

	/**
	 * @return the drawer
	 */
	ISetupAndRun getDrawer();

}
