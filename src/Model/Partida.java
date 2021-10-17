package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class Partida {
    /*
    * Pode ser que eu tenha que inicializar esses atributos
    *
    * */
    private  int numJogadores;
    private boolean isDupla;
    private List<Jogador> ordem;
    private Tabuleiro t;

    public Partida(){

        Scanner s = new Scanner(System.in);
        List<Model.Cor> jaFoi = new ArrayList<>();

        System.out.println("Vocês jogarão em dupla? [SIM/NAO]");
        
        
        String resp = s.next();
        
        
        //System.out.println(resp);
        if (resp.equals("SIM")) {
        	this.isDupla = true;
            this.numJogadores = 4;
        }

        else {
        	this.isDupla = false;
            System.out.println("Numero de jogadores: ");
            this.numJogadores = s.nextInt();
        }

        //algoritmo para escolher a cor
        while (jaFoi.size() != this.numJogadores){
        	System.out.println(numJogadores);
        	
            int x;
            Model.Cor corEscolhida;

            System.out.println("Selecione uma cor: AMARELO [0], VERDE [1], AZUL [2], PRETO [3]");
            x = s.nextInt();
            
            
            if (x >= 0 && x <= 4) {
            	corEscolhida = Model.Cor.values()[x];
            	//System.out.printf("Cor: %s\n", corEscolhida);
            	
            	if(!jaFoi.contains(corEscolhida)) {
            		
            		jaFoi.add(corEscolhida);
            		
            		System.out.printf("Cor adicionada com sucesso! JaFoi tem %d elementos\n", jaFoi.size());
            	}
            	
            }

            
        }

        //s.close();

        ordem = new ArrayList<>();
        escolheOrdem(jaFoi);
        
        //tabuleuiro preenchido

        t = new Tabuleiro();

    }

    private void escolheOrdem(List<Model.Cor> l){
    	

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
        Jogador p = new Jogador(maior, cont++);
        ordem.add(p);
        
        if (maior == Model.Cor.PRETO) {
        	if (l.contains(Model.Cor.AZUL)) {
        		ordem.add(new Jogador(Model.Cor.AZUL, cont++));
        	}
        	if (l.contains(Model.Cor.AMARELO)) {
        		ordem.add(new Jogador(Model.Cor.AMARELO, cont++));
        	}
        	if (l.contains(Model.Cor.VERDE)) {
        		ordem.add(new Jogador(Model.Cor.VERDE, cont++));
        	}
        }
        
        else if (maior == Model.Cor.AZUL) {
        	if (l.contains(Model.Cor.AMARELO)) {
        		ordem.add(new Jogador(Model.Cor.AMARELO, cont++));
        	}
        	if (l.contains(Model.Cor.VERDE)) {
        		ordem.add(new Jogador(Model.Cor.VERDE, cont++));
        	}
        	if (l.contains(Model.Cor.PRETO)) {
        		ordem.add(new Jogador(Model.Cor.PRETO, cont++));
        	}
        }
        
        else if (maior == Model.Cor.AMARELO) {
        	if (l.contains(Model.Cor.VERDE)) {
        		ordem.add(new Jogador(Model.Cor.VERDE, cont++));
        	}
        	if (l.contains(Model.Cor.PRETO)) {
        		ordem.add(new Jogador(Model.Cor.PRETO, cont++));
        	}
        	if (l.contains(Model.Cor.AZUL)) {
        		ordem.add(new Jogador(Model.Cor.AZUL, cont++));
        	}
        }
        
        
        else if (maior == Model.Cor.VERDE) {
        	if (l.contains(Model.Cor.PRETO)) {
        		ordem.add(new Jogador(Model.Cor.PRETO, cont++));
        	}
        	if (l.contains(Model.Cor.AZUL)) {
        		ordem.add(new Jogador(Model.Cor.AZUL, cont++));
        	}
        	if (l.contains(Model.Cor.AMARELO)) {
        		ordem.add(new Jogador(Model.Cor.AMARELO, cont++));
        	}
        }
        

        System.out.printf("O jogador a começar é o %s\n", maior.toString());
        

    }

    //y -> latitude -> raio
    //x -> longitude -> theta
    public int criaPartida(){

        Scanner s = new Scanner(System.in);
        Dado d1 = new Dado();
        Dado dCor = new Dado();

        int valorD1,valorD2;
        int latitudeIni, longitudeIni, latitudeFim, longitudeFim;

        int cont = 5;
        while(cont-- > 0){
        	System.out.printf("==> Rodadas para acabar: %d\n", cont);
            //rodada
            for(int i = 0; i < this.ordem.size(); i++){


                System.out.printf("%s, sua vez de jogar!\n", ordem.get(i).getCor());

                valorD1 = d1.getNumAleatorio(6,1);
                valorD2 = d1.getNumAleatorio(6,1);
                System.out.printf("Você tirou %d e %d!%n", valorD1, valorD2);
                
                latitudeIni = d1.getNumAleatorio(5, 0);
                longitudeIni = d1.getNumAleatorio(5, 0);
                latitudeFim = d1.getNumAleatorio(5, 0);
                longitudeFim = d1.getNumAleatorio(5, 0);

                System.out.printf("Casa inicial: longitude %d e latitude %d\n", longitudeIni, latitudeIni);
                System.out.printf("Casa final: longitude %d e latitude %d\n", longitudeFim, latitudeFim);

                Casa inicio = t.getBoard()[longitudeIni][latitudeIni];
                Casa fim = t.getBoard()[longitudeFim][latitudeFim];
                
                int n = t.verificaJogada(ordem.get(i), inicio, fim, valorD1, valorD2);

                if (n == valorD1)
                    System.out.println("Movimentação liberada com o dado 1!");
                else if (n == valorD2)
                    System.out.println("Movimentação liberada com o dado 2!");
                else if (n == valorD1 + valorD2)
                    System.out.println("Movimentação liberada com os dados 1 e 2!");
                else
                    System.out.println("Parece que você não pode se mover para lá!");
                System.out.println(n);
                /* Movimentação do usuario com base na latitude e longitude
                 
                System.out.println("Escolha com qual peao você quer andar.");
                System.out.printf("Latitude: ");
                
                latitudeIni = s.nextInt();
                System.out.printf("Longitude: ");
                
                longitudeIni = s.nextInt();

                System.out.println("Agora, escolha para onde quer andar...");
                System.out.printf("Latitude: ");
                latitudeFim = s.nextInt();
                System.out.printf("Longitude: ");
                longitudeFim = s.nextInt();

                
                Casa inicio, fim;
                
                if (latitudeIni == -1) inicio = t.getPoloSul();
                else if (latitudeIni == 12) inicio = t.getPoloNorte();
                else inicio = t.getBoard()[longitudeIni][latitudeIni];
                
                if (latitudeFim == -1) fim = t.getPoloSul();
                else if (latitudeFim == 12) fim = t.getPoloNorte();
                else fim = t.getBoard()[longitudeFim][latitudeFim];
                
                int n = t.verificaJogada(ordem.get(i), inicio, fim, valorD1, valorD2);

                if (n == valorD1)
                    System.out.println("Movimentação liberada com o dado 1!");
                else if (n == valorD2)
                    System.out.println("Movimentação liberada com o dado 2!");
                else if (n == valorD1)
                    System.out.println("Movimentação liberada com os dados 1 e 2!");
                else
                    System.out.println("Parece que você não pode se mover para lá!");
            }

			*/

            }
        }
        return 0;
    }


    public int getNumJogadores() {
        return numJogadores;
    }
}
