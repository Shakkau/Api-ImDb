package Stickers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class StickerMachine {

	public void create() throws IOException {
		// leitura da imagem
		BufferedImage image = ImageIO.read(new File("entrada/ShawShank.jpg"));
		// criar uma nova imagem em memória e com transparência
		int width = image.getWidth();
		int height = image.getHeight();
		int newHeight = height + 150;
		
		BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);
		// copiar imagem original pra uma nova imagem (em memória)
		Graphics2D graphic = (Graphics2D) newImage.getGraphics();
		graphic.drawImage(image, null, 0, 0);
		
		// escrever uma frase na nova imagem
		
		// escrever a nova imagem em arquivo
		ImageIO.write(newImage, "png", new File("Saida/figurinha.png"));
		
	}
}
