package defs.interfaces;

import defs.classes.Field;
import defs.enums.Colors;
import defs.enums.Ids;

public interface IMove {

	void execute();

	Colors getCol();

	IPiece getFig();

	Field getPrev();

	Ids getPrevId();

	Field getNext();

	Ids getNextId();

}
