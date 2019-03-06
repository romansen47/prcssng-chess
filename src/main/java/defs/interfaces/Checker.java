package defs.interfaces;

import java.util.List;

import defs.classes.Field;

public interface Checker {

	List<Field> getPossibleFieldsWithoutCheck();

	List<Field> getPossibleFieldsWithCheckForFriends();

	List<Field> getAttackers();

}
