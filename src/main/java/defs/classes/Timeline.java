package defs.classes;

import java.util.ArrayList;

import defs.interfaces.ISpiel;

public class Timeline extends ArrayList<Move> implements ISpiel {

	private static final long serialVersionUID = 1L;
	private static Timeline instance = null;

	private Timeline() {
	}

	public static Timeline getInstance() {
		if (instance == null) {
			instance = new Timeline();
		}
		return instance;

	}

	public String toStr() {
		String str = "";
		for (Move move : instance) {
			str += move.toString() + "\n ";
		}
		return str;
	}

}
