package ElementosDoSistema;

import java.util.*;
import javafx.scene.image.Image;
/**
 *
 * @author Eduardo Cavalari Valenca 11234381
 */

/**
 *
 * Classe Criada para Representar as bases do jogo
 * que podem ser atingidas pelo jogador ou pelas naves inimigas
 */
public class Base extends Sprite {

     /*
    Default Constructor
    @param image  Imagem para ser utilizada na estrutura
    @param positionX  PosicaoX da Estrutura
    @param positionY  PosicaoY da Estrutura
    @param width  Largura da Estrutura
    @param height  Altura da Estrutura
    @param TipoTiro  0 para canhao, 1 para Nave
    */
   public Base(Image image,double positionX,double positionY,double width,double height) {
        super(image,positionX,positionY,width,height);
        ResistenciaRestante=14;
    }

    /**
     Indica a "Vida" da barreira, quantos tiros ela aguenta 
     */
    private int ResistenciaRestante;
    
    /**
     Altera A ResistenciaRestante da barreira
     @param R Novo valor de Resistencia
     */
    public void setResistencia(int R) {
      
       ResistenciaRestante=R;
    }
 
    /**
     Retorna a ResistenciaRestante da barreira
     @return Quanto de Resistencia sobra da barreira
     */
    public int getResistencia() {
        // TODO implement here
        return ResistenciaRestante;
    }

    
   

}