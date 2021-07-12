package avaliacaoMapas.model;

import java.util.ArrayList;

public interface AvMapasLogicaIF {
	public String[] analisarImagens(String uri, String simbolo) throws Exception;
	public ArrayList<String> nomeCor(String simbolo) throws Exception;
}
