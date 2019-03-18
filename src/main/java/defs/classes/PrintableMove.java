package defs.classes;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PrintableMove {

	@SuppressWarnings("unused")
	@XmlElement(name = "FirstI")
	private int i1;
	@SuppressWarnings("unused")
	@XmlElement(name = "FirstJ")
	private int j1;
	@SuppressWarnings("unused")
	@XmlElement(name = "SecondI")
	private int i2;
	@SuppressWarnings("unused")
	@XmlElement(name = "SecondJ")
	private int j2;
	@SuppressWarnings("unused")
	@XmlElement(name = "toString")
	private String str;

	public PrintableMove() {
		str = null;
		;
	}

	public PrintableMove(Move move) {
		i1 = move.getPrev().getI();
		j1 = move.getPrev().getJ();
		i2 = move.getNext().getI();
		j2 = move.getNext().getJ();
		str = move.toString();
	}

	public void toXml() throws Exception {
		File file = new File(str + ".xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(defs.classes.PrintableMove.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(this, file);
		jaxbMarshaller.marshal(this, System.out);
	}
}
