package model;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import Model.Dado;

class DadoTest {

	@Test
	public void testGetNumAleatorio() {
		Dado a = new Dado();
		int num = a.getNumAleatorio(6,1);
		if (num >6  || num <1) {
			fail("Falhou");
		}
		else {
		assertTrue(true);
		}
	}

	@Test
	public void testGetCorAleatoria() {
		Dado a = new Dado();
		Model.Cor num = a.getCorAleatoria();
		if  (num == Model.Cor.AZUL || num == Model.Cor.VERDE || num == Model.Cor.AMARELO || num == Model.Cor.PRETO ||num == Model.Cor.VERMELHO1 || num == Model.Cor.VERMELHO2) {
			assertTrue(true);	
			}
		else {
		fail("Cor invalida");
		}
		
	}

}