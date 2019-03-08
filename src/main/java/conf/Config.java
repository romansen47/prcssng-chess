package conf;

/**
 * 
 * @author roman
 *
 *         final config, modelled as a singleton class
 */
final public class Config {

	/**
	 * GAMESIZE+1 is the amount of fields in each directions
	 */
	public final static int GAMESIZE = 7;

	/**
	 * static instance of Config
	 */
	private static Config instance = null;

	/**
	 * Do we use a random player?
	 */
	private static boolean RandomPlayer = false;

	/**
	 * the stanard size of a field
	 */
	public final static int SIZE = 100;

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
		return Config.RandomPlayer;
	}

	/**
	 * private Constructor
	 */
	private Config() {
	}

	/**
	 * Setter for RandomPlayer
	 * 
	 * @param randomPlayer instance of RandomPlayer
	 */
	public void setRandomPlayer(boolean randomPlayer) {
		Config.RandomPlayer = randomPlayer;
	}

}
