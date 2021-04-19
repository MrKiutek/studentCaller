package Vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

public class Ferreur extends JFrame{
    
    JLabel ltext1,ltext2,rien;
    JButton br;

    public Ferreur(){
        fenetre(450,150);
        this.setLayout(new GridLayout(3,1));
        panelDebut();
        panelFin();
        this.setVisible(true);
    }

    private void fenetre (int a, int b){
        this.setTitle("Student Caller");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
        this.setSize(a,b);                                 
        this.setLocationRelativeTo(null);
    }

    private void panelDebut() {
        JPanel panel1 = new JPanel(new GridLayout(1,1));
        JPanel panel2 = new JPanel(new GridLayout(1,1));
        ltext1 = new JLabel("Erreur !!",JLabel.CENTER);  
        ltext1.setFont(new Font("Serif", Font.BOLD, 20));
        panel1.add(ltext1);
        this.add(panel1);

        ltext2 = new JLabel("L'identifiant et/ou le mot de passe est faux",JLabel.CENTER);  
        ltext2.setFont(new Font("Serif", Font.BOLD, 20));
        panel2.add(ltext2);
        this.add(panel2);
    }

    private void panelFin (){
        JPanel panel3 = new JPanel(new GridLayout(1,3));
        rien = new JLabel("");
        panel3.add(rien);

        br = new JButton("Retour");
        panel3.add(br);

        rien = new JLabel("");
        panel3.add(rien);

        this.add(panel3);

        this.br.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) { 
                Flogin flog = new Flogin();
                dispose();
            }
        });
    }
}
