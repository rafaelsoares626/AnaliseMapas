package avaliacaoMapas.model.entity;

public class ImagemRGB extends Imagem {    
    
	public ImagemRGB(int altura, int largura, CorRGB pixel) {        
        super(altura, largura, pixel);
		/*this.pixel = new Cor[altura][largura];
        for(int i = 0; i < this.pixel.length; i++){
            for(int j = 0; j < this.pixel[i].length; j++){
                this.pixel[i][j] = pixel; 
            }
        }*/
	}

    public Cor getPixel(int altura, int largura){
         return this.pixel[altura][largura];
    }

	public void setPixel(int altura, int largura, Cor pixel) {
		this.pixel[altura][largura] = (CorRGB) pixel;		
	}; 

    public String toHexa(){
        
        String s = "";
        for(int i = 0; i < this.getAltura(); i++){
            for(int j = 0; j < this.getLargura(); j++){
                if(j == 0){
                    s = s + "\n" + this.getPixel(i, j).toHex();
                }else{
                    s = s + this.getPixel(i, j).toHex();
                }
            }
        }
        return s;
    }
}    
    