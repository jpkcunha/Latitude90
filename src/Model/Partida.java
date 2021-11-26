package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Scanner;	
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;


public class Partida {
    /*
    * Pode ser que eu tenha que inicializar esses atributos
    *
    * */
	private static Partida p=null;
	
    public  int numJogadores;
    public boolean isDupla;
    public Tabuleiro t;
    private HashMap<String,Jogador> listaJogadores;
    public HashMap<String, String> duplas;
    public Queue<String> filaJogadores;

    
    private Partida(){}
    
    //Singleton
    public static Partida getPartida() {
    	if (p == null) p = new Partida();
    	return p;
    }
    
    public void iniciaPartida(int nJog, boolean dupla, String[] ordem, HashMap<String, Integer> inicio, HashMap<String, String> hashDuplas) {
    	this.numJogadores = nJog;
    	this.isDupla = dupla;
    	this.t = new Tabuleiro();
    	this.duplas = new HashMap<>();
    	this.filaJogadores = new LinkedList<>();
    	this.listaJogadores = new HashMap<>();
    	
    	for (int i = 0; i < nJog; i++) {
    		Cor c = getCor(ordem[i]);
    		int polo = inicio.get(ordem[i]);
    		Jogador j = new Jogador(c, polo);
    		duplas.put(ordem[i], hashDuplas.get(ordem[i]));
    		filaJogadores.add(ordem[i]);
    		listaJogadores.put(ordem[i], j);
    	}    	
    }

    
    
    public void movePeao(int xi, int yi, int xf, int yf, String CorJogador) {
    	Casa inicio, fim;
    	inicio = getCasa(xi, yi);
    	fim = getCasa(xf, yf);
    	int i = inicio.retiraPeao(getCor(CorJogador));
    	
    }
        
    public void iniciaRodada(int xi, int yi, int xf, int yf, String corVez) {
    	System.out.println("Vez do " + corVez + "...");
    	int d1 = jogaDadoNum();
    	int d2 = jogaDadoNum();
    	
    	if (movePeao(xi, yi, xf, yf, d1, d2, corVez)) {
    		System.out.println("Moveu a peca.");
    	}
    	else System.out.println("Não moveu a peca.");
    	
    	
    	
    }
    
    
    public String getVez() {
    	return this.filaJogadores.peek();
    }
    
    public void terminaRodada() {
    	String terminouAVez = this.filaJogadores.remove();
    	
    	this.filaJogadores.add(terminouAVez);
    }
    
    public int jogaDadoNum() {
    	Dado d1 = new Dado();
    	//checar valor
    	return d1.getNumAleatorio(1, 7);
    }
    
    public String jogaDadoCor() {
    	Dado d1 = new Dado();
    	Model.Cor c = d1.getCorAleatoria();
    	return decifraCorJogador(c);
    }
    
    public String decifraCorJogador(Cor c) {
    	if (c.equals(Cor.AMARELO)) return "Amarelo";
    	else if (c.equals(Cor.VERDE)) return "verde";
    	else if (c.equals(Cor.PRETO)) return "Preto";
    	else if (c.equals(Cor.AZUL)) return "Azul";
    	return "Vermelho";
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
    
    /*
    public void criaPartida(){
    	
    	System.out.println("Partida iniciada");
    	
    	Casa c = this.t.getPolo(0);
    	
    	System.out.println(this.t.getPolo(1).getCores()[1]);
    	
    	
    }
*/
    

    // Get - atributos da classe Partida
    public int getNumJogadores() {
    	return numJogadores;
    }

    
    // Get - classes do Model
    
    public Cor getCor(String s) {
    	if (s.equals("Amarelo")) return Cor.AMARELO;
    	else if (s.equals("Azul")) return Cor.AZUL;
    	else if (s.equals("Preto")) return Cor.PRETO;
    	else if (s.equals("Verde")) return Cor.VERDE;
    	else return Cor.VERMELHO1;
    }
    
    public Casa getCasa(int x, int y){
    	if (x == -1) return this.t.getPolo(0);
    	else if (y == 12) return this.t.getPolo(1);
    	else if (x >= 0 && x <= 11 && y >= 0 && y <= 11) return this.t.getBoard()[x][y];
    	else return null; //Erro
    }
    
    public Peao getPeao(String cor, int i) {
    	Jogador j = getJogador(cor);
    	if (j == null) return null;
    	if (i >= 0 && i < 6) return j.getPeoes()[i];
    	return null; //Erro
    }
    
    public Jogador getJogador(String s) { return listaJogadores.get(s); }
}