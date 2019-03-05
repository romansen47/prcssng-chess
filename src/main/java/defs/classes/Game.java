package defs.classes;

import artint.RandomPlayer;
import conf.Config;
import conf.Referee;
import conf.Timeline;
import defs.enums.Colors;
import defs.interfaces.IRefs;

final public class Game implements IRefs{

	private final Field[][] Chessboard;
	private static Player Player;
	private static Player White;
	private static Player Black;

	final private Timeline MoveList = Timeline.getInstance();

	private static Game instance = null;

	public static Game getInstance() {
		if (instance == null) {
			instance = new Game();
		}
		return instance;
	}

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

	public void setup() {
		setWhite(new Player(Colors.WHITE));
		if (Config.isRandomPlayer()){
			setBlack(new RandomPlayer());
		}
		else{
			setBlack(new Player(Colors.BLACK));
		}
		setPlayer(getWhite());
	}

	public Field getField(int i, int j) {
		return Chessboard[i][j];
	}

	public Player getBlack() {
		return Black;
	}

	public void setBlack(Player black) {
		Black = black;
	}

	public Player getWhite() {
		return White;
	}

	public void setWhite(Player white) {
		White = white;
	}

	public Player getPlayer() {
		return Player;
	}
	
	public Player getOtherPlayer() {
		if (getPlayer()==getWhite()) {
			return getBlack();
		}
		return getWhite();
		
	}

	public void setPlayer(Player player) {
		Player = player;
	}

	public Timeline getMoveList() {
		return MoveList;
	}
	
}
