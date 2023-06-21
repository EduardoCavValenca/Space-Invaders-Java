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
import javafx.stage.Stage;


/**
 *
 * @author Eduardo Cavalari Valenca 11234381
 */

/**
 *
 * Classe Criada para apresentar tela de Creditos
 */
public class Creditos extends Application {

    Creditos(){
        MouseX=-1;
        MouseY=-1;
        ApertouVoltar=false;
    }
    
    private Stage theStage; //Janela do Jogo
    
    private double MouseX; //Posicoes do mouse
    private double MouseY;
    
    private boolean ApertouVoltar; //Verdadeiro Caso clique no botao voltar
    
    
    AudioClip  Click = new AudioClip(this.getClass().getResource("Click.wav").toExternalForm()); //Som do clique
    
    @Override
    //@Throws Exception Caso de Erro com janela
    public void start(Stage Stage_ignorar) throws Exception {
       
        Menu RetornaMenu = new Menu();
        RetornaMenu.setStage(theStage); //Seta janela para Menu Caso queira retornar
        
        Group root = new Group();
        Scene theScene = new Scene( root ); //Seta Cena
        theStage.setScene( theScene );
        
      
        
        Canvas canvas = new Canvas( theStage.getWidth(), theStage.getHeight() );
        root.getChildren().add(canvas);
 
        GraphicsContext gc = canvas.getGraphicsContext2D(); //Desenho
        
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 1366, 768);
        
        
      
        Image TelaCreditos = new Image( "Imagens/FundoCreditos.png" );
        Image BotaoVoltarP = new Image( "Imagens/BotaoVoltarPequeno.png" ); //Imagens
        Image BotaoVoltarG = new Image( "Imagens/BotaoVoltarGrande.png" );
   
      
        new AnimationTimer()
        {
        @Override
        public void handle(long currentNanoTime)
            {
             
            //Desenha Tela    
            gc.drawImage( TelaCreditos, 0, -5 );
                      
            if(MouseX >150 && MouseX <360 && MouseY>590 && MouseY<660) //Desenha Botao
                gc.drawImage( BotaoVoltarG, 140, 590 );
            else
                gc.drawImage( BotaoVoltarP, 160, 600 );
                     
            try {
            if(ApertouVoltar){
                this.stop();
                RetornaMenu.Intro.setVolume(0);
                RetornaMenu.start(null); //Retorna pro Menu
                
            }
                
            } catch (Exception ex) {
                System.out.println("Erro Abertura de janela");
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
                    ApertouVoltar = true;
                     Click.play(0.15);
                 }       
                 
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
