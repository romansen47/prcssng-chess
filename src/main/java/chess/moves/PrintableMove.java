package chess.moves;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Move")
@XmlType(propOrder = { "id", "str", "i1", "j1", "i2", "j2" })
public class PrintableMove {

	private int id;
	private int j1;
	private int i1;
	private int j2;
	private int i2;
	private String str;

	public PrintableMove() {
		setStr(null);
	}

	public PrintableMove(Move move, int id) {
		setId(id);
		setI1(move.getPrev().getI());
		setJ1(move.getPrev().getJ());
		setI2(move.getNext().getI());
		setJ2(move.getNext().getJ());
		setStr(move.toString());
	}

	@XmlElement(name = "FirstFieldVertical")
	public int getI1() {
		return i1;
	}

	public void setI1(int i1) {
		this.i1 = i1;
	}

	@XmlElement(name = "FirstFieldHorizontal")
	public int getJ1() {
		return j1;
	}

	public void setJ1(int j1) {
		this.j1 = j1;
	}

	@XmlElement(name = "SecondFieldVertical")
	public int getI2() {
		return i2;
	}

	public void setI2(int i2) {
		this.i2 = i2;
	}

	@XmlElement(name = "SecondFieldHorizontal")
	public int getJ2() {
		return j2;
	}

	public void setJ2(int j2) {
		this.j2 = j2;
	}

	/**
	 * @return the id
	 */
	@XmlElement(name = "ID")
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the str
	 */
	@XmlElement(name = "ChessNotation")
	public String getStr() {
		return str;
	}

	/**
	 * @param str the str to set
	 */
	public void setStr(String str) {
		this.str = str;
	}

	@Override
	public String toString() {
		return str;
	}
}
