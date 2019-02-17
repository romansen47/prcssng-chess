package visual;

import processing.core.PApplet;

public class ChessGui extends processingTemplate.GuiTemplate {

	@Override
	public boolean run(String arg0) {
		PApplet.main(arg0);
		return true;
	}

	@Override
	public void setup() {
		fullScreen();
		background(0);
	}

	@Override
	public void settings() {

	}

	@Override
	public void draw() {
		fill(255);
		ellipse(200, 200, 200, 200);
	}
}
