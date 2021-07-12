package avaliacaoMapas.model.entity;

public class Imagem extends Object implements java.io.Serializable{
    
    public Cor[][] pixel;
    
	public Imagem(int altura, int largura, Cor pixel) {        
        this.pixel = new Cor[altura][largura];
        for(int i = 0; i < this.pixel.length; i++){
            for(int j = 0; j < this.pixel[i].length; j++){
                this.pixel[i][j] = pixel; 
            }
        }
	}    
	
    public int getAltura() {
        return this.pixel.length; 
    }    
    
    public int getLargura() {
        return this.pixel[0].length; 
    }    
    
    public Cor getPixel(int altura, int largura){
        return this.pixel[altura][largura];
   }

	public void setPixel(int altura, int largura, Cor pixel) {
		this.pixel[altura][largura] = pixel;		
	}; 

    
    //public abstract Cor getPixel(int altura, int largura);

    //public abstract void setPixel(int altura, int largura, Cor pixel);
    
    //public abstract String toHexa();

    //@Override
    public String toString(){
        
        String s = "";
        for(int i = 0; i < this.getAltura(); i++){
            for(int j = 0; j < this.getLargura(); j++){
                if(j == 0){
                    s = s + "\n" + this.getPixel(i, j).toString();
                }else{
                    s = s + this.getPixel(i, j).toString() + "|";
                }
            }
        }
        return s;
    }
    

}