package Model;

public class Casa{

    private int isSpecial;
    private boolean isFull;
    private int posX;
    private int posY;
    private int[] peoes;
    private int nElementos;
    private Model.Cor[] cores;

    public Casa() {
    	this.isSpecial = -1;
    	this.isFull = false;
    	this.peoes = new int[] {-1, -1};
    	this.cores = new Model.Cor[] {Cor.VERMELHO1, Cor.VERMELHO1};
    	this.nElementos = 2;
    }

    public Casa(int x, int y) {
    	this.isSpecial = -1;
    	this.isFull = false;
    	this.peoes = new int[] {-1, -1};
    	this.cores = new Model.Cor[] {Cor.VERMELHO1, Cor.VERMELHO1};
    	this.nElementos = 2;
    	this.posX = x;
    	this.posY = y;
    }
    
    public void setPosX(int x) { this.posX = x; }
    

    public void setPosY(int y) { this.posY = y; }
    
    
    public void setPolo(int polo) {
    	if (polo == 0)	this.posX = -1; //Polo Sul
    	else if (polo == 1) posX = 12; //Polo Norte
    	else return; //Erro
		posY = posX;
    	this.nElementos = 12;
		this.peoes = new int[12];
		for (int i = 0; i < 12; i++) peoes[i] = -1; //Inicializa array de peoes
    }
    
    
    public void setCor(Cor c) {
    	if (this.cores[0] == Cor.VERMELHO1) this.cores[0] = c;
    	else if (this.cores[1] == Cor.VERMELHO1) this.cores[1] = c;
    }
    
    public void setSpecial(int n) {
    	this.isSpecial = n;
    }
    
    
    
    //Retorna o índice do peao do jogador que estiver na casa ou -1 se inexistente
    public int verificaPeao(Jogador j) {
    	if (j.getCor() == cores[0]) return peoes[0];
    	else if (j.getCor() == cores[1]) return peoes[1];
    	return -1; //Não há peão
    }
    
    //Verifica se há peão no polo
    public int verificaPolo(Jogador j, int polo) {
    	if (this.cores[0] != j.getCor() && this.cores[1] != j.getCor()) return -1;
    	
    	int pos;
    	if (this.cores[0] == j.getCor()) pos = 0;
    	else if (this.cores[1] == j.getCor()) pos = 1;
    	else return -1;
    	
    	for (int i = 6*pos; i < 6*(pos+1); i++) if (this.peoes[i] != -1) return this.peoes[i];
    	
    	return -1;
    }

    
    //Adiciona - modo individual significa cor dupla == VERMELHO1
    public void adicionaPeao(Peao p, Model.Cor dupla) {
    	if (this.isFull) return; //Casa cheia
    	else if (cores[0] == Cor.VERMELHO1 && cores[1] == Cor.VERMELHO1) { //Casa vazia
    		peoes[0] = p.getId();
    		cores[0] = p.getCor();
    	}
    	else if (cores[0] == Cor.VERMELHO1) { //1 pino só
    		if (cores[1] == p.getCor() || cores[1] == dupla) { //Pino da mesma cor ou da cor da dupla
        		peoes[0] = p.getId();
        		cores[0] = p.getCor();
        		this.isFull = true;
    		}
    		else { //Pino adversário ==> volta pro inicio
        		peoes[0] = p.getId();
        		cores[0] = p.getCor();
        		// Adversario volta pro inicio!!!
    		}
    	}
    	else if (cores[1] == Cor.VERMELHO1) { //1 pino só
    		if (cores[0] == p.getCor() || cores[0] == dupla) { //Pino da mesma cor ou da cor da dupla
        		peoes[1] = p.getId();
        		cores[1] = p.getCor();
        		this.isFull = true;
    		}
    		else { //Pino adversário ==> volta pro inicio
        		peoes[1] = p.getId();
        		cores[1] = p.getCor();
        		// Adversario volta pro inicio!!!
    		}
    	}
    }
    
    //Retorna índice do peão
    public int retiraPeao(Model.Cor cor) {
    	for (int i = peoes.length - 1; i < 0; i --) {
    		if (cores[i] == cor) {
    			cores[i] = Cor.VERMELHO1;
    			int indice = peoes[i];
    			peoes[i] = -1;
    			return indice;
    		}
    	}
    	return -1; //Erro
    }

    public int retiraPolo(Model.Cor cor) {
    	int pos;
    	if (cores[0] == cor) pos = 0;
    	else if (cores[1] == cor) pos = 1;
    	for (int i = ; i < 0; i --) {
    		if (cores[i] == cor) {
    			cores[i] = Cor.VERMELHO1;
    			int indice = peoes[i];
    			peoes[i] = -1;
    			return indice;
    		}
    	}
    	return -1; //Erro
    }
    
    public int verificaPosLivre() {
    	for (int i = 0; i < this.nElementos; i++) {
    		if (peoes[i] == -1) return i; 
    	}
    	return -1; //Erro
    }
    
    public int getIsSpecial() {return isSpecial;}
    
    public boolean getIsFull() {return isFull;}

    public int getPosX() {return posX;}
    
    public int getPosY() {return posY;}
    
    public int[] getPeoes() {return peoes;}
    
    public Cor[] getCores() {return cores;}


}