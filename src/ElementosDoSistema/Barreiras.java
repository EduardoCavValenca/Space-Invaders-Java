/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ElementosDoSistema;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author Eduardo Cavalari Valenca 11234381
 */

/**
 *
 * Classe Criada para Representar as barreiras do jogo
 * que podem ser atingidas pelo jogador ou pelas naves inimigas
 */
public class Barreiras {
    
    public Barreiras(){
        int i;
        Base B =null;
        for(i=0;i<4;i++)
        {    
            B = new Base(ImgBase14,100+i*350,500,130,95);
            Bases.add(B);
        }    
    }
    
    
    //Conteiner contendo todas as bases
    public ArrayList<Base> Bases = new ArrayList<Base>();
 
    //SetaImagens
    Image ImgBase1 = new Image("Imagens/Barreira1.png");
    Image ImgBase2 = new Image("Imagens/Barreira2.png");
    Image ImgBase3 = new Image("Imagens/Barreira3.png");
    Image ImgBase4 = new Image("Imagens/Barreira4.png");
    Image ImgBase5 = new Image("Imagens/Barreira5.png");
    Image ImgBase6 = new Image("Imagens/Barreira6.png");
    Image ImgBase7 = new Image("Imagens/Barreira7.png");
    Image ImgBase8 = new Image("Imagens/Barreira8.png");
    Image ImgBase9 = new Image("Imagens/Barreira9.png");
    Image ImgBase10 = new Image("Imagens/Barreira10.png");
    Image ImgBase11 = new Image("Imagens/Barreira11.png");
    Image ImgBase12 = new Image("Imagens/Barreira12.png");
    Image ImgBase13 = new Image("Imagens/Barreira13.png");
    Image ImgBase14 = new Image("Imagens/Barreira14.png");
    
    
    /**
     Renderiza todas as bases na tela
     @param gc Onde sera Renderizado
     */
    public void render(GraphicsContext gc)
    {
        for(Base base : Bases) {
            base.render(gc);
        }
    }
    
    /**
     Caso a base tome um tiro, troca imagem ou é eliminada
     @param B Base que leveu o tiro
     */
    public void RecebeDano(Base B)
    {
        int Res =B.getResistencia();
        int Img = Res -1;
        
        if(Res!=1)
        {    
            //Troca a imagem
            if(Img==13) B.setImage(ImgBase13);
            if(Img==12) B.setImage(ImgBase12);
            if(Img==11) B.setImage(ImgBase11);
            if(Img==10) B.setImage(ImgBase10);
            if(Img==9) B.setImage(ImgBase9);
            if(Img==8) B.setImage(ImgBase8);
            if(Img==7) B.setImage(ImgBase7);
            if(Img==6) B.setImage(ImgBase6);
            if(Img==5) B.setImage(ImgBase5);
            if(Img==4) B.setImage(ImgBase4);
            if(Img==3) B.setImage(ImgBase3);
            if(Img==2) B.setImage(ImgBase2);
            if(Img==1) B.setImage(ImgBase1);
           
            B.setResistencia(Res-1);
        }    
        else
           Bases.remove(B); //Caso acabou a resistencia
    }
    
    /**
     Verifica se um tiro acertou a Base
     @param s Tiro a ser analisado
     @return Retorna true se houve colisao
     */
    public boolean VerificaTiro(Sprite s)
    {
     int i;
       for (i=0;i<Bases.size();i++) { 		      
          if(Bases.get(i).intersects(s))
          {    
              //Caso acerte
              RecebeDano(Bases.get(i));
              return true;
          }    
      }
     return false;
       
    }
    
    /**
     Verifica se alguma Nave alcancou a barreira
     @param s Nave a ser Analisada
     */
    public void VerificaColisaoNave(Sprite s)
    {
       int i;
            for (i=0;i<Bases.size();i++) { 		      
               if(Bases.get(i).intersects(s))
                   Bases.remove(Bases.get(i));
            }
          
          
    }
    
    
    
    
   
            
            
    
}
