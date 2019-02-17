package defs.interfaces;

import defs.enums.BlackWhite;

public interface IBlackWhite {

	public BlackWhite getCol();

	default public int getColAsInt() {
		if (getCol() == BlackWhite.WHITE) {
			return 255;
		}
		return 0;
	}

}
