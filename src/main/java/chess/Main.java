package chess;

import java.util.List;

import conf.Config;
import defs.classes.Drawer;
import defs.classes.Field; 
import defs.classes.Move;
import defs.classes.Piece;
import defs.classes.Player;
import defs.classes.Referee;
import defs.classes.Setup;
import defs.enums.Colors;
import processing.core.PImage;
import processing.template.Gui;

public class Main extends Gui {

	final static String mainclass = "chess.Main";
	final static String path ="";

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

	private Setup setup=Setup.getInstance(this);
	private Drawer drawer=Drawer.getInstance(this);
	
	public static void main(String[] args) {
		(new Gui()).run(mainclass);
	}

	
	@Override
	public void settings() {
	}
	
	
	@Override
	public void setup() {
		
		setup.execute();		
		stroke(0);
	}

	
	@Override
	public void draw() {

		drawer.execute();
		
	}
		
	public Player getOtherPlayer() {
		return getSpiel().getOtherPlayer();
	}
	
	public static Field getField(int i, int j) {
		return getSpiel().getField(i, j);
	}

	public int getPosI() {
		int PosI = (mouseX - mouseX % Config.Size) / Config.Size;
		return PosI;
	}

	public int getPosJ() {
		int PosJ = 7 - (mouseY - mouseY % Config.Size) / Config.Size;
		return PosJ;
	}

	public PImage getWhiteKing() {
		return whiteKing;
	}

	public void setWhiteKing(PImage whiteKing) {
		this.whiteKing = whiteKing;
	}

	public PImage getBlackKing() {
		return blackKing;
	}

	public void setBlackKing(PImage blackKing) {
		this.blackKing = blackKing;
	}

	public PImage getWhiteQueen() {
		return whiteQueen;
	}

	public void setWhiteQueen(PImage whiteQueen) {
		this.whiteQueen = whiteQueen;
	}

	public PImage getBlackQueen() {
		return blackQueen;
	}

	public void setBlackQueen(PImage blackQueen) {
		this.blackQueen = blackQueen;
	}

	public PImage getWhiteKnight() {
		return whiteKnight;
	}

	public void setWhiteKnight(PImage whiteKnight) {
		this.whiteKnight = whiteKnight;
	}

	public PImage getBlackKnight() {
		return blackKnight;
	}

	public void setBlackKnight(PImage blackKnight) {
		this.blackKnight = blackKnight;
	}

	public PImage getWhiteBishop() {
		return whiteBishop;
	}

	public void setWhiteBishop(PImage whiteBishop) {
		this.whiteBishop = whiteBishop;
	}

	public PImage getBlackBishop() {
		return blackBishop;
	}

	public void setBlackBishop(PImage blackBishop) {
		this.blackBishop = blackBishop;
	}

	public PImage getWhiteTower() {
		return whiteTower;
	}

	public void setWhiteTower(PImage whiteTower) {
		this.whiteTower = whiteTower;
	}

	public PImage getBlackTower() {
		return blackTower;
	}

	public void setBlackTower(PImage blackTower) {
		this.blackTower = blackTower;
	}

	public PImage getWhitePawn() {
		return whitePawn;
	}

	public void setWhitePawn(PImage whitePawn) {
		this.whitePawn = whitePawn;
	}

	public PImage getBlackPawn() {
		return blackPawn;
	}

	public void setBlackPawn(PImage blackPawn) {
		this.blackPawn = blackPawn;
	}

	public static String getLoc() {
		return mainclass;
	}

	public String getPath() {
		return path;
	}

	public static Game getSpiel() {
		return Game.getInstance();
	}

	public static Referee getReferee() {
		return Game.getReferee();
	}

	public void processMove(Move move) {
		if (move != null) {
			getSpiel().getZugListe().add(move);
			System.out.println((getSpiel().getZugListe()).toStr());
			move.execute(getSpiel());
		}
	}
	
	public static Player getPlayer() {
		return Game.getInstance().getPlayer();
	}
}
