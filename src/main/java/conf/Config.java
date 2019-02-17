package conf;

final public class Config {

	public final static int Size = 150;

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
