package avaliacaoMapas.model;

import avaliacaoMapas.model.entity.CorRGB;
import avaliacaoMapas.model.entity.Imagem;

public class Atlas {
    
    public Imagem[] atlas;

    public Atlas(){
        this.atlas = new Imagem[0];
    }    
        
    public void addImagem(Imagem imagem){        
        Imagem[] novo = new Imagem[this.atlas.length + 1];
        for(int i = 0; i < this.atlas.length; i++){
            novo[i] = this.atlas[i];
        }
        novo[novo.length - 1] = imagem;
        this.atlas = novo;        
    } 
    
    public int getAtlasTam(){
        return this.atlas.length;
    }
    
    public String toString(){        
        String s = "";
        for(int i = 0; i < this.getAtlasTam(); i++){
            s = s + this.atlas[i] + "\n";          
        }
        return s;
    }
    
    private Imagem[] getSimilares(double minimo, double maximo, double pctMinimo, Imagem[] atlasAux){
        
        int qtdImagensSimilares = 0;
        int qtdPixelsSimilares = 0;
        
        for(int k = 0; k < atlasAux.length; k++){
            qtdPixelsSimilares = 0;
            for(int i = 0; i < atlasAux[k].getAltura(); i++){
                for(int j = 0; j < atlasAux[k].getLargura(); j++){
                    if(atlasAux[k].getPixel(i, j).getLuminosidade() >= minimo &&  atlasAux[k].getPixel(i, j).getLuminosidade() <= maximo){
                        qtdPixelsSimilares++;
                    }
                }
            }
            double areaImagem = (double)(atlasAux[k].getAltura() * atlasAux[k].getLargura());
            if(qtdPixelsSimilares/areaImagem >= pctMinimo){
                qtdImagensSimilares++;
            }
        }
        
        int r = -1;
        Imagem[] soSimilaresRGB = new Imagem[qtdImagensSimilares];
        for(int k = 0; k < atlasAux.length; k++){
            qtdPixelsSimilares = 0;
            for(int i = 0; i < atlasAux[k].getAltura(); i++){
                for(int j = 0; j < atlasAux[k].getLargura(); j++){
                    if(atlasAux[k].getPixel(i, j).getLuminosidade() >= minimo &&  atlasAux[k].getPixel(i, j).getLuminosidade() <= maximo){
                        qtdPixelsSimilares++;
                    }
                }
            }
            double areaImagem = (double)(atlasAux[k].getAltura() * atlasAux[k].getLargura());
            if(qtdPixelsSimilares/areaImagem >= pctMinimo){
                r++;
                soSimilaresRGB[r] = atlasAux[k];
            }
        }
        return soSimilaresRGB; //soSimilaresRGB;
    }
    
    public Imagem[] getImagemPorLuminosidade(int red, int green, int blue, double pctMinimo, double limiarSimilaridade){
        //Filtrando as imagens RGB
        Imagem[] atlasAux = this.atlas;
        //atlasRGB = this.soRGB();
        
        //Construindo a cor e calculando a luminosidade
        CorRGB corRGB_parametro = new CorRGB(red, green, blue);
        int luminosidade = corRGB_parametro.getLuminosidade();
        
        //Valor mínimo do pixel
        double minimo = minLimiar(luminosidade, limiarSimilaridade);
        minimo = tratarMinimo(minimo);
        //Valor máximo do pixel
        double maximo = maxLimiar(luminosidade, limiarSimilaridade);
        maximo = tratarMaximo(maximo);
        
        Imagem[] atlasImagensSimilares = this.getSimilares(minimo, maximo, pctMinimo, atlasAux);
        
        return atlasImagensSimilares;
    }

    public double tratarMinimo(double minimo){
        if(minimo < 0){
            minimo = 0;
        }
        return minimo;
    }
    
    public double tratarMaximo(double maximo){
        if(maximo > 255){
            maximo = 255;
        }
        return maximo;
    }
    
    public double minLimiar(int luminosidade, double limiarSimilaridade){
        double min = luminosidade - (luminosidade * limiarSimilaridade);
        return min;
    }
    
    public double maxLimiar(int luminosidade, double limiarSimilaridade){
        double max = luminosidade + (luminosidade * limiarSimilaridade);
        return max;
    }    
}

