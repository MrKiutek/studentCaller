package Controler;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import Vue.Flogin;

public class Main {
 
    public static void main(String[] args) throws UnsupportedLookAndFeelException {

        UIManager.setLookAndFeel(new NimbusLookAndFeel());

        Flogin Flog = new Flogin();
        //Fcharge fcharge= new Fcharge();
    }
}