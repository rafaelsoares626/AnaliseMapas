package avaliacaoMapas.model.entity;

public class CorRGB extends Cor{    
    
    public static final CorRGB RED = new CorRGB(255,0,0);
    public static final CorRGB GREEN = new CorRGB(0,255,0);
    public static final CorRGB BLUE = new CorRGB(0,0,255);
    public static final CorRGB BLACK = new CorRGB(0,0,0);
    public static final CorRGB WHITE = new CorRGB(255,255,255);
        
    private int red;
    private int green;
    private int blue;

    public CorRGB(int red, int green, int blue){
        this.setRed(red);
        this.setGreen(green);
        this.setBlue(blue);
    };

    public CorRGB (String id, String nome, String simbolo, int red, int green, int blue){
        super(id, nome, simbolo);
    	this.setRed(red);
        this.setGreen(green);
        this.setBlue(blue);
    };    

    private void setRed(int red){
        this.red = validarCorRGB(red);
    };    

    private void setGreen(int green){
        this.green = validarCorRGB(green);
    };

    private void setBlue(int blue){
        this.blue = validarCorRGB(blue);
    };    
    
	private int validarCorRGB(int cor){
	    if(cor > 255)
	    	cor = 255;
	    if(cor < 0)
	    	cor = 0;
	    return cor;
	};

    public int getRed(){
        return this.red;
    };    

    public int getGreen(){
        return this.green;
    };

    public int getBlue(){
        return this.blue;
    };

    public int getLuminosidade() {
        return (int)(this.getRed() * 0.3 +this.getGreen() * 0.59 + this.getBlue() * 0.11);
    }
    
    public String toString() {
        return "#" + this.getId() + "#" + this.getNome() + "#" + this.getSimbolo() +  "#" + this.getRed() + "#" + this.getGreen() + "#" + this.getBlue();// + "#" + this.getLuminosidade()
    } 

	public String toHex() {
		return "#" + this.intToHex(this.getRed()) + 
					 this.intToHex(this.getGreen()) + 
					 this.intToHex(this.getBlue());
	}
	
	private String intToHex(int valor) {
		String res = "";
		int dig1 = valor / 16;
		int dig2 = valor % 16;
		res = res + intToCharHex(dig1) + intToCharHex(dig2); 
		return res;
	}
	
	private char intToCharHex(int valor) {
		char charHex;
		if(valor >= 0 && valor <= 9)
			charHex = (char) ('0' + valor);
		else
			charHex = (char) ('A' + valor - 10);
		return charHex;
	}
}