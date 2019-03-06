package conf;

/**
 * 
 * @author roman
 *
 *         final config, modeeled as a singleton class
 */
final public class Config {

	/**
	 * the stanard size of a field
	 */
	public final static int SIZE = 100;

	public final static int GAMESIZE = 7;

	private static Config instance = null;

	private Config() {
	}

	private static boolean RandomPlayer = false;

	public static Config getInstance() {
		if (instance == null) {
			instance = new Config();
		}
		return instance;
	}

	public static boolean isRandomPlayer() {
		return RandomPlayer;
	}

	public void setRandomPlayer(boolean randomPlayer) {
		RandomPlayer = randomPlayer;
	}

}
