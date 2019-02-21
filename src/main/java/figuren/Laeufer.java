package figuren;

import java.util.ArrayList;
import java.util.List;

import chess.Main;
import conf.Config;
import defs.classes.Field;
import defs.classes.Piece;
import defs.enums.Colors;
import defs.enums.Ids;

public class Laeufer extends Piece {

	public Laeufer(Colors col, Field field) throws Exception{
		super(Ids.Laeufer, col, field);
	}

	@Override
	public List<Field> getPossibleMoves() {

		List<Field> lst = new ArrayList<Field>();
		List<Field> lst1 = new ArrayList<Field>();
		List<Field> lst2 = new ArrayList<Field>();
		List<Field> lst3 = new ArrayList<Field>();
		List<Field> lst4 = new ArrayList<Field>();

		lst.add(this.getField());
		lst1.add(this.getField());
		lst2.add(this.getField());
		lst3.add(this.getField());
		lst4.add(this.getField());

		int i = 1;
		while (getPosI() + i <= 7 && getPosJ() + i <= 7
				&& checkForValidity(getSpiel().getField(7 - getPosI() - i, getPosJ() + i), lst1)) {
			lst.add(getSpiel().getField(7 - getPosI() - i, getPosJ() + i));
			i += 1;
		}
		i = 1;
		while (getPosI() + i <= 7 && getPosJ() - i >= 0
				&& checkForValidity(getSpiel().getField(7 - getPosI() - i, getPosJ() - i), lst2)) {
			lst.add(getSpiel().getField(7 - getPosI() - i, getPosJ() - i));
			i += 1;
		}
		i = 1;
		while (getPosI() - i >= 0 && getPosJ() + i <= 7
				&& checkForValidity(getSpiel().getField(7 - getPosI() + i, getPosJ() + i), lst3)) {
			lst.add(getSpiel().getField(7 - getPosI() + i, getPosJ() + i));
			i += 1;
		}
		i = 1;
		while (getPosI() - i >= 0 && getPosJ() - i >= 0
				&& checkForValidity(getSpiel().getField(7 - getPosI() + i, getPosJ() - i), lst4)) {
			lst.add(getSpiel().getField(7 - getPosI() + i, getPosJ() - i));
			i += 1;
		}

		lst1.remove(0);
		lst2.remove(0);
		lst3.remove(0);
		lst4.remove(0);

		lst.addAll(lst1);
		lst.addAll(lst2);
		lst.addAll(lst3);
		lst.addAll(lst4);

		return lst;
	}

	@Override
	public void draw(Main main) {
		main.fill(getColAsInt());
		final int size = Config.Size;
		if (col == Colors.WHITE) {
			main.image(main.getWhiteBishop(), size * getField().getJ(), size * getField().getI(), size, size);
		} else {
			main.image(main.getBlackBishop(), size * getField().getJ(), size * getField().getI(), size, size);
		}
	}

}
