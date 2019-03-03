package conf;

import java.util.ArrayList;
import java.util.List;

import defs.interfaces.IMove;
import defs.interfaces.IRefs;

/**
 * 
 * @author roman
 *
 * TODO
 */
public class Timeline extends ArrayList<IMove> implements IRefs {

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

	public List<String> toStr() {
		List<String> ans=new ArrayList<>();
		for (IMove move : instance) {
			ans.add(move.toString() + "\n");
		}
		return ans;
	}

}
