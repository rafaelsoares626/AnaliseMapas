package avaliacaoMapas.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import avaliacaoMapas.model.entity.Cor;
import avaliacaoMapas.model.entity.Imagem;
import avaliacaoMapas.persistencia.CorDAOSQL;
import avaliacaoMapas.persistencia.ImagemDAOFile;

public class AppAvMapas implements AvMapasLogicaIF {	
		
	public String[] analisarImagens(String uri, String simbolo) throws Exception{
    	//###APURACAO DO PERCENTUAL TOTAL#######################################
		//Imagem original
    	ImagemDAOFile imagemDAO = new ImagemDAOFile();
    	Imagem imagem = imagemDAO.readFileCor(uri);
    	double tamImagem = imagem.getAltura()*imagem.getLargura();
    	
    	//Vetor com os pixels correspondentes ao simbolo informado
    	ImagemDAOFile imagemSimbolDAO = new ImagemDAOFile();    	
    	String[] imSimbol = imagemSimbolDAO.findBySimboloII(uri, simbolo);
    	double tamImSimbol = imSimbol.length/3;    	
    	
    	//Apurando o percentual total do simbolo da imagem, bem como convertendo o resultado para String
    	double percentPix = (double) (tamImSimbol/tamImagem);
    	String percentPixString = Double.toString(percentPix);
    	
    	//### BUSCA DO NOME DAS CORES QUE COMPOEM O SIMBOLO#########
    	//Busca simbolo no banco    	
    	CorDAOSQL corDAO = new CorDAOSQL();    	
    	//Collection<Cor> cores = corDAO.findBySimbolo(simbolo);
    	ArrayList<String> cores = nomeCor(simbolo);
    	double tamCollect = cores.size(); 
    	int tamCollectInt = cores.size();
    	
    	//###PREENCHENDO O VETOR DE INFORMAÇÕES AO USUARIO COM OS VALORES APURADOS NESTA CLASSE
    	//Vetor que retornará as informações solicitadas pelo usuario
    	String[] informacoesUsuario = new String[(1 + tamCollectInt)*2];
    	//Preenchendo o vetor com o percentual total ("nome do simbolo" e "percentual total")
    	int r = 2;
    	String nomeCor;
    	int totalPixelsNome = 0;
    	double percentPixDiscrim = 0;
    	
    	informacoesUsuario[0] = simbolo;
    	informacoesUsuario[1] = percentPixString;
    	
    	for(int k = 0; k < cores.size(); k++) {
	    	for(int i = 0; i < imagem.getAltura(); i++) {
	    		for(int j = 0; j < imagem.getLargura(); j++) {
	    			if(cores.get(k).equals(imagem.getPixel(i,j).getNome())) {
	    				totalPixelsNome++; 
	    				
	    			}
	    		}
	    	}
	    	percentPixDiscrim = (double)totalPixelsNome/tamImagem;
	    	informacoesUsuario[r] = cores.get(k);
	    	informacoesUsuario[r + 1] = Double.toString(percentPixDiscrim);	    	
	    	r = r + 2;
	    	totalPixelsNome = 0;
    	} 
    	return informacoesUsuario;    	    	    
    }
 
    public ArrayList<String> nomeCor(String simbolo) throws Exception{
    	CorDAOSQL corSQL = new CorDAOSQL();
    	Collection<Cor> nomesCores = corSQL.findBySimbolo(simbolo);
    	ArrayList<String> nomeCoresII = new ArrayList<String>();
    	for(Cor c : nomesCores) {
    		nomeCoresII.add(c.getNome());
    	}
    	return nomeCoresII;
    } 
    
}
