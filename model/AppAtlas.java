package avaliacaoMapas.model;

import avaliacaoMapas.conversor.Conversor;
import avaliacaoMapas.conversor.Convert_CMYK_To_RGB;
import avaliacaoMapas.model.entity.CorCMYK;
import avaliacaoMapas.model.entity.CorRGB;
import avaliacaoMapas.model.entity.Imagem;
import avaliacaoMapas.model.entity.ImagemCMYK;
import avaliacaoMapas.model.entity.ImagemRGB;

public class AppAtlas {
    private Atlas atlas1;
    
    public AppAtlas(){
        this.atlas1 = new Atlas();
    }   
    
    public void testeAtlas(){
        System.out.println(" ");        
        System.out.println("################## Teste Atlas ###################"); 
        System.out.println("__________________________________________________");

        
        
        
        CorRGB cor_RGB1 = new CorRGB(-37, 150, 390);
        ImagemRGB imagemRGB1 = new ImagemRGB(3, 5, cor_RGB1);
        imagemRGB1.setPixel(0, 0, cor_RGB1);
        imagemRGB1.setPixel(0, 1, cor_RGB1);
        imagemRGB1.setPixel(0, 2, cor_RGB1);
        imagemRGB1.setPixel(0, 3, cor_RGB1); 
        imagemRGB1.setPixel(0, 4, cor_RGB1);         
        imagemRGB1.setPixel(1, 0, cor_RGB1);
        imagemRGB1.setPixel(1, 1, cor_RGB1);
        imagemRGB1.setPixel(1, 2, cor_RGB1);
        imagemRGB1.setPixel(1, 3, cor_RGB1); 
        imagemRGB1.setPixel(1, 4, cor_RGB1);
        imagemRGB1.setPixel(2, 0, cor_RGB1);
        imagemRGB1.setPixel(2, 1, cor_RGB1);
        imagemRGB1.setPixel(2, 2, cor_RGB1);
        imagemRGB1.setPixel(2, 3, cor_RGB1); 
        imagemRGB1.setPixel(2, 4, cor_RGB1);
        this.atlas1.addImagem(imagemRGB1);
        
        Imagem imagemRGB2 = new ImagemRGB(4, 5, new CorRGB(0, 0, 0));
        this.atlas1.addImagem(imagemRGB2);
            
        ImagemCMYK imagemCMYK1 = new ImagemCMYK(3, 3, new CorCMYK(0, 0, 0, 0));
        this.atlas1.addImagem(imagemCMYK1);
        
        ImagemRGB imagemRGB3 = new ImagemRGB(5, 5, new CorRGB(0, 0, 0));
        this.atlas1.addImagem(imagemRGB3);
        
        ImagemRGB imagemRGB4 = new ImagemRGB(6, 5, new CorRGB(0, 0, 0));
        this.atlas1.addImagem(imagemRGB4);
        
        ImagemRGB imagemRGB5 = new ImagemRGB(7, 5, new CorRGB(0, 0, 0));
        this.atlas1.addImagem(imagemRGB5);

        ImagemRGB imagemRGB6 = new ImagemRGB(2, 2, new CorRGB(0, 0, 0));
        this.atlas1.addImagem(imagemRGB6);
        
        ImagemCMYK imagemCMYK2 = new ImagemCMYK(4, 3, new CorCMYK(0, 0, 0, 0));
        this.atlas1.addImagem(imagemCMYK2);
        
        ImagemCMYK imagemCMYK4 = new ImagemCMYK(3, 5, new CorCMYK(0, 0, 0, 0));  
        CorCMYK cor_CMYK5 = new CorCMYK(-37, 150, 70, 100);
        CorCMYK cor_CMYK4 = new CorCMYK(-37, 150, 70, 46);
        imagemCMYK4.setPixel(0, 0, cor_CMYK4);
        imagemCMYK4.setPixel(0, 1, cor_CMYK4);
        imagemCMYK4.setPixel(0, 2, cor_CMYK4);
        imagemCMYK4.setPixel(0, 3, cor_CMYK4); 
        imagemCMYK4.setPixel(0, 4, cor_CMYK4);         
        imagemCMYK4.setPixel(1, 0, cor_CMYK4);
        imagemCMYK4.setPixel(1, 1, cor_CMYK4);
        imagemCMYK4.setPixel(1, 2, cor_CMYK5);
        imagemCMYK4.setPixel(1, 3, cor_CMYK5); 
        imagemCMYK4.setPixel(1, 4, cor_CMYK5);
        imagemCMYK4.setPixel(2, 0, cor_CMYK5);
        imagemCMYK4.setPixel(2, 1, cor_CMYK5);
        imagemCMYK4.setPixel(2, 2, cor_CMYK5);
        imagemCMYK4.setPixel(2, 3, cor_CMYK5); 
        imagemCMYK4.setPixel(2, 4, cor_CMYK4);
        this.atlas1.addImagem(imagemCMYK4);        
        
        int result =  this.atlas1.getAtlasTam(); 
        System.out.println("Existem " + result + " mapas armazenados no banco do atlas.");
        System.out.println("__________________________________________________");
        System.out.println(" ");
        
        System.out.println(this.atlas1.toString());
        
        System.out.println(" "); 
        System.out.println("################ Fim Teste Atlas #################");
        System.out.println(" ");  
    }    
    
    
    public void testeSimilaridade(){    
        System.out.println(" ");        
        System.out.println("############### Teste Similaridade ###############"); 
        System.out.println("__________________________________________________");
        
        int cor1 = 116;
        int cor2 = 116;
        int cor3 = 116;
        double pctMinimo = 0.5;
        double limiar = 0.05;
        int corLumi = new CorRGB(cor1, cor2, cor3).getLuminosidade();
        
        Imagem[] resultado2 = this.atlas1.getImagemPorLuminosidade(cor1, cor2, cor3, pctMinimo, limiar);
        System.out.println("Pixels RGB informados: " + cor1 + ", " + cor2 + " e " + cor3 + " | " + "Luminosidade RGB: " + corLumi);
        System.out.println("\nPercentual mínimo: " + pctMinimo + " | " + "Limiar: " + limiar);
        System.out.println("__________________________________________________");
        System.out.println("As imagens que contêm luminosidades similares à informada pelo usuário são:");
        System.out.println(" ");
        for(int i = 0; i < resultado2.length; i++){            
            System.out.println(resultado2[i]);
        }
        if(resultado2.length == 0){
            System.out.println("Não existem imagens com luminosidade similiar à informada pelo usuário!");
        }
        System.out.println(" ");
        System.out.println("############# Fim Teste Similaridade #############");
        System.out.println(" "); 
    }
    
    public void testeInterface(){    
        System.out.println(" ");        
        System.out.println("################# Teste Interface ################"); 
        System.out.println("__________________________________________________");
        
        ImagemCMYK imagemCMYKInterface = new ImagemCMYK(3, 5, new CorCMYK(0, 0, 0, 0));  
        CorCMYK cor_CMYK5 = new CorCMYK(-37, 150, 70, 100);
        CorCMYK cor_CMYK4 = new CorCMYK(-37, 150, 70, 46);
        imagemCMYKInterface.setPixel(0, 0, cor_CMYK4);
        imagemCMYKInterface.setPixel(0, 1, cor_CMYK4);
        imagemCMYKInterface.setPixel(0, 2, cor_CMYK4);
        imagemCMYKInterface.setPixel(0, 3, cor_CMYK4); 
        imagemCMYKInterface.setPixel(0, 4, cor_CMYK4);         
        imagemCMYKInterface.setPixel(1, 0, cor_CMYK4);
        imagemCMYKInterface.setPixel(1, 1, cor_CMYK4);
        imagemCMYKInterface.setPixel(1, 2, cor_CMYK5);
        imagemCMYKInterface.setPixel(1, 3, cor_CMYK5); 
        imagemCMYKInterface.setPixel(1, 4, cor_CMYK5);
        imagemCMYKInterface.setPixel(2, 0, cor_CMYK5);
        imagemCMYKInterface.setPixel(2, 1, cor_CMYK5);
        imagemCMYKInterface.setPixel(2, 2, cor_CMYK5);
        imagemCMYKInterface.setPixel(2, 3, cor_CMYK5); 
        imagemCMYKInterface.setPixel(2, 4, cor_CMYK5);        
        
        System.out.println(" ");
        Imagem imagemInterface = new Conversor().converter(imagemCMYKInterface, new Convert_CMYK_To_RGB());
        System.out.println("Imagem Original (CMYK)");   
        System.out.println(" "); 
        System.out.println(imagemCMYKInterface.toString());
        
        System.out.println(" "); 
        System.out.println("Imagem Convertida (RGB)"); 
        System.out.println(" "); 
        System.out.println(imagemInterface.toString());
        
        System.out.println(" ");
        System.out.println("############### Fim Teste Interface ##############");
    }
    
    public static void main(String[] args) {
        
        AppAtlas app = new AppAtlas();       
        app.testeAtlas();
        app.testeSimilaridade();
        app.testeInterface();
        
    }
}