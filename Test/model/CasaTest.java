package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import Model.*;

class CasaTest {

	@Test
	void testVerificaPeao() {
		Jogador j = new Jogador(Model.Cor.AZUL, 1);
		Casa a = new Casa();
		Peao p = new Peao();
		p.setCor(Model.Cor.AZUL);
		p.setId(4);
		a.adicionaPeao(p);

		assertEquals(a.verificaPeao(j),4);
		
	}
	
	@Test
	void testRetiraPeao() {
		Jogador j = new Jogador(Model.Cor.VERDE, 4);
		Casa a = new Casa();
		Peao p = new Peao();
		p.setCor(Model.Cor.AZUL);
		p.setId(4);
		a.adicionaPeao(p);
		
		a.retiraPeao();
		assertEquals(a.verificaPeao(j),-1);
		
	}
}