package Model;

public class Casa{

    private boolean isSpecial;
    private int posX;
    private int posY;
    private int[][] posicoes;
    private Peao[] peoes;

    public Casa() {
    	this.isSpecial = false;
    	this.posX = 0;
    	this.posY= 0;
    }

    public Casa(int posX, int posY) {
    	this.posX = posX;
    	this.posY= posY;
    }

    public void setPolo(int polo) {
    	int[] centro;
    	this.posicoes = new int [][] {{-18, 16}, {0, 24}, {18, 16}, {-18, -16}, {0, -24}, {18, -16}};
    	
    	if (polo == 0) { //Polo Sul
    		centro = new int[] {203, 332};
    		this.posX = -1;
    		this.posY = -1;
    	}
    	else if (polo == 1) { //Polo Norte
    		centro = new int[] {545, 332};
    		this.posX = 12;
    		this.posY = 12;
    		
    	}
    	else return;
    	
    	for (int i = 0; i < 6; i++) {
    		posicoes[i][0] += centro[0];
    		posicoes[i][1] += centro[1];
    	}
    	
		
    }
    
    public void setSpecial() {
    	this.isSpecial = true;
    }
    
    
    public void setPos_x(int pos_x) {
        this.posX = pos_x;
    }

    
    public void setPos_y(int pos_y) {
        this.posY = pos_y;
    }
    

    
    //Calcula posicoes para uma determinada casa;
    public void setPos() {
    	int[] centro;
    	if (this.posX < 6) centro = new int[] {203, 332};
    	else centro = new int[] {545, 332};
    	
    	/*
    	 * Cada objeto casa ter� uma lista de 3 pares coordenados - posicoes
    	 * As posi��es 0 e 2 ser�o ocupadas por pe�as; a posicao 1 � referente a ficha vermelha, 
    	 * se a casa for especial (isSpecial = true) 
    	 */
    	
    	return;
    }
    

    public int getPos_x() {
        return posX;
    }

    public int getPos_y() {
        return posY;
    }

    public boolean getIsSpecial() {return isSpecial;}



}