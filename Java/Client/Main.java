/*----------------- Les bibliothéques -----------------*/ 

import Vue.Flogin;
import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/*----------------- Classe Principal ------------------*/
/*----------- Lanceur de l'interface client -----------*/

public class Main {
 
    public static void main(String[] args) throws UnsupportedLookAndFeelException {  
        
        UIManager.setLookAndFeel(new NimbusLookAndFeel());  //Commande permettant d'utiliser un design spécifique et non celui par défaut
        
        Flogin Flog = new Flogin();                         //Commande pour aller dans Flogin()       
    }
}
