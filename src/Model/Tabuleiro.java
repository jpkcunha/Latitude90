package Model;

public class Tabuleiro{


    private Casa poloNorte = new Casa();
    private Casa poloSul = new Casa();
    private Casa[][] board = new Casa[12][12];
    
    public Tabuleiro() {
    	poloSul.setPolo(0);
    	poloNorte.setPolo(1);
    	//Comentario
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