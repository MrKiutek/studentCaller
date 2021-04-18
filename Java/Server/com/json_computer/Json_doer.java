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

public class Json_doer {
    private final GsonBuilder builder;
    private final Gson gson; 

    public Json_doer(){
        this.builder = new GsonBuilder();
        this.gson = builder.create();
    }

    public void make(All_Cours all_cours){
        try {
            Writer writer = new FileWriter("cours.json");
            gson.toJson(all_cours, writer);
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        };
    }

    public All_Cours read(){
        Type type = new TypeToken<All_Cours>(){}.getType();
        try {
            Reader reader;
            reader = new FileReader("cours.json");
            return gson.fromJson(reader,type);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
