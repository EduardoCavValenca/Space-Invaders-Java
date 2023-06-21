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
 * Classe Criada para apresentar o Menu coms botoes interagiveis para inicar novas janelas
 */

public class Menu extends Application {
    
    /*
    Default Constructor
    */
    public Menu(){
        MouseX=-1; 
        
        ApertouSinglePlayer = false;
        ApertouMultiPlayer = false; //Variaveis booleanas para botoes
        ApertouCreditos = false;
        
        //Cria janela
        theStage = new Stage();
        theStage.setTitle( "Space Invaders - 11234381" );
        theStage.setHeight(768);
        theStage.setWidth(1366);
        theStage.getIcons().add(new Image( "Imagens/Icone.png"));
        Intro.setVolume(0.2);
        Intro.setCycleCount(10);          
    }
    
    private double MouseX; //Posicao X do mouse
    private double MouseY; //Posicao Y do mouse
    
    public int Teste=0; //Variavel para saber se veio da janela creditos
    
    private Stage theStage; //Janela
    
    private boolean ApertouSinglePlayer;
    private boolean ApertouMultiPlayer; //Variaveis de botoes 
    private boolean ApertouCreditos;
    
    private boolean Music= true; //Caso musica esteja tocando
    
    public AudioClip Intro= new AudioClip(this.getClass().getResource("IntroMusic.mp3").toExternalForm()); //Musica
    
    
    
    @Override
    //@Throws Exception Caso de Erro com janela 
    public void start(Stage Stage_ignorar)  {
        if(Teste==1) //Caso Musica ja esteja tocando 
            Intro.stop();
      
        Creditos Cred = new Creditos(); //Prepara janela
        Cred.setStage(theStage);
     
        ComandoSingle JogoSingle = new ComandoSingle(); //Prepara janela
        JogoSingle.setStage(theStage);
        
        ComandoMulti JogoMult = new ComandoMulti(); //Prepara janela
        JogoMult.setStage(theStage);

     
        Group root = new Group();
        Scene theScene = new Scene( root ); //Gera nova cena
        theStage.setScene( theScene );
        
        Canvas canvas = new Canvas( theStage.getWidth(), theStage.getHeight()); 
        root.getChildren().add(canvas);
 
        GraphicsContext gc = canvas.getGraphicsContext2D(); //Desenha na tela
        
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 1366, 768);
        
    
        Image TelaMenu = new Image( "Imagens/FundoJogoComFundo.png" );
        Image BotaoSingleP = new Image( "Imagens/BotaoSinglePlayerPequeno.png" );
        Image BotaoSingleG = new Image( "Imagens/BotaoSinglePlayerGrande.png" );
        Image BotaoMultP = new Image( "Imagens/BotaoMultiPlayerPequeno.png" );
        Image BotaoMultG = new Image( "Imagens/BotaoMultiPlayerGrande.png" ); //Seta imagens
        Image BotaoCreditosP = new Image( "Imagens/BotaoCreditosPequeno.png" );
        Image BotaoCreditosG = new Image( "Imagens/BotaoCreditosGrande.png" );
        Image MusicOnP = new Image( "Imagens/MusicOn.png" );
        Image MusicoffP = new Image( "Imagens/MusicOff.png" );
        Image MusicOnG = new Image( "Imagens/MusicOnGrande.png" );
        Image MusicoffG = new Image( "Imagens/MusicOffGrande.png" );
          
        
        AudioClip  Click = new AudioClip(this.getClass().getResource("Click.wav").toExternalForm());            
        
        Intro.play(); //Inicia Musica
      
        
        theScene.setOnMouseMoved( //Verifica variaveis do mouse
        new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent e)
            {
                MouseX=e.getX(); //Pega a posicao do mouse
                MouseY=e.getY();
                 
            }
        });
        
        theScene.setOnMouseClicked( //Caso haja clique do mouse
        new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent e)
            {
                
                if(MouseX >300 && MouseX <680 && MouseY> 340 && MouseY<430) //Caso o clique esteja na regiao de um botao
                {    
                    ApertouSinglePlayer = true;
                    Click.play(0.15);
               
                }    
                    
                if(MouseX >700 && MouseX <1080 && MouseY> 340 && MouseY<430) //Caso o clique esteja na regiao de um botao
                {    
                    ApertouMultiPlayer = true;
                    Click.play(0.15);
                }    
                    
                if(MouseX >535 && MouseX <800 && MouseY>490 && MouseY<580) //Caso o clique esteja na regiao de um botao
                {    
                    ApertouCreditos = true;
                    Click.play(0.15);
                } 
                
                 if(Music) 
                {    
                    if(MouseX >1150 && MouseX <1350 && MouseY> 0 && MouseY<200) //Caso o clique esteja na regiao de um botao
                    {    
                       Music=false;
                       Click.play(0.15);
                       Intro.stop();
                    }   
                }

                else
                {
                   if(MouseX >1150 && MouseX <1350 && MouseY> 0 && MouseY<200) //Caso o clique esteja na regiao de um botao
                   {    
                       //Inicia musica
                       Music=true;
                       Click.play(0.15);
                       if(Intro.getVolume()==0)
                           Intro.setVolume(0.2);
                       Intro.play();
                   }    
                } 
                 
            }
        });
        
      
        
        //Faz Animacao da janela
        new AnimationTimer()
        {
        @Override
        public void handle(long currentNanoTime)
            {
                
                
            gc.drawImage( TelaMenu, 0, 0 ); //tela de fundo
            
            if(MouseX >300 && MouseX <680 && MouseY> 340 && MouseY<430) //Desenha Imagem dependendo do mouse
                gc.drawImage( BotaoSingleG, 285, 335 );
            else
                gc.drawImage( BotaoSingleP, 320, 340 );
            
            if(MouseX >700 && MouseX <1080 && MouseY> 340 && MouseY<430) //Desenha Imagem dependendo do mouse
                gc.drawImage( BotaoMultG, 685, 335 );
            else
                gc.drawImage( BotaoMultP, 705, 340 );
            
            if(MouseX >535 && MouseX <800 && MouseY> 490 && MouseY<580) //Desenha Imagem dependendo do mouse
                gc.drawImage( BotaoCreditosG, 520, 490 );
            else
                gc.drawImage( BotaoCreditosP, 550, 490 );
            
            if(Music)
            {    
                if(MouseX >1150 && MouseX <1350 && MouseY> 0 && MouseY<200)  //Desenha Imagem dependendo do mouse
                   gc.drawImage( MusicOnG, 1170, 10 );
                else
                   gc.drawImage( MusicOnP, 1200, 30 );
            }
            
            else
            {
               if(MouseX >1150 && MouseX <1350 && MouseY> 0 && MouseY<200) //Desenha Imagem dependendo do mouse 
                   gc.drawImage( MusicoffG, 1170, 21 );
                else
                   gc.drawImage( MusicoffP, 1200, 41 ); 
            }    
            
            try {
            if(ApertouCreditos)
            {
                this.stop();
                Cred.start(null); //Inicia tela de creditos
                
            }    
            
            if(ApertouMultiPlayer)
            {
                this.stop();
                JogoMult.start(null); //Inicia Jogo Multiplayer
            }    
                    
            if(ApertouSinglePlayer)
            {
                this.stop();
                JogoSingle.start(null);//Inicia Jogo SinglePlayer
            }    
             
            } catch (Exception ex) {
                System.out.println("Erro Abertura de janela");
            }
            
           }
        }.start();
         
         
        if(!(theStage.isShowing())) //Inicia Stage 
            theStage.show();
     
    }
     
    
    /*
    Muda Stage da classe para o sendo utlizado
    @param Antigo   Stage que nao esta sendo utlizado
    */
    public void setStage(Stage Antigo){
        
        theStage=Antigo;
    }

   
     
}
    

