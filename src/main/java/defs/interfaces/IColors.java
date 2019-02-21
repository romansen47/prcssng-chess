package defs.interfaces;

import defs.enums.Colors;

public interface IColors {

	public Colors getCol();

	default public int getColAsInt() {
		if (getCol() == Colors.WHITE) {
			return 255;
		}
		return 0;
	}

}
