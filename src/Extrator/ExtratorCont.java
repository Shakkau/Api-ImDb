package Extrator;

import java.util.List;
import Entities.Conteudo;

public interface ExtratorCont {
	
	List<Conteudo> extraiConteudos(String json);
	
}
