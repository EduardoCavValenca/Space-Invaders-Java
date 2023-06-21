/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceGrafica;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ElementosDoSistema.*;
import Engine.*;

/**
 *
 * @author Eduardo Cavalari Valenca 11234381
 */

/**
 *
 * Classe Criada para o Controlar mostrar o JogoModoMultiPlayer
 */
public class JogoMultiPlayer extends Application {
    
    /*
    Default Constructor
    @param Level  Level em que o jogador esta
    @param Pontuacao  Pontuacao do Jogador
    @param Modo  1 para SinglePlayer, 2 para Multi
    @param EE   true se EE foi ativado
    */ 
    public  JogoMultiPlayer(int level, Integer Pontuacao, boolean EE){
    
        Level=level;
        this.EE=EE;
        if(Pontuacao==0)
          this.Pontuacao = new Integer(0); 
        else
          this.Pontuacao=Pontuacao;  
    }
    
    private Stage theStage; //Janela
   
    
    private boolean EE=false;//booleano para controle do EE
    
    private  int Level;//Level da fase
    
    private Integer Pontuacao; //Pontuacao do Jogador
    
    
     long ControleFrameRate;
    
    @Override
     //@Throws Exception Caso de Erro com janela
    public void start(Stage Stage_ignorar) throws Exception {
       
        TransicaoDeFase ProxFase = new TransicaoDeFase(Level+1,Pontuacao,2,EE);
        ProxFase.setStage(theStage); //Janela caso o jogador Venca
        
        FimDeJogo Fim = new FimDeJogo(Pontuacao,Level,EE);
        Fim.setStage(theStage);//Janela Caso o Jogador perca
        
        Group root = new Group();
        Scene theScene = new Scene( root ); //Seta Cena
        theStage.setScene( theScene );

        Canvas canvas = new Canvas( theStage.getWidth(), theStage.getHeight() );
        root.getChildren().add(canvas);
 
        GraphicsContext gc = canvas.getGraphicsContext2D();//Desenho
        
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 1366, 768);
      
        ArrayList<String> input = new ArrayList<String>(); //Controla Input do jogador (Teclado)
        
        Image ImgCanhao = new Image("Imagens/canhao.png"); //Carrega Imagem Canhao
        
        Canhao c1 = new Canhao(ImgCanhao,200,670,80,57);//Cria Canhao do Jogador1
        Canhao c2 = new Canhao(ImgCanhao,800,670,80,57);//Cria Canhao do Jogador2
        
        
        EngineMultiPlayer engine = new EngineMultiPlayer(c1,c2,Level ,Pontuacao); //Cria Engine para modoDeJogo
        
        engine.IniciaFase(gc);//Seta variaveis iniciais para o jogo
        
        ControleFrameRate = System.currentTimeMillis();
        
        //Faz Animacao do jogo
        new AnimationTimer()
        {
        @Override
        public void handle(long currentNanoTime)
            {
           
            if(currentNanoTime - ControleFrameRate > 140*100000)
            {    
                ControleFrameRate = System.currentTimeMillis();
                int x = engine.RodaJogo(gc); //Retorna 0, caso nada ocorra
                if(x==1)                     //Retorna 1, caso jogador venca
                {                            //Retorna 2, caos jogador Perca
                    try {                    //Retorna 3, caos jogador AtiveEE
                        this.stop();         //Retorna >10, Pontuacao do Jogador
                        ProxFase.setPontuacao(Pontuacao);
                        ProxFase.setEE(EE);
                        ProxFase.start(null);//Vai para proxima Fase
                    } catch (Exception ex) {
                        System.out.println("Erro ao carregar a pagina");
                    }
                }

                if(x==2)
                {
                    try {
                        this.stop();
                        Fim.setPontuacao(Pontuacao);
                        Fim.setEE(EE);
                        Fim.start(null); //Vai para tela de fim
                    } catch (Exception ex) {
                        System.out.println("Erro ao carregar a pagina");
                    }
                }
                if(x==3) //Seta variavel booleana do EE para true
                    EE=true;


                if(x>10)
                    Pontuacao=x;     //Pontuacao do Jogador

                if(input.contains("A")) //Move o canhao para esquerda, A do teclado
                    if(c1.getX()>4)     //Caso esteja viavel ir para a esquerda
                        c1.MudaPosicaoX(-6);
                if(input.contains("D")) //Move o canhao para direita, D do teclado
                    if(c1.getX()<560)  //Caso esteja viavel ir para a direita
                        c1.MudaPosicaoX(6);
                if(input.contains("SPACE")) //Ativa Tiro, Espaco do teclado
                {     
                    c1.Atira(); //canhao atira
                    input.remove("SPACE");
                }     

                if(input.contains("LEFT"))  //Move o canhao para esquerda, setaEsquerda do teclado
                    if(c2.getX()>674)       //Caso esteja viavel ir para a esquerda
                        c2.MudaPosicaoX(-6);
                if(input.contains("RIGHT")) //Move o canhao para direita, setaDireita do teclado
                    if(c2.getX()<1267)      //Caso esteja viavel ir para a direita
                        c2.MudaPosicaoX(6);
                if(input.contains("ENTER")) //Ativa Tiro, Enter do teclado
                {    
                    c2.Atira();
                    input.remove("ENTER");
                }     
                
                ControleFrameRate =  currentNanoTime; 
            }
            
            }

        }.start();
        
        
        theScene.setOnKeyPressed(
        new EventHandler<KeyEvent>()
        {
            public void handle(KeyEvent e)
            {
                //Pega cliques do teclado
                String code = e.getCode().toString();
                if ( !input.contains(code) )
                    input.add( code ); 
                 
            }
        });
        
       
        
        theScene.setOnKeyReleased(
        new EventHandler<KeyEvent>()
        {
            public void handle(KeyEvent e)
            {
                //Elimina entradas do teclado
                String code = e.getCode().toString();
                input.remove( code ); 
                 
            }
        });
          
               
    }
    
    /*
    Muda Stage da classe para o sendo utlizado
    @param Antigo   Stage que nao esta sendo utlizado
    */
    public void setStage(Stage Antigo){
        
        theStage=Antigo;
    }
}