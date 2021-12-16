package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import Model.Peao;

class PeaoTest {

	@Test
	void testGetId() {
		Peao p = new Peao();
		p.setId(0);
		assertEquals(p.getId(),0);
	}

	@Test
	void testGetCor() {
		Peao p = new Peao();
		p.setCor(Model.Cor.VERDE);
		assertEquals(p.getCor(), Model.Cor.VERDE);
	}
}