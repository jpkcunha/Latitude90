package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Partida {

    private  int numJogadores;
    private String dupla;
    private List<Jogador> aux;
    private List<Jogador> ordem;

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
                aux.add(p);

                System.out.println("Cor adicionada com sucesso!");
            }
        }

    }

    public void escolheOrdem(){
        System.out.println("Vamos escolher a ordem as suas ordens!!");
        for (int counter = 0; counter < aux.size(); counter++){

            Dado d = new Dado();

            int valorRetirado = d.getNumAleatorio(6,1);
            System.out.printf("%s retirou no dado o valor: %d%n", aux.get(counter).getCor(), valorRetirado);


        }
    }


    public void criaPartida(){

        while(true){

        }
    }



    public int getNumJogadores() {
        return numJogadores;
    }
}