package ElementosDoSistema;

import java.util.*;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

/**
 *
 * @author Eduardo Cavalari Valenca 11234381
 */
/**
 *
 * Classe Criada para Representar o Jogador "Canhao" que matara as Naves
 */
public class Canhao extends Sprite {

     /*
    Default Constructor
    @param image  Imagem para ser utilizada na estrutura
    @param positionX  PosicaoX da Estrutura
    @param positionY  PosicaoY da Estrutura
    @param width  Largura da Estrutura
    @param height  Altura da Estrutura
    @param TipoTiro  0 para canhao, 1 para Nave
    */
   public Canhao(Image image,double positionX,double positionY,double width,double height) {
        super(image,positionX,positionY,width,height);
    }

   
   //Bolean que representa caso esteja morto
    private boolean AnimacaoMorte = false;
    
    //Imagem do tiro do canhao
    private Image ImgTiroCanhao = new Image("Imagens/TiroAliado.png");
   
    //Tiro do Canhao
    public Tiro TiroCanhao=null;
   
    //Caso tenha  mais que 0 vidas true
    private boolean Vivo=true;
    
    //Vidas Inciais
    private int Vidas =3;
    
    //Som tiro
    private   AudioClip  shoot = new AudioClip(this.getClass().getResource("shoot.wav").toExternalForm());
    
    //Som Morte
    private   AudioClip  morte = new AudioClip(this.getClass().getResource("explosion.wav").toExternalForm());

    /*
    Checa Colisao com Nave Inimiga
    @param S  Estrutura para checar Colisao
    */
    public void VerificaColisaoNave(Sprite s)
    {
        if(this.intersects(s))
        {    
          Vivo=false;    
        }
    }
     
    /*
    @return true se canhao esta vivo, false o contrario
    */
    public boolean getVivo(){
        
        return Vivo;
    } 
    
    /*
    Canhao Atira caso, nao tenha Tiro Ativo
    */
    public void Atira()
    {
        if(TiroCanhao==null)
        {
          shoot.play(0.05);
          //Cria Tiro
          TiroCanhao = new Tiro (ImgTiroCanhao,this.positionX+40,this.positionY-50,10,37,0);
          
        }    
    }
    /*
    Verifica se o Tiro do canhao colidiu com alguma estrutura
    @param S  Estrutura para checar Colisao
    */
    public boolean VerificaTiro(Sprite s)
    {
        
        if(this.intersects(s))
        {    
            morte.play(0.05);
            if(Vidas<=0)
                  Vivo=false;
              Vidas--;
              return true;
        }    
     return false;
       
    }
    
    
    /*
    @return Quantida de vidas restantes
    */
    public int getVidas(){
        return Vidas;
    }
    
    /*
    Atualiza a quantidade de vidas do canhao
    @param Vidas Nova quantidade de Vidas do Canhao
    */
    public void setVidas(int Vidas){
        this.Vidas=Vidas;
    }
    
    /*
    Verifica se canhao esta com mais que zero vidas
    */
    public void TestaVidas(){
         if(Vidas<0){
             Vivo=false;
             
         }
            
    }
    
}