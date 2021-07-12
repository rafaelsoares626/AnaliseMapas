package avaliacaoMapas.conversor;

import avaliacaoMapas.model.entity.Cor;
import avaliacaoMapas.model.entity.CorCMYK;
import avaliacaoMapas.model.entity.CorRGB;
import avaliacaoMapas.model.entity.Imagem;
import avaliacaoMapas.model.entity.ImagemRGB;

public class Convert_CMYK_To_RGB implements ConversorCor {
    
    public Imagem getNovoMapa(int altura, int largura){
        ImagemRGB novoMapa = new ImagemRGB(altura, largura, new CorRGB(0, 0, 0));
        return novoMapa;
    }

    public Cor converter(Cor cor){        
    	CorRGB rgbConvertida = new CorRGB(0, 0, 0);
    	
    	if(cor instanceof CorCMYK){
	    	CorCMYK corConverter = (CorCMYK) cor;             
	        rgbConvertida =  cmykParaRgb(corConverter);        
        }
    	
        return rgbConvertida;
    }
    
    public CorRGB cmykParaRgb(CorCMYK pixel) {
        int red = (int)(255 * (1 - pixel.getCyan())/100 * (1 - pixel.getBlack())/100);
        int green = (int)(255 * (1 - pixel.getMagenta())/100 * (1- pixel.getBlack())/100);
        int blue = (int)(255 * (1 - pixel.getYellow())/100 * (1 - pixel.getBlack())/100);
            
        CorRGB resultado = new CorRGB(red, green, blue);
        return resultado;
    }
}