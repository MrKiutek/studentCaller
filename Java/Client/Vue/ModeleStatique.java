/*----------------- Le package ------------------------*/

package Vue;

/*----------------- Les bibliothéques -----------------*/

import javax.swing.table.AbstractTableModel;

/*---------------------- Classe -----------------------*/

public class ModeleStatique extends AbstractTableModel {

/*------------------ Variable global ------------------*/

    private Object[][] donnees;
    private final String[] entetes = {"Prénom", "Nom", "Crédit d'absence"};

/*------------------- Constructeur --------------------*/

    public ModeleStatique() { 
        super();
 
        donnees = new Object[][]{
                {"Prénom", "NOM", "Crédit d'absence"}
        };
    }
 
    //Fonction permettant l'ajout d'une ligne au tableau
    public void addRow(String Nom,String Prenom,String RFID){ 

        //Création d'un nouvel objet de taille
        Object[][] newObj = new Object[donnees.length+1][donnees[0].length];    

        // Copie des données de l'objet existant dans le nouveau tableau
        for(int i = 0; i < donnees.length; i++){                                
            for(int j = 0; j<donnees[i].length; j++){
                newObj[i][j] = donnees[i][j];
            }
        }

        //Ajout des données de la nouvelle ligne dans la derniere case du tableau
        newObj[donnees.length][0] = Prenom;
        newObj[donnees.length][1] = Nom;
        newObj[donnees.length][2] = RFID;

        //Remplacement des anciennes données par les nouvelles avec la nouvelle ligne
        donnees = newObj;                                                       

        //Event pour signaler que les données ont changées et qu'il faut actualiser la vue
        fireTableDataChanged();                                                 
    }

    //Fonction pour supprimer toute la table sauf l'entête
    public void clearTable(){ 
        donnees = new Object[][]{
            {"Prénom", "NOM", "Crédit d'absence"}      
    };

    //Event pour signaler que les données ont changées et qu'il faut actualiser la vue
    fireTableDataChanged();         
    }

/*---------------------- Getter -----------------------*/
    
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
}