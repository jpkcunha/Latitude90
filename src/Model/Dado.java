package Model;

import java.util.Random;

public class Dado {
	
	public int getNumAleatorio() {
		return (int) ((Math.random() * (6 - 1)) + 1);
	}
	
	public Cor getCorAleatoria() {
		Random random = new Random();
		return Cor.values()[random.nextInt((Cor.values().length))];
	}
	
}