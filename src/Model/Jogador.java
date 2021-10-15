package Model;

public class Jogador {

    private int ordem;
    private Cor c;

    public Model.Cor getCor() {
        return c;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setCor(Model.Cor c) {
        this.c = c;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }
}
