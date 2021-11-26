package Model;

import java.util.ArrayList;

public class Jogador {

    private Cor c;
    private int inicio;
    private Peao[] peoes = new Peao[6];
    
    public Jogador(Model.Cor c, int inicio) {
    	this.c = c;
    	this.inicio = inicio;
    	
    	// Inicializa vetor de peoes
    	for (int i = 0; i < 6; i ++) peoes[i] = new Peao(c, i);
    	
    }

    public Model.Cor getCor() {
        return c;
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
}