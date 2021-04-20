package Vue;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import com.entities.Cours;
import Controler.ReceiveData;
import java.util.ArrayList;

public class Fintbas extends JFrame {
 
    JLabel lcla, lcours, ldate, lheure,ltexte;
    JComboBox cbcla, cbcours, cbdate, cbheure;
    JButton bact, bgo,bdeco;
    ArrayList<Cours> listCours;
    JTable AffTableau1,AffTableau2;

    private ImageIcon imagECE, imageECE2;

    //Création des Arraylist
    ArrayList <String> cours = new ArrayList <>();
    ArrayList <String> cla = new ArrayList <>();
    ArrayList <String> date = new ArrayList <>();
    ArrayList <String> heure = new ArrayList <>(); 

    public Fintbas(){  
    
        fenetre();              //Appel de la fonction fenêtre
    }

    //Fonction permettant de générer le design de la fenêtre
    public void gene(){
        paneltitre();           //Appel de la fonction paneltitre
        panelespace();          //Appel de la fonction panelespace
        panelcentre1();         //Appel de la fonction panelcentre1
        panelcentre2();         //Appel de la fonction panelcentre2
        panelcentre3();         //Appel de la fonction panelcentre3
        tableau();              //Appel de la fonction tableau
        panelespace();          //Appel de la fonction panelspace
        panelfin2();            //Appel de la fonction panelfin2
        panelespace();          //Appel de la fonction panelspace

        //Création d'un Listener permttant de savoir quand l'utilisateur appuye sur le bouton
        this.bgo.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                String cours = "" + cbcours.getItemAt(cbcours.getSelectedIndex());      //Récupére les informations de la JComboBox
                lcours.setText(cours);                                                  //Mets l'information de la JComboBox dans le JLable
                String classe = "" + cbcla.getItemAt(cbcla.getSelectedIndex());         //Récupére les informations de la JComboBox
                lcla.setText(classe);                                                   //Mets l'information de la JComboBox dans le JLable
                String date = "" + cbdate.getItemAt(cbdate.getSelectedIndex());         //Récupére les informations de la JComboBox
                ldate.setText(date);                                                    //Mets l'information de la JComboBox dans le JLable
                String heure = "" + cbheure.getItemAt(cbheure.getSelectedIndex());      //Récupére les informations de la JComboBox
                lheure.setText(heure);                                                  //Mets l'information de la JComboBox dans le JLable
                try {
                    startSearch();                                                      //Appel la fonction startSearch
                } catch (ClassNotFoundException | IOException | InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }  
        });

        //Création d'un Listener permttant de savoir quand l'utilisateur appuye sur le bouton
        this.bact.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {                                                
                try {
                    Fcharge fcharge= new Fcharge();                                     //Va dans la fonction Fcharge
                    dispose();                                                          //Femer la fentre Flogin
                } catch (ClassNotFoundException | IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }        
            }  
        });
    }
    
    private void fenetre(){
        this.setTitle("Student Caller");                        //Nom de notre fenêtre
        this.setExtendedState(Frame.MAXIMIZED_BOTH);            //La fenêtre s'affiche en pleinne écran 
        this.setSize(1500, 1000);                               //Taille de la fenêtre par défaut                                        
        this.setLocationRelativeTo(null);                       //Centre la fenêtre au centre de l'écran
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //Permet de fermer le programme quand nous fermons la fenêtre
        this.setResizable(true);                                //Permet à l'utilisateur de redimensionner la fenêtre
        this.setLayout(new GridLayout(9,1));                    //Crée une grille de 9 ligne et 1 colonne pour le design de la fenêtre
    }

    private void paneltitre (){
        JPanel panel1 = new JPanel(new GridLayout(1,3));

        panel1.add(image());

        ltexte = new JLabel("Liste de présence",JLabel.CENTER);  
        ltexte.setFont(new Font("Serif", Font.BOLD, 40));
        panel1.add(ltexte);

        panel1.add(image2());

        this.add(panel1);
    }

    private void panelcentre1 (){

        JPanel panel2 = new JPanel(new GridLayout(1,7));
        
        bact = new JButton("Actualiser");                                              
        panel2.add(bact);

        DefaultListCellRenderer centr = new DefaultListCellRenderer();
        centr.setHorizontalAlignment(JLabel.CENTER);
 
        cbcours =new JComboBox(cours.toArray());  
        cbcours.setRenderer(centr);    
        panel2.add(cbcours);

        cbcla =new JComboBox(cla.toArray());
        cbcla.setRenderer(centr);      
        panel2.add(cbcla);
        cbdate =new JComboBox(date.toArray());  
        cbdate.setRenderer(centr);     
        panel2.add(cbdate);

        cbheure =new JComboBox(heure.toArray()); 
        cbheure.setRenderer(centr);       
        panel2.add(cbheure);

        bgo = new JButton("Recherche");                                             
        panel2.add(bgo);

        this.add(panel2);
    }

    private void panelcentre2 (){

        JPanel panel3 = new JPanel(new GridLayout(1,7));
        
        ltexte = new JLabel("");  
        panel3.add(ltexte);

        lcours = new JLabel("",JLabel.CENTER);
        panel3.add(lcours);

        lcla = new JLabel("",JLabel.CENTER);  
        panel3.add(lcla);

        ldate = new JLabel("",JLabel.CENTER);  
        panel3.add(ldate);

        lheure = new JLabel("",JLabel.CENTER);  
        panel3.add(lheure);

        ltexte = new JLabel("");  
        panel3.add(ltexte);
        
        this.add(panel3);
    }

    private void panelcentre3 (){
        JPanel panel4 = new JPanel(new GridLayout(1,5));

        ltexte = new JLabel("");  
        panel4.add(ltexte);

        ltexte = new JLabel("Present",JLabel.CENTER);
        ltexte.setFont(new Font("Serif", Font.BOLD, 20));  
        panel4.add(ltexte);

        ltexte = new JLabel("");  
        panel4.add(ltexte);

        ltexte = new JLabel("Absent",JLabel.CENTER);
        ltexte.setFont(new Font("Serif", Font.BOLD, 20));  
        panel4.add(ltexte);

        ltexte = new JLabel("");  
        panel4.add(ltexte);

        this.add(panel4);
    }

    private void tableau(){
        JPanel panel5 = new JPanel(new GridLayout(1,5));

        ltexte = new JLabel("");  
        panel5.add(ltexte);

        String[] entetes1 = {"Prénom", "Nom"};
        Object[][] donnees1 = {
            {"", "",""},};

        ModeleStatique model = new ModeleStatique();

        

        AffTableau1 = new JTable();
        AffTableau1.setModel(model);

        panel5.add(AffTableau1);
        this.add(panel5);
  
        ltexte = new JLabel("");
        panel5.add(ltexte);

        String[] entetes2 = {"Prénom", "Nom"};
        Object[][] donnees2 = {
            {"", ""},};
        AffTableau2 = new JTable(new ModeleStatique());
        panel5.add(AffTableau2);
  
        ltexte = new JLabel("");
        panel5.add(ltexte);

        this.add(panel5);
    }

    private void panelespace (){
        JPanel panel6 = new JPanel(new GridLayout(1,1));

        ltexte = new JLabel("");  
        panel6.add(ltexte);
 
        panel6.add(ltexte);
        
        this.add(panel6);
    }

    private void panelfin2 (){
        JPanel panel7 = new JPanel(new GridLayout(1,3));

        ltexte = new JLabel("");  
        panel7.add(ltexte);
        
        bdeco = new JButton("Deconnection");
        panel7.add(bdeco);
        
        ltexte = new JLabel("");
        panel7.add(ltexte);
        
        this.add(panel7);

        this.bdeco.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                Flogin flog = new Flogin();
                dispose();
            } 
        });
    }
    
    private JLabel image (){
        imagECE = new ImageIcon (this.getClass().getResource("/ECE.png"));

        return (new JLabel(imagECE));
    }

    private JLabel image2 (){
        imagECE = new ImageIcon (this.getClass().getResource("/ECE2.png"));

        return (new JLabel(imagECE));
    }

    public void setCours(ArrayList<String> Cours) {
        this.cours = Cours;
    }

    public void setCla(ArrayList<String> cla) {
        this.cla = cla;
    }

    public void setDate(ArrayList<String> date) {
        this.date = date;
    }

    public void setHeure(ArrayList<String> heure) {
        this.heure = heure;
    } 

    public void setListCours(ArrayList<Cours> tab){
        this.listCours = tab;
        
    }
    
    private int startSearch() throws UnknownHostException, ClassNotFoundException, IOException, InterruptedException{ //Fonction permettant le remplissage des deux tableau en fonction des paramètres choisi par l'utilisateur

        //Récupération des données selectionnées par l'utilisateur
        String cours = "" + cbcours.getItemAt(cbcours.getSelectedIndex());  
        String classe = "" + cbcla.getItemAt(cbcla.getSelectedIndex());  
        String date = "" + cbdate.getItemAt(cbdate.getSelectedIndex());  
        String heure = "" + cbheure.getItemAt(cbheure.getSelectedIndex());

        //Création des formats pour la date et l'heure
        DateFormat dateForm = new SimpleDateFormat("MM/dd/yyyy");
        DateFormat timeForm = new SimpleDateFormat("hh:mm:ss a");

        //Indice du cours selectionné
        int index= 0;

        // Reception de la liste des cours directement depuis le serveur
        ArrayList<Cours> rd = new ReceiveData().start();

        //Suppression des données dans les tableaux
        ((ModeleStatique)AffTableau1.getModel()).clearTable();
        ((ModeleStatique)AffTableau2.getModel()).clearTable();

        //Récupération de l'indice du cours selectionné par l'utilisateur dans la liste
        for(int i = 0; i<rd.size(); i++){

            if(cours.equals(rd.get(i).getMatiere()) && classe.equals(rd.get(i).getSalle()) && date.equals(dateForm.format(rd.get(i).getDate_heure())) && heure.equals(timeForm.format(rd.get(i).getDate_heure()))){
                index = i;
                break;  
            }else{
                return 0;
            }
        }

        //Ajout de tout les présents dans le tableau de présents et affichage de celui-ci
        for(String key : rd.get(index).getPresent().keySet() ){
            ((ModeleStatique)AffTableau1.getModel()).addRow(rd.get(index).getPresent().get(key).getNom(), rd.get(index).getPresent().get(key).getPrenom(), String.valueOf(rd.get(index).getPresent().get(key).getCreditAbsence()));
        }

        //Ajout de tout les absent dans le tableau des absent et affichage de celui-ci
        for(String key : rd.get(index).getAbsent().keySet() ){
            ((ModeleStatique)AffTableau2.getModel()).addRow(rd.get(index).getAbsent().get(key).getNom(), rd.get(index).getAbsent().get(key).getPrenom(), String.valueOf(rd.get(index).getAbsent().get(key).getCreditAbsence()));
        }
        return 1;
    }
}