package conf;

/**
 * 
 * @author roman
 *
 *         final config, modelled as a singleton class
 */
public final class Config {

	/**
	 * GAMESIZE+1 is the amount of fields in each directions
	 */
	public static final int GAMESIZE = 7;

	/**
	 * static instance of Config
	 */
	private static Config instance = null;

	/**
	 * Do we use a random player?
	 */
	private static boolean randomPlayer = false;

	/**
	 * the stanard size of a field
	 */
	public static final int SIZE = 100;

	/**
	 * getter for the static instance
	 * 
	 * @return the static instance
	 */
	public static Config getInstance() {
		if (Config.instance == null) {
			Config.instance = new Config();
		}
		return Config.instance;
	}

	/**
	 * Getter for RandomPlayer
	 * 
	 * @return the random player
	 */
	public static boolean isRandomPlayer() {
		return Config.randomPlayer;
	}

	/**
	 * Setter for RandomPlayer
	 * 
	 * @param randomPlayer instance of RandomPlayer
	 */
	public static void setRandomPlayer(boolean randomPlayer) {
		Config.randomPlayer = randomPlayer;
	}

	/**
	 * private Constructor
	 */
	private Config() {
	}

}
