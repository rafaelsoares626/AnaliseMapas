package avaliacaoMapas.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import avaliacaoMapas.auxiliar.MatrizCor;
import avaliacaoMapas.conversor.Conversor;
import avaliacaoMapas.conversor.Convert_CMYK_To_RGB;
import avaliacaoMapas.model.entity.Cor;
import avaliacaoMapas.model.entity.CorCMYK;
import avaliacaoMapas.model.entity.CorRGB;
import avaliacaoMapas.model.entity.Imagem;
import avaliacaoMapas.model.entity.ImagemCMYK;
import avaliacaoMapas.model.entity.ImagemRGB;
//import avaliacaoMapas.persistencia.CorDAOFile;
import avaliacaoMapas.persistencia.CorDAOSQL;
import avaliacaoMapas.persistencia.ImagemDAOFile;



public class AppAvMapas implements AvMapasLogicaIF {
	
		
	public String[] analisarImagens(String uri, String simbolo) throws Exception{
    	//Imagem original
    	ImagemDAOFile imagemDAO = new ImagemDAOFile();
    	Imagem imagem = imagemDAO.readFileCor(uri);
    	double tamImagem = imagem.getAltura()*imagem.getLargura();
    	
    	//Vetor com os pixels correspondentes ao simbolo informado
    	ImagemDAOFile imagemSimbolDAO = new ImagemDAOFile();    	
    	String[] imSimbol = imagemSimbolDAO.findBySimboloII(uri, simbolo);
    	double tamImSimbol = imSimbol.length/3;    	
    	
    	//Busca simbolo no banco    	
    	CorDAOSQL corDAO = new CorDAOSQL();    	
    	Collection<Cor> cores = corDAO.findBySimbolo(simbolo);
    	double tamCollect = cores.size(); 
    	int tamCollectInt = cores.size();
    	
    	//Apurando o percentual total do simbolo da imagem, bem como convertendo o resultado para String
    	double percentPix = (double) (tamImSimbol/tamImagem);
    	String percentPixString = Double.toString(percentPix);
    	
    	//Vetor que retornará as informações solicitadas pelo usuario
    	String[] informacoesUsuario = new String[(1 + tamCollectInt)*2];
    	//String[] informacoesUsuario = new String[2];
    	
    	//Preenchendo as informações "nome do simbolo" e "percentual total"
    	informacoesUsuario[0] = simbolo;
    	informacoesUsuario[1] = percentPixString;
    	/*int j = 2;
    	for(int i = 2; i < informacoesUsuario.length; i = i + 2) {
    		if(imagem.getPixel(i, j).getId().equals(cores.id())) {
	    		informacoesUsuario[i] = imSimbol[j];
	    		informacoesUsuario[i+1] = imSimbol[j+1];
    		}
    	}*/
    	//TENTAR DISCRIMINAR OS PERCENTUAIS
    	
    	return informacoesUsuario;    	
    	    
    }
 


	public static void main(String[] args) throws Exception {		
		System.out.println("BANCO");
		CorDAOSQL app1 = new CorDAOSQL();
		System.out.println(app1.findAll());	
		System.out.println("");
		String uri = "C:\\Users\\rafae\\Desktop\\repositorioImagens\\Imagem_1";
		System.out.println("ARQUIVOS");			
		System.out.println((new ImagemDAOFile()).readFileCor(uri));
		System.out.println("");
		System.out.println("COMBOBOX");
		CorDAOSQL app2 = new CorDAOSQL();
		System.out.println(app2.listarSimbolos());	
		System.out.println("");		
		
		System.out.println("SO ID");
		Imagem imagemteste = (new ImagemDAOFile()).readFileCor(uri);
		//AppAvMapas app3 = new AppAvMapas();
		//(new AppAvMapas()).getIdCor(imagemteste);
		//System.out.println(idCores);
		//System.out.println(app3.getIdCor(imagemteste).toString());
		
		System.out.println("SO SIMBOL");
		//System.out.println((new ImagemDAOFile()).findBySimboloII(uri, "ESTRADAS"));
		System.out.println("");
		System.out.println((new CorDAOSQL()).findBySimbolo("VEGETACAO"));
		
		String[] str = ((new ImagemDAOFile()).findBySimboloII(uri, "VEGETACAO"));
		for(int i = 0; i < str.length; i++) {
			System.out.println(str[i]);
		}

		
	}
	
}

