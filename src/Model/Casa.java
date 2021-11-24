package Model;

public class Casa{

    private int isSpecial;
    private boolean isFull;
    private int posX;
    private int posY;
    private int[] peoes;
    private Model.Cor cor;
    private int nElementos;

    public Casa() {
    	this.isSpecial = -1;
    	this.isFull = false;
    	this.peoes = new int[] {-1, -1};
    	this.cor = Cor.VERMELHO1;
    }

    public Casa(int x, int y) {
    	this.isSpecial = -1;
    	this.isFull = false;
    	this.peoes = new int[] {-1, -1};
    	this.cor = Cor.VERMELHO1;
    	this.posX = x;
    	this.posY = y;    	
    }
    
    public void setPosX(int x) {
    	this.posX = x;
    }
    

    public void setPosY(int y) {
    	this.posY = y;
    }
    
    
    //Estabelece as posi��es dos pinos nos polos na interface gr�fica TIRAR PRO VIEW
    public void setPolo(int polo) {
    	int[] centro;
    	
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
    
    }
    
    //M�todo ainda n�o finalizado para a interface gr�fica
    public void setSpecial(int n) {
    	this.isSpecial = n;
    	/*
    	if (n == 0) inicializa ficha vermelha na posi��o da esquerda (theta 1)
    	else if (n == 1) inicializa ficha vermelha na posi��o do centro (theta 2)
    	else if (n == 2) inicializa ficha vermelha na posi��o da direita (theta 3)
    	else erro
    	*/
    }
    
    
    public void unsetSpecial() { //Ap�s retirar a ficha vermelha
    	this.isSpecial = -1;
    }
    
    /*
     * Calcula posicoes de interface gr�fica para uma determinada casa;
     
    public void setPos() {
    	int[] centro;
    	
    	//if (this.posX < 6) centro = new int[] {203, 332};
    	//else centro = new int[] {545, 332};
    	
    	/*
    	 * Cada objeto casa ter� uma lista de 3 pares coordenados - posicoes
    	 * Duas posi��es ser�o ocupadas por pe�as; a outra � referente � ficha vermelha, 
    	 * se a casa for especial (isSpecial = true) 
    	 
    	
    	return;
    }
    
*/
    
    //Retorna o �ndice do peao do jogador que estiver na casa ou -1 se inexistente
    public int verificaPeao(Jogador j) {
    	if (j.getCor() == cor) {

			System.out.println("Cor igual!");
    		if (peoes[1] != -1) {
    			System.out.println(peoes[1]);
    			return peoes[1]; //2 pe�es na casa
    		}
    		else  {
    			System.out.println(peoes[0]);
    			return peoes[0];
    		}
    	}
    	return -1;
    }

    
    public void adicionaPeao(Peao p) {
    	if (p.getCor() == cor) {
    		peoes[1] = p.getId();
    		this.isFull = true;
    	}
    	else {
    		cor = p.getCor();
    		peoes[0] = p.getId();
    	}
    }
    
    public void retiraPeao() {
    	for (int i = peoes.length - 1; i < 0; i --) {
    		if (peoes[i] != -1) {
    			peoes[i] = -1;
    			if (i == 0) cor = Cor.VERMELHO1;
    			return;
    		}
    	}
    	return;
    }
    
    public int getIsSpecial() {return isSpecial;}
    
    public boolean getIsFull() {return isFull;}

    public int getPosX() {return posX;}
    
    public int getPosY() {return posY;}


}