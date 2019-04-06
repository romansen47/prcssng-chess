package defs.classes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import defs.enums.Colors;

class FieldTest {

	static Field fld;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		fld = new Field(Colors.WHITE, 7, 4);
	}

	@Test
	void testGetI() {
		assertEquals(fld.getI(), 7);
	}

	@Test
	void testGetJ() {
		assertEquals(fld.getJ(), 4);
	}

	@Test
	void testGetPiece() {
		assertEquals(fld.getPiece(), null);
	}

}
