/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ElementosDoSistema;

import javafx.scene.image.Image;

/**
 *
 * @author Eduardo Cavalari Valenca 11234381
 */
/**
 *
 * Classe criada para representar o EE (Easter Egg) do Jogo
 */
public class EasterEgg extends Sprite {
    /*
    Default Constructor
    @param image  Imagem para ser utilizada na estrutura
    @param positionX  PosicaoX da Estrutura
    @param positionY  PosicaoY da Estrutura
    @param width  Largura da Estrutura
    @param height  Altura da Estrutura
    @param TipoTiro  0 para canhao, 1 para Nave
    */
    public EasterEgg(Image image,double positionX,double positionY,double width,double height) {
        super(image,positionX,positionY,width,height);
    }
    
}
