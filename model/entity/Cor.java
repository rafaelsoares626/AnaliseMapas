package avaliacaoMapas.model.entity;

public abstract class Cor extends Object implements java.io.Serializable{ 
	
	private String id;
	private String nome;
	private String simbolo;

    public Cor(){
    	super();
    };
    
    public Cor(String id, String nome, String simbolo){
    	super();
		this.id = id;
		this.nome = nome;
		this.simbolo = simbolo;
    };

	public abstract int getLuminosidade();
	public abstract String toHex();

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSimbolo() {
		return simbolo;
	}
	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}