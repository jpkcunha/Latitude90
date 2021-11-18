package Model;

import java.util.Random;

public class Dado {
	
	   public int getNumAleatorio(int a, int b) {
	        return (int) ((Math.random() * (a - b)) + b);
	    }
	
	public Cor getCorAleatoria() {
		Random random = new Random();
		return Cor.values()[random.nextInt((Cor.values().length))];
	}
	
}