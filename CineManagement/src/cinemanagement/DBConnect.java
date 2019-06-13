package cinemanagement;

import java.sql.*;


public class DBConnect {
    private Connection cn=null;
    private Statement st=null;
    private ResultSet rs=null;
    private PreparedStatement pst=null;
    String us;
    String ps;
    
    DBConnect (String user,String pass){
        us = user;
        ps = pass;
        try {
            Class.forName("com.mysql.jdbc.Driver");             //Carichiamo i driver per il supporto 
            
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cineman","root","");
            st = cn.createStatement();
            
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    DBConnect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");             //Carichiamo i driver per il supporto 
            
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cineman","root","");
            //pst = cn.prepareStatement(us)
            //st = cn.createStatement();
            
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    
    
    public String login () {
        String C="";
        String quer = "SELECT userID,pass,Category FROM dipendenti d INNER JOIN User u on d.iD_Dip = u.ID_Dipendente";
        try {
            rs = st.executeQuery(quer);
            
            while (rs.next()){
                String ut = rs.getString("userID");
                String pss = rs.getString("pass");
                String cat = rs.getString("Category");
                if (us.equals(ut)&&(ps.equals(pss))){
                   C=cat;
                   System.out.println("Logged\n\n");
                   break;
                }else {
                    System.out.println("Credenziali Sbagliate per entrare.");
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return C;
    }
    
    public void newFilm (String titolo,int anno,String scene,String regista,String imageUrl){
        String query1 ="CREATE TABLE IF NOT EXISTS films (ID int AUTO_INCREMENT PRIMARY KEY,"
                                        + "titolo char(40) not null,"
                                        + "anno int not null,"
                                        + "scene char(40),"
                                        + "regista char(20),"
                                        + "image BLOB)";
        System.out.println(query1);
        try {
            pst = cn.prepareStatement(query1);
            pst.executeUpdate();
            String query2 ="INSERT INTO films VALUES(null,'"+titolo+"','"+anno+"','"+scene+"','"+regista+"','"+imageUrl+"')";
            System.out.println(query2);
            pst = cn.prepareStatement(query2);
            pst.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }
    
    
    public void newDipendente (String nome,String cognome,String dob,String idCode,String userN,String pass){
        String query1 ="CREATE TABLE IF NOT EXISTS Dipendenti (ID_Dip char(15) PRIMARY KEY,"
                                                 +"Nome Char(20) NOT NULL,"
                                                 +"Cognome char (20) NOT NULL,"
                                                 +"DataN Date NOT NULL,"
                                                 +"Category char(10) NOT NULL)";
        String query2 ="CREATE TABLE IF NOT EXISTS User (userID char(20) PRIMARY KEY,"
                                            +"pass Char(20) NOT NULL,"
                                            +"ID_Dipendente char(15) References Dipendenti(ID_Dip))";
                
        
        
        try {
            pst = cn.prepareStatement(query1);
            pst.executeUpdate();
            
            pst = cn.prepareStatement(query2);
            pst.executeUpdate();
            
            
            
            String query3 ="INSERT INTO Dipendenti VALUES('"+idCode+"','"+nome+"','"+cognome+"','"+java.sql.Date.valueOf(dob)+"','dip')";
            System.out.println(query3);
            pst = cn.prepareStatement(query3);
            pst.execute();
            
            
            if ((!"".equals(userN))&&(!"".equals(pass))){
                String query4 ="INSERT INTO User VALUES('"+userN+"','"+pass+"','"+idCode+"')";
                pst = cn.prepareStatement(query4);
                System.out.println(query4);
                pst.execute();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }
    
}
