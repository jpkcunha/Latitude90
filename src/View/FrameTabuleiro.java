package View;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class FrameTabuleiro extends JFrame implements MouseListener {
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
		addMouseListener(this);
		
	}

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        // desenha o fundo
        g2d.drawImage(background, 0, 0, null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    	Point p = e.getPoint();
        int x = (int) p.getX();
        int y = (int) p.getY();
        System.out.printf("x = %d, y = %d\n", x, y);
    }
    
    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}
    
    public void abrir() {
    	this.setVisible(true);
    }

}

