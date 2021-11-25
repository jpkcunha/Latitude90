package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Scanner;	
import java.util.HashMap;


public class Partida {
    /*
    * Pode ser que eu tenha que inicializar esses atributos
    *
    * */
	
    private  int numJogadores;
    private boolean isDupla;
    private ArrayList<Jogador> ordem = new ArrayList<Jogador>();
    private Tabuleiro t;
    private HashMap<Model.Cor, Model.Cor> duplas = new HashMap<>();

    public Partida(){
    	
    	this.numJogadores = 4;
    	this.isDupla = false;
        //tabuleiro preenchido
        t = new Tabuleiro();
        
    	adicionaJogador(Model.Cor.PRETO, 0, 0);
    	adicionaJogador(Model.Cor.AZUL, 1, 0);
    	adicionaJogador(Model.Cor.AMARELO, 2, 1);
    	adicionaJogador(Model.Cor.VERDE, 3, 1);
    	
    	//Não há duplas
    	for (int i = 0; i < numJogadores; i++) this.duplas.put(ordem.get(i).getCor(), Cor.VERMELHO1);
    	
 
        
        //Adicionar peões nos polos
        Peao[] auxP;
        Jogador auxJ;
        for (int i = 0; i < ordem.size(); i++) {
        	auxJ = ordem.get(i);
        	auxP = auxJ.getPeoes();
        	for (int j = 0; j < auxP.length; j++) {
        		this.t.getPolo(auxJ.getInicio()).adicionaPeao(auxP[i], duplas.get(auxJ.getCor()));
        	}
        }
    }
    
    
    private void adicionaJogador(Model.Cor c, int ordem, int polo) {
    	this.ordem.add(new Jogador(c, ordem, polo));
    	this.t.getPolo(polo).setCor(c);
    }
    
/*
    private void escolheOrdem(List<Model.Cor> l, ArrayList<Integer> l2){
    	

        Scanner s = new Scanner(System.in);
        System.out.println("Vamos ver quem começa!!");

        int n = 0;
        Model.Cor maior = Model.Cor.VERMELHO1;
        Dado d = new Dado();
        
        for (int i = 0; i < numJogadores; i++) {
            int atual = d.getNumAleatorio(6, 1);
            System.out.printf("Jogador %s: você jogou o dado e saiu %d\n", l.get(i).toString(), atual);
            
            if (atual > n) {
            	n = atual;
            	maior = l.get(i);
            	}
        }
        s.close();
        
        
        int cont = 0;
        Jogador p = new Jogador(maior, cont, l2.get(cont++));
        ordem.add(p);
        
        if (maior == Model.Cor.PRETO) {
        	if (l.contains(Model.Cor.AZUL)) {
        		ordem.add(new Jogador(Model.Cor.AZUL, cont, l2.get(cont++)));
        	}
        	if (l.contains(Model.Cor.AMARELO)) {
        		ordem.add(new Jogador(Model.Cor.AMARELO, cont, l2.get(cont++)));
        	}
        	if (l.contains(Model.Cor.VERDE)) {
        		ordem.add(new Jogador(Model.Cor.VERDE, cont, l2.get(cont++)));
        	}
        }
        
        else if (maior == Model.Cor.AZUL) {
        	if (l.contains(Model.Cor.AMARELO)) {
        		ordem.add(new Jogador(Model.Cor.AMARELO, cont, l2.get(cont++)));
        	}
        	if (l.contains(Model.Cor.VERDE)) {
        		ordem.add(new Jogador(Model.Cor.VERDE, cont, l2.get(cont++)));
        	}
        	if (l.contains(Model.Cor.PRETO)) {
        		ordem.add(new Jogador(Model.Cor.PRETO, cont, l2.get(cont++)));
        	}
        }
        
        else if (maior == Model.Cor.AMARELO) {
        	if (l.contains(Model.Cor.VERDE)) {
        		ordem.add(new Jogador(Model.Cor.VERDE, cont, l2.get(cont++)));
        	}
        	if (l.contains(Model.Cor.PRETO)) {
        		ordem.add(new Jogador(Model.Cor.PRETO, cont, l2.get(cont++)));
        	}
        	if (l.contains(Model.Cor.AZUL)) {
        		ordem.add(new Jogador(Model.Cor.AZUL, cont, l2.get(cont++)));
        	}
        }
        
        
        else if (maior == Model.Cor.VERDE) {
        	if (l.contains(Model.Cor.PRETO)) {
        		ordem.add(new Jogador(Model.Cor.PRETO, cont, l2.get(cont++)));
        	}
        	if (l.contains(Model.Cor.AZUL)) {
        		ordem.add(new Jogador(Model.Cor.AZUL, cont, l2.get(cont++)));
        	}
        	if (l.contains(Model.Cor.AMARELO)) {
        		ordem.add(new Jogador(Model.Cor.AMARELO, cont, l2.get(cont++)));
        	}
        }
        

        System.out.printf("O jogador a começar é o %s\n", maior.toString());
        

    }

*/    

    //y -> latitude -> raio
    //x -> longitude -> theta
    public void criaPartida(){
    	
    	System.out.println("Partida iniciada");
    	
    	Casa c = this.t.getPolo(0);
    	
    	System.out.println(this.t.getPolo(1).getCores()[1]);
    	
    	
    }


    public int getNumJogadores() {
        return numJogadores;
    }
}
