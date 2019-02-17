package defs.interfaces;

import java.util.List;

import defs.classes.Field;

public interface IValidityChecker extends IBlackWhite {

	default public boolean checkForValidity(Field fld, List<Field> lst) {
		boolean ans = false;
		Field last = lst.get(lst.size() - 1);
		if ((last.getFigur() == null || last.getFigur() == this) && fld.getFigur() == null)
			ans = true;
		if ((last.getFigur() == null || last.getFigur() == this) && fld.getFigur() != null
		        && fld.getFigur().getCol() != getCol())
			ans = true;
		if (ans) {
			lst.add(fld);
		}
		return ans;
	}
}
