package View;

import Model.Partida;
import Controller.ObservadoInfo;

public class ObservadorInfo{
	
	private FrameTabuleiro a;
	private ObservadoInfo sd;
	
    public ObservadorInfo(ObservadoInfo Botaosubject, FrameTabuleiro e){
        this.sd = Botaosubject;
        this.sd.attach(this);
        this.a = e;
        
        
     }
     public void update() {
         int botaoApertado = sd.getbotaoApertado();
         System.out.println(sd.getbotaoApertado());
         
         a.setndado1(sd.getDado1());
         a.setndado2(sd.getDado2());
         a.repaint();
         if(botaoApertado == 1) {
             //System.out.println(api.Hit(api.getJogadorPorNome(nomeJogador)));]
      	   
      	   
      	   System.out.println("ok");
             //subject.varrerDados();
         }
        



     }}
