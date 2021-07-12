package avaliacaoMapas.auxiliar;

import avaliacaoMapas.model.entity.Cor;

@SuppressWarnings("serial")
public class MatrizCor implements java.io.Serializable{
	private Cor[][] cores = new Cor[3][5];
	
	public void setCor(int i, int j, Cor cor) {
		this.cores[i][j] = cor;
	}
	
	public String toString() {
		String rep = "";
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 5; j++)
				rep+= this.cores[i][j].toString() + " | ";
			rep+= "\n";
		}	
		return rep;
	}
}
