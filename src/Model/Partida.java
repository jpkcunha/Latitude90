package Model;

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
    public HashMap<String, String> duplas;
    public Queue<String> filaJogadores;
    private int dado1 = -1;
    private int dado2 = -1;
    private String dado3;

    private HashMap<String,Jogador> listaJogadores;
    private String[] coresDado = new String[] {"Amarelo", "Verde", "Preto", "Azul", "Aleatório"};

    
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

    	preenchePolos(ordem);
    	System.out.println(this.numJogadores);
    	System.out.println(inicio.get(ordem[0]));
    	System.out.println(inicio.get(ordem[1]));
    	System.out.println(inicio.get(ordem[2]));
    	System.out.println(inicio.get(ordem[3]));
    	System.out.println(getVez());
    	
    }

    private void preenchePolos(String[] ordem) {
    	int polo, pos;
    	Jogador j;
    	Casa p;
    	for (int i = 0; i < numJogadores; i++) {
    		pos = 0;
    		j = listaJogadores.get(ordem[i]);
    		polo = j.getInicio();
    		p = this.t.getPolo(polo);
    		if (p.getPeoes()[0] != -1) pos = 1; //Os pinos de um jogador já foram adicionados
    		for (int k = 6*pos; k < 6*(pos+1); k++) {
    			p.adicionaPolo(j.getPeoes()[k-6*pos], getCor(duplas.get(ordem[i])));
    			System.out.printf("Peao %d do jogador %s\n", k-6*pos, getCorInv(j.getCor()));
    		}
    	}
    	exibe();
    }
    
    public void exibe() {
    	int[] peoes = this.t.getPolo(0).getPeoes();
    	Cor[] cores = this.t.getPolo(0).getCores();
    	for (int i = 0; i < peoes.length; i++) System.out.println(peoes[i]);
    	for (int i = 0; i < cores.length; i++) System.out.println(cores[i]); 
    	peoes = this.t.getPolo(1).getPeoes();
    	cores = this.t.getPolo(1).getCores();
    	for (int i = 0; i < peoes.length; i++) System.out.println(peoes[i]);
    	for (int i = 0; i < cores.length; i++) System.out.println(cores[i]); 
    	
    }

    public void movePeao(int xi, int yi, int xf, int yf, String corJogador) {
    	Casa inicio, fim;
    	inicio = getCasa(xi, yi);
    	int i = inicio.retiraPeao(getCor(corJogador));
    	if (i == -1) return;

    	Peao p = getPeao(corJogador, i);
    	String s = duplas.get(corJogador);
    	if (s == null) return;

    	fim = getCasa(xf, yf);
    	fim.adicionaPeao(p, getCor(s));
    	exibe();
    	
    }
    
    
    public String getVez() {
    	return this.filaJogadores.peek();
    }
    
    public void passaVez() {
    	String anterior = this.filaJogadores.remove();
    	this.filaJogadores.add(anterior);
    	System.out.printf("%s passou a vez! Vez do %s!\n", anterior, getVez());
    	
    }
    
    
    
    public int jogaDadoNum() { return (int) (Math.random()*6 + 1); }
    
    public String jogaDadoCor() { return coresDado[(int)(Math.random()*4)]; }
   
    
    public void finalizaPeao(String cor, int i) {
    	Peao p = getPeao(cor, i);
    	Jogador j = getJogador(cor);
    	j.adicionaFinalizado();
    	p.finalizar();
    	
    }
    
    public int verificaJogada(String cor, int xIni, int yIni, int xFin, int yFin, int n1, int n2) {
    	return this.t.verificaJogada(getJogador(cor), xIni, yIni, xFin, yFin, n1, n2);
    }

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
    
    protected String getCorInv(Cor c) {
    	if (c == Cor.AMARELO) return "Amarelo";
    	else if (c == Cor.AZUL) return "Azul";
    	else if (c == Cor.PRETO) return "Preto";
    	else if (c == Cor.VERDE) return "Verde";
    	else return "Vermelho";
    }
    
    public Casa getCasa(int x, int y){
    	if (x == -1) return this.t.getPolo(0);
    	else if (y == 12) return this.t.getPolo(1);
    	else if (x >= 0 && x <= 11 && y >= 0 && y <= 11) return this.t.getBoard()[x][y];
    	else return null; //Erro
    }
    
    public Jogador getJogador(String s) { return listaJogadores.get(s); }
    
    public Peao getPeao(String cor, int i) {
    	Jogador j = getJogador(cor);
    	if (j == null) return null;
    	if (i >= 0 && i < 6) return j.getPeoes()[i];
    	return null; //Erro
    }

    public int getInicio(String cor) {
    	Jogador j = getJogador(cor);
    	return j.getInicio();
    }
    
    public int getFim(String cor) {
    	Jogador j = getJogador(cor);
    	return 1-j.getInicio();
    }
    
    public int[] getPeoes(int x, int y) {
    	return getCasa(x, y).getPeoes();
    }
    
    public String[] getCores(int x, int y) {
    	Cor[] aux;
    	String[] s = new String[2];
    	aux = getCasa(x, y).getCores();
    	s[0] = getCorInv(aux[0]);
    	s[1] = getCorInv(aux[1]);
    	return s;
    	
    }
    
    public int getSpecial(int x, int y) { return getCasa(x, y).getIsSpecial(); }
    
    public int getDado1() { return dado1; }
    
    public int getDado2() { return dado2; }
    
    public String getDado3()  { return dado3; }

    public void setDado1() { dado1 = jogaDadoNum(); }
    
    public void setDado2() { dado2 = jogaDadoNum(); }
    
    public void setDado3() { dado3 = jogaDadoCor(); }

}