package defs.classes;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PrintableMove {

	private int id;
	private int i1;
	private int j1;
	private int i2;
	private int j2;
	private String str;

	public PrintableMove() {
		setStr(null);
	}

	public PrintableMove(Move move,int id) {
		setI1(move.getPrev().getI());
		setJ1(move.getPrev().getJ());
		setI2(move.getNext().getI());
		setJ2(move.getNext().getJ());
		setStr(move.toString());
		this.setId(id);
	}

	public void toXml() throws Exception {
		File file = new File(getStr() + ".xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(defs.classes.PrintableMove.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(this, file);
		jaxbMarshaller.marshal(this, System.out);
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */	
	@SuppressWarnings("unused")
	@XmlElement(name = "id")
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the i1
	 */
	@SuppressWarnings("unused")
	@XmlElement(name = "FirstFieldVertical")
	public int getI1() {
		return i1;
	}

	/**
	 * @param i1 the i1 to set
	 */
	public void setI1(int i1) {
		this.i1 = i1;
	}

	/**
	 * @return the j1
	 */
	@SuppressWarnings("unused")
	@XmlElement(name = "FirstFieldHorizontal")
	public int getJ1() {
		return j1;
	}

	/**
	 * @param j1 the j1 to set
	 */
	
	public void setJ1(int j1) {
		this.j1 = j1;
	}

	/**
	 * @return the i2
	 */
	@SuppressWarnings("unused")
	@XmlElement(name = "SecondFieldVertical")
	public int getI2() {
		return i2;
	}

	/**
	 * @param i2 the i2 to set
	 */
	public void setI2(int i2) {
		this.i2 = i2;
	}

	/**
	 * @return the j2
	 */
	@SuppressWarnings("unused")
	@XmlElement(name = "SecondFieldHorizontal")
	public int getJ2() {
		return j2;
	}

	/**
	 * @param j2 the j2 to set
	 */
	public void setJ2(int j2) {
		this.j2 = j2;
	}

	/**
	 * @return the str
	 */
	@SuppressWarnings("unused")
	@XmlElement(name = "toString")
	public String getStr() {
		return str;
	}

	/**
	 * @param str the str to set
	 */
	public void setStr(String str) {
		this.str = str;
	}
}
