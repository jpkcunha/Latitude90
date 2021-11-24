package View;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.lang.Math.*;
import java.util.ArrayList;
import java.util.HashMap;

//TEM QUE TIRAR!!!!
import Model.*;
import java.util.Random;


public class FrameTabuleiro extends JFrame implements MouseListener, ActionListener {
	private final int LARG_DEFAULT = 1200;
	private final int ALT_DEFAULT = 700;
	private final String VERDE = "#257817";
	private final String AMARELO = "#b58500";
	private final String CINZA = "#3b3b3b";

	public int qtoJogadores = 0;
	public boolean dupla = false;
	
	protected JComboBox<String> opcaoNum1;
	protected JComboBox<String> opcaoNum2;
	protected JComboBox<String> opcaoCor;
	HashMap<String, Integer> pontos = new HashMap<>();


	public String atual = "Dado1";
	private final Image background = Imagem.get("background"); 
	private final Image d1 = Imagem.get("Dado1");
	private Image dadoAtual1 = Imagem.get("Dado1");
	private Image dadoAtual2 = Imagem.get("Dado1");
	

	public FrameTabuleiro(int qtoJogadores, boolean dupla) {
		
		String []numOpcoes = new String[]{"1", "2", "3", "4", "5", "6", "Aleatório"};
		String []corOpcoes = new String[] {"Amarelo", "Verde", "Preto", "Azul", "Aleatório"};
		
		this.dupla = dupla;
		if (!dupla) {
			this.qtoJogadores = qtoJogadores;
		}
		
		//preenchendo o hashmap de pontos
		pontos.put("Amarelo", 0);
		pontos.put("Verde", 0);
		pontos.put("Azul", 0);
		pontos.put("Preto", 0);
		
		
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		int sl=screenSize.width;
		int sa=screenSize.height;
		int x=sl/2-LARG_DEFAULT/2;
		int y=sa/2-ALT_DEFAULT/2;
		setBounds(x,y,LARG_DEFAULT,ALT_DEFAULT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Latitude 90");
		
		//combobox dado 1
		opcaoNum1 = new JComboBox<String>(numOpcoes);
		opcaoNum1.setSelectedIndex(6);
		opcaoNum1.setBounds(760, 120, 100, 30);
		this.add(opcaoNum1);
		opcaoNum1.setVisible(true);
		
		//combobox dado 2
		opcaoNum2 = new JComboBox<String>(numOpcoes);
		opcaoNum2.setSelectedIndex(6);
		opcaoNum2.setBounds(910, 120, 100, 30);
		this.add(opcaoNum2);
		opcaoNum2.setVisible(true);
		opcaoNum2.addActionListener(null);
		
		
		//combobox dado cor
		opcaoCor = new JComboBox<String>(corOpcoes);
		opcaoCor.setSelectedIndex(0);
		opcaoCor.setBounds(1060, 120, 100, 30);
		this.add(opcaoCor);
		opcaoCor.setVisible(true);

		setLayout(null);
		setResizable(false);
		addMouseListener(this);
		
	}



    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        Font f = new Font("Arial", Font.BOLD, 28);

        // desenha o fundo
        g2d.drawImage(background, 0, 0, null);
        
        //desenha os dados
        g2d.drawImage(dadoAtual1, 770, 30, null);
        g2d.drawImage(dadoAtual2, 920, 30, null);
        g2d.drawImage(d1, 1070, 30, null);
        
        //desenha botoes
        g2d.setColor(Color.decode(CINZA));
        g2d.fillRect(900, 210, 130, 60);
        g2d.fillRect(900, 300, 130, 60);
        
        g2d.setColor(Color.WHITE);
        g2d.setFont(f);
        g2d.drawString("Lançar", 920, 250);
        g2d.drawString("Salvar", 920, 340);
        
        
        //desenhas areas de pontos
        g2d.setColor(Color.BLACK);
        g2d.drawLine(739, 400, 1200, 400);
        g2d.drawLine(739, 550, 1200, 550);
        g2d.drawLine(970, 400, 970, 700);
        
        g2d.setFont(f);
        g2d.setColor(Color.BLACK);
        g2d.drawString("Pontos: ", 770, 480);
        
        g2d.setColor(Color.BLUE);
        g2d.drawString("Pontos: ", 1000, 480);
        
        g2d.setColor(Color.decode(VERDE));
        g2d.drawString("Pontos: ", 770, 630);
        
        g2d.setColor(Color.decode(AMARELO));
        g2d.drawString("Pontos: ", 1000, 630);
        
        //desenhando os pontos
        
        g2d.setColor(Color.BLACK);
        g2d.drawString(pontos.get("Preto").toString(), 900, 480);
        
        g2d.setColor(Color.BLUE);
        g2d.drawString(pontos.get("Azul").toString(), 1130, 480);
        
        g2d.setColor(Color.decode(VERDE));
        g2d.drawString(pontos.get("Verde").toString(), 900, 630);
        
        g2d.setColor(Color.decode(AMARELO));
        g2d.drawString(pontos.get("Amarelo").toString(), 1130, 630);
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    	Point p = e.getPoint();
    	int x, y, val1, val2;
        x = (int) p.getX();
        y = (int) p.getY();
        
        if ((x >= 900 && x <= 1030) && (y >= 210 && y <= 270)) {
        	String dado1 = (String)opcaoNum1.getSelectedItem();
        	String dado2 = (String)opcaoNum2.getSelectedItem();
        	
        	if (dado1.equals("Aleatório")) {
        		val1 = (int)((Math.random() * (6 - 1)) + 1);
        		val2 = Integer.parseInt(dado2);
        		System.out.printf("Joga dado 1 Aleatório ==> %d\n", val1);
        	}
        	
        	if (dado2.equals("Aleatório")){
        		val1 = Integer.parseInt(dado1);
        		val2 = (int)((Math.random() * (6 - 1)) + 1);
        		System.out.printf("Joga dado 2 Aleatório ==> %d\n", val2);
        	}
        	
        	else {
        		val1 = Integer.parseInt(dado1);
        		val2 = Integer.parseInt(dado2);
        		//dadoAtual1 = Imagem.get("Dado", Integer.parseInt(dado1));
        		//dadoAtual2 = Imagem.get("Dado", Integer.parseInt(dado2));
        	}

    		dadoAtual1 = Imagem.get("Dado", val1);
    		dadoAtual2 = Imagem.get("Dado", val2);
    		this.repaint();
        	
        	

        }
        
        if (x < 738) calculaLatLong(x, y); 
        	
        else if ((x >= 900 && x <= 1030) && (y >= 300 && y <= 430)) {
        	System.out.println("Clicou no botão Salvar");
        }
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
    
    private int calculaLongitude(int x, int y) {
    	double theta, dx, dy;
    	dx = (double)x;
    	dy = (double)y;
    	
    	//1o quadrante
    	if (dx > 0 && dy > 0) theta = Math.toDegrees(Math.atan(dy/dx));
    	
    	//2o e 3o quadrantes
    	else if (dx < 0) theta = 180 + Math.toDegrees(Math.atan(dy/dx));
    	
    	//4o quadrante
    	else if( dx > 0 && dy < 0) theta = 360 + Math.toDegrees(Math.atan(dy/dx));
    	
    	//x = 0 ==> arctan(infinito)
    	else {
    		if (dy > 0) theta = 90.0;
    		if (dy < 0) theta = 270.0;
    		else theta = 0.0;
    	}
    	
    	//int result =(int)Math.floor(theta/30);
    	//System.out.printf("-> %f, %d\n",theta, result);
    	//return result;
    	return (int)Math.floor(theta/30);
    	
    }
    
    private int calculaLatitude(int x, int y, int polo, int longitude) {
    	int latitude;
    	double r, abs;
    	
    	//longitude = calculaLongitude(x, y);
    	abs = Math.abs((double)x);
    	
    	//criando a lista
    	ArrayList<Integer> lista = new ArrayList<Integer>();
    	lista.add(0);
    	lista.add(5);
    	lista.add(6);
    	lista.add(11);
    	
    	
    	if (lista.contains(longitude)){ //Longitudes especiais (passagens entre hemisférios)
    		
    		if (abs < 32) latitude = -1;
    		else if (abs < 56) latitude = 0;
    		else if (abs < 76) latitude = 1;
    		else if (abs < 101) latitude = 2;
    		else if (abs < 123) latitude = 3;
    		else if (abs < 148) latitude = 4;
    		else if (abs < 172) latitude = 5;
    		else return -2; //fora do tabuleiro
    	}
    	else {
    		r = Math.sqrt(x*x + y*y);
    		if (r < 36) latitude = -1;
    		else if (r < 65) latitude = 0;
    		else if( r < 90) latitude = 1;
    		else if( r < 117) latitude = 2;
    		else if( r < 145) latitude = 3;
    		else if( r < 170) latitude = 4;
    		else if( r < 195) latitude = 5;
    		else return -2; //fora do tabuleiro
    		
    		if (polo == 1) {
    			if (latitude == -1) latitude = 12;
    			else latitude = 11 - latitude;
    		}
    	}
    	
    	return latitude;       
    }  

    public void calculaLatLong(int x, int y) {
    	int latitude, longitude, xfin, yfin, polo;

    	// Transposição - coordenadas cartesianas para polares centradas nos polos
        if (x < 374) {
        	xfin = x - 203;
        	polo = 0; //Polo Sul
        }
        else {
        	xfin = 545 - x;
        	polo = 1; //Polo Norte
        }
        yfin = 332 - y;
        
        //Calcula latitude e longitude
    	longitude = calculaLongitude(xfin, yfin);
    	latitude = calculaLatitude(xfin, yfin, polo, longitude);

    	//Corrige polos e fora do tabuleiro
    	if (latitude == -1 || latitude == 12 || latitude == -2) longitude = latitude;
    	
    	System.out.printf("\nx=%d, y=%d, lat=%d, long=%d\n", x, y, latitude, longitude);
    	
    	calculaPosicao(latitude, longitude, 0);
    	calculaPosicao(latitude, longitude, 1);
    	if (latitude > 2 && latitude < 9) calculaPosicao(latitude, longitude, 2);
    }
    
    public void calculaPosicao(int latitude, int longitude, int pos) {
    	int x, y;
    	double theta;
    	
    	//Latitudes menores precisam somente de 2 espaços - casas especiais começam após o 3o anel
    	if (latitude < 3 || latitude > 8) 
    		theta = Math.toRadians(15*(2*longitude + 1) + (pos-1)*10);
    	else 
    		theta = Math.toRadians(15*(2*longitude + 1) + (pos-1)*7.5);

    	
    	if (longitude == 0 || longitude == 11) {
    		x = 45 + 23*latitude;
    		y = (int)(45*Math.atan(theta));
    	}
    	else if (longitude == 5 || longitude == 6) {
    		x = -45 - 23*latitude;
    		y = (int)(45*Math.atan(theta));
    	}
    	else {
    		x = (int)((50 + 26*latitude)*Math.cos(theta));
    		y = (int)((50 + 26*latitude)*Math.sin(theta));
    	}
    	
    	System.out.printf("Latitude %d, longitude %d, posição %d: x=%d y=%d\n", latitude, longitude, pos, x, y);
    	
    }
    
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj.equals(opcaoNum2)) this.repaint();
    }
}

