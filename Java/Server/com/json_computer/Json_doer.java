package com.json_computer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import com.entities.All_Cours;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

/* ------------------Classe Json_doer------------------ */
/* Cette classe compose le faiseur de fichier json.     */
/* Il récupère un objet et l'écrit dans un fichier json */
/* ---------------------------------------------------- */

public class Json_doer {
    private final GsonBuilder builder;
    private final Gson gson; 

    /* ---Constructeur--- */
    public Json_doer(){
        this.builder = new GsonBuilder();
        this.gson = builder.create();
    }

    /* ---Ecrit un fichier Json contenant tous les cours--- */
    public void make(All_Cours all_cours){
        try {
            Writer writer = new FileWriter("cours.json"); //Crée l'écriveur
            gson.toJson(all_cours, writer);               //Ecrit le json
            writer.close();                               //Fermeture de l'écriveur
        } catch (IOException e) {
            e.printStackTrace();
        };
    }

    /* ---Lit un fichier Json et reconstruit un objet All_Cours--- */
    public All_Cours read(){
        Type type = new TypeToken<All_Cours>(){}.getType(); //Récupère le type d'un objet All_Cours
        try {
            Reader reader;                                  //Crée le lecteur
            reader = new FileReader("cours.json");          //Lit le json
            return gson.fromJson(reader,type);              //Retourne l'objet créé
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
