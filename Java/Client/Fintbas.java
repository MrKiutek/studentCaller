import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class Fintbas extends JFrame {
 
    JLabel lcla, lcours, ldate, lheure,ltexte;
    JComboBox cbcla, cbcours, cbdate, cbheure;
    JButton bact, bgo,bdeco;

    private ImageIcon imagECE, imageECE2;

    ArrayList <String> cours = new ArrayList <>();
    ArrayList <String> cla = new ArrayList <>();
    ArrayList <String> date = new ArrayList <>();
    ArrayList <String> heure = new ArrayList <>(); 

    public Fintbas(){  
    
        fenetre(); 
    }

    public void gene(){
        paneltitre();
        panelespace();
        panelcentre1();
        panelcentre2();
        panelcentre3();
        tableau();
        panelespace();
        panelfin2();
        panelespace();

        this.bgo.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {       
            String cours = "" + cbcours.getItemAt(cbcours.getSelectedIndex());  
            lcours.setText(cours);
            String classe = "" + cbcla.getItemAt(cbcla.getSelectedIndex());  
            lcla.setText(classe);
            String date = "" + cbdate.getItemAt(cbdate.getSelectedIndex());  
            ldate.setText(date);
            String heure = "" + cbheure.getItemAt(cbheure.getSelectedIndex());  
            lheure.setText(heure);
            }  
        });

        this.bact.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {       
                dispose();
                Fcharge fcharge= new Fcharge();        
            }  
        });
    }
    
    private void fenetre(){
        this.setTitle("Student Caller");    
        this.setExtendedState(Frame.MAXIMIZED_BOTH);                                
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setLayout(new GridLayout(9,1));
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
        cours.add("Anglais");
        cours.add("Math");
        date.add("1/1/2020");

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
        cours.add("Anglais");
        cours.add("Math");
        date.add("1/1/2020");

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
            {"Johnathan", "Sykes"},
            {"Adrien", "Germain"}};
        JTable AffTableau1 = new JTable(donnees1,entetes1);
        panel5.add(AffTableau1);
        this.add(panel5);
  
        ltexte = new JLabel("");
        panel5.add(ltexte);

        String[] entetes2 = {"Prénom", "Nom"};
        Object[][] donnees2 = {
            {"Johnathan", "Sykes"},
            {"Adrien", "Germain"}};
        JTable AffTableau2 = new JTable(donnees2,entetes2);
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
}