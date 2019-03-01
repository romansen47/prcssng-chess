package conf;

/**
 * 
 * @author roman
 *
 * final config, modeeled as a singleton class
 */
final public class Config {

	/**
	 * the stanard size of a field
	 */
	public final static int SIZE = 100;

	private static Config instance = null;

	private Config() {
	}

	public static Config getInstance() {
		if (instance == null) {
			instance = new Config();
		}
		return instance;
	}

}
