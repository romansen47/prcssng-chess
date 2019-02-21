package defs.interfaces;

import java.util.List;

import defs.classes.Field;

public interface IValidityChecker extends IColors {

	default public boolean checkForValidity(Field fld, List<Field> lst) {
		boolean ans = false;
		Field last = lst.get(lst.size() - 1);
		if ((last.getPiece() == null || last.getPiece() == this) && fld.getPiece() == null)
			ans = true;
		if ((last.getPiece() == null || last.getPiece() == this) && fld.getPiece() != null
				&& fld.getPiece().getCol() != getCol())
			ans = true;
		if (ans) {
			lst.add(fld);
		}
		return ans;
	}
}
