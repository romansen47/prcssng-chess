package config;

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
	public static int GAMESIZE = 7;

	/**
	 * static instance of Config
	 */
	private static Config instance = null;

	/**
	 * Do we use an easy player?
	 */
	private boolean easyPlayer = true;

	/**
	 * Do we use a random black player?
	 */
	private boolean randomBlackPlayer = false;

	/**
	 * Do we use a random white player?
	 */
	private boolean randomWhitePlayer = false;

	/**
	 * the standard size of a field
	 */
	public final int SIZE = 60;

	/**
	 * getter for the static instance
	 *
	 * @return the static instance
	 */
	public static Config getInstance() {
		if (instance == null) {
			instance = new Config();
		}
		return instance;
	}

	/**
	 * Getter for RandomBlackPlayer
	 *
	 * @return the black random player
	 */
	public boolean isRandomBlackPlayer() {
		return randomBlackPlayer;
	}

	/**
	 * Getter for RandomWhitePlayer
	 *
	 * @return the white random player
	 */
	public boolean isRandomWhitePlayer() {
		return randomWhitePlayer;
	}

	/**
	 * Setter for RandomBlackPlayer
	 *
	 * @param randomPlayer instance of RandomPlayer
	 */
	public void setRandomBlackPlayer(boolean randomPlayer) {
		randomBlackPlayer = randomPlayer;
	}

	/**
	 * Setter for RandomPlayer
	 *
	 * @param randomPlayer instance of RandomWhitePlayer
	 */
	public void setRandomWhitePlayer(boolean randomPlayer) {
		randomWhitePlayer = randomPlayer;
	}

	/**
	 * private Constructor
	 */
	private Config() {
	}

	/**
	 * @return the easyPlayer
	 */
	public boolean isEasyPlayer() {
		return easyPlayer;
	}

	/**
	 * @param easyPlayer the easyPlayer to set
	 */
	public void setEasyPlayer(boolean easyPlayer) {
		this.easyPlayer = easyPlayer;
	}

}
