package Controller;

import View.*;
import Model.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class controller {

	private Partida p;
	private Menu m;
	private FrameTabuleiro t;
	
	public controller() {
		

		Imagem.carregar();
		this.p = Partida.getPartida();
		this.m = new Menu();
		this.m.abrir();
	
		
		
		
		while(m.isVisible()) {
			
			System.out.println("chega");

		}
		ObservadoInfo novo = new ObservadoInfo(p);
		this.t = new FrameTabuleiro(novo , 2, false );
		
		ObservadorButton b = new ObservadorButton(novo , t.getObervadoButton() ,p, "Vermelho");
		
		//(int nJog, boolean dupla, String[] ordem, HashMap<String, Integer> inicio, HashMap<String, String> hashDuplas);
		int n = 4;
		String[] ordem = {"Amarelo", "Preto", "Verde", "Azul"};
		
		HashMap<String, Integer> inicio = new HashMap<>();
		inicio.put(ordem[0], 0);
		inicio.put(ordem[1], 0);
		inicio.put(ordem[2], 1);
		inicio.put(ordem[3], 1);
		
		HashMap<String, String> hashDuplas = new HashMap<>();
		for (int i=0; i<n; i++) hashDuplas.put(ordem[i], "Vermelho");
		
		p.iniciaPartida(4, false, ordem, inicio, hashDuplas);

		//3 rodadas só de sacanagem
		p.passaVez();
		p.passaVez();
		p.passaVez();
		p.passaVez();
		p.passaVez();
		p.passaVez();
		p.passaVez();
		p.passaVez();
		p.passaVez();
		p.passaVez();
		p.passaVez();
		p.passaVez();
		
		Scanner s = new Scanner(System.in);
		String jog, str = null;
		int polo, xi, yi, xf, yf, peao, lat, lon, result;
		for (int rodada = 1; rodada < 10; rodada++) {
			for (int vez = 0; vez < 4; vez++) {
				while (str != "") {
					System.out.println("==>");
					str = s.nextLine();
				}
				jog = p.getVez();
				
				System.out.printf("Vez do %s\n", jog);
				
				int d1 = p.jogaDadoNum();
				int d2 = p.jogaDadoNum();
				System.out.printf("Tirou %d e %d\n", d1, d2);
				
				if (d1 == d2) {
					String d3 = p.jogaDadoCor();
					System.out.printf("Dado especial ==> %s\n", d3);
					
					if (d3.equals(p.getVez())) {
						polo = p.getFim(jog);
						System.out.printf("Explorador para o polo %d!!\n", polo);
					}
					
				}
				else {
					//System.out.println("Selecione um peão:");
					//peao = s.nextInt();
					System.out.println("Selecione uma latitude:");
					yi = s.nextInt();
					System.out.println("Selecione uma longitude:");
					xi = s.nextInt();
					System.out.println("Selecione uma latitude final:");
					yf = s.nextInt();
					System.out.println("Selecione uma longitude final:");
					xf = s.nextInt();
					
					result = p.verificaJogada(jog,  xi, yi, xf, yf, d1, d2);
					System.out.printf("==> %d\n",result);
					if (result != 0) p.movePeao(xi, yi, xf, yf, jog);
					else System.out.println("Não se movimentou");
					
					}
				
				p.passaVez();
				System.out.println("");
				str = null;
			}
		}
				
	
		
	}
	
	public void calculaPosicao(int lat, int lon) {
		int[] peoes = p.getPeoes(lon, lat);
		int pos;
		for (int i = 0; i < peoes.length; i++) {
			if (peoes[i] == -1 && p.getSpecial(lon, lat) != i) pos = i; 
		}
	}
	
	
    
	public static void main(String args[]) {
		new controller();
	}
	
	/*
	 * iniciaPartida
	 * finalizaPartida
	 * verificaJogada
	 * verificaMovimento
	 * verificaAdversario
	 * verificaEspecial
	 * efeitoCarta
	 * moverFichas
	 * passaVez
	 * carregaJogo
	 * salvaJogo
	 * fechaJogo
	 * contaPontos
	 * colocarFichas
	 * 
	 * 
	 * 
	 * 
	 * 1- Inicializa partida
	 * 2- joga dado
	 * 2a - valores diferentes = dados normais
	 * 2b - valores iguais = dado especial; se cor = corJogador finalizaPeao; senao PASSA A VEZ
	 * 3- escolhe x,y inicio
	 * 4- escolhe x,y fim
	 * 5- verifica movimentacao
	 * 5a- true: verifica se come peça; true come, false PASSA A VEZ
	 * 		i) verifica especial: true ==> fazer efeito; false ==> PASSA A VEZ
	 * 5b- false: PASSA A VEZ
	 */
}
