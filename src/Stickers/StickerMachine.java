package Stickers;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class StickerMachine {

	public void create(InputStream input, String nomeArquivo) throws IOException {
		// leitura da imagem
		// InputStream input = new FileInputStream(new
		// File("entrada/Cachorro_loco.jpg"));
		// InputStream inputURL = new
		// URL("https://www.qualittas.com.br/blog/wp-content/uploads/2017/08/M%C3%AAs-do-cachorro-louco.png").openStream();
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
		var style = new Font("Impact", Font.BOLD, 65);
		graphic.setFont(style);
		graphic.setColor(Color.black);

		String text = "FILMASSO";
		FontMetrics fontMetric = graphic.getFontMetrics();
		Rectangle2D ret = fontMetric.getStringBounds(text, graphic);

		int largText = (int) ret.getWidth();
		int positionTextX = (width - largText) / 2;
		int positionTextY = newHeight - 100;

		graphic.drawString(text, positionTextX, positionTextY);
		
		var fontRender = graphic.getFontRenderContext();
		var textLayout = new TextLayout(text, style, fontRender);
		
		Shape outline = textLayout.getOutline(null);
		AffineTransform transform = graphic.getTransform();
		
		transform.translate(positionTextX, positionTextY);
		graphic.setTransform(transform);
		
		var outlineStroke = new BasicStroke(width * 0.004f);
		graphic.setStroke(outlineStroke);
		
		graphic.setColor(Color.magenta);
		graphic.draw(outline);
		graphic.setClip(outline);

		// escrever a nova imagem em arquivo
		ImageIO.write(newImage, "png", new File(nomeArquivo));

	}
}
