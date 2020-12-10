package chess.game.moves;

import java.io.File;

import javax.swing.JFileChooser;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import chess.game.moves.impl.PrintableMove;
import chess.game.pieces.IPiece;
import defs.classes.Field;
import defs.classes.Game;

@XmlRootElement(name = "TheTimeline")
public class PrintableTimeline {

	final JFileChooser chooser = new JFileChooser();

	private PrintableMove[] moves;

	/**
	 * private Constructor
	 */
	public PrintableTimeline() {
		final Timeline tl = Timeline.getInstance();
		setMoves(new PrintableMove[tl.size()]);
		int i = 0;
		for (final IMove move : tl) {
			getMoves()[i] = new PrintableMove((chess.game.moves.impl.Move) move, ++i);
		}
	}

	public void toXml() throws Exception {

		int ans = 0;
		try {
			ans = chooser.showSaveDialog(null);
		} catch (Exception e) {
			System.out.println("Speichern abgebrochen");
		}
		String path = chooser.getSelectedFile().getPath();
		if (ans == JFileChooser.APPROVE_OPTION) {
			System.out.println("Die zu öffnende Datei ist: " + path);
		}
		final File file = new File(path);
		final JAXBContext jaxbContext = JAXBContext.newInstance(chess.game.moves.PrintableTimeline.class);
		final Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(this, file);
		jaxbMarshaller.marshal(this, System.out);
	}

	public void restoreFromXml(String path) throws JAXBException {
		final File file = new File(path);
		final JAXBContext jContext = JAXBContext.newInstance(PrintableTimeline.class);
		final Unmarshaller unmarshallerObj = jContext.createUnmarshaller();
		final PrintableTimeline ptl = ((PrintableTimeline) (unmarshallerObj.unmarshal(file)));
		final PrintableMove[] newMoves = ptl.getMoves();
		Timeline.getInstance().clear();
		IPiece piece;
		for (final PrintableMove move : newMoves) {
			final Field fld1 = Game.getInstance().getField(move.getI1(), move.getJ1());
			final Field fld2 = Game.getInstance().getField(move.getI2(), move.getJ2());
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
