package View;
import Model.*;

import java.util.ArrayList;
import java.util.List;

import Controller.ObservadorButton;



public class ObservadoButton {

   private List<ObservadorButton> observers = new ArrayList<ObservadorButton>();
   private FrameTabuleiro jj;
   private int botaoApertado;

    protected ObservadoButton(FrameTabuleiro jj) {
        this.jj = jj;
    }

    public int getbotaoApertado() {
        return this.botaoApertado;
    }
    
   public void varrerDados() {
      this.botaoApertado = jj.getBotaoApertado();
      System.out.println(this.botaoApertado);
      System.out.println("observadoButton");
      
      
      notifyAllObservers();
   }

   public void attach(ObservadorButton observer){
      observers.add(observer);
   }

   public void notifyAllObservers(){
      for (ObservadorButton observer : observers) {
         observer.update();
      }
   }
}