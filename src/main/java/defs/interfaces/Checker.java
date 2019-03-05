package defs.interfaces;
import java.util.List;

import defs.classes.*;

public interface Checker {

	List<Field> getPossibleFieldsWithoutCheck();
	
	List<Field> getPossibleFieldsWithCheckForFriends();
	
	List<Field> getAttackers();
	
}
