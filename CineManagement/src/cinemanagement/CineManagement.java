package cinemanagement;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;


public class CineManagement implements ActionListener{
    JFrame f = new JFrame ("Cinema Management");
    JTextField user;
    JPasswordField pass;
    JButton send;
    private static String OK ="ok";
    
    
    CineManagement (){
        f.setBounds(100, 100, 500, 200);
        f.setLayout(new BorderLayout());
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
        
        user = new JTextField("",3);
        pass = new JPasswordField();
        pass.addActionListener(this);
        
        
        
        JPanel centerP = new JPanel();
        centerP.setLayout(new GridLayout(2,2,2,2));
        centerP.add(new JLabel("Username : "));
        centerP.add(user);
        centerP.add(new JLabel("Password"));
        centerP.add(pass);
        
        send = new JButton("Accedi");
        send.addActionListener(this);
        
        JPanel southP = new JPanel();
        southP.setLayout(new FlowLayout());
        southP.add(send);
        southP.add(new JLabel(""));
        
        
        
        f.add(new JLabel("Login"),BorderLayout.NORTH);
        f.add(centerP,BorderLayout.CENTER);
        f.add(southP,BorderLayout.SOUTH);
        f.setVisible(true);
    }
    public String dayOfweek (int var){
        String giorno="";
        switch (var) {
            case 1:
                giorno ="domenica";
                break;
            case 2:
                giorno ="lunedi";
                break;    
            case 3:
                giorno ="martedi";
                break;
            case 4:
                giorno ="mercoledi";
                
                break;
            case 5:
                giorno ="giovedi";
                
                break;    
            case 6:
                giorno ="venerdi";
                break;    
            case 7:
                giorno ="sabato";
                break;
        }
        return giorno;
    
    }
    
    public static void main(String[] args) {
        new CineManagement();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String cmd = ae.getActionCommand();     
        
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK); 
        
        String giorno = dayOfweek(day);
        
        //registra il comando Invio
        if ((ae.getSource()==send)||(OK.equalsIgnoreCase(cmd))){
            System.out.println(cmd);
            String us = user.getText();
            char [] passs = pass.getPassword();
            String password="";
            for (int i = 0; i < passs.length; i++) {
                password += passs[i];
            }
            //Dichiariamo DBCOnnect e passiamo UserName e Password
            
            DBConnect dbcn = new DBConnect(us,password);
            boolean logged = false;
            if ("admin".equals(dbcn.login())){
                Mng mng = new Mng();
                f.setVisible(false);
                mng.setVisible(true);
                logged= true;
            }
            if ("dip".equals(dbcn.login())){
                Tickets tck = new Tickets(giorno);
                f.setVisible(false);
                tck.setVisible(true);
                logged=true;
            }
            
            if (logged==false){
                JOptionPane.showMessageDialog(null,"Username o password sono errati.");
            }
        }
    }
}
