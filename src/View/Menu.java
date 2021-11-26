package View;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Menu extends JFrame implements ActionListener{
	private final int LARG_DEFAULT=300;
	private final int ALT_DEFAULT=400;
	public boolean dupla = false;
	public int qtoJogadores = 0;
	
    protected JButton botaoNovaPartida;
    protected JButton botaoCarregarPartida;
    protected JButton botaoComecarPartida;
    protected JComboBox<String> opcoesJogadores;
    protected String[] opcoes;
    
    
    public boolean ativado;

	public Menu() {
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
		


        // adicionado os botoes

        botaoNovaPartida = new JButton("Nova partida");
        botaoNovaPartida.setBounds(20, 100, 120, 40);
        botaoNovaPartida.addActionListener(this);
        this.add(botaoNovaPartida);

        botaoCarregarPartida = new JButton("Carregar Partida");
        botaoCarregarPartida.setBounds(160, 100, 120, 40);
        botaoCarregarPartida.addActionListener(this);
        this.add(botaoCarregarPartida);

        botaoComecarPartida = new JButton("Começar Partida");
        botaoComecarPartida.setBounds(20, 100, 120, 40);
        botaoComecarPartida.setVisible(false);
        botaoComecarPartida.addActionListener(this);
        this.add(botaoComecarPartida);

        opcoes = new String[]{"2 jogadores", "4 jogadores", "2 duplas"};
        opcoesJogadores = new JComboBox<>(opcoes);
        opcoesJogadores.setSelectedIndex(0);
        opcoesJogadores.setBounds(160, 100, 120, 40);
        opcoesJogadores.setVisible(false);
        opcoesJogadores.addActionListener(this);
        this.add(opcoesJogadores);
	}

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        // desenha os botoes
        botaoNovaPartida.repaint();
        botaoCarregarPartida.repaint();
        botaoComecarPartida.repaint();
        opcoesJogadores.repaint();
    }
    
    public void fecha() {
        this.setVisible(false);
        this.dispose();
    }
    
    
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj.equals(botaoNovaPartida)) {  // apertou o botao de nova partida
            this.botaoNovaPartida.setVisible(false);
            this.botaoCarregarPartida.setVisible(false);
            this.botaoComecarPartida.setVisible(true);
            this.opcoesJogadores.setVisible(true);
            System.out.println("Botão novo jogo apertado");

        } else if (obj.equals(botaoCarregarPartida)) {  // apertou o botao de carregar partida
            //this.botaoNovaPartida.setVisible(false);
            System.out.println("Botão carregar apertado");

        } else if (obj.equals(botaoComecarPartida)) {  // apertou o botao de comecar partida
            System.out.println("Começar jogo");
            FrameTabuleiro t = new FrameTabuleiro(this.qtoJogadores, this.dupla);
            t.abrir();
            fecha();           
            

        } else {  // mudou a quantidade de jogadores
            JComboBox<String> cb = (JComboBox<String>) obj;
            String quantidade = (String)cb.getSelectedItem();
            
            if (quantidade.equals("2 duplas")) {
            	this.dupla = true;
            }
            
            else if (quantidade.equals("2 jogadores")) {
            	this.qtoJogadores = 2;
            	
            }
            
            else if (quantidade.equals("4 jogadores")) {
            	this.qtoJogadores = 4;
            }
            
            System.out.printf("==> %s\n", quantidade);
            //this.quantidadeJogadores = Integer.parseInt(String.valueOf(quantidade.charAt(0)));
        }
    }
    
	public static void main(String args[]) {
		Imagem.carregar();
		Menu f = new Menu();
		f.setVisible(true);
		
		
	}
}