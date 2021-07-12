package avaliacaoMapas.auxiliar;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import avaliacaoMapas.model.entity.Cor;
import avaliacaoMapas.model.entity.CorRGB;
import avaliacaoMapas.model.entity.Imagem;
import avaliacaoMapas.model.entity.CorCMYK;
//import avaliacaoMapas.persistencia.CorDAOFile;
//import avaliacaoMapas.persistencia.CorDAOIF;

public class GeradorMatrizCores {	

	@SuppressWarnings("unused")
	private void montar() throws IOException {
		Random random = new Random();
		Imagem matrizCor = new Imagem(3,5,new CorRGB("OOO", "XXX", "XXX", 0, 0, 0));
		List<Cor> cores = new ArrayList<Cor>();
		cores.add(new CorRGB("V01", "VERMELHO", "EDIFICIOS", 255, 0, 0));
		cores.add(new CorCMYK("C01", "VERDE CLARO", "VEGETACAO", 100, 0, 0, 0));
		cores.add(new CorCMYK("M01", "ROXO", "ESTRADAS", 0, 100, 0, 0));
		cores.add(new CorRGB("G01", "VERDE ESCURO", "VEGETACAO", 0, 255, 0));
		cores.add(new CorCMYK("B01", "PRETO", "ESGOTO", 0, 0, 0, 100));		
		
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 5; j++) 
				matrizCor.setPixel(i, j, cores.get(random.nextInt(cores.size())));
						
		this.writeFileCor(matrizCor);
		System.out.println(matrizCor);
	}
	
	private void writeFileCor(Imagem mc) throws IOException {
		FileOutputStream out = new FileOutputStream("C:\\Users\\rafae\\Desktop\\repositorioImagens\\Imagem_1");
		ObjectOutputStream oos = new ObjectOutputStream(out);
		oos.writeObject(mc);
		oos.close();
		out.close();
	}  

	private void readFileCor() throws IOException, ClassNotFoundException{
		MatrizCor matrizConta;
		FileInputStream in = new FileInputStream("C:\\Users\\rafae\\Desktop\\repositorioImagens\\Imagem_1");
		ObjectInputStream ois = new ObjectInputStream(in);
		matrizConta = (MatrizCor) ois.readObject();
		ois.close();
		in.close();
		System.out.println(matrizConta);
	}	
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		//(new GeradorMatrizCores()).montar();
		//(new GeradorMatrizCores()).readFileCor();
	}
}
