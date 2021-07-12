package avaliacaoMapas.persistencia;

import java.util.Collection;
import avaliacaoMapas.model.entity.Cor;

public interface CorDAOIF {
	public void salvar(Cor c) throws Exception;
	public void atualizar(Cor c) throws Exception;
	public Cor findByNome(String nome) throws Exception;
	public Collection<Cor> findBySimbolo(String simbolo) throws Exception;
	public Collection<Cor> findAll() throws Exception;
	
	//public Collection<String> listarSimbolos() throws Exception;
}
