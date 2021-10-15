package Model;

public class Casa{

    private boolean isSpecial;
    private boolean isFull;
    private int posX;
    private int posY;
    private int[][] posicoes;
    private Peao[] peoes;

    public Casa() {
    	this.isSpecial = false;
    	this.isFull = false;
    }

    
    public void setPosX(int x) {
    	this.posX = x;
    }
    

    public void setPosY(int y) {
    	this.posY = y;
    }
    
    public void setPolo(int polo) {
    	int[] centro;
    	this.posicoes = new int [][] {{-18, 16}, {0, 24}, {18, 16}, {-18, -16}, {0, -24}, {18, -16}};
    	
    	if (polo == 0) { //Polo Sul
    		posX = -1;
    		posY = -1;
    		centro = new int[] {203, 332};
    	}
    	else if (polo == 1) { //Polo Norte
    		posX = 12;
    		posY = 12;
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
    
    
    //Retorna a posicao do peao na casa ou -1 se inexistente
    public int verificaPeao(Jogador j) {
    	for (int i = 0; i < this.peoes.length; i ++) {
    		if (peoes[i].getCor() == j.getCor()) return i;
    	}
    	return -1;
    }
    
    public boolean getIsSpecial() {return isSpecial;}
    
    public boolean getIsFull() {return isFull;}

    public int getPosX() {return posX;}
    
    public int getPosY() {return posY;}


}