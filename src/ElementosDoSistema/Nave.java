package ElementosDoSistema;

import javafx.scene.image.Image;

/**
 *
 * @author Eduardo Cavalari Valenca 11234381
 */
/**
 *
 * Classe criada para representar os invasores no jogo
 */
public class Nave extends Sprite {

     /*
    Default Constructor
    @param image  Imagem para ser utilizada na estrutura
    @param positionX  PosicaoX da Estrutura
    @param positionY  PosicaoY da Estrutura
    @param width  Largura da Estrutura
    @param height  Altura da Estrutura
    @param TipoTiro  0 para canhao, 1 para Nave
    */
   public Nave(Image image,double positionX,double positionY,double width,double height, int TipoNave) {
        super(image,positionX,positionY,width,height);
        this.TipoNave=TipoNave;
      
    }

    //Tipo de Nave Baseado em sua altura na matriz
    private int TipoNave;
   
    //Caso tenha sido eliminada vira true
    private boolean AnimacaoMorte = false;
      
    /*
    Pega o tipo da Nave
    @return Tipo da Nave
    */
    public int getTipo(){
        return TipoNave;
    }
    
    /*
    Seta AnimacaoMorte para true
    */
    public void setAnimacaoMorte() {
        AnimacaoMorte=true;
    }
    
    /*
    Pega o tipo da Nave
    @return true se AnimacaoMorte for true, false o contrario
    */
    public boolean getAnimacaoMorte() {
        return AnimacaoMorte;
    }

  

  

}