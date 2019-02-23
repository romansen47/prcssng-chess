package conf;

import java.util.ArrayList;

import chess.Move;
import defs.interfaces.IRefs;

/**
 * 
 * @author roman
 *
 * TODO
 */
public class Timeline extends ArrayList<Move> implements IRefs {

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
