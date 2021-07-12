package avaliacaoMapas.conversor;

import avaliacaoMapas.model.entity.Cor;
import avaliacaoMapas.model.entity.Imagem;

public class Conversor {
    
    public Imagem converter(Imagem imagem, ConversorCor conversor){
        Imagem novo = conversor.getNovoMapa(imagem.getAltura(), imagem.getLargura());
        for(int iCont = 0; iCont < imagem.getAltura(); iCont++){
            for(int jCont = 0; jCont < imagem.getLargura(); jCont++){
                Cor cor = imagem.getPixel(iCont, jCont);
                Cor novaCor = conversor.converter(cor);
                novo.setPixel(iCont, jCont, novaCor);
            }
        }
    return novo;
    }
    
}