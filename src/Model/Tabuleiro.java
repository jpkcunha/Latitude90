package Model;

class Tabuleiro{


    private Casa poloNorte;
    private Casa poloSul;
    private Casa[][] board;
    
    protected Tabuleiro() {

        poloNorte = new Casa();
        poloSul = new Casa();
    	poloSul.setPolo(0);
    	poloNorte.setPolo(1);

    	
    	board = new Casa[12][12];
    	
    	for (int i = 0; i < 12; i ++) {
    		for (int j = 0; j < 12; j ++) {
    			board[i][j] = new Casa (i, j);
    			if (j >= 3 && j <= 8) board[i][j].setNumElementos(3);
    		}
    	}
    	
    	//Determina as casas especiais
    	board[1][3].setSpecial(0);
    	board[3][4].setSpecial(1);
    	board[4][5].setSpecial(0);	
    	board[7][3].setSpecial(1);
    	board[9][4].setSpecial(1);
    	board[10][5].setSpecial(0);
    	board[1][6].setSpecial(2);
    	board[2][7].setSpecial(1);
    	board[4][8].setSpecial(1);
    	board[7][6].setSpecial(2);
    	board[8][7].setSpecial(1);
    	board[10][8].setSpecial(1);
    
    }

    
    private boolean verificaDireita(int xIni, int xFin, int y, int n) {
    	int i, x = xIni;
		for (i = 1; i <= n; i++) {
			x = (12 + xIni + i)%12;
			if (board[x][y].getIsFull()) break; //Caminho bloqueado
		}
		if (i == n+1 && x == xFin) {
			System.out.println("Pode andar pra direita!");
			return true;
		}
		return false;
    }
  
    private boolean verificaEsquerda(int xIni, int xFin, int y, int n) {
    	int i, x = xIni;
		for (i = 1; i <= n; i++) {
			x = (12 + xIni - i)%12;
			if (board[x][y].getIsFull()) break; //Caminho bloqueado
		}
		if (i == n+1 && x == xFin) {
			System.out.println("Pode andar pra esquerda!");
			return true;
		}
		return false;
    }

    private boolean verificaBaixo(int x, int yIni, int yFin, int n) {
    	int i, y = yIni;
    	
		for (i = 1; i <= n; i++) {
			y++;
			if (board[x][y].getIsFull()) break; //Caminho bloqueado
			if (y == 6 && x != 2 && x != 3 && x != 8 && x != 9) break;
		}
		if (i == n+1 && y == yFin) {
			System.out.println("Pode andar pra baixo!");
			return true;
		}
		return false;
    }
    
    private boolean verificaCima(int x, int yIni, int yFin, int n) {
    	int i, y = yIni;
    	
		for (i = 1; i <= n; i++) {
			y--;
			if (board[x][y].getIsFull()) break; //Caminho bloqueado
			if (y == 5 && x != 2 && x != 3 && x != 8 && x != 9) break;
		}
		if (i == n+1 && y == yFin) {
			System.out.println("Pode andar pra cima!");
			return true;
		}
		return false;
    }
    
    
    //x -> longitude
    //y -> latitude
    private boolean verificaMovimento(int xIni, int yIni, int xFin, int yFin, int n) {
    	System.out.printf("Verifica movimento de (%d, %d) para (%d, %d) - pode andar %d\n", xIni, yIni, xFin, yFin, n);
    	System.out.println("Está no polo sul?");
    	//PoloSul
    	if (yIni == -1) return verificaBaixo(xFin, yIni, yFin, n);

    	System.out.println("Está no polo norte?");
    	//PoloNorte
    	if (yIni == 12) return verificaCima(xFin, yIni, yFin, n);

    	System.out.println("Está na mesma latitude?");
    	//Movimentação dentro da mesma latitude
    	if (yIni == yFin) return verificaDireita(xIni, xFin, yIni, n) || verificaEsquerda(xIni, xFin, yIni, n); 

    	System.out.println("Passa pelo polo sul?");
    	//Chega no polo sul - movimentação para baixo em qualquer longitude
    	if (verificaCima(xIni, yIni, -1, yIni + 1)) return verificaBaixo(xFin, -1, yFin, n - (yIni + 1));

    	System.out.println("Passa pelo polo norte?");
    	//Chega no polo norte - movimentação para cima em qualquer longitude
    	if (verificaBaixo(xIni, yIni, 12, 12 - yIni)) return verificaCima(xFin, 12, yFin, n - (12 - yIni));

    	System.out.println("Vertical sem chegar nos polos?");
    	//Movimentação vertical sem chegar nos polos
    	if (verificaBaixo(xIni, yIni, yFin, n) || verificaCima(xIni, yIni, yFin, n)) return true;
    	
    	System.out.println("Movimento inválido");
    	return false;
    	
    }
    
    
    protected int verificaJogada(Jogador j, int xIni, int yIni, int xFin, int yFin, int n1, int n2) {
    	System.out.printf("Verifica jogada de (%d, %d) para (%d, %d) - %d e %d\n", xIni, yIni, xFin, yFin, n1, n2);
    	//Verifica se o peão do jogador está na casa de inicio do movimento
    	int pos;
    	if (xIni == -1) pos = this.poloSul.verificaPolo(j);
    	else if (xIni == 12) pos = this.poloNorte.verificaPolo(j);
    	else pos = this.board[xIni][yIni].verificaPeao(j);
    	System.out.printf("Pos=%d\n", pos);
    	//Jogador não contém peão dentro da casa inicial
    	if (pos == -1) return 0;
    	
    	/*
    	int xIni, yIni, xFin, yFin, maior, menor;
    	
    	xIni = inicio.getPosX();
    	yIni = inicio.getPosY();
    	xFin = fim.getPosX();
    	yFin = fim.getPosY();*/
    	
    	int maior, menor;
    	if (n1 > n2) {
    		maior = n1;
    		menor = n2;
    	}
    	else { //Função não é chamada quando os dados são iguais ==> dado especial
    		maior = n2;
    		menor = n1;
    	}
    	System.out.println(maior);
    	System.out.println(menor);
    	
    	//A jogada é possível utilizando somente o menor dado? (inclui passar pelos polos)
    	if (verificaMovimento(xIni, yIni, xFin, yFin, menor)) return menor;
    	
    	//A jogada é possível utilizando somente o maior dado? (inclui passar pelos polos)
    	if (verificaMovimento(xIni, yIni, xFin, yFin, maior)) return maior;
    	
    	//A jogada é possível utilizando um movimento único com a soma dos dados? (inclui passar pelos polos)
    	if (verificaMovimento(xIni, yIni, xFin, yFin, maior + menor)) return maior + menor;
    	if (xIni == -1) {
    		if ((verificaBaixo((12 + xFin + maior)%12, yIni, yFin, menor) && verificaEsquerda((12 + xFin + maior)%12, xFin, yFin, maior)) || 
       			 verificaBaixo((12 + xFin - maior)%12, yIni, yFin, menor) && verificaDireita((12 + xFin - maior)%12, xFin, yFin, maior) ||
       			 verificaBaixo((12 + xFin + maior)%12, yIni, yFin, maior) && verificaEsquerda((12 + xFin + menor)%12, xFin, yFin, maior) ||
       			 verificaBaixo((12 + xFin - maior)%12, yIni, yFin, maior) && verificaDireita((12 + xFin - menor)%12, xFin, yFin, maior)) {
    			return maior + menor;
    		}
    	}

    	if (xIni == 12) {
    		if ((verificaCima((12 + xFin + maior)%12, yIni, yFin, menor) && verificaEsquerda((12 + xFin + maior)%12, xFin, yFin, maior)) || 
       			 verificaCima((12 + xFin - maior)%12, yIni, yFin, menor) && verificaDireita((12 + xFin - maior)%12, xFin, yFin, maior) ||
       			 verificaCima((12 + xFin + maior)%12, yIni, yFin, maior) && verificaEsquerda((12 + xFin + menor)%12, xFin, yFin, maior) ||
       			 verificaCima((12 + xFin - maior)%12, yIni, yFin, maior) && verificaDireita((12 + xFin - menor)%12, xFin, yFin, maior)) {
    			return maior + menor;
    		}
    	}

    	
    	/* A jogada é possível em L (cada dado em uma direção)?
    	 * - Vertical com o menor dado e depois horizontal com o maior dado
    	 * - Vertical com o maior dado e depois horizontal com o menor dado
    	 * - Horizontal com o menor dado e depois vertical com o maior dado
    	 * - Horizontal com o maior dado e depois vertical com o menor dado
    	 * - Vertical com o menor dado e depois vertical com o maior dado (passando pelos polos)
    	 * - Vertical com o maior dado e depois vertical com o menor dado (passando pelos polos)
    	 * OBS.: Sem casas bloqueadas, se houver caminho há 2 possíveis
    	 */
    	else if ((verificaMovimento(xIni, yIni, xIni, yFin, menor) && verificaMovimento(xIni, yFin, xFin, yFin, maior)) || 
           (verificaMovimento(xIni, yIni, xIni, yFin, maior) && verificaMovimento(xIni, yFin, xFin, yFin, menor)) ||
           (verificaMovimento(xIni, yIni, xFin, yIni, menor) && verificaMovimento(xFin, yIni, xFin, yFin, maior)) ||
           (verificaMovimento(xIni, yIni, xFin, yIni, maior) && verificaMovimento(xFin, yIni, xFin, yFin, menor)) || 
           (verificaMovimento(xIni, yIni, xIni, yFin, menor) && verificaMovimento(xFin, yIni, xFin, yFin, maior)) || 
           (verificaMovimento(xIni, yIni, xIni, yFin, maior) && verificaMovimento(xFin, yIni, xFin, yFin, menor))) {
    		return maior + menor;
    	}
    	return 0;
    	
    }
    
    /* Geters and Seters*/
    protected Casa getPolo(int polo) {
        if (polo == 0) return poloSul;
        return poloNorte;

    }


    protected Casa[][] getBoard() {
        return board;
    }
    
}
