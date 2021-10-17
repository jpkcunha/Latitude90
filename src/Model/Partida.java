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
    private String dupla;
    private List<Jogador> ordem;
    private Tabuleiro t;

    Partida(){

        Scanner s = new Scanner(System.in);
        List<String> jaFoi = new ArrayList<>();

        System.out.println("Vocês jogarão em dupla? [S/N]");
        this.dupla = s.next();

        if(this.dupla == "S") {
            this.numJogadores = 4;
        }

        else{
            System.out.println("Numero de jogadores: ");
            this.numJogadores = s.nextInt();
        }

        //algoritmo para escolher a cor
        while (jaFoi.size() != this.numJogadores){
            Jogador p = new Jogador();
            String corEscolhida;

            System.out.println("Selecione uma cor: ");
            corEscolhida = s.next();

            if(!jaFoi.contains(corEscolhida)) {

                jaFoi.add(corEscolhida);
                //p.setCor(corEscolhida);
                ordem.add(p);

                System.out.println("Cor adicionada com sucesso!");
            }
        }

        //tabuleuiro preenchido

        t = new Tabuleiro();

    }

    private void escolheOrdem(){
        System.out.println("Vamos escolher a ordem as suas ordens!!");

        /*
        for (int counter = 0; counter < aux.size(); counter++){

            Dado d = new Dado();

            int valorRetirado = d.getNumAleatorio(6,1);
            System.out.printf("%s retirou no dado o valor: %d%n", aux.get(counter).getCor(), valorRetirado);


        }*/

        Collections.shuffle(ordem);

        for(int i = 0; i < ordem.size(); i++){
            System.out.printf("#%d: %s%n", i+1, ordem.get(i).getCor());
            //ordem.
        }
    }

    //y -> latitude -> raio
    //x -> longitude -> theta
    public int criaPartida(){

        Scanner s = new Scanner(System.in);
        Dado d1 = new Dado();
        Dado dCor = new Dado();

        int valorD1,valorD2;
        int latitudeIni, longitudeIni, latitudeFim, LongitudeFim;

        while(true){

            //rodada
            for(int i = 0; i < this.ordem.size(); i++){


                System.out.printf("%s, sua vez de jogar!", ordem.get(i).getCor());

                valorD1 = d1.getNumAleatorio(6,1);
                valorD2 = d1.getNumAleatorio(6,1);
                System.out.printf("Você tirou %d e %d!%n", valorD1, valorD2);

                System.out.println("Escolha com qual peao você quer andar.");
                System.out.printf("Latitude: ");
                latitudeIni = s.nextInt();
                System.out.printf("Longitude: ");
                longitudeIni = s.nextInt();

                System.out.println("Agora, escolha para onde quer andar...");
                System.out.printf("Latitude: ");
                latitudeFim = s.nextInt();
                System.out.printf("Longitude: ");
                LongitudeFim = s.nextInt();

                System.out.printf("Deseja andar:%n[1]%d%n[2]%d%n[3]%d", valorD1, valorD2, valorD1 + valorD2);

                if(s.next() == "1")
                    if(t.verificaMovimento(longitudeIni, latitudeIni, LongitudeFim, latitudeFim, valorD1))
                        System.out.println("Movimentação liberada!");
                    else
                        System.out.println("Parace que você não pode se mover para lá!");

                if(s.next() == "2")
                    if (t.verificaMovimento(longitudeIni, latitudeIni, LongitudeFim, latitudeFim, valorD2))
                            System.out.println("Movimentação liberada!");
                    else
                        System.out.println("Parace que você não pode se mover para lá!");

                if(s.next() == "3")
                    if(t.verificaMovimento(longitudeIni, latitudeIni, LongitudeFim, latitudeFim, valorD1 + valorD2))
                        System.out.println("Movimentação liberada!");
                    else
                        System.out.println("Parace que você não pode se mover para lá!");


            }

            if (t.getPoloSul().getIsFull() || t.getPoloNorte().getIsFull()){


                return 1;
            }


        }
    }


    public int getNumJogadores() {
        return numJogadores;
    }
}
