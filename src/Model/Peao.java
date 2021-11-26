package Model;

public class Peao {

    Cor c;
    private int id;
    private boolean finalizado;

    public Peao(Cor c, int id) {
    	this.c = c;
    	this.id = id;
    	this.finalizado = false;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCor(Model.Cor c) {
        this.c = c;
    }

    public void finalizar() {
    	this.finalizado = true;
    }
    
    public int getId() {
        return id;
    }


    public Model.Cor getCor() {
        return c;
    }

    public boolean getFinalizado() {
        return finalizado;
    }

}