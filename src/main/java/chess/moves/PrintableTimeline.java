package chess.moves;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import chess.pieces.IPiece;
import defs.classes.Field;
import defs.classes.Game;

@XmlRootElement(name = "TheTimeline")
public class PrintableTimeline {

	private PrintableMove[] moves;

	/**
	 * private Constructor
	 */
	public PrintableTimeline() {
		final Timeline tl = Timeline.getInstance();
		setMoves(new PrintableMove[tl.size()]);
		int i = 0;
		for (final IMove move : tl) {
			getMoves()[i] = new PrintableMove((chess.moves.Move) move, ++i);
		}
	}

	public void toXml() throws Exception {
		final File			file			= new File("target/TimeLine.xml");
		final JAXBContext	jaxbContext		= JAXBContext.newInstance(chess.moves.PrintableTimeline.class);
		final Marshaller	jaxbMarshaller	= jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(this, file);
		jaxbMarshaller.marshal(this, System.out);
	}

	public void restoreFromXml() throws JAXBException {
		final File				file			= new File("target/TimeLine.xml");
		final JAXBContext		jContext		= JAXBContext.newInstance(PrintableTimeline.class);
		final Unmarshaller		unmarshallerObj	= jContext.createUnmarshaller();
		final PrintableTimeline	ptl				= ((PrintableTimeline) (unmarshallerObj.unmarshal(file)));
		final PrintableMove[]	newMoves		= ptl.getMoves();
		Timeline.getInstance().clear();
		IPiece piece;
		for (final PrintableMove move : newMoves) {
			final Field	fld1	= Game.getInstance().getField(move.getI1(), move.getJ1());
			final Field	fld2	= Game.getInstance().getField(move.getI2(), move.getJ2());
			piece = fld1.getPiece();
			piece.getMove(fld2).execute();
		}
	}

	/**
	 * @return the moves
	 */
	@XmlElement(name = "Move")
	public PrintableMove[] getMoves() {
		return moves;
	}

	/**
	 * @param moves the moves to set
	 */
	public void setMoves(PrintableMove[] moves) {
		this.moves = moves;
	}

}
