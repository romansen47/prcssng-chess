package testclasses;

import java.nio.file.Path;

import org.junit.BeforeClass;
import org.junit.Test;

import chess.Drawer;
import chess.IMain;
import chess.Main;

public class TestRunner {

	static IMain main;
	final static String PATH = "chess.Main";
	final static String mainPath = "src/test/resources/situations/";

	@BeforeClass
	public static void setup() {
		main = new Main();
		main.run(PATH);
	}

	@Test
	public void runCastlingTests() throws Exception {
		final String source = mainPath + "castlings/";
		runTests("Castlings:", source);
	}

	@Test
	public void runEnPassantTests() throws Exception {
		final String source = mainPath + "enPassants/";
		runTests("EnPassant:", source);
	}

	private static void runTest(String path) throws Exception {
		System.out.println("Zugkette " + path + " wird durchlaufen...");
		((Drawer) main.getDrawer()).restoreFromXml(path.toString());
	}

	private static void runTests(String name, String path) throws Exception {
		System.out.println("----------");
		System.out.println(name);
		System.out.println("----------");
		for (Path src : ((Drawer) main.getDrawer()).ls(path.toString())) {
			runTest(path + src.toString());
		}
		System.out.println("----------");
		System.out.println("");
	}

	/**
	 * Getter for MainClass
	 *
	 * @return the main class as string
	 */
	public static String getMainClass() {
		return "testclasses.TestRunner";
	}

}
