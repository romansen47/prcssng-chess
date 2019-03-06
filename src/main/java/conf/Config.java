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
	 * @return
	 */
	public static Config getInstance() {
		if (instance == null) {
			instance = new Config();
		}
		return instance;
	}

	/**
	 * Getter for RandomPlayer
	 * 
	 * @return
	 */
	public static boolean isRandomPlayer() {
		return RandomPlayer;
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
		RandomPlayer = randomPlayer;
	}

}
