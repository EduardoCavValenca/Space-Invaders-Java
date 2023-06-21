/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetospaceinvaders.pkg11234381;

import InterfaceGrafica.*;

/**
 *
 * @author eduar
 */
public class ProjetoSpaceInvaders11234381 {
    
   public static void main(String[] args) {   
     
        Menu IniciaJogo = new Menu();
    
        try {
            IniciaJogo.start(null);
        } catch (Exception ex) {
            System.out.println("Erro Abertura de janela");
        }
    }
}
