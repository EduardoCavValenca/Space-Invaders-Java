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

/**
 *
 * @author Eduardo Cavalari Valenca 11234381
 */

/**
 *
 * Classe Criada para apresentar tela de Comandos
 */
public class ComandoSingle extends Application{

    private long Time; //Tempo de aparicao
    private Stage theStage; //janela do jogo
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        JogoSinglePlayer JogoSingle = new JogoSinglePlayer(1,0,false);
        JogoSingle.setStage(theStage); //Seta janela para NovoJogoSinglePlayer
        
        Group root = new Group();
        Scene theScene = new Scene( root ); //Seta Cena
        theStage.setScene( theScene );

        Canvas canvas = new Canvas( theStage.getWidth(), theStage.getHeight() );
        root.getChildren().add(canvas);
 
        GraphicsContext gc = canvas.getGraphicsContext2D();//Desenho
        
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 1366, 768);
      
        ArrayList<String> input = new ArrayList<String>(); //Pega input do usuario
        
        Image ImgFundo = new Image("Imagens/ComandoSingle.png");
        
         Time = System.currentTimeMillis(); //Pega tempo de execucao
        
         Time=Time+5000; //Tempo de fim de janela 5s
        
        //Faz animacao
        new AnimationTimer()
        {
        @Override
        public void handle(long currentNanoTime)
            {
                //Desenha Fundo
                gc.drawImage( ImgFundo, 0, 0 ); 
          
                if(input.contains("SPACE")|| System.currentTimeMillis()> Time) //Caso passe do tempo
                {    
                    this.stop();
                    try {
                        JogoSingle.start(null); //Comeca jogo
                    } catch (Exception ex) {
                        System.out.println("Erro ao abrir a pagina");
                    }
                    
                }     
          
           }

        }.start();
         
         
       theScene.setOnKeyPressed(
        new EventHandler<KeyEvent>()
        {
            public void handle(KeyEvent e)
            {
               //Adicona dados do teclado
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
               //Elimina dados do teclado
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
