package testclasses;

import java.nio.file.Path;

import org.junit.BeforeClass;
import org.junit.Test;

import chess.ConcreteChess;
import config.Drawer;
import config.IMain;
import defs.classes.Referee;

public class TestRunner {

	static IMain main;
	final static String PATH = "chess.ConcreteChess";
	final static String mainPath = "src/test/resources/movechains/";

	@BeforeClass
	public static void setup() {
		main = new ConcreteChess();
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

	@Test
	public void runIssues() throws Exception {
		final String source = mainPath + "issues/";
		runTests("Issues:", source);
	}

	private static void runTest(String path) throws Exception {
		System.out.println("Zugkette " + path + " wird durchlaufen...");
		((Drawer) main.getDrawer()).restoreFromXml(path.toString());
		Referee.getInstance().reset();
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
