package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import Model.Jogador;


class JogadorTest {
	
	//Pronto
	@Test
	void testGetCor() {
		Jogador a = new Jogador(Model.Cor.VERDE, 2);
		assertEquals(a.getCor(), Model.Cor.VERDE);
		
	}
	
	//Pronta
	@Test
	public void testGetOrdem() {
		Jogador a = new Jogador(Model.Cor.VERDE, 5);
		int num = a.getOrdem();
		assertEquals(num,5);
	}
}