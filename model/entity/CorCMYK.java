package avaliacaoMapas.model.entity;

public class CorCMYK extends Cor{
	
    public static final CorCMYK BLACK = new CorCMYK(0,0,0,100);
    public static final CorCMYK WHITE = new CorCMYK(0,0,0,0);
        
    private int cyan;
    private int magenta;
    private int yellow;
    private int black;
    
    public CorCMYK(int cyan, int magenta, int yellow, int black){
        this.setCyan(cyan);
        this.setMagenta(magenta);
        this.setYellow(yellow);
        this.setBlack(black);
    };
    
    public CorCMYK (String id, String nome, String simbolo, int cyan, int magenta, int yellow, int black){
        super(id, nome, simbolo);
        this.setCyan(cyan);
        this.setMagenta(magenta);
        this.setYellow(yellow);
        this.setBlack(black);
    };    
    
    public int getCyan(){
        return this.cyan;
    };    

    public int getMagenta(){
        return this.magenta;
    };

    public int getYellow(){
        return this.yellow;
    };

    public int getBlack(){
        return this.black;
    };

    private void setCyan(int cyan){
        this.cyan = validarCorCMYK(cyan);
    };   

    private void setMagenta(int magenta){
        this.magenta = validarCorCMYK(magenta);
    };

    private void setYellow(int yellow){
        this.yellow = validarCorCMYK(yellow);
    };

    private void setBlack(int black){
        this.black = validarCorCMYK(black);
    };
    
    private int validarCorCMYK(int cor){
        if(cor > 100)
        	cor = 100;
        if(cor < 0)
        	cor = 0;
        return cor;
    };

    public int getLuminosidade() {
        return (int)(this.getBlack() * 255 / 100);
    }    
    //@Override
    public String toString() {
        return "#" + this.getId() + "#" + this.getNome() + "#" + this.getSimbolo() + "#" + this.getCyan() + "#" + this.getMagenta() + "#" + this.getYellow() + "#" + this.getBlack();//+ "#" + this.getLuminosidade()
    }
    
	public String toHex() {
		return "#" + this.intToHex(this.getCyan()) + 
					 this.intToHex(this.getMagenta()) + 
					 this.intToHex(this.getYellow()) +
					 this.intToHex(this.getBlack());
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