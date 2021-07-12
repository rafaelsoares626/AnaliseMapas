package avaliacaoMapas.persistencia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import avaliacaoMapas.model.entity.Cor;
import avaliacaoMapas.model.entity.CorRGB;
import avaliacaoMapas.model.entity.Imagem;

public class ImagemDAOFile implements ImagemDAOIF {
	 	/*public void writeFileCor(Imagem imagem) throws IOException {
	    	
			FileOutputStream out = new FileOutputStream("C:\\Users\\rafae\\Desktop\\repositorioImagens\\imagem_2");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(imagem);
			oos.close();
			out.close();
	    }*/
	    
	 	public Imagem readFileCor(String uri)throws IOException, ClassNotFoundException  {
	    	
	    	FileInputStream in = new FileInputStream(uri);//caminhoArquivoMapa+".bin"
	    	ObjectInputStream ois = new ObjectInputStream(in);
			Imagem imagem = (Imagem) ois.readObject();
			ois.close();
			return imagem;
	    }
	 	
		  public Collection<Cor> findBySimbolo(String uri, String simbolo) throws Exception{//
		    	FileInputStream in = new FileInputStream(uri);//caminhoArquivoMapa+".bin"
		    	ObjectInputStream ois = new ObjectInputStream(in);
				Imagem imagem = (Imagem) ois.readObject();
				Set<Cor> cores = new HashSet<Cor>();
				//int cont = 0;
				for(int i = 0; i < imagem.getAltura(); i++) {
					for(int j = 0; j < imagem.getLargura(); j++) {
						if(imagem.getPixel(i, j).getSimbolo().equals(simbolo)) {
							cores.add(imagem.getPixel(i, j));
							//cont++;
						}
					} 
				}
		        return cores;		           
		  }   
		  
		  public String[] findBySimboloII(String uri, String simbolo) throws Exception{//
		    	FileInputStream in = new FileInputStream(uri);//caminhoArquivoMapa+".bin"
		    	ObjectInputStream ois = new ObjectInputStream(in);
				Imagem imagem = (Imagem) ois.readObject();
				
				int cont = 0;
				
				for(int i = 0; i < imagem.getAltura(); i++) {
					for(int j = 0; j < imagem.getLargura(); j++) {
						if(imagem.getPixel(i, j).getSimbolo().equals(simbolo)) {
							//cores[cont] = imagem.getPixel(i, j).getSimbolo();
							cont++;
						}
					} 
				}
				
				String[] cores = new String[cont*3];
				int k = -3;
				for(int i = 0; i < imagem.getAltura(); i++) {
					for(int j = 0; j < imagem.getLargura(); j++) {
						if(imagem.getPixel(i, j).getSimbolo().equals(simbolo)) {
							k = k + 3;
							cores[k] = imagem.getPixel(i, j).getSimbolo();
							cores[k+1] = imagem.getPixel(i, j).getId();
							cores[k+2] = imagem.getPixel(i, j).getNome();
						}
					} 
				}
		        return cores;		           
		  }  
}
