import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

public class Flogin extends JFrame {

    JLabel lco,lmp, lti,ltext,rien;
    JButton bco;
    JPasswordField tmp;
    JTextField tco;

    private ImageIcon imagECE;
 
    public Flogin(){  
        
        fenetre(350,250);
        this.setLayout(new GridLayout(3,1));
        paneltitre();
        panelmilieu();
        panelfin();
        this.setVisible(true);
    }

    private void fenetre (int a, int b){
        this.setTitle("Student Caller");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
        this.setSize(a,b);                                 
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    private void paneltitre (){
        JPanel panel1 = new JPanel(new GridLayout(1,1));

        lti = new JLabel("S'identifier :",JLabel.CENTER);    
        lti.setFont(new Font("Serif", Font.BOLD, 30));
        panel1.add(lti);

        this.add(panel1);
    }

    private void panelmilieu(){
        JPanel panel2 = new JPanel(new GridLayout(2,2));

        lco = new JLabel("Identifiant :",JLabel.CENTER);;
        panel2.add(lco);

        tco = new JTextField();  
        panel2.add(tco);

        lmp = new JLabel("Mot de passe :",JLabel.CENTER);   
        panel2.add(lmp);

        tmp = new JPasswordField();    
        panel2.add(tmp);
        
        this.add(panel2);

        this.tmp.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){ 
                if (connect(tco.getText(), tmp.getText()) == true){
                    Fintbas Fintbas = new Fintbas();
                    Fintbas.gene();
                    Fintbas.setVisible(true);
                }
                else{
                    Ferreur fenetreErreur = new Ferreur();
                }
                dispose();
            }});

            this.tco.addActionListener(new ActionListener(){ 
                public void actionPerformed(ActionEvent e){ 
                    if (connect(tco.getText(), tmp.getText()) == true){
                        Fintbas Fintbas = new Fintbas();
                        Fintbas.gene();
                        Fintbas.setVisible(true);
                    }
                    else{
                        Ferreur fenetreErreur = new Ferreur();
                    }
                    dispose();
                }});
    }

    private void panelfin (){
        JPanel panel3 = new JPanel(new GridLayout(1,3));
        rien = new JLabel("");
        panel3.add(rien);
        bco = new JButton("Connection");
        panel3.add(bco);
        this.add(panel3);
        rien = new JLabel("");
        panel3.add(rien);

        this.bco.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) { 
                if (connect(tco.getText(), tmp.getText()) == true){
                    Fintbas Fintbas = new Fintbas();
                    Fintbas.gene();
                    Fintbas.setVisible(true);
                }
                else{
                    Ferreur fenetreErreur = new Ferreur();
                }
                dispose();
            } 
        });
    }

    private boolean connect (String id, String mp){
        if ((id.equals("admin")) && (mp.equals("1234"))){
            return true;
        }
        else {
            return false;
        }
    }

}