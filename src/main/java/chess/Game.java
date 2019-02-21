package chess;

import defs.classes.Field;
import defs.classes.Player;
import defs.classes.Timeline;
import defs.enums.Colors;
import defs.enums.State;
import defs.interfaces.IGame;

final public class Game implements IGame{

	private final Field[][] Chessboard;
	private static Player Player;
	private static Player White;
	private static Player Black;
	private static Referee referee = null;
	private State state = State.White_Turn;

	final private Timeline Zugliste = Timeline.getInstance();

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
		referee = Referee.getInstance();
	}

	public void setup() throws Exception {
		setWhite(new Player(Colors.WHITE));
		setPlayer(getWhite());
		setBlack(new Player(Colors.BLACK));
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

	public static Referee getReferee() {
		return referee;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Timeline getZugListe() {
		return Zugliste;
	}

}
