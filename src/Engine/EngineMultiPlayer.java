/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import ElementosDoSistema.*;
/**
 *
 * @author Eduardo Cavalari Valenca 11234381
 */

/**
 *
 * Classe Criada para Funcionamento do JogoMultiPlayer
 */
public class EngineMultiPlayer {
    
     /*
    Default Constructor
    @param Level  Level em que o jogador esta
    @param Pontuacao  Pontuacao do Jogador
    @param c1  Canhao do Jogador 1
    @param c2  Canhao do Jogador 2
    */ 
    public EngineMultiPlayer(Canhao c1,Canhao c2, int Level, Integer Pontuacao){
        this.C1 = c1;//Aponta para o canhao1 criado no Jogo
        this.C2 = c2;//Aponta para o canhao2 criado no Jogo
        this.Level=Level; //Pega o Level do Jogo
        Enemys = new Inimigos(Level); //Cria Inimigos em determinada linha baseado no Level
        LimiteTiro = 60 - (10*Level); //Variavel para controle da velocidade do tiro
        this.Pontuacao = Pontuacao; //Pega pontuacao do Jogador
        VelocidadeTiroInimigo=6+Level;  //Seta velocidade do tiro inimigo baseado no Level
        C1.setVidas(6); //Inicia vidas para 6 
        C2.setVidas(6);
    }
    
    Barreiras Bases = new Barreiras(); //Cria linha de barreiras
    Inimigos Enemys; //Variavel das naves inimigas
    Canhao C1; //Canhao1 do Jogador
    Canhao C2; //Canhao2 do Jogador
    
    private Integer Level; //Level do Jogador
    private Integer Pontuacao = new Integer(0);//Pontuacao do Jogador
    private boolean EE;//Caso o EE (Easter Egg) seja ativado
    
    private int ContadorAnimacao=0; //Controla movimentacao dos aliens
    private int ContadorTiro=0; //Controla Tiro dos Aliens
    private int LimiteTiro; //Tempo para Tiro dos aliens
    private int VelocidadeTiroInimigo; //Tempo para movimentacao dos inimigos
    
    private int Velocidade1=65;
    private int Velocidade2=55;
    private int Velocidade3=45; //Velocidades de Jogo baseados nos inimigos restantes
    private int Velocidade4=25;
    private int VelocidadeExtrema=10;
    private int VelocidadeAtual=65;
    
    private int ContadorFim=0; //Animacaofinal de morte
    
    private boolean CanhaoEliminado1;//Caso jogador tome tiro
    private boolean CanhaoEliminado2;//Caso jogador tome tiro
    
    private int ContadorCanhaoEliminado;//Tempo de aguardo apos morrer
    
    private boolean EnviouPontuacao =false;//Controle se a Engine ja enviou 
                                            //Pontuacao de volta
       
    
    Image FundoEspaco = new Image( "Imagens/FundoEspaco.png" );
    Image Explosao = new Image( "Imagens/Explosao.png" );
    Image MorteNave = new Image( "Imagens/MorteNAVE.png" );
    Image MorteCanhao = new Image( "Imagens/canhaoMorte.png" ); //Setas Imagens
    Image ImgCanhao = new Image("Imagens/canhao.png");
    Image Kakashi = new Image("Imagens/KKshi.png");
    Image MiniCanhao = new Image("Imagens/MiniCanhao.png");
    Image Divisao = new Image("Imagens/Divisao.png");
    
    
    
    // Som do EE(Easter Egg)
    private   AudioClip  SomEE = new AudioClip(this.getClass().getResource("EE.mp3").toExternalForm());
    
     //Imagem do EE(Easter Egg)
    private EasterEgg KK = new EasterEgg(Kakashi,10,10,112,112);
    
    /*
    Realiza as preparacoes para iniciar o Jogo
    @param gc  Desenhar na tela
    */
    public void IniciaFase(GraphicsContext gc)
    {
        //Printa Imagens Iniciais
        gc.drawImage( FundoEspaco, 0, -5 );
        gc.drawImage(Divisao, 650, 625);
        Enemys.render(gc);
        C1.render(gc);
        C2.render(gc);
             
    }
    
    /*
    Realiza todas as interacoes do Jogo
    @param gc  Desenhar na tela
    @return  caso nada ocorra:0
            caso jogador venca: 1
            caos jogador Perca: 2
            caos jogador AtiveEE: 3
            caso retorne Pontuacao: Pontuacao 
    */
    public int RodaJogo(GraphicsContext gc){
        int i;
        
        //Caso tenha derrotado todos os inimigos
        //e ainda n enviado a pontuacao
        if(Enemys.Naves.isEmpty() && !EnviouPontuacao)
        {    
            EnviouPontuacao=true;
            return Pontuacao;
            
        }    
        
        //Caso tenha derrotado todos os inimigos
        //e ainda ja tenha enviado o resultado
        if(Enemys.Naves.isEmpty()&& EnviouPontuacao)
            return 1;
          
        //Caso Jogador Perca e ainda n tenha
        //enviado a Pontuacao
        if(!(C1.getVivo())&& !EnviouPontuacao )
        {    
            EnviouPontuacao=true;
            return Pontuacao;
        }    
        
        //Caso Jogador Perca e ainda e ja tenha
        //enviado a Pontuacao
        if(!(C1.getVivo())&& EnviouPontuacao )
        {    
            if(ContadorFim==60)
                return 2; //Retorna Derrota
            else ContadorFim++;
        }    
        
        //Pausa o Jogo por um tempo caso jogador leve um tiro
        if(CanhaoEliminado1 || CanhaoEliminado2)
        {    
            ContadorCanhaoEliminado++;
            if(ContadorCanhaoEliminado==60)
            {    
                if(CanhaoEliminado1)
                {    
                    CanhaoEliminado1=false;
                    C1.setImage(ImgCanhao);
                }
                if(CanhaoEliminado2)
                {    
                    CanhaoEliminado2=false;
                    C2.setImage(ImgCanhao);
                }  
                ContadorCanhaoEliminado=0;
            }    
        }    
        else
        {  
            //Roda Jogo
            
            //Desenha a tela
            
            gc.drawImage( FundoEspaco, 0, -5 );
            gc.drawImage(Divisao, 650, 625);
            
            gc.setFill( Color.WHITE );
            gc.setLineWidth(2);
            Font theFont=Font.font("Berlin Sans FB Demi", FontWeight.BOLD, 30 );
            gc.setFont( theFont );           
                       
            
            gc.fillText( "Level:" , 10, 25 );
            gc.fillText( Level.toString() , 100, 25 );
            gc.fillText( "Pontuação:" , 200, 25 );
            gc.fillText( Pontuacao.toString() , 370, 25 );
            gc.fillText( "Vidas:" , 450, 25 );
            gc.fillText( "Space Invaders Eng Comp Edition" , 900, 25 );

            //Desenha miniVidas
            if(C1.getVidas()>0)
                gc.drawImage(MiniCanhao, 550, 5);
            if(C1.getVidas()>1)
                gc.drawImage(MiniCanhao, 600, 5);
            if(C1.getVidas()>2)    
                gc.drawImage(MiniCanhao, 650, 5);
            if(C1.getVidas()>3)    
                gc.drawImage(MiniCanhao, 700, 5);
            if(C1.getVidas()>4)    
                gc.drawImage(MiniCanhao, 750, 5);
            if(C1.getVidas()>5)    
                gc.drawImage(MiniCanhao, 800, 5);
            
            //Desenha Inimigos canhao e bases
            Enemys.render(gc);
            C1.render(gc);
            C2.render(gc);
            Bases.render(gc);

            //Revela EE (Easter Egg)
            if(Level==1 && Enemys.Naves.size()<10 && !EE)
            {
                 gc.drawImage(Kakashi,10,10);
                 if(C1.TiroCanhao!=null)
                 {
                    if(KK.intersects(C1.TiroCanhao))
                    {    
                        SomEE.play(0.30);
                        C1.TiroCanhao=null;
                        EE=true;
                        return 3;
                    }
                 }    
            }    

            //Controlador de Velocidade
            if(Enemys.Naves.size()==45)
                VelocidadeAtual=Velocidade2;
            if(Enemys.Naves.size()==30)
                VelocidadeAtual=Velocidade3;
            if(Enemys.Naves.size()==15)
                VelocidadeAtual=Velocidade4;
            if(Enemys.Naves.size()==1)
                VelocidadeAtual=VelocidadeExtrema;

            //Caso ainda existam Bases
            if(!(Bases.Bases.isEmpty()))
            {    
                for (Nave nave : Enemys.Naves) { 
                     Bases.VerificaColisaoNave(nave);//Verifica Colisao Base/Naves
                }
            }    


            for (Nave nave : Enemys.Naves) { 
                C1.VerificaColisaoNave(nave);
                C2.VerificaColisaoNave(nave); //Verifica Colisao Canhao/Naves
                if(!(C2.getVivo()))
                    C1.setVidas(-1);
            }

            //Funcao que pega todos os tiros inimgos, movimenta, verifica colisao
            // e caso necessario remove o tiro do array
            for (i=0;i<Enemys.TirosNaves.size();i++) { 
                if(this.verificaTiroInimigo(Enemys.TirosNaves.get(i), gc))
                    Enemys.TirosNaves.remove(Enemys.TirosNaves.get(i));
            }

            ContadorTiro++;
            //Caso deu o intervalo para novo tiro
            if(ContadorTiro==LimiteTiro)
            {    
                Enemys.Atira();
                ContadorTiro=0;
            }
            
            //Verifica Tiro do canhao , movimenta, verifica colisao
            // e caso necessario coloca o tiro para null
            this.verificaTiroCanhao(C1, gc);
            this.verificaTiroCanhao(C2, gc);

           

            ContadorAnimacao++;
            //Caso deu o intervalo para movimentacao
            if(ContadorAnimacao==VelocidadeAtual)
            {    
                Enemys.Avanca();//Movimenta Inimigos
                ContadorAnimacao=0;
            }
            
         C1.TestaVidas();
        }
        return 0;
                
    }
    
    /*
    Verifica o Tiro do Canhao, pode movimenta-lo, ou colidi-lo com outras estruturas
    @param C   Tiro do Canhao que esta sendo Verificadp
    @param gc  Desenhar na tela
    */
    public void verificaTiroCanhao(Canhao C,GraphicsContext gc){
        
        int i;
        for(i=0;i<1;i++)
        {    
            if(C.TiroCanhao!=null)
            {    
                C.TiroCanhao.render(gc);//Desenha Tiro
                
                if(C.TiroCanhao.getY()<5)
                {
                   if(C.TiroCanhao.FazExplosao())  //Realiza a animacao de tiro
                   {                               //Explodido 
                       C.TiroCanhao=null; //Remove Tiro
                       break;
                   }
                   break;
                }
                
                if(C.TiroCanhao.getY()>5)
                    C.TiroCanhao.avanca(10);//Movimenta Tiro

                if(C.TiroCanhao.getY()<5)//Realiza a animacao do tiro explodindo
                {    
                    C.TiroCanhao.MudaPosicaoX(-20);
                    C.TiroCanhao.setImage(Explosao);
                    break;
                }    

                if(Bases.VerificaTiro(C.TiroCanhao)) //Verifica colisao Tiro/Bases
                {    
                    C.TiroCanhao=null;
                    break;
                }    
                
                //Verifica colisao Tiro/Inimigos, retorna pontos conseguidos
                int Ponts=Enemys.VerificaTiro(C.TiroCanhao,Pontuacao);
                
                if(Ponts>0)
                {    
                    Pontuacao+=Ponts;  //Adiciona Pontuacao
                    C.TiroCanhao=null;
                    break;
                }    
            }        
        }
        
        
    }
    
    /*
    Verifica o Tiro inimigo, pode movimenta-lo, ou colidi-lo com outras estruturas
    @param T   Tiro que esta sendo verificado
    @param gc  Desenhar na tela
    @return True se tiro colidiu. falso o contrario
    */ 
     public boolean verificaTiroInimigo(Tiro T,GraphicsContext gc){
        
        int i;
        
        for(i=0;i<1;i++)
        {    
                T.render(gc);//Desenha Tiro
                
                if(T.getY()>=700)
                {
                   if(T.FazExplosao())  //Realiza a animacao de tiro explosao
                   {    
                      return true;//Remove Tiro
                       
                   }
                   break;
                }
                
                if(T.getEliminouCanhou())
                {
                   if(T.MataCanhao())
                   {    
                      return true;
                       
                   }
                   break;
                }
                
                if(T.getY()<700)  //Movimenta Tiro
                    T.avanca(VelocidadeTiroInimigo);

                if(T.getY()>=700)
                {    
                    T.MudaPosicaoX(-20);
                    T.setImage(Explosao);
                    break;
                }    

                if(Bases.VerificaTiro(T))  //Verifica colisao Tiro/Bases
                {    
                    return true;    
                }
                
                if(C1.VerificaTiro(T))  //Verifica colisao Tiro/Canhao
                {    
                   this.AcertouCanhao(T, gc,C1);
                    
                }
                 
                if(C2.VerificaTiro(T))  //Verifica colisao Tiro/Canhao
                {  
                   C1.setVidas(C1.getVidas()-1);
                   this.AcertouCanhao(T, gc,C2);
                    
                } 

               
                  
        }
        
        return false;
    }
      
    /*
    Elimina Canhao e faz Animacao de morte
    @param T   Tiro que eleminou o canhao
    @param gc  Desenhar na tela
    @param C   Canhao que foi atingido
    */ 
    public void AcertouCanhao(Tiro T,GraphicsContext gc, Canhao C)
    {
        T.setX(C.getX()); //Utiliza Tiro para animacao de morte do canhao
        T.setY(C.getY()); //Utiliza Tiro para animacao de morte do canhao
        if(C==C1) C1.setX(200);  //Retorna canhao para a posicao inicial
        if(C==C2) C2.setX(800);  //Retorna canhao para a posicao inicial
        T.setEliminouCanhou();
        T.setImage(MorteNave); //Muda imagem do Tiro
        C.setImage(MorteCanhao);
        
        //Renderiza todos os elemntos atualizados
        gc.drawImage( FundoEspaco, 0, -5 );
        gc.drawImage(Divisao, 650, 625);
        Enemys.render(gc);
        Bases.render(gc);
        T.render(gc);
        C1.render(gc);
        C2.render(gc);
        gc.setFill( Color.WHITE );
        gc.setLineWidth(2);
        Font theFont=Font.font("Berlin Sans FB Demi", FontWeight.BOLD, 30 );
        gc.setFont( theFont );           
                       
            
        gc.fillText( "Level:" , 10, 25 );
        gc.fillText( Level.toString() , 100, 25 );
        gc.fillText( "Pontuação:" , 200, 25 );
        gc.fillText( Pontuacao.toString() , 370, 25 );
        gc.fillText( "Vidas:" , 450, 25 );
        gc.fillText( "Space Invaders Eng Comp Edition" , 900, 25 );
        
        if(C1.getVidas()>0)
            gc.drawImage(MiniCanhao, 550, 5);
        if(C1.getVidas()>1)
            gc.drawImage(MiniCanhao, 600, 5);
        if(C1.getVidas()>2)    
            gc.drawImage(MiniCanhao, 650, 5);
        if(C1.getVidas()>3)    
            gc.drawImage(MiniCanhao, 700, 5);
        if(C1.getVidas()>4)    
            gc.drawImage(MiniCanhao, 750, 5);
        if(C1.getVidas()>5)    
            gc.drawImage(MiniCanhao, 800, 5);
            

        if(C==C1) CanhaoEliminado1=true; //Seta boolean de eliminado para true
        if(C==C2) CanhaoEliminado2=true; //Seta boolean de eliminado para true
        
        int j;
        //Reseta todos os tiros nas naves ativos
        for(j=0;j<Enemys.TirosNaves.size();j++)
        {    
            if(Enemys.TirosNaves.get(j)!=T)
                Enemys.TirosNaves.remove(j);
        }        

    } 
    
}
