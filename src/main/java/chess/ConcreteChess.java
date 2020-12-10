package chess;

import config.AbstractChess;
import processing.template.impl.Gui;

public class ConcreteChess extends AbstractChess {

	/**
	 * Getter for MainClass
	 *
	 * @return the main class as string
	 */
	@Override
	public String getMainClass() {
		return ConcreteChess.MAINCLASS;
	}

	/**
	 * Initial main method
	 *
	 * @param args some options
	 */
	public static void main(String[] args) {
		(new Gui()).run(ConcreteChess.MAINCLASS);
	}

	@Override
	public void settings() {
		/**
		 * Nothing to do here...
		 */
	}

	/**
	 * functionality outsourced to Setup class
	 */
	@Override
	public void setup() {
		try {
			setup.execute();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.stroke(0);
	}

	/**
	 * functionality outsourced to Drawer class
	 */
	@Override
	public void draw() {
		if (getPlayer() != null) {
			try {
				getDrawer().execute();
			} catch (final Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				Thread.sleep(300);
				System.exit(0);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
