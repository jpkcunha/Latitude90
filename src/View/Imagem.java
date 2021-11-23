package View;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Imagem {
	
	private static final HashMap<String, Image> hash = new HashMap<>();
	private static final String s = "src/View/img/";
	
	public static void carregar() {

        try {

            hash.put("Carta", ImageIO.read(new File(s+"carta.jpg")));
            hash.put("Carta1", ImageIO.read(new File(s+"C01.png")));
            hash.put("Carta2", ImageIO.read(new File(s+"C02.png")));
            hash.put("Carta3", ImageIO.read(new File(s+"C03.png")));
            hash.put("Carta4", ImageIO.read(new File(s+"C04.png")));
            hash.put("Carta5", ImageIO.read(new File(s+"C05.png")));
            hash.put("Carta6", ImageIO.read(new File(s+"C06.png")));
            hash.put("Carta7", ImageIO.read(new File(s+"C07.png")));
            hash.put("Carta8", ImageIO.read(new File(s+"C08.png")));
            hash.put("Carta9", ImageIO.read(new File(s+"C09.png")));
            hash.put("Carta10", ImageIO.read(new File(s+"C10.png")));
            hash.put("Carta11", ImageIO.read(new File(s+"C11.png")));
            hash.put("Carta12", ImageIO.read(new File(s+"C12.png")));
            hash.put("Carta13", ImageIO.read(new File(s+"C13.png")));
            hash.put("Carta14", ImageIO.read(new File(s+"C14.png")));
            hash.put("Carta15", ImageIO.read(new File(s+"C15.png")));
            hash.put("Carta16", ImageIO.read(new File(s+"C16.png")));
            hash.put("Carta17", ImageIO.read(new File(s+"C17.png")));
            hash.put("Carta18", ImageIO.read(new File(s+"C18.png")));

            
            hash.put("Dado1", ImageIO.read(new File(s+"Dado1.png")));
            hash.put("Dado2", ImageIO.read(new File(s+"Dado2.png")));
            hash.put("Dado3", ImageIO.read(new File(s+"Dado3.png")));
            hash.put("Dado4", ImageIO.read(new File(s+"Dado4.png")));
            hash.put("Dado5", ImageIO.read(new File(s+"Dado5.png")));
            hash.put("Dado6", ImageIO.read(new File(s+"Dado6.png")));
            

            hash.put("background", ImageIO.read(new File(s+"Tabuleiro.jpg")));
        }
        catch (IOException e) {
            System.out.println("Erro carregando arquivo de imagens " + e.toString());
            System.exit(-1);
        }	
	}

	public static Image get(String x, int n) { return hash.get(x+String.valueOf(n)); }
	
	public static Image get(String x) { return hash.get(x); }
	 

}
