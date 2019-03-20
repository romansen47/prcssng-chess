package conf;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import defs.classes.Field;
import defs.classes.Game;
import defs.classes.PrintableMove;
import defs.interfaces.IMove;
import defs.interfaces.IPiece;

@XmlRootElement(name = "TheTimeline")
public class PrintableTimeline {

	private PrintableMove[] moves;

	/**
	 * private Constructor
	 */
	public PrintableTimeline() {
		Timeline tl = Timeline.getInstance();
		setMoves(new PrintableMove[tl.size()]);
		int i = 0;
		for (IMove move : tl) {
			getMoves()[i] = new PrintableMove((defs.classes.Move) move, ++i);
		}
	}

	public void toXml() throws Exception {
		File file = new File("target/TimeLine.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(conf.PrintableTimeline.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(this, file);
		jaxbMarshaller.marshal(this, System.out);
	}

	public void restoreFromXml() throws JAXBException {
		File file = new File("target/TimeLine.xml");
		JAXBContext jContext = JAXBContext.newInstance(PrintableTimeline.class);
		Unmarshaller unmarshallerObj = jContext.createUnmarshaller();
		PrintableTimeline ptl = ((PrintableTimeline) (unmarshallerObj.unmarshal(file)));
		PrintableMove[] newMoves = ptl.getMoves();
		Timeline.getInstance().clear();
		IPiece piece;
		for (PrintableMove move : newMoves) {
			Field fld1 = Game.getInstance().getField(move.getI1(), move.getJ1());
			Field fld2 = Game.getInstance().getField(move.getI2(), move.getJ2());
			piece = fld1.getPiece();
			piece.getMove(fld2).execute();
		}
	}

	/**
	 * @return the moves
	 */
	@XmlElement(name = "Move")
	public PrintableMove[] getMoves() {
		return this.moves;
	}

	/**
	 * @param moves the moves to set
	 */
	public void setMoves(PrintableMove[] moves) {
		this.moves = moves;
	}

}
