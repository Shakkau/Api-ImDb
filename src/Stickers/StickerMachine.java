package Stickers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

public class StickerMachine {

	public void create(InputStream input, String nomeArquivo) throws IOException {
		// leitura da imagem
		//InputStream input = new FileInputStream(new File("entrada/Cachorro_loco.jpg"));
		//InputStream inputURL = new URL("https://www.qualittas.com.br/blog/wp-content/uploads/2017/08/M%C3%AAs-do-cachorro-louco.png").openStream();
		BufferedImage image = ImageIO.read(input);
		
		// criar uma nova imagem em memória e com transparência
		int width = image.getWidth();
		int height = image.getHeight();
		int newHeight = height + 200;
		
		BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);
		// copiar imagem original pra uma nova imagem (em memória)
		Graphics2D graphic = (Graphics2D) newImage.getGraphics();
		graphic.drawImage(image, null, 0, 0);
		
		// escrever uma frase na nova imagem
		var style = new Font(Font.SANS_SERIF, Font.BOLD, 65);
		graphic.setFont(style);
		graphic.setColor(Color.black);
		
		graphic.drawString("FILMASSO", 200, newHeight - 100);
		
		// escrever a nova imagem em arquivo
		ImageIO.write(newImage, "png", new File("saida/" + nomeArquivo));
		
	}
}
