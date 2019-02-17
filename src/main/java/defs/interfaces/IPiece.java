package defs.interfaces;

import java.util.ArrayList;
import java.util.List;

import defs.classes.Field;
import defs.classes.Piece;
import defs.classes.Player;

public interface IPiece {

	List<Field> getPossibleMoves();

	public Field getField();

	public void setField(Field field);

	default public List<Field> getGuardingFields(Player player) {
		List<Field> list = new ArrayList<Field>();
		List<Piece> all = player.getFiguren();
		for (Piece fig : all) {
			if (fig.getPossibleMoves().contains(this.getField())) {
				list.add(fig.getField());
			}
		}
		return list;
	}

	default public int getPosI() {
		return getField().getI();
	}

	default public int getPosJ() {
		return getField().getJ();
	}
}
