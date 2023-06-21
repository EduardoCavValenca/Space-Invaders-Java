package ElementosDoSistema;

import java.util.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

/**
 *
 * @author Eduardo Cavalari Valenca 11234381
 */

/**
 *
 * Classe Criada para Conseguir controlar os inimigos uniformimente
 */
public class Inimigos {

    /**
     * Cria Matriz 5 por 11 de Naves
     * Default constructor
     * @param Level Level em que o Jogador Esta
     */
    public Inimigos(int Level) {
        int i,j;
        
        
       
        double PosX=-50;
        double PosY=10+40*(Level-1);
     
        Nave N = null;
        
        for(i=0;i<5;i++)
        {    
            PosY+=40;
            PosX=-50;
         
            for(j=0;j<11;j++)
            {  
                
                PosX+=70;
                
                //Cria Naves dependendo de sua linha
                if(i==0)
                    N = new Nave(ImgNave1Foto1,PosX,PosY,40,30,1);                    
                if(i==1 || i==2)
                    N = new Nave(ImgNave2Foto1,PosX,PosY,40,30,2);
                if(i==3 || i==4)
                    N = new Nave(ImgNave3Foto1,PosX,PosY,40,30,3);
                
                Naves.add(N);
               
            }
        }
        
    }
    
    
    //Pega Imagens  
    private    Image ImgNave1Foto1 = new Image("Imagens/Nave1Foto1.png");
    private    Image ImgNave1Foto2 = new Image("Imagens/Nave1Foto2.png");
    private    Image ImgNave2Foto1 = new Image("Imagens/Nave2Foto1.png");
    private    Image ImgNave2Foto2 = new Image("Imagens/Nave2Foto2.png");
    private    Image ImgNave3Foto1 = new Image("Imagens/Nave3Foto1.png");
    private    Image ImgNave3Foto2 = new Image("Imagens/Nave3Foto2.png");
    private    Image ImgExplosao= new Image("Imagens/Explosao.png");
    private    Image TiroInimigo= new Image("Imagens/GifTiro.gif");
    
    //Pega Audios
    private   AudioClip  morte = new AudioClip(this.getClass().getResource("invaderkilled.wav").toExternalForm()); 
    private   AudioClip  movimenta = new AudioClip(this.getClass().getResource("fastinvader1.wav").toExternalForm());
    private   AudioClip  movimenta2 = new AudioClip(this.getClass().getResource("fastinvader2.wav").toExternalForm());
    
    
    //Animacao de movimento
    private int Contador =1;
    
    /**
     Conteiner contendo todas as naves Vivas
     */
    public ArrayList<Tiro> TirosNaves = new ArrayList<Tiro>();
    
    /**
     Indica se os inimigos estao se movendo para direita ou esquerda
     */
    public boolean movDireita = true;
    
    /**
     Cria Matriz de Naves na Heap
     */
    public ArrayList<Nave> Naves = new ArrayList<Nave>();

    /*
    Movimenta todos os aliens Vivos e remove do array os eliminados
    */
    public void Avanca(){
        
        int i;
        //Remove Eliminados
        for(i=0;i<Naves.size();i++)
        {
            if(Naves.get(i).getAnimacaoMorte())
                Naves.remove(Naves.get(i));
        }
        
        double LimiteEsquerda=20;
        double LimiteDireita=1280;
        boolean Descer=false;
        
        //Realiza animacao do movimento
        if(Contador==2)
        {   
           movimenta2.play(0.05);  
           Contador=1;
        }   
        else
        {    
           movimenta.play(0.05); 
           Contador=2; 
        } 
        
        //Verifica se alguma nave esta no limite da tela
        for (Nave nave : Naves) {
        
            if(movDireita)
            {    
                if(nave.positionX==LimiteDireita)
                {    
                    Descer =true;
                    break;
                }    
            }
            else
                if(nave.positionX==LimiteEsquerda)
                {    
                    Descer =true;
                    break;
                }    
        }
        
       // Caso necessite descer
        if(Descer)
        {
            if(movDireita)
            {
                 for (Nave nave : Naves) {
                     nave.MudaPosicaoY(40);
                     movDireita=false;
                     this.AlteraImagem(nave); //Animacao
                 }
            }
            else
            {
                 for (Nave nave : Naves) {
                     nave.MudaPosicaoY(40);
                     movDireita=true;                    
                     this.AlteraImagem(nave); //Animacao
                 }
            }    
        }
        else
        {
            //Vai para direita
            if(movDireita)
            {
                 for (Nave nave : Naves) {
                    nave.MudaPosicaoX(35);
                    this.AlteraImagem(nave);//Animacao 
                 }
            }
            else
            {
                //Vai para esquerda
                 for (Nave nave : Naves) {
                    nave.MudaPosicaoX(-35);
                    this.AlteraImagem(nave); //Animacao
                 }
            } 
        }    
   
    }
 
    /*
    Altera imagem das naves para realizar a animacao
    @param nave Nave que tera sua imagem trocada
    */
    public void AlteraImagem(Nave nave){
        if(Contador==1)
        {    
            if(nave.getTipo()==1) nave.setImage(ImgNave1Foto2);
            if(nave.getTipo()==2) nave.setImage(ImgNave2Foto2);
            if(nave.getTipo()==3) nave.setImage(ImgNave3Foto2);
        }
        else
        {
            if(nave.getTipo()==1) nave.setImage(ImgNave1Foto1);
            if(nave.getTipo()==2) nave.setImage(ImgNave2Foto1);
            if(nave.getTipo()==3) nave.setImage(ImgNave3Foto1);
        }   
    }
   
    /*
    Faz um tiro aleatorio partir de umas das naves vivas
    */
    public void Atira() {
        Random rand = new Random();
        int i = rand.nextInt(Naves.size());
        double TiroX = Naves.get(i).positionX;
        double TiroY = Naves.get(i).positionY;
        Tiro T = new Tiro(TiroInimigo,TiroX+20,TiroY+50,4,37,1);
        TirosNaves.add(T);
    }
    
   /*
    Renderiza as naves
    @param gc Onde sera renderizado
    */ 
   public void render(GraphicsContext gc)
    {
       for (Nave nave : Naves) { 		      
           nave.render(gc);
      }
    }
   
   /*
    Troca imagem de Nave para explosao e marca como eliminada
    @param N Nave que recebeu tiro
    @param Pontuacao Pontuacao da eliminacao
    */ 
   public int RecebeTiro(Nave N,int Pontuacao)
    {
        N.setAnimacaoMorte();
        N.setImage(ImgExplosao);
        morte.play(0.05);
        if(N.getTipo()==1) return 30;
        if(N.getTipo()==2) return 20;
        if(N.getTipo()==3) return 10;
        return 0;
    }
   
   /*
    Verifica se Tiro do Canhao colidiu com alguma nave
    @param s Tiro do canhao
    @param Pontuacao Pontuacao do jogasdor
    */ 
   public int VerificaTiro(Sprite s, Integer Pontuacao)
    {
       for (Nave nave : Naves) { 		      
          if(nave.intersects(s))
          {    
              
              return RecebeTiro(nave,Pontuacao);
          }    
      }
      return 0; 
    }

}