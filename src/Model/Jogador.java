package Model;

import java.util.ArrayList;

public class Jogador {

    private int ordem;
    private Cor c;
    private int inicio;
    private Peao[] peoes = new Peao[6];
    
    public Jogador(Model.Cor c, int ordem, int inicio) {
    	this.ordem = ordem;
    	this.c = c;
    	this.inicio = inicio;
    	
    	// Inicializa vetor de peoes
    	for (int i = 0; i < 6; i ++) peoes[i] = new Peao(c, i);
    	
    }

    public Model.Cor getCor() {
        return c;
    }

    public int getOrdem() {
        return ordem;
    }
    
    public int getInicio() {
        return inicio;
    }   
    
    public Peao[] getPeoes() {
        return peoes;
    }

    public void setCor(Model.Cor c) {
        this.c = c;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }
}
