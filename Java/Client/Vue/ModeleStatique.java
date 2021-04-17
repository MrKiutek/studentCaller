package Vue;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.table.AbstractTableModel;

public class ModeleStatique extends AbstractTableModel {
    private Object[][] donnees;
    private int row=1,colons=3;
 
    private final String[] entetes = {"Prénom", "Nom", "Crédit d'absence"};
 
    public ModeleStatique() {
        super();
 
        donnees = new Object[][]{
                {"Prénom", "NOM", "Crédit d'absence"}
        };
    }
 
    public int getRowCount() {
        return donnees.length;
    }
 
    public int getColumnCount() {
        return entetes.length;
    }
 
    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }
 
    public Object getValueAt(int rowIndex, int columnIndex) {
        return donnees[rowIndex][columnIndex];
    }

    public void addRow(String Nom,String Prenom,String RFID){

        row = row++;
        Object[][] newObj = new Object[donnees.length+1][donnees[0].length];
        System.out.println(String.valueOf(donnees.length));
        for(int i = 0; i < donnees.length; i++){
            for(int j = 0; j<donnees[i].length; j++){
                newObj[i][j] = donnees[i][j];
            }
        }

        newObj[donnees.length][0] = Prenom;
        newObj[donnees.length][1] = Nom;
        newObj[donnees.length][2] = RFID;

        donnees = newObj;

        fireTableDataChanged();


    }

    public void clearTable(){
        donnees = new Object[][]{
            {"Prénom", "NOM", "Crédit d'absence"}

            
    };

    fireTableDataChanged();
    }

}