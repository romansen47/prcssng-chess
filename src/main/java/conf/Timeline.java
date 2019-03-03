package conf;

import java.util.ArrayList;
import java.util.List;

import defs.enums.Colors;
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
		String tmp;
		int i=1;
		for (IMove move:instance) {
			tmp=i+++": "+move.toString();
			if(move.getCol()==Colors.WHITE) {
				ans.add(tmp);
			}
			else {
				String last=ans.get(ans.size()-1);
				last=last+" ,  "+tmp;
				ans.set(ans.size()-1, last);
			}
			tmp=null;
		}
		return ans;
	}

}
