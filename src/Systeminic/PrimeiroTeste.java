package Systeminic;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import Extrator.*;
import ClientHttp.Bodyhttp;
import Entities.Conteudo;
import Stickers.StickerMachine;

public class PrimeiroTeste {

	public static void main(String[] args) throws IOException, InterruptedException {
		String color = "\u001b[38;5;246m", re_color = "\u001b[m";

		// var extract = new ExtratorContImDb();
		// String url =
		// "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";

		var extract = new ExtratorContNasa();
		String url = "https://api.nasa.gov/planetary/apod?api_key=IpgmjlZUb5y4fdMJscpFfOHOvchubb26G9XPgvJB";

		var http = new Bodyhttp();
		String json = http.buscaDados(url);

		// exibição de dados

		List<Conteudo> content = extract.extraiConteudos(json);

		var sm = new StickerMachine();
		var diretorio = new File("saida/");
		diretorio.mkdir();

		for (int i = 0; i < 1; i++) {
			Conteudo conteudo = content.get(i);

			InputStream inputURL = new URL(conteudo.getImage()).openStream();
			String nomeArq = "saida/" + conteudo.getTitle() + ".png";

			sm.create(inputURL, nomeArq);

			System.out.println(color + "Title: " + re_color + conteudo.getTitle());
			// System.out.println(color + "Poster: " + re_color + conteudo.getImage());
			// System.out.println(color + "Rating: " + re_color + urlRating);
			System.out.println("");
		}
	}

}
