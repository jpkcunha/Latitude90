package Controller;

import java.util.ArrayList;
import java.util.List;

import View.ObservadorInfo;
import Model.Partida;

public class ObservadoInfo {
	   private List<ObservadorInfo> observers = new ArrayList<ObservadorInfo>();
	   
	   private int botaoApertado;
	   private Partida api;
	   private int dado1 = -1;
	   private int dado2 = -1;
	   
	    protected ObservadoInfo(Partida api) {
	       this.api = api;
	    }
	    
	    public int getDado1() {
	    	return this.dado1;
	    }
	    public int getDado2() {
	    	return this.dado2;
	    }

	    public int getbotaoApertado() {
	        return this.botaoApertado;
	    }
	   public void varrerDados() {
	      //this.botaoApertado = jj.getBotaoApertado();
	      System.out.println(this.botaoApertado);
	      System.out.println("observadoButton");
	      
	      this.dado1 = this.api.getDado1();
	      this.dado2 = this.api.getDado2();
	      
	      
	      notifyAllObservers();
	   }

	   public void attach(ObservadorInfo observer){
	      observers.add(observer);
	   }

	   public void notifyAllObservers(){
	      for (ObservadorInfo observer : observers) {
	         observer.update();
	      }
	   }
}
