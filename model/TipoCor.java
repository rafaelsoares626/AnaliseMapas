package avaliacaoMapas.model;

public enum TipoCor {
    CorRGB(1), CorCMYK(2);
    
    private int codTipoCor;
    
    TipoCor(int codTipoCor){
        this.codTipoCor = codTipoCor;
    }  
    
    public int value(){
        return codTipoCor;
    }    
}
