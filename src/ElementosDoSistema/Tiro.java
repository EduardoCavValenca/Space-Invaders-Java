package ElementosDoSistema;

import java.util.*;
import javafx.scene.image.Image;

/**
 *
 * @author Eduardo Cavalari Valenca 11234381
 */

/**
 *
 * Classe criada para representar um Tiro do jogador os das naves inimigas 
 */
public class Tiro extends Sprite {
     /*
    Default Constructor
    @param image  Imagem para ser utilizada na estrutura
    @param positionX  PosicaoX da Estrutura
    @param positionY  PosicaoY da Estrutura
    @param width  Largura da Estrutura
    @param height  Altura da Estrutura
    @param TipoTiro  0 para canhao, 1 para Nave
    */ 
    public Tiro(Image image,double positionX,double positionY,double width,double height, int TipoTiro) {
        super(image,positionX,positionY,width,height);
        this.TipoTiro = TipoTiro;
    }
    
    //0 caso seja Tiro do Canhao
    //1 caso seja Tiro das Naves
    private int TipoTiro;
    
    //Contador para Animacao de explosao do tiro
    private int Explodindo =0;
    
    //Contador para Animacao de matando canhao
    private int MatandoCanhao=0;
    
    //Caso tenha acertado Canhao
    private boolean EliminouCanhou=false;
    
    
    /*
    Realiza a movimentacao do Tiro baseado no Tipo
    @param Y  Valor da alteracao
    */
    public void avanca(double Y){
        if(TipoTiro==0)
        {    
            this.MudaPosicaoY(-Y);
          
        }    
        else
        {    
            this.MudaPosicaoY(Y);
        }    
    }
    
    /*
    Aumenta o Contador de explosao
    */
    public boolean FazExplosao(){
        Explodindo++;
        if(Explodindo==10)
            return true;
        return false;
    }
    
    /*
    Altera Eliminou para true
    */
    public void setEliminouCanhou(){
        EliminouCanhou=true;
    }
    /*
    @return true, se Eliminou canhao é true, false se o contraario
    */
    public boolean getEliminouCanhou(){
        return EliminouCanhou;
    }
    /*
    Realizando o contador de matando canhao
    @return true, caso chegue no valor desejado, false o contrario
    */
    public boolean MataCanhao(){
         MatandoCanhao++;
        if(MatandoCanhao==1)
            return true;
        return false;
    }
            
            

}