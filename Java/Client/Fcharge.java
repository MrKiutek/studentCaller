import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Fcharge extends JFrame {
   
    JProgressBar progress;
    JPanel panel1,panel2;
    JLabel ltext;

    ArrayList <String> cours = new ArrayList <>();
    ArrayList <String> cla = new ArrayList <>();
    ArrayList <String> date = new ArrayList <>();
    ArrayList <String> heure = new ArrayList <>();

    private ImageIcon imagECE;
    
    int i;

    public Fcharge(){

        cours.add("physique");
        cla.add("SE4 APP1");
        cla.add("SE5 APP1");

        fenetre(550,350);
        orgaFen(); 
        this.setVisible(true);

        avancement();

        Fintbas Fintbas = new Fintbas();
        Fintbas.setCours(cours);
        Fintbas.setCla(cla);
        Fintbas.setHeure(heure);
        Fintbas.setDate(date);
        Fintbas.gene();

        dispose();
        Fintbas.setVisible(true);
    }
    
    private void fenetre (int a, int b){
        this.setTitle("Student Caller");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
        this.setSize(a,b);                                 
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout (2,1));
    }

    private void orgaFen (){
        panel1 = new JPanel();
        panel1.add(image());
        this.add(panel1);

        panel2 = new JPanel(new GridLayout(2,3));
        
        ltext = new JLabel("");  
        panel2.add(ltext);

        progress = new JProgressBar(0,1000);
        progress.setBounds(1,1,165,30);
        progress.setValue(0);  
        progress.setStringPainted(true);
        panel2.add(progress);

        ltext = new JLabel("");  
        panel2.add(ltext);

        ltext = new JLabel("");  
        panel2.add(ltext);

        ltext = new JLabel("Chargement ...",JLabel.CENTER);
        panel2.add(ltext);

        ltext = new JLabel("");  
        panel2.add(ltext);

        this.add(panel2);
    }

    private JLabel image (){
        imagECE = new ImageIcon (this.getClass().getResource("/ECE.png"));

        return (new JLabel(imagECE));
    }

    public void avancement()
    {
        for ( i=0; i<1000000; i++ ){
            progress.setValue(i);
        }
        
    }

    public ArrayList<String> getCours() {
        return this.cours;
    }

    public ArrayList<String> getCla() {
        return this.cla;
    }

    public ArrayList<String> getDate() {
        return this.date;
    }

    public ArrayList<String> getHeure() {
        return this.heure;
    }
}
