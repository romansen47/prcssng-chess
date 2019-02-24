package defs.interfaces;

import java.util.ArrayList;
import java.util.List;

import defs.classes.Field;

public interface ILongDist {

	public Field getField();
	
	default public List<Field> createList(){
		List<Field> lst = new ArrayList<Field>();
		lst.add(this.getField());
		return lst;
	}
	
	public void checkDirections(List<Field> lst,List<Field> lst1,List<Field> lst2,List<Field> lst3,List<Field> lst4) ;
	
	default List<Field> longDistCheck(){
		
		List<Field> lst =createList();
		List<Field> lst1=createList();
		List<Field> lst2=createList();
		List<Field> lst3=createList();
		List<Field> lst4=createList();
		
		checkDirections(lst,lst1,lst2,lst3,lst4);
	
		lst.addAll(lst1);
		lst.addAll(lst2);
		lst.addAll(lst3);
		lst.addAll(lst4);

	return lst;
}
}
