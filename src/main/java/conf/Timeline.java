package conf;

import java.util.ArrayList;
import java.util.List;

import defs.enums.Colors;
import defs.interfaces.IMove;
import defs.interfaces.IRefs;

/**
 *
 * @author RoMansen
 *
 */
public class Timeline extends ArrayList<IMove> implements IRefs {

	/**
	 * static instance
	 */
	private static Timeline instance = null;

	/**
	 * Whatever
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Getter for static instance
	 *
	 * @return the static instance
	 */
	public static Timeline getInstance() {
		if (Timeline.instance == null) {
			Timeline.instance = new Timeline();
		}
		return Timeline.instance;
	}

	/**
	 * private Constructor
	 */
	private Timeline() {
	}

	/**
	 * gives a list with all moves in chess notation
	 *
	 * @return a list of strings
	 */
	public List<String> toStr() {
		final List<String> ans = new ArrayList<>();
		String tmp;
		int i = 1;
		for (final IMove move : Timeline.instance) {
			tmp = i++ + ": " + move.toString();
			if (move.getCol() == Colors.WHITE) {
				ans.add(tmp);
			} else {
				String last = ans.get(ans.size() - 1);
				last = last + " ,  " + tmp;
				ans.set(ans.size() - 1, last);
			}
		}
		return ans;
	}

}
