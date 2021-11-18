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
    	
    	for (int i = 0; i < 12; i ++) {
    		for (int j = 0; j < 12; j ++) {
    			board[i][j] = new Casa (i, j);
    		}
    	}
    	
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
    private boolean verificaMovimento(int xIni, int yIni, int xFin, int yFin, int n) {
    	
    	//PoloSul
    	if (yIni == -1) return verificaBaixo(xFin, yIni, yFin, n);

    	//PoloNorte
    	if (yIni == 12) return verificaCima(xFin, yIni, yFin, n);
    	
    	//Movimenta��o dentro da mesma latitude
    	if (yIni == yFin) return verificaDireita(xIni, xFin, yIni, n) || verificaEsquerda(xIni, xFin, yIni, n); 
    	
    	//Chega no polo sul - movimenta��o para baixo em qualquer longitude
    	if (verificaCima(xIni, yIni, -1, yIni + 1)) return verificaBaixo(xFin, -1, yFin, n - (yIni + 1));

    	//Chega no polo norte - movimenta��o para cima em qualquer longitude
    	if (verificaBaixo(xIni, yIni, 12, 12 - yIni)) return verificaCima(xFin, 12, yFin, n - (12 - yIni));

    	//Movimenta��o vertical sem chegar nos polos
    	if (verificaBaixo(xIni, yIni, yFin, n) || verificaCima(xIni, yIni, yFin, n)) return true;
    	
    	return false;
    	
    }
    
    
    public int verificaJogada(Jogador j, Casa inicio, Casa fim, int n1, int n2) {
    	
    	int pos = inicio.verificaPeao(j);
    	
    	//Jogador n�o contem peao dentro da casa inicial
    	if (pos == -1) return 0;
    	
    	int xIni, yIni, xFin, yFin, maior, menor;
    	
    	xIni = inicio.getPosX();
    	yIni = inicio.getPosY();
    	xFin = fim.getPosX();
    	yFin = fim.getPosY();
    	
    	if (n1 > n2) {
    		maior = n1;
    		menor = n2;
    	}
    	else { //Fun��o n�o � chamada quando os dados s�o iguais ==> dado especial
    		maior = n2;
    		menor = n1;
    	}
    	
    	//A jogada � poss�vel utilizando somente o menor dado? (inclui passar pelos polos)
    	if (verificaMovimento(xIni, yIni, xFin, yFin, menor)) return menor;
    	
    	//A jogada � poss�vel utilizando somente o maior dado? (inclui passar pelos polos)
    	if (verificaMovimento(xIni, yIni, xFin, yFin, maior)) return maior;
    	
    	//A jogada � poss�vel utilizando um movimento �nico com a soma dos dados? (inclui passar pelos polos)
    	if (verificaMovimento(xIni, yIni, xFin, yFin, maior + menor)) return maior + menor;
    	
    	/* A jogada � poss�vel em L (cada dado em uma dire��o)?
    	 * - Vertical com o menor dado e depois horizontal com o maior dado
    	 * - Vertical com o maior dado e depois horizontal com o menor dado
    	 * - Horizontal com o menor dado e depois vertical com o maior dado
    	 * - Horizontal com o maior dado e depois vertical com o menor dado
    	 * - Vertical com o menor dado e depois vertical com o maior dado (passando pelos polos)
    	 * - Vertical com o maior dado e depois vertical com o menor dado (passando pelos polos)
    	 * OBS.: Sem casas bloqueadas, se houver caminho h� 2 poss�veis
    	 */
    	
    	if ((verificaMovimento(xIni, yIni, xIni, yFin, menor) && verificaMovimento(xIni, yFin, xFin, yFin, maior)) || 
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
