/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceGrafica;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author Eduardo Cavalari Valenca 11234381
 */

/**
 *
 * Classe Criada para apresentar tela de fim de Jogo
 */
public class FimDeJogo extends Application  {
   /*
    Default Constructor
    @param Fase  Level em que o jogador esta
    @param Pontuacao  Pontuacao do Jogador
    @param EE   true se EE foi ativado
    */ 
    FimDeJogo(int Pontuacao,int Fase, boolean ee){
        this.Pontuacao=Pontuacao;
        this.Fase=Fase;
        ApertouMenu=false;
        MouseX=-1;
        MouseY=-1;
        EasterEgg = ee;      
    }
    
    
    private Stage theStage; //Janela
    
    private Integer Pontuacao; //Pontuacao do Jogador
    private Integer Fase; //Fase atingida
    
    private  boolean EasterEgg; //true Caso EE, tenha sido ativado
    
    private boolean ApertouMenu; //Caso o jogador Aperte o Menu
    
    private double MouseX; //Posicoes do Mouse
    private double MouseY;
    
    AudioClip  Click = new AudioClip(this.getClass().getResource("Click.wav").toExternalForm()); //Barulho Clique
    
    @Override
     //@Throws Exception Caso de Erro com janela
    public void start(Stage primaryStage) throws Exception {
        
        Menu menu = new Menu(); //Seta janela para voltar para o menu
        menu.setStage(theStage);
        menu.Teste=1; //Seta vriavel do menu
        
        
        Group root = new Group();
        Scene theScene = new Scene( root ); //Seta Cena
        theStage.setScene( theScene );

        Canvas canvas = new Canvas( theStage.getWidth(), theStage.getHeight()); 
        root.getChildren().add(canvas);
 
        GraphicsContext gc = canvas.getGraphicsContext2D(); //Desenho na Tela
        
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 1366, 768);
     
        Image ImgDerrota = new Image("Imagens/Imagem Derrota.png");
        Image BotaoVoltarP = new Image( "Imagens/BotaoVoltarPequeno.png" ); //Carrega Imagens
        Image BotaoVoltarG = new Image( "Imagens/BotaoVoltarGrande.png" );
        Image EE = new Image ("Imagens/KK Hokage.gif");
        
         new AnimationTimer()
        {
        @Override
        public void handle(long currentNanoTime)
            {          
                gc.drawImage(ImgDerrota, 0, 0);
                              
                gc.setFill( Color.WHITE );
                gc.setLineWidth(2);         //Desenha na tela
                Font theFont = Font.font( "Berlin Sans FB Demi", FontWeight.BOLD, 68 );
                gc.setFont( theFont );
                
                gc.fillText( Fase.toString() , 750, 310 );
                gc.fillText( Pontuacao.toString() , 630, 385); //Texto na tela
                
                Font theFont2 = Font.font( "Berlin Sans FB Demi", FontWeight.BOLD, 60 );
                gc.setFont( theFont2 );
                if(EasterEgg)
                {    
                    gc.fillText( "SIM!", 845, 480); //Escreve mensagem do EE
                    gc.drawImage(EE,950,500);
                }
                else
                    gc.fillText( "Não!", 845, 480); //Escreve mensagem do EE
                
                if(MouseX >150 && MouseX <360 && MouseY>590 && MouseY<660) //Desenha Botao
                    gc.drawImage( BotaoVoltarG, 140, 590 );
                else
                    gc.drawImage( BotaoVoltarP, 160, 600 );
                
                if(ApertouMenu)
                {
                    this.stop(); //Vai para Menu
                    menu.start(null);
                }    
            }    

        }.start();
         
        theScene.setOnMouseMoved(
        new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent e)
            {
                //Pega Dados do Mouse
                MouseX=e.getX();
                MouseY=e.getY();
                 
            }
        });
        
        theScene.setOnMouseClicked(
        new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent e)
            {
                //Caso clique com o mouse
                if(MouseX >150 && MouseX <360 && MouseY>590 && MouseY<660)
                {    
                    ApertouMenu=true;
                     Click.play(0.15);
                }    
            }
        }); 
         
         
         
         
    }
    
     /*
    Atualiza a pontuacao do jogador
    @param Points   Valor para que Pontuacao sera alterado
    */
    public void setPontuacao(int Points){
        
        Pontuacao=Points;
    }
    
    /*
    Muda Stage da classe para o sendo utlizado
    @param Antigo   Stage que nao esta sendo utlizado
    */
    public void setStage(Stage Antigo){
        
        theStage=Antigo;
    }
    
    /*
    Seta variavel caso o EE (Easter Egg) tenha sido ativado
    @param EE true caso EE tenha sido ativado, false caso contrario
    */ 
     public void setEE(boolean EE){
        
        this.EasterEgg=EE;
    }
    
    
}
