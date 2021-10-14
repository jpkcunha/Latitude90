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
    
    public void movimenta() {
    	
    }


}