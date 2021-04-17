package com.entities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class All_Cours {
    private HashMap<Date, Cours> list_with_date;
    private HashMap<String, HashMap<Date, Cours>> list_with_salle;
    private DateFormat format;

    public All_Cours(){

        this.format( new SimpleDateFormat("dd/MM/yyyy HH:mm:ss") );    
    }
}
