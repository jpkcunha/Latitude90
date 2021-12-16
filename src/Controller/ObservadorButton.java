package Controller;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import Model.Partida;
import View.ObservadoButton;
import View.FrameTabuleiro;


public class ObservadorButton{
    private Partida api;

    private String nomeJogador; 

    private ObservadoInfo j;
    private ObservadoButton Botaosubject;

    public ObservadorButton(ObservadoInfo j, ObservadoButton Botaosubject, Partida api, String cor){
          this.Botaosubject = Botaosubject;
          this.Botaosubject.attach(this);
          this.api = api;
          this.nomeJogador = nomeJogador;
          this.j = j;
       }
       public void update() {
           int botaoApertado = Botaosubject.getbotaoApertado();
           System.out.println(Botaosubject.getbotaoApertado());
           if(botaoApertado == 1) {
               //System.out.println(api.Hit(api.getJogadorPorNome(nomeJogador)));]
        	   api.setDado1();
        	   api.setDado2();
        	   j.varrerDados();
        	   System.out.println("ok");
               //subject.varrerDados();
           }
          



       }

    }