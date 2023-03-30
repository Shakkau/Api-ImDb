package Systeminic;

import JsonParser.*;
import Stickers.StickerMachine;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class PrimeiroTeste {

	public static void main(String[] args) throws IOException, InterruptedException {
		// API do HTTP, get and request = buscar na api do google e devolver o body em forma de String
		String color = "\u001b[38;5;246m", re_color = "\u001b[m";
		String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
		URI endereco = URI.create(url);
		var client = HttpClient.newHttpClient();
		var request = HttpRequest.newBuilder(endereco).GET().build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		String body = response.body();

		// Separar informações e selecionar oque deverá ser mostrado
		var parser = new TesteJson();

		List<Map<String, String>> listaDeFilmes = parser.parse(body);

		// exibição de dados
		var sm = new StickerMachine();
		var diretorio = new File("saida/");
		
		diretorio.mkdir();
		
		for (int i = 0; i < 5; i++) {
			var filme = listaDeFilmes.get(i);
		
			String urlTitle = filme.get("title");
			String urlImage = filme.get("image");
			String urlRating = filme.get("imDbRating");
			String nomeArq = "saida/" + urlTitle + ".png";
			
			InputStream inputURL = new URL(urlImage).openStream();
			
			sm.create(inputURL, nomeArq);

			System.out.println(color + "Title: " + re_color + urlTitle);
			System.out.println(color + "Poster: " + re_color + urlImage);
			System.out.println(color + "Rating: " + re_color + urlRating);
			System.out.println("");
		}
	}

}
