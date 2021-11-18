package Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import Model.Tabuleiro;
import Model.Casa;

class TabuleiroTest {

	/* Teste de fun��o de movimenta��o (Controller)
	@Test
	void testVerificaJogada() {
		fail("Not yet implemented");
	}
	*/

	@Test
	void testGetPoloSul() {
		Tabuleiro t = new Tabuleiro();
		Casa polo = t.getPoloSul();
		assertEquals(polo.getPosY(),-1);
	}

	@Test
	void testGetPoloNorte() {
		Tabuleiro t = new Tabuleiro();
		Casa polo = t.getPoloNorte();
		assertEquals(polo.getPosY(),12);
	}

}
