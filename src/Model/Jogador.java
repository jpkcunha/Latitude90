package Model;

import java.util.ArrayList;

class Jogador {

    private Cor c;
    private int inicio;
    private Peao[] peoes = new Peao[6];
    private int numFinalizados;
    private int pontos;
    
    protected Jogador(Model.Cor c, int inicio) {
    	this.c = c;
    	this.inicio = inicio;
    	this.numFinalizados = 0;
    	this.pontos = 0;
    	
    	
    	// Inicializa vetor de peoes
    	for (int i = 0; i < 6; i ++) peoes[i] = new Peao(c, i);
    	
    }

    protected Model.Cor getCor() {
        return c;
    }
    
    protected int getInicio() {
        return inicio;
    }   

    protected Peao[] getPeoes() {
        return peoes;
    }

    protected int getFinalizados() {
        return numFinalizados;
    }
    
    protected int getPontos() {
        return pontos;
    }

    protected void adicionaFinalizado() {
    	this.numFinalizados ++;
    }
    
    protected void setCor(Model.Cor c) {
        this.c = c;
    }
}