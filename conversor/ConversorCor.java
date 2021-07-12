package avaliacaoMapas.conversor;

import avaliacaoMapas.model.entity.Cor;
import avaliacaoMapas.model.entity.Imagem;

public interface ConversorCor{
    public Imagem getNovoMapa(int altura, int largura);    
    public Cor converter(Cor cor);    
}