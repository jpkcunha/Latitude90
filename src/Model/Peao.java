package Model;

class Peao {

    Cor c;
    private int id;
    private boolean finalizado;

    protected Peao(Cor c, int id) {
    	this.c = c;
    	this.id = id;
    	this.finalizado = false;
    }

    protected void setId(int id) {
        this.id = id;
    }

    protected void setCor(Model.Cor c) {
        this.c = c;
    }

    protected void finalizar() {
    	this.finalizado = true;
    }
    
    protected int getId() {
        return id;
    }


    protected Model.Cor getCor() {
        return c;
    }

    protected boolean getFinalizado() {
        return finalizado;
    }

}