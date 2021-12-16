package View;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;	
import java.util.HashMap;
import java.util.Random;

public class FrameOrdem extends JFrame implements MouseListener{
	
	private final int LARG_DEFAULT=300;
	private final int ALT_DEFAULT=400;
	
	
	//Pega o numedo do dado de cada jogador e mostra a ordem do jogo 
	private HashMap<String, Integer> valor_dado = new HashMap<>();
	
	private int qtoJogadores;
	
	private String comeca;
      
	public FrameOrdem(int num_de_jogadores) { // caso de 2 pessoas jogando
		
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		int sl=screenSize.width;
		int sa=screenSize.height;
		int x=sl/2-LARG_DEFAULT/2;
		int y=sa/2-ALT_DEFAULT/2;
		setBounds(x,y,LARG_DEFAULT,ALT_DEFAULT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Escolher ordem");
		setLayout(null);
		setResizable(false);
		
	    
	  
	}
	
		public void paint(Graphics g) {
			super.paint(g);
	    	Graphics2D g2d = (Graphics2D) g;
	    	g2d.setColor(Color.decode("#3b3b3b"));
	    	g2d.fillRect(LARG_DEFAULT/2 - 130/2, ALT_DEFAULT/2, 130, 60);
	    	Font titulo = new Font("Arial", Font.BOLD, 28);
	    	Font f  = new Font("Arial", Font.BOLD, 18);
	    	
	    	g2d.setColor(Color.WHITE);
	    	g2d.setFont(f);
	    	g2d.drawString("Lançar dado", LARG_DEFAULT/2 - 55, ALT_DEFAULT/2 + 35);
	    	
	    	g2d.setColor(Color.BLACK);
	    	
	    	g2d.drawString("Jogue o dado para decidir a ordem.", LARG_DEFAULT/2 - 120, 100);
	    	
	    	

	 }
		
	@Override
	public void mouseClicked(MouseEvent e) {
		   Point p = e.getPoint();
		   int x, y;
		   x = (int) p.getX();
	       y = (int) p.getY();
		   
	 }
	   
	    @Override
	    public void mouseExited(MouseEvent e) {}

	    @Override
	    public void mouseEntered(MouseEvent e) {}

	    @Override
	    public void mousePressed(MouseEvent e) {}

	    @Override
	    public void mouseReleased(MouseEvent e) {}
	    
	    
	    public static void main(String[] args) {
			Imagem.carregar();
			FrameOrdem o = new FrameOrdem(4);
			o.setVisible(true);
		}
}