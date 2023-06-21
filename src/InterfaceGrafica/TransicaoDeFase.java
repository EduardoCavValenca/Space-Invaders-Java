/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceGrafica;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Eduardo Cavalari Valenca 11234381
 */

/**
 *
 * Classe Criada para Apresentar Cena depois de um jogador concluir uma fase
 */
public class TransicaoDeFase  extends Application {
    /*
    Default Constructor
    @param Level  Level em que o jogador esta
    @param Pontuacao  Pontuacao do Jogador
    @param Modo  1 para SinglePlayer, 2 para Multi
    @param EE   true se EE foi ativado
    */   
    TransicaoDeFase(int Level,Integer Pontuacao,int Modo,boolean EE){
        this.Level=Level;
        ModoJogo=Modo;
        this.Pontuacao=Pontuacao;
        this.EE=EE;
    }
    
    private Integer Pontuacao;
    private int Level;
    private int ModoJogo; //Variaveis do Jogo Atual para criar nova fase
    private boolean EE;
    
    private Stage theStage; //Janela da cena

    private long Time; //Tempo para mudar de janela
    
    @Override
    //@Throws Exception Caso de Erro com janela 
    public void start(Stage Stage_Ignorar) throws Exception {
        
        JogoSinglePlayer ProxFase1 = new JogoSinglePlayer(Level,Pontuacao,EE);
        ProxFase1.setStage(theStage); //Caso modo de jogo seja SinglePlayer
        
        JogoMultiPlayer ProxFase2 = new JogoMultiPlayer(Level,Pontuacao,EE);
        ProxFase2.setStage(theStage); //Caso modo de jogo seja MultiPlayer
        
        Group root = new Group();
        Scene theScene = new Scene( root ); //Seta cena
        theStage.setScene( theScene );

        Canvas canvas = new Canvas( theStage.getWidth(), theStage.getHeight() );
        root.getChildren().add(canvas);
 
        GraphicsContext gc = canvas.getGraphicsContext2D(); //Desenho na tela
        
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 1366, 768);
     
        Image Imgfase = new Image("Imagens/TransicaoDeFase.png");
        
         Time = System.currentTimeMillis(); //Pega tempo da execucao
        
         Time=Time+5000; //Tempo de tela é 5s
        
         
        //Faz Animacao da janela 
        new AnimationTimer()
        {
        @Override
        public void handle(long currentNanoTime)
            {
                
                gc.drawImage(Imgfase, 0, 0);

            if(ModoJogo==1) //Caso seja SinglePlayer
            {    
                if(System.currentTimeMillis()> Time) //Caso de o tempo para iniciar a janela
                {    
                    this.stop();

                try {
                    ProxFase1.start(null); //Inicia Jogo

                } catch (Exception ex) {
                    System.out.println("Erro ao Abrir Janela");
                }
 
                }
            }
            
            
            if(ModoJogo==2) //Caso seja MultiPlayer
            {    
                if(System.currentTimeMillis()> Time) //Caso de o tempo para iniciar a janela
                {    
                    this.stop();

                try {
                    ProxFase2.start(null); //Inicia Jogo

                } catch (Exception ex) {
                    System.out.println("Erro ao Abrir Janela");
                }
 
                }
            }
            
            }    

        }.start();
    }
    
    /*
    Muda Stage da classe para o sendo utlizado
    @param Antigo   Stage que nao esta sendo utlizado
    */
    public void setStage(Stage Antigo){
        
        theStage=Antigo;
    }
    
    /*
    Muda Pontuacao para determinado valor
    @param Points variavel para qual sera setado a pontuacao
    */
     public void setPontuacao(int Points){
        
        Pontuacao=Points;
    }
     
    /*
    Seta variavel caso o EE (Easter Egg) tenha sido ativado
    @param EE true caso EE tenha sido ativado, false caso contrario
    */ 
      public void setEE(boolean EE){
        
        this.EE=EE;
    }
    
}
