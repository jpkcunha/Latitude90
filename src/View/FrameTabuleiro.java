package View;

import java.awt.*;
import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;


public class FrameTabuleiro extends JFrame{
	private final int LARG_DEFAULT = 1200;
	private final int ALT_DEFAULT = 700;
	private final Image background = Imagem.get("background"); 

	public FrameTabuleiro() {
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		int sl=screenSize.width;
		int sa=screenSize.height;
		int x=sl/2-LARG_DEFAULT/2;
		int y=sa/2-ALT_DEFAULT/2;
		setBounds(x,y,LARG_DEFAULT,ALT_DEFAULT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Latitude90");
		setLayout(null);
		setResizable(false);
		
	}

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        // desenha o fundo
        g2d.drawImage(background, 0, 0, null);
    }
    
    public void abrir() {
    	this.setVisible(true);
    }

}

