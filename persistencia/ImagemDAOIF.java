package avaliacaoMapas.persistencia;

import java.io.IOException;
import java.util.Collection;

import avaliacaoMapas.model.entity.Cor;
import avaliacaoMapas.model.entity.Imagem;

public interface ImagemDAOIF {
	//public void writeFileCor(Imagem imagem)throws IOException;
	public Imagem readFileCor(String uri)throws IOException, ClassNotFoundException;
	public Collection<Cor> findBySimbolo(String uri, String simbolo) throws Exception;
	public String[] findBySimboloII(String uri, String simbolo) throws Exception;
}
