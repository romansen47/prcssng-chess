package config;

/**
 *
 * @author RoMansen
 *
 *         the singleton classes setup
 *         and drawer use an execute
 *         method
 */
public interface ISetupAndRun extends IRefs {

	/**
	 * main method of Setup and Drawer
	 *
	 * @throws Exception when having
	 *                   problems loading or
	 *                   saving a file
	 */
	void execute() throws Exception;
}
