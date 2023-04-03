package Systeminic;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

import ClientHttp.Bodyhttp;
import JsonParser.TesteJson;
import Stickers.StickerMachine;

public class PrimeiroTeste {

	public static void main(String[] args) throws IOException, InterruptedException {
		// API do HTTP, get and request = buscar na api do google e devolver o body em
		// forma de String

		String color = "\u001b[38;5;246m", re_color = "\u001b[m";
		// String url =
		// "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
		String url = "https://api.nasa.gov/planetary/apod?api_key=IpgmjlZUb5y4fdMJscpFfOHOvchubb26G9XPgvJB";

		var http = new Bodyhttp();
		String json = http.buscaDados(url);

		// Separar informações e selecionar oque deverá ser mostrado

		var parser = new TesteJson();
		List<Map<String, String>> listaDeConteudo = parser.parse(json);

		// exibição de dados

		var sm = new StickerMachine();
		var diretorio = new File("figurinhaNASA/");

		diretorio.mkdir();

		for (int i = 0; i < 1; i++) {
			var conteudo = listaDeConteudo.get(i);

			String urlTitle = conteudo.get("title");
			String urlImage = conteudo.get("url").replaceAll("(@+)(.*),jpg$", "$1.jpg");
			// String urlRating = conteudo.get("imDbRating");
			String nomeArq = "figurinhaNASA/" + urlTitle + ".png";

			InputStream inputURL = new URL(urlImage).openStream();

			sm.create(inputURL, nomeArq);

			System.out.println(color + "Title: " + re_color + urlTitle);
			System.out.println(color + "Poster: " + re_color + urlImage);
			// System.out.println(color + "Rating: " + re_color + urlRating);
			System.out.println("");
		}
	}

}
