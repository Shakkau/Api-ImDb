package Extrator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import Entities.Conteudo;
import JsonParser.TesteJson;

public class ExtratorContNasa implements ExtratorCont {

	public List<Conteudo> extraiConteudos(String json) {
		// extrair conteudo q importa
		var parser = new TesteJson();
		List<Map<String, String>> listaDeAtributos = parser.parse(json);

		List<Conteudo> conteudos = new ArrayList<>();

		// popular lista de conteudo
		for (Map<String, String> atributos : listaDeAtributos) {
			String urlTitle = atributos.get("title");
			String urlImage = atributos.get("url");

			var cont = new Conteudo(urlTitle, urlImage);

			conteudos.add(cont);
		}

		return conteudos;
	}
}
