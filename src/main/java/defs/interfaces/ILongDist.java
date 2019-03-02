package defs.interfaces;

import java.util.ArrayList;
import java.util.List;

import conf.Config;
import defs.classes.Field;

public interface ILongDist extends IPiece,IValidityChecker{

	@Override
	public Field getField();
	
	@Override
	int getPosI();
	
	@Override
	int getPosJ();
	
	default public List<Field> createList(){
		List<Field> lst = new ArrayList<Field>();
		lst.add(this.getField());
		return lst;
	}
	
	public void checkDirections(List<Field> lst) ;
	
	default List<Field> longDistCheck(){
		List<Field> lst = createList();		
		checkDirections(lst);
		return lst;
	}
	
	public default void checkDirection(List<Field> lst,int[] direction) {
		List<Field> tmpList=new ArrayList<>();
		tmpList.add(getField());
		int i = 1;
		while (getPosI() + i*direction[0] >=0 && getPosI() + i*direction[0] <= Config.GAMESIZE && 
				getPosJ() + i*direction[1] >=0 && getPosJ() + i*direction[1] <= Config.GAMESIZE &&
				checkForValidity(getGame().getField(getPosI()+i*direction[0], getPosJ()+i*direction[1]), tmpList)) {
			i += 1;
		}
		lst.addAll(tmpList);
	}
	
	@Override
	default List<IMove> getPossibleMoves() {
		return convertFieldsToMoves(longDistCheck());
	}
	
}
