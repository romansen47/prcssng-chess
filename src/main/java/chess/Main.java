package chess;

import conf.Config;
import defs.classes.Field;
import defs.classes.Move;
import defs.classes.Player;
import processing.core.PImage;
import processing.template.Gui;

public class Main extends Gui {

	final static String mainclass = "chess.Main";
	final static String path = "C:/Users/roman/eclipse-workspace/chess/src/main/java/pics/";

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
	protected boolean clicked;

	public static void main(String[] args) {
		(new Gui()).run(mainclass);
	}

	@Override
	public void settings() {
	}

	@Override
	public void setup() {
		background(255);
		surface.setResizable(true);
		surface.setSize(12 * Config.Size, 8 * Config.Size);
		surface.setLocation(displayWidth - width >> 1, displayHeight - height >> 1);
		frameRate(60);
		getSpiel().setup();
		whiteKing = loadImage(path + "white_king.png");
		blackKing = loadImage(path + "black_king.png");
		whiteQueen = loadImage(path + "white_queen.png");
		blackQueen = loadImage(path + "black_queen.png");
		whiteKnight = loadImage(path + "white_knight.png");
		blackKnight = loadImage(path + "black_knight.png");
		whiteBishop = loadImage(path + "white_bishop.png");
		blackBishop = loadImage(path + "black_bishop.png");
		whiteTower = loadImage(path + "white_rook.png");
		blackTower = loadImage(path + "black_rook.png");
		whitePawn = loadImage(path + "white_pawn.png");
		blackPawn = loadImage(path + "black_pawn.png");
		stroke(0);
		getReferee();
	}

	@Override
	public void draw() {
		if (clicked() == 1) {
			clicked = true;
		} else {
			clicked = false;
		}
		drawChessboard();
		if (clicked) {
			if (!getReferee().isMarked()) {
				if (getSpiel().getField(getPosJ(), getPosI()).getFigur() != null
						&& getSpiel().getField(getPosJ(), getPosI()).getFigur().getCol() == getPlayer().getCol()) {
					getReferee().setMarked(getSpiel().getField(getPosJ(), getPosI()));
				}
			} else {
				getReferee().setMarked2(getSpiel().getField(getPosJ(), getPosI()));
			}
			loop();
		}
		Move move = getReferee().getZug();
		if (move != null) {
			getSpiel().getZugListe().add(move);
			System.out.println((getSpiel().getZugListe()).toStr());
			move.execute(getSpiel());
		}
	}

	public void drawChessboard() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (getReferee().getMarked() != null) {
					rahmen(getReferee().getMarked());
				}
				getSpiel().getField(i, j).draw(this);
				stroke(0);
				strokeWeight(3);
				line(0, 0, 8 * Config.Size, 0);
				line(0, 0, 0, 8 * Config.Size);
				line(8 * Config.Size, 0, 8 * Config.Size, 8 * Config.Size);
				line(0, 8 * Config.Size, 8 * Config.Size, 8 * Config.Size);
			}
		}
		if (getReferee().getMarked() != null) {
			for (Field fld : getReferee().getMarked().getFigur().getPossibleMoves()) {
				rahmen(fld);
			}
		}
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

	public static String getPath() {
		return path;
	}

	public static Game getSpiel() {
		return Game.getInstance();
	}

	public static Referee getReferee() {
		return Game.getReferee();
	}

	public void rahmen(Field fld) {
		stroke(0, 255, 0);
		strokeWeight(7);
		int size = Config.Size;
		line(fld.getJ() * size, fld.getI() * size, (fld.getJ() + 1) * size, fld.getI() * size);
		line(fld.getJ() * size, fld.getI() * size, fld.getJ() * size, (fld.getI() + 1) * size);
		line((fld.getJ() + 1) * size, fld.getI() * size, (fld.getJ() + 1) * size, (fld.getI() + 1) * size);
		line(fld.getJ() * size, (fld.getI() + 1) * size, (fld.getJ() + 1) * size, (fld.getI() + 1) * size);
	}

	public static Player getPlayer() {
		return Game.getInstance().getPlayer();
	}
}
