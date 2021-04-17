package com.json_computer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;

import com.entities.Cours;
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

    public void make(ArrayList<Cours> cours){
        try {
            Writer writer = new FileWriter("cours.json");
            gson.toJson(cours, writer);
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        };
    }

    public ArrayList<Cours> read(){
        Type type = new TypeToken<ArrayList<Cours>>(){}.getType();
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
