package Model;

public class Tabuleiro{


    private Casa poloNorte;
    private Casa poloSul;
    private Casa[][] board;
    
    public Tabuleiro() {

        poloNorte = new Casa();
        poloSul = new Casa();
    	poloSul.setPolo(0);
    	poloNorte.setPolo(1);

    	
    	board = new Casa[12][12];
    	board[3][1].setSpecial(0);
    	board[3][7].setSpecial(1);
    	board[4][5].setSpecial(1);	
    	board[4][11].setSpecial(1);
    	board[5][4].setSpecial(0);
    	board[5][10].setSpecial(0);
    	board[8][1].setSpecial(1);
    	board[8][7].setSpecial(1);
    	board[7][5].setSpecial(1);
    	board[7][11].setSpecial(1);
    	board[6][4].setSpecial(2);
    	board[6][10].setSpecial(2);
    	
    	for (int i = 0; i < 12; i ++) {
    		for (int j = 0; j < 12; j ++) {
    			board[i][j].setPosX(i);
    			board[i][j].setPosY(j);
    		}
    	}
    
    }

    
    private boolean verificaDireita(int xIni, int xFin, int y, int n) {
    	int i, x = xIni;
		for (i = 1; i <= n; i++) {
			x = (12 + xIni + i)%12;
			if (board[x][y].getIsFull()) break; //Caminho bloqueado    			
		}
		if (i == n+1 && x == xFin) return true;
		return false;
    }
  
    private boolean verificaEsquerda(int xIni, int xFin, int y, int n) {
    	int i, x = xIni;
		for (i = 1; i <= n; i++) {
			x = (12 + xIni - i)%12;
			if (board[x][y].getIsFull()) break; //Caminho bloqueado    			
		}
		if (i == n+1 && x == xFin) return true;
		return false;
    }

    private boolean verificaBaixo(int x, int yIni, int yFin, int n) {
    	int i, y = yIni;
    	
		for (i = 1; i <= n; i++) {
			y++;
			if (board[x][y].getIsFull()) break; //Caminho bloqueado
			if (y == 6 && x != 2 && x != 3 && x != 8 && x != 9) break;
		}
		if (i == n+1 && y == yFin) return true;
		return false;
    }
    
    private boolean verificaCima(int x, int yIni, int yFin, int n) {
    	int i, y = yIni;
    	
		for (i = 1; i <= n; i++) {
			y--;
			if (board[x][y].getIsFull()) break; //Caminho bloqueado
			if (y == 5 && x != 2 && x != 3 && x != 8 && x != 9) break;
		}
		if (i == n+1 && y == yFin) return true;
		return false;
    }
    
    
    //x -> longitude
    //y -> latitude
    public boolean verificaMovimento(int xIni, int yIni, int xFin, int yFin, int n) {
    	
    	//PoloSul
    	if (yIni == -1) return verificaBaixo(xFin, yIni, yFin, n);

    	//PoloNorte
    	if (yIni == 12) return verificaCima(xFin, yIni, yFin, n);
    	
    	//Movimentação dentro da mesma latitude
    	if (yIni == yFin) return verificaDireita(xIni, xFin, yIni, n) || verificaEsquerda(xIni, xFin, yIni, n); 
    	
    	//Chega no polo sul - movimentação para baixo em qualquer longitude
    	if (verificaCima(xIni, yIni, -1, yIni + 1)) return verificaBaixo(xFin, -1, yFin, n - (yIni + 1));

    	//Chega no polo norte - movimentação para cima em qualquer longitude
    	if (verificaBaixo(xIni, yIni, 12, 12 - yIni)) return verificaCima(xFin, 12, yFin, n - (12 - yIni));

    	//Movimentação vertical sem chegar nos polos
    	if (verificaBaixo(xIni, yIni, yFin, n) || verificaCima(xIni, yIni, yFin, n)) return true;
    	
    	return false;
    	
    }
    
    
    public boolean verificaJogada(Jogador j, Casa inicio, Casa fim, int n1, int n2) {
    	
    	int pos = inicio.verificaPeao(j);
    	
    	//Jogador não contem peao dentro da casa inicial
    	if (pos == -1) return false;
    	
    	int xIni, yIni, xFin, yFin;
    	
    	xIni = inicio.getPosX();
    	yIni = inicio.getPosY();
    	xFin = fim.getPosX();
    	yFin = fim.getPosX();
    	
    	
    	return false;
    	
    	
    	
    }
    
    /* Geters and Seters*/
    public Casa getPoloSul() {
        return poloSul;
    }

    public Casa getPoloNorte() {
        return poloNorte;
    }

    public Casa[][] getBoard() {
        return board;
    }
    
}
