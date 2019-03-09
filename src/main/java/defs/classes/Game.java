package defs.classes;

import artint.RandomPlayer;
import conf.Config;
import conf.Referee;
import conf.Timeline;
import defs.enums.Colors;
import defs.interfaces.IRefs;

public final class Game implements IRefs {

	/**
	 * the black player
	 */
	private static Player black;
	/**
	 * static instance
	 */
	private static Game instance = null;
	/**
	 * the acting player
	 */
	private static Player player;
	/**
	 * the white player
	 */
	private static Player white;

	/**
	 * getter for the black player
	 * 
	 * @return the black player
	 */
	public static Player getBlack() {
		return Game.black;
	}

	/**
	 * Getter for static instance
	 * 
	 * @return the static instance
	 */
	public static Game getInstance() {
		if (Game.instance == null) {
			Game.instance = new Game();
		}
		return Game.instance;
	}

	/**
	 * getter for the acting player
	 * 
	 * @return the acting player
	 */
	public static Player getPlayer() {
		return Game.player;
	}

	/**
	 * getter for the white player
	 * 
	 * @return the white player
	 */
	public static Player getWhite() {
		return Game.white;
	}

	/**
	 * setter for the black player
	 * 
	 * @param black the black player
	 */
	public static void setBlack(Player black) {
		Game.black = black;
	}

	/**
	 * setter for the active player
	 * 
	 * @param player the player
	 */
	public static void setPlayer(Player player) {
		Game.player = player;
	}

	/**
	 * setter for the white player
	 * 
	 * @param white the white player
	 */
	public static void setWhite(Player white) {
		Game.white = white;
	}

	/**
	 * the chessboard
	 */
	private final Field[][] chessboard;

	/**
	 * the final timeline
	 */
	private final Timeline moveList = Timeline.getInstance();

	/**
	 * private constructor
	 */
	private Game() {
		// Generate the chess board
		this.chessboard = new Field[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((i + j) % 2 == 0) {
					this.chessboard[i][j] = new Field(Colors.BLACK, i, j);
				} else {
					this.chessboard[i][j] = new Field(Colors.WHITE, i, j);
				}
			}
		}
		Referee.getInstance();
	}

	/**
	 * Getter for fields from the chessboard
	 * 
	 * @param i coordinate for the row
	 * @param j coordinate for the column
	 * @return the field at given coordinates
	 */
	public Field getField(int i, int j) {
		return this.chessboard[i][j];
	}

	/**
	 * getter for the timeline
	 * 
	 * @return the timeline
	 */
	public Timeline getMoveList() {
		return this.moveList;
	}

	/**
	 * getter for the passive player
	 * 
	 * @return the passive player
	 */
	public Player getOpponent() {
		if (getPlayer() == getWhite()) {
			return getBlack();
		}
		return getWhite();
	}

	/**
	 * setup method for the game. creates the players and sets the white player as
	 * the acting player to perform the first move
	 */
	public void setup() {
		setWhite(new Player(Colors.WHITE));
		if (Config.isRandomPlayer()) {
			setBlack(new RandomPlayer());
		} else {
			setBlack(new Player(Colors.BLACK));
		}
		setPlayer(getWhite());
	}

}
