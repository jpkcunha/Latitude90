package Model;

public class Casa{

    private boolean isSpecial;
    private boolean isFull;
    private int[][] posicoes;
    private Peao[] peoes;

    public Casa() {
    	this.isSpecial = false;
    	this.isFull = false;
    }

    public void setPolo(int polo) {
    	int[] centro;
    	this.posicoes = new int [][] {{-18, 16}, {0, 24}, {18, 16}, {-18, -16}, {0, -24}, {18, -16}};
    	
    	if (polo == 0) { //Polo Sul
    		centro = new int[] {203, 332};
    	}
    	else if (polo == 1) { //Polo Norte
    		centro = new int[] {545, 332};
    	}
    	else return;
    	
    	for (int i = 0; i < 6; i++) {
    		posicoes[i][0] += centro[0];
    		posicoes[i][1] += centro[1];
    	}
    }
    
    public void setSpecial(int n) {
    	this.isSpecial = true;
    	/*
    	if (n == 0) inicializa ficha vermelha na esquerda (theta 1)
    	else if (n == 1) inicializa ficha vermelha no centro (theta 2)
    	else if (n == 2) inicializa ficha vermelha na direita (theta 3)
    	else erro
    	*/
    }
    
    //Calcula posicoes para uma determinada casa;
    public void setPos() {
    	int[] centro;
    	
    	//if (this.posX < 6) centro = new int[] {203, 332};
    	//else centro = new int[] {545, 332};
    	
    	/*
    	 * Cada objeto casa terá uma lista de 3 pares coordenados - posicoes
    	 * Duas posições serão ocupadas por peças; a outra é referente à ficha vermelha, 
    	 * se a casa for especial (isSpecial = true) 
    	 */
    	
    	return;
    }
    
    public boolean getIsSpecial() {return isSpecial;}
    
    public boolean getIsFull() {return isFull;}


}