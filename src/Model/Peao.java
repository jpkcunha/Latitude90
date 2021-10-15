package Model;

public class Peao {

    Cor nomeCor;
    private int id;
    private int posX;
    private int posY;



    public void setId(int id) {
        this.id = id;
    }

    public void setNomeCor(Cor nomeCor) {
        this.nomeCor = nomeCor;
    }
    

    
    public void setPos_x(int pos_x) {
        this.posX = pos_x;
    }

    
    public void setPos_y(int pos_y) {
        this.posY = pos_y;
    }
    

    public int getId() {
        return id;
    }

    public Cor getNomeCor() {
        return nomeCor;
    }
    


    public int getPos_x() {
        return posX;
    }

    public int getPos_y() {
        return posY;
    }

    

}