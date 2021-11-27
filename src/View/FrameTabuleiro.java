package View;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.lang.Math.*;
import java.util.ArrayList;	
import java.util.HashMap;

import Controller.ObservadoInfo;
import Controller.ObservadorButton;
//TEM QUE TIRAR!!!!
import java.util.Random;


public class FrameTabuleiro extends JFrame implements MouseListener{
	private final int LARG_DEFAULT = 1200;
	private final int ALT_DEFAULT = 700;
	private final String VERDE = "#257817";
	private final String AMARELO = "#b58500";
	private final String CINZA = "#3b3b3b";
	private final int RAIO = 30;
	private final int DIAMETRO = 15;
	public int qtoJogadores = 0;
	public boolean dupla = false;
	
	protected JComboBox<String> opcaoNum1;
	protected JComboBox<String> opcaoNum2;
	protected JComboBox<String> opcaoCor;
	public HashMap<String, Integer> pontos = new HashMap<>();


	private final Image background = Imagem.get("background");
	private final Image carta = Imagem.get("Carta"); 
	private final Image d1 = Imagem.get("Dado1");
	private Image dadoAtual1 = Imagem.get("Dado1");
	private Image dadoAtual2 = Imagem.get("Dado1");
	private Image dadoCorAtual = Imagem.get("DadoAmarelo");
	private int dado1 = 1;
	private int dado2 = 1; 
	private String dado3 = "Vermelho";
	private int[] inicio = new int[] {0, 0};
	private int[] fim = new int[] {0, 0};
	
	private Image peaoPreto = Imagem.get("peaoPreto");
	private Image peaoAzul = Imagem.get("peaoAzul");
	private Image peaoVerde = Imagem.get("peaoVerde");
	private Image peaoAmarelo = Imagem.get("peaoAmarelo");
	public String vezAtual  = "Verde";
	
	private int flagDado = 0;
	
	private String []numOpcoes = new String[]{"1", "2", "3", "4", "5", "6", "Aleatório"};
	private String []corOpcoes = new String[] {"Amarelo", "Verde", "Preto", "Azul", "Aleatório"};
	
	
	public int [][]posAzul = new int[6][2];
	public int [][]posVerde = new int[6][2];
	public int [][]posPreto = new int[6][2];
	public int [][]posAmarelo = new int[6][2];
	
	public int [][]pecasEspeciais = new int[12][2];
	
	private int botaoApertado = -1;
	private int ndado1;
	private int ndado2;
	
	/*
	//private ObservadoButton b = new ObservadoButton(this);
	//private ObservadoInfo novo;
	
	public ObservadoButton getObervadoButton() {
		return this.b;
	}*/
	
	public void setndado1(int n) {
		this.ndado1 = n;
	}
	
	public void setndado2(int n) {
		this.ndado2 = n;
	}
	
	public FrameTabuleiro(/*ObservadoInfo novo, */int qtoJogadores, boolean dupla) {
		
		this.setVisible(true);	

		//this.novo = novo;
		
		//ObservadorInfo novo2 = new ObservadorInfo(novo, this);
		
		
		this.dupla = dupla;
		if (!dupla) {
			this.qtoJogadores = qtoJogadores;
		}else {
			this.qtoJogadores = 4;
		}
		
		//preenchendo o hashmap de pontos
		pontos.put("Amarelo", 0);
		pontos.put("Verde", 0);
		pontos.put("Azul", 0);
		pontos.put("Preto", 0);

		//inicializandos os vetores de posições com -1
		inicializaVetores();
		
		//inicializando vetor das posicoes de fichas com -1
		inicializaVetorFichas();
		
		
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
		
		// Fecha a combobox 
		opcaoNum2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) { 
		    	desenha();
		    	}
		});
		
		//teste peao
		
		atualizaVetorPeao(0, "Amarelo", 200, 334);
		atualizaVetorPeao(1, "Amarelo", 200, 334);
		atualizaVetorPeao(3, "Amarelo", 200, 334);
		
		//teste fichas
		
		atualizaVetorFichas(0, 113, 428);
		
		
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

	public void desenha() {
		this.repaint();
	}
	
	public int getBotaoApertado() {
		return this.botaoApertado;
	}

	public void incrementaPontuação(String j) {
		pontos.put(j, pontos.get(j) + 1);	
	}
	
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        Font f = new Font("Arial", Font.BOLD, 28);

        // desenha o tabuleiro com as cartas
        g2d.drawImage(background, 0, 0, null);
        g2d.drawImage(carta, 317, 567, 120, 100, null);
        
        //desenha os dados
        g2d.drawImage(Imagem.get("Dado", dado1), 770, 30, null);
        g2d.drawImage(Imagem.get("Dado", dado2), 920, 30, null);
        g2d.drawImage(Imagem.get("Dado" + dado3), 1070, 30, null);
        
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
        
        
        //desenhado a vez do jogador
        
        if (vezAtual.equals("Preto")) {
        	g2d.setStroke(new BasicStroke(5));
        	g2d.setColor(Color.BLACK);
        	g2d.drawLine(771, 486, 913, 486);
        	
        }
        else if (vezAtual.equals("Azul")) {
        	g2d.setColor(Color.BLUE);
        	g2d.setStroke(new BasicStroke(5));
        	g2d.drawLine(1003, 484, 1143, 484);
        }
        else if (vezAtual.equals("Amarelo")) {
        	g2d.setColor(Color.decode(AMARELO));
        	g2d.setStroke(new BasicStroke(5));
        	g2d.drawLine(1003, 634, 1144, 634);
        }
        else if (vezAtual.equals("Verde")) {
        	g2d.setColor(Color.decode(VERDE));
        	g2d.setStroke(new BasicStroke(5));
        	g2d.drawLine(774, 634, 912, 634);
        }
        else {
        	System.out.println("Cor nao reconhecida.");
        }
        
        
        //desenhando fichas especiais
       
       
        
        g2d.setColor(Color.RED);
        
        if(!vetorVazio(pecasEspeciais));
        for (int i = 0; i < pecasEspeciais.length; i++) {
        	if(pecasEspeciais[i][0] != -1 && pecasEspeciais[i][1] != -1) {
        		g2d.fillOval(pecasEspeciais[i][0] - DIAMETRO/2, pecasEspeciais[i][1] - DIAMETRO/2, DIAMETRO, DIAMETRO);
        	}
        }
        
        /*
        g2d.fillOval(50, 222, 15, 10);
        g2d.fillOval(155, 179, 10, 10);
        g2d.fillOval(113 - 5, 428 - 5, 10, 10);
        g2d.fillOval(242 - 5, 488 - 5, 10, 10);
        g2d.fillOval(351 - 5, 440 - 5, 10, 10);
        g2d.fillOval(277 - 5, 231 - 5, 10, 10);
        g2d.fillOval(441 - 5, 183 - 5, 10, 10);
        g2d.fillOval(504 - 5, 182 - 5, 10, 10);
        g2d.fillOval(455 - 5, 430 - 5, 10, 10);
        g2d.fillOval(585 - 5, 487 - 5, 10, 10);
        g2d.fillOval(647 - 5, 485 - 5, 10, 10);
        g2d.fillOval(634 - 5, 245 - 5, 10, 10);
        */
        

       //tentando desenhar uma peça
        if (!vetorVazio(posPreto)) {
        	for (int i = 0; i < posPreto.length; i++) {
        		if (posPreto[i][0] != -1 && posPreto[i][0] != -1) {
        			g2d.drawImage(peaoPreto, posPreto[i][0], posPreto[i][1] + (i * 3), null);
        		}
        	}
        }
        if (!vetorVazio(posAzul)) {
        	for (int i = 0; i < posAzul.length; i++) {
        		if (posAzul[i][0] != -1 && posAzul[i][0] != -1) {
        			g2d.drawImage(peaoAzul, posAzul[i][0], posAzul[i][1] + (i * 3), null);
        		}
        	}
        }
        if (!vetorVazio(posVerde)) {
        	for (int i = 0; i < posVerde.length; i++) {
        		if (posVerde[i][0] != -1 && posVerde[i][0] != -1) {
        			g2d.drawImage(peaoVerde, posVerde[i][0], posVerde[i][1] + (i * 3), null);
        		}
        	}
        }
        if (!vetorVazio(posAmarelo)) {
        	for (int i = 0; i < posAmarelo.length; i++) {
        		if (posAmarelo[i][0] != -1 && posAmarelo[i][0] != -1) {
        			g2d.drawImage(peaoAmarelo, posAmarelo[i][0], posAmarelo[i][1] + (i * 3), null);
        		}
        	}
        }
        
    }

	@Override
    public void mouseClicked(MouseEvent e) {
    	Point p = e.getPoint();
    	int x, y;
        x = (int) p.getX();
        y = (int) p.getY();
        
        System.out.printf("x= %d y=%d\n", x, y);
        
        if (flagDado == 0 && (x >= 900 && x <= 1030) && (y >= 210 && y <= 270)) {
        	String val1 = (String)opcaoNum1.getSelectedItem();
        	String val2 = (String)opcaoNum2.getSelectedItem();
        	String val3 = (String)opcaoCor.getSelectedItem();
        	setDado1(val1);
        	setDado2(val2);
        	setDado3(val3);
        	this.repaint();
        	flagDado = 0;
        	
        	if (dado1 == dado2 && dado3 != vezAtual) Controller.controller.passaVez();
/*
        	if (dado1.equals("Aleatório") && dado2.equals("Aleatório")) {
        		
        		this.botaoApertado = 1;
        		
        		b.varrerDados();
        	}

        	else if (dado1.equals("Aleatório")) {
        		dado1 = "Aleatório";
        		dado2 = Integer.parseInt(dado2);
        	}
        	
        	else if (dado2.equals("Aleatório")){
        		ndado1 = Integer.parseInt(dado1);
        		ndado2 = (int)(Math.random()*6) + 1;
        	}
        	
        	else {
        		ndado1 = Integer.parseInt(dado1);
        		ndado2 = Integer.parseInt(dado2);
        	}
        	
        	if (dado3.equals("Aleatório")) {
        		dado3 = this.corOpcoes[val3];
        		
        	}
*/

        }
        
        if (flagDado == 1 && x < 738) {
        	if (dado1 == dado2) Controller.controller.jogaEspecial(x, y, dado3);
        }
        	
        else if ((x >= 900 && x <= 1030) && (y >= 300 && y <= 430)) {
        	System.out.println("Clicou no botão Salvar");
        	this.repaint();
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
    
    /*
    public void abrir() {
    	this.setVisible(true);
    }*/
    
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
    }
    
    public void mudaPosicao(int latitude, int longitude, int pos, String cor, int id) {
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

    	if (cor == "Amarelo") {
    		posAmarelo[id][0] = x;
    		posAmarelo[id][1] = y;
    	}
    	else if (cor == "Azul") {
    		posAzul[id][0] = x;
    		posAzul[id][1] = y;
    	}
    	else if (cor == "Verde") {
    		posVerde[id][0] = x;
    		posVerde[id][1] = y;
    	}
    	else if (cor == "Preto") {
    		posPreto[id][0] = x;
    		posPreto[id][1] = y;
    	}
    }
    
    private void inicializaVetores() {
    	for (int i =0; i < 6; i++) {
    		this.posAmarelo[i][0] = -1;
    		this.posAmarelo[i][1] = -1;
    		
    		this.posVerde[i][0] = -1;
    		this.posVerde[i][1] = -1;
    		
    		this.posPreto[i][0] = -1;
    		this.posPreto[i][1] = -1;
    		
    		this.posAzul[i][0] = -1;
    		this.posAzul[i][1] = -1;
    	}
    }
    
    public void inicializaVetorFichas() {
    	for (int i = 0; i < pecasEspeciais.length; i++) {
    		this.pecasEspeciais[i][0] = -1;
    		this.pecasEspeciais[i][1] = -1;
    	}
    }
    
    private boolean vetorVazio(int [][]pos) {
    	for (int i = 0; i < pos.length; i++) {
    		if (pos[i][0] == -1 || pos[i][1] == -1) {
    			return false;
    		}
    	}
    	
    	return true;
    }
    
    public void atualizaVetorPeao(int idPeca, String idCor, int x, int y) {
    	
    	if (idCor.equals("Azul")) {
    		this.posAzul[idPeca][0] = x;
    		this.posAzul[idPeca][1] = y;
    	}
    	
    	else if(idCor.equals("Verde")) {
    		this.posVerde[idPeca][0] = x;
    		this.posVerde[idPeca][1] = y;
    	}
    	else if(idCor.equals("Preto")) {
    		this.posPreto[idPeca][0] = x;
    		this.posPreto[idPeca][1] = y;
    	}
    	else if(idCor.equals("Amarelo")) {
    		this.posAmarelo[idPeca][0] = x;
    		this.posAmarelo[idPeca][1] = y;
    	}
    	else {
    		System.out.println("Cor nao identidicada.");
    	}
    }
    
    public void atualizaVetorFichas(int idFicha, int x, int y) {
    	this.pecasEspeciais[idFicha][0] = x;
    	this.pecasEspeciais[idFicha][1] = y;
    }
   
    

    public int getDado1() { return dado1; }
    
    public int getDado2() { return dado2; }
    
    public String getDado3()  { return dado3; }

    public void setDado1(String s) {
    	if (s == "Aleatório") dado1 = (int) (Math.random()*6 + 1);
    	else dado1 = Integer.valueOf(s); 
    	}
    
    public void setDado2(String s) {
    	if (s == "Aleatório") dado2 = (int) (Math.random()*6 + 1);
    	else dado2 = Integer.valueOf(s); 
    	}
    
    public void setDado3(String s) {
    	if (s == "Aleatório") dado3 = corOpcoes[(int)(Math.random()*4)];
    	else dado3 = s; 
    	}
 
    public void setFlag(int n) { flagDado = n; }
    
    public void setVezAtual(String s) { vezAtual = s; }
}