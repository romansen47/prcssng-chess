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
	 * Do we use a random black player?
	 */
	private static boolean randomBlackPlayer = true;

	/**
	 * Do we use a random black player?
	 */
	private static boolean randomWhitePlayer = true;

	/**
	 * the standard size of a field
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
	 * Getter for RandomBlackPlayer
	 *
	 * @return the black random player
	 */
	public static boolean isRandomBlackPlayer() {
		return Config.randomBlackPlayer;
	}

	/**
	 * Getter for RandomWhitePlayer
	 *
	 * @return the white random player
	 */
	public static boolean isRandomWhitePlayer() {
		return Config.randomWhitePlayer;
	}

	/**
	 * Setter for RandomBlackPlayer
	 *
	 * @param randomPlayer instance of RandomPlayer
	 */
	public static void setRandomBlackPlayer(boolean randomPlayer) {
		Config.randomBlackPlayer = randomPlayer;
	}

	/**
	 * Setter for RandomPlayer
	 *
	 * @param randomPlayer instance of RandomWhitePlayer
	 */
	public static void setRandomWhitePlayer(boolean randomPlayer) {
		Config.randomWhitePlayer = randomPlayer;
	}

	/**
	 * private Constructor
	 */
	private Config() {
	}

}
