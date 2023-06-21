/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ElementosDoSistema;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author Eduardo Cavalari Valenca 11234381
 */
/**
 *
 * Classe Criada para Representar o Jogador "Canhao" que matara as Naves
 */
public class Sprite
{
     /*
    Default Constructor
    @param image  Imagem para ser utilizada na estrutura
    @param positionX  PosicaoX da Estrutura
    @param positionY  PosicaoY da Estrutura
    @param width  Largura da Estrutura
    @param height  Altura da Estrutura
    */
    Sprite(Image image,double positionX,double positionY,double width,double height){
        this.image= image;
        this.positionX= positionX;
        this.positionY= positionY; //Seta Variaveis
        this.width= width;
        this.height= height;
        
    }
    
    //Imagem da estrutura
    protected Image image;
    
    //PosicaoX da estrutura
    protected double positionX;
    
    //PosicaoY da estrutura
    protected double positionY;  
    
    //Largura da estrutura
    protected double width;
    
    //Altura da Estrutura
    protected double height;
 
    /*
    Altera a posicaoX da estrutura
    @param Alteracao  Valor que sera Alterado
    */
    public void MudaPosicaoX(double Alteracao)
    {
        positionX += Alteracao;
        
    }
    
    /*
    Altera a posicaoY da estrutura
    @param Alteracao  Valor que sera Alterado
    */
    public void MudaPosicaoY(double Alteracao)
    {
        positionY += Alteracao;
        
    }
 
    /*
    Desenha a estrutura na tela
    @param gc Onde sera feito a renderizacao
    */
    public void render(GraphicsContext gc)
    {
        gc.drawImage( image, positionX, positionY );
    }
 
    /*
    Cria retangulo envolta da Imagem
    */
    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(positionX,positionY,width,height);
    }
    /*
    Verifica se alguma outra estrutura colidiu com essa
    @param S  Estrutura para checar Colisao
    */
    public boolean intersects(Sprite s)
    {
        return s.getBoundary().intersects( this.getBoundary() );
    }
    
    /*
    Troca a imagem da estrutura
    @param image  Nova Imagem
    */
    public void setImage(Image image)
    {
        this.image=image;
    }
    
    /*
    Retorna a PosicaoX da Estrutura
    @return PosicaoX
    */
    public double getX()
    {
        return positionX;
    }
     
    /*
    Retorna a PosicaoY da Estrutura
    @return PosicaoX
    */
    public double getY()
    {
        return positionY;
    }
    
    /*
    Altera a PosicaoX da Estrutura
    @param x  NovaPosicao
    */
    public void setX(double x)
    {
       positionX=x;
    }
     
    /*
    Altera a PosicaoY da Estrutura
    @param y  NovaPosicao
    */
    public void setY(double y)
    {
        positionY=y;
    }
}