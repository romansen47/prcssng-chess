package defs.classes;

import artint.RandomPlayer;
import conf.Config;
import conf.Referee;
import conf.Timeline;
import defs.enums.Colors;
import defs.interfaces.IRefs;

final public class Game implements IRefs {

	/**
	 * the black player
	 */
	private static Player Black;
	/**
	 * static instance
	 */
	private static Game instance = null;
	/**
	 * the acting player
	 */
	private static Player Player;
	/**
	 * the white player
	 */
	private static Player White;

	/**
	 * Getter for static instance
	 * 
	 * @return the static instance
	 */
	public static Game getInstance() {
		if (instance == null) {
			instance = new Game();
		}
		return instance;
	}

	/**
	 * the chessboard
	 */
	private final Field[][] Chessboard;

	/**
	 * the final timeline
	 */
	final private Timeline MoveList = Timeline.getInstance();

	/**
	 * private constructor
	 */
	private Game() {
		// Generate the chess board
		Chessboard = new Field[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((i + j) % 2 == 0) {
					Chessboard[i][j] = new Field(Colors.BLACK, i, j);
				} else {
					Chessboard[i][j] = new Field(Colors.WHITE, i, j);
				}
			}
		}
		Referee.getInstance();
	}

	/**
	 * getter for the black player
	 * 
	 * @return
	 */
	public Player getBlack() {
		return Black;
	}

	/**
	 * Getter for fields from the chessboard
	 * 
	 * @param i coordinate for the row
	 * @param j coordinate for the column
	 * @return the field at given coordinates
	 */
	public Field getField(int i, int j) {
		return Chessboard[i][j];
	}

	/**
	 * getter for the timeline
	 * 
	 * @return the timeline
	 */
	public Timeline getMoveList() {
		return MoveList;
	}

	/**
	 * getter for the passive player
	 * 
	 * @return
	 */
	public Player getOpponent() {
		if (getPlayer() == getWhite()) {
			return getBlack();
		}
		return getWhite();
	}

	/**
	 * getter for the acting player
	 * 
	 * @return the acting player
	 */
	public Player getPlayer() {
		return Player;
	}

	/**
	 * getter for the white player
	 * 
	 * @return the white player
	 */
	public Player getWhite() {
		return White;
	}

	/**
	 * setter for the black player
	 * 
	 * @param black the black player
	 */
	public void setBlack(Player black) {
		Black = black;
	}

	/**
	 * setter for the active player
	 * 
	 * @param player
	 */
	public void setPlayer(Player player) {
		Player = player;
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

	/**
	 * setter for the white player
	 * 
	 * @param white the white player
	 */
	public void setWhite(Player white) {
		White = white;
	}

}
