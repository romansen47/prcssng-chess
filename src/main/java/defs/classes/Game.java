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
		if (Game.instance == null) {
			Game.instance = new Game();
		}
		return Game.instance;
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
		this.Chessboard = new Field[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((i + j) % 2 == 0) {
					this.Chessboard[i][j] = new Field(Colors.BLACK, i, j);
				} else {
					this.Chessboard[i][j] = new Field(Colors.WHITE, i, j);
				}
			}
		}
		Referee.getInstance();
	}

	/**
	 * getter for the black player
	 * 
	 * @return the black player
	 */
	public Player getBlack() {
		return Game.Black;
	}

	/**
	 * Getter for fields from the chessboard
	 * 
	 * @param i coordinate for the row
	 * @param j coordinate for the column
	 * @return the field at given coordinates
	 */
	public Field getField(int i, int j) {
		return this.Chessboard[i][j];
	}

	/**
	 * getter for the timeline
	 * 
	 * @return the timeline
	 */
	public Timeline getMoveList() {
		return this.MoveList;
	}

	/**
	 * getter for the passive player
	 * 
	 * @return the passive player
	 */
	public Player getOpponent() {
		if (this.getPlayer() == this.getWhite()) {
			return this.getBlack();
		}
		return this.getWhite();
	}

	/**
	 * getter for the acting player
	 * 
	 * @return the acting player
	 */
	public Player getPlayer() {
		return Game.Player;
	}

	/**
	 * getter for the white player
	 * 
	 * @return the white player
	 */
	public Player getWhite() {
		return Game.White;
	}

	/**
	 * setter for the black player
	 * 
	 * @param black the black player
	 */
	public void setBlack(Player black) {
		Game.Black = black;
	}

	/**
	 * setter for the active player
	 * 
	 * @param player the player
	 */
	public void setPlayer(Player player) {
		Game.Player = player;
	}

	/**
	 * setup method for the game. creates the players and sets the white player as
	 * the acting player to perform the first move
	 */
	public void setup() {
		this.setWhite(new Player(Colors.WHITE));
		if (Config.isRandomPlayer()) {
			this.setBlack(new RandomPlayer());
		} else {
			this.setBlack(new Player(Colors.BLACK));
		}
		this.setPlayer(this.getWhite());
	}

	/**
	 * setter for the white player
	 * 
	 * @param white the white player
	 */
	public void setWhite(Player white) {
		Game.White = white;
	}

}
