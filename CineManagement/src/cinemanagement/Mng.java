/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemanagement;

/**
 *
 * @author haider
 */
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.*;
import java.awt.*;
import java.net.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;
public class Mng extends javax.swing.JFrame {
    Connection cn=null;
    PreparedStatement st=null;
    ResultSet rs=null;
    DBConnect dbmn = new DBConnect();
    
    /**
     * Creates new form Mng
     */
    public Mng() {
        initComponents();
        try {
            Class.forName("com.mysql.jdbc.Driver");             //Carichiamo i driver per il supporto 
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cineman","root","");
        }catch(Exception e){
            System.out.println(e);
        }
        
        update_table();
        update_tableD();
        update_filmCombo();
        createOrari1();
    }
    
    private void createOrari1(){
        
        String query1="CREATE TABLE Orari (ID int AUTO_INCREMENT PRIMARY KEY,titolo Char(40), sala int ,giorno char(10),Ora char(10))";
        String query2="INSERT INTO Orari VALUES (null,null,null,'lunedi','16:30')," +
"					(null,null,null,'lunedi','18:30')," +
"                                        (null,null,null,'lunedi','20:30')," +
"					(null,null,null,'lunedi','22:30')," +
"                                        (null,null,null,'martedi','16:30')," +
"					(null,null,null,'martedi','18:30')," +
"                                        (null,null,null,'martedi','20:30')," +
"					(null,null,null,'martedi','22:30')," +
"                                        (null,null,null,'mercoledi','16:30')," +
"					(null,null,null,'mercoledi','18:30')," +
"                                        (null,null,null,'mercoledi','20:30')," +
"					(null,null,null,'mercoledi','22:30')," +
"                                        (null,null,null,'giovedi','16:30')," +
"					(null,null,null,'giovedi','18:30')," +
"                                        (null,null,null,'giovedi','20:30')," +
"					(null,null,null,'giovedi','22:30')," +
"                                        (null,null,null,'venerdi','16:30')," +
"					(null,null,null,'venerdi','18:30')," +
"                                        (null,null,null,'venerdi','20:30')," +
"					(null,null,null,'venerdi','22:30')," +
"                                        (null,null,null,'sabato','16:30')," +
"					(null,null,null,'sabato','18:30')," +
"                                        (null,null,null,'sabato','20:30')," +
"					(null,null,null,'sabato','22:30')," +
"                                        (null,null,null,'domenica','16:30')," +
"					(null,null,null,'domenica','18:30')," +
"                                        (null,null,null,'domenica','20:30')," +
"					(null,null,null,'domenica','22:30');";
        try {
            st = cn.prepareStatement(query1);
            st.executeUpdate();
            st = cn.prepareStatement(query2);
            st.executeUpdate();
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    
    
    private void update_table(){
        try {
            //Se la tabella è gia stata creata sorpassa il primo comando se no Ne crea una vuota.
            String query1 ="CREATE TABLE IF NOT EXISTS films (ID int AUTO_INCREMENT PRIMARY KEY,"
                                        + "titolo char(40) not null,"
                                        + "anno int not null,"
                                        + "scene char(40),"
                                        + "regista char(20),"
                                        + "image BLOB)";
            st = cn.prepareStatement(query1);
            st.executeUpdate();
            String sql = "select * from films";
            st = cn.prepareStatement(sql);
            rs = st.executeQuery();
            film_Table.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    
    private void update_filmCombo (){/*
        int itemCount = lunFilm1.getItemCount();
        if (itemCount!=0){
            for(int i=0;i<itemCount;i++){
                lunFilm1.removeItemAt(i);
                lunFilm2.removeItemAt(i);
                lunFilm3.removeItemAt(i);
                lunFilm4.removeItemAt(i);
                marFilm1.removeItemAt(i);
                marFilm2.removeItemAt(i);
                marFilm3.removeItemAt(i);
                marFilm4.removeItemAt(i);
                merFilm1.removeItemAt(i);
                merFilm2.removeItemAt(i);
                merFilm3.removeItemAt(i);
                merFilm4.removeItemAt(i);
                gioFilm1.removeItemAt(i);
                gioFilm2.removeItemAt(i);
                gioFilm3.removeItemAt(i);
                gioFilm4.removeItemAt(i);
                venFilm1.removeItemAt(i);
                venFilm2.removeItemAt(i);
                venFilm3.removeItemAt(i);
                venFilm4.removeItemAt(i);
                sabFilm1.removeItemAt(i);
                sabFilm2.removeItemAt(i);
                sabFilm3.removeItemAt(i);
                sabFilm4.removeItemAt(i);
                domFilm1.removeItemAt(i);
                domFilm2.removeItemAt(i);
                domFilm3.removeItemAt(i);
                domFilm4.removeItemAt(i);
            }
        }*/
        
        String query = "SELECT titolo from films";
        try {
            st = cn.prepareStatement(query);
            rs = st.executeQuery();
            
            while (rs.next()){
                String titolo =rs.getString("titolo");
                lunFilm1.addItem(titolo);
                lunFilm2.addItem(titolo);
                lunFilm3.addItem(titolo);
                lunFilm4.addItem(titolo);
                marFilm1.addItem(titolo);
                marFilm2.addItem(titolo);
                marFilm3.addItem(titolo);
                marFilm4.addItem(titolo);
                merFilm1.addItem(titolo);
                merFilm2.addItem(titolo);
                merFilm3.addItem(titolo);
                merFilm4.addItem(titolo);
                gioFilm1.addItem(titolo);
                gioFilm2.addItem(titolo);
                gioFilm3.addItem(titolo);
                gioFilm4.addItem(titolo);
                venFilm1.addItem(titolo);
                venFilm2.addItem(titolo);
                venFilm3.addItem(titolo);
                venFilm4.addItem(titolo);
                sabFilm1.addItem(titolo);
                sabFilm2.addItem(titolo);
                sabFilm3.addItem(titolo);
                sabFilm4.addItem(titolo);
                domFilm1.addItem(titolo);
                domFilm2.addItem(titolo);
                domFilm3.addItem(titolo);
                domFilm4.addItem(titolo);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }
    
    
    private void update_orari(String titolo,String sala,String giorno,String ora){
        if ((!titolo.contains("Scegli"))||(!sala.contains("Scegli"))){
                String s = "UPDATE orari SET titolo='"+titolo+"' WHERE giorno = '"+giorno+"' AND ora ='"+ora+"' AND sala='"+sala+"'";
                try {
                    st = cn.prepareStatement(s);
                    st.executeUpdate();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
    }
    
    
    private void update_tableD(){
        try {
            //Se la tabella è gia stata creata sorpassa il primo comando se no Ne crea una vuota.
            String query1 ="CREATE TABLE IF NOT EXISTS Dipendenti (ID_Dip char(15) PRIMARY KEY,"
                                                    +"Nome Char(20) NOT NULL," 
                                                    +"Cognome char (20) NOT NULL,"
                                                    +"DataN Date NOT NULL,"
                                                    +"Category char(10) NOT NULL)";
            st = cn.prepareStatement(query1);
            st.executeUpdate();
            String query2 ="CREATE TABLE IF NOT EXISTS User (userID char(20) PRIMARY KEY,"
                                                +"pass Char(20) NOT NULL,"
                                                +"ID_Dipendente char(15) References dipendenti(ID_Dip))";
            st = cn.prepareStatement(query2);
            st.executeUpdate();
            
            
            String sql = "SELECT * FROM Dipendenti D LEFT JOIN User U ON D.ID_Dip=U.ID_Dipendente";
            st = cn.prepareStatement(sql);
            rs = st.executeQuery();
            dipendenti_table.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        add = new javax.swing.JButton();
        titolo = new javax.swing.JTextField();
        anno = new javax.swing.JTextField();
        scene = new javax.swing.JTextField();
        regista = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        photolink = new javax.swing.JTextField();
        clean = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        delete = new javax.swing.JButton();
        update = new javax.swing.JButton();
        showImage = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        film_Table = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        delete1 = new javax.swing.JButton();
        update1 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        dipendenti_table = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        userid = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        password = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        add1 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        dob = new javax.swing.JTextField();
        cognome = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        nome = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        idCode = new javax.swing.JTextField();
        clean1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        lunFilm1 = new javax.swing.JComboBox();
        jLabel20 = new javax.swing.JLabel();
        lunFilm2 = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lunFilm4 = new javax.swing.JComboBox();
        lunFilm3 = new javax.swing.JComboBox();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        marFilm1 = new javax.swing.JComboBox();
        marFilm2 = new javax.swing.JComboBox();
        jLabel24 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        marFilm3 = new javax.swing.JComboBox();
        marFilm4 = new javax.swing.JComboBox();
        jLabel26 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        merFilm1 = new javax.swing.JComboBox();
        jLabel28 = new javax.swing.JLabel();
        merFilm2 = new javax.swing.JComboBox();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        merFilm4 = new javax.swing.JComboBox();
        jLabel30 = new javax.swing.JLabel();
        merFilm3 = new javax.swing.JComboBox();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        gioFilm1 = new javax.swing.JComboBox();
        gioFilm2 = new javax.swing.JComboBox();
        jLabel32 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jPanel33 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        gioFilm3 = new javax.swing.JComboBox();
        gioFilm4 = new javax.swing.JComboBox();
        jLabel34 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jPanel36 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        venFilm1 = new javax.swing.JComboBox();
        venFilm2 = new javax.swing.JComboBox();
        jLabel36 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jPanel37 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        venFilm3 = new javax.swing.JComboBox();
        venFilm4 = new javax.swing.JComboBox();
        jLabel38 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jPanel40 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        sabFilm1 = new javax.swing.JComboBox();
        sabFilm2 = new javax.swing.JComboBox();
        jLabel40 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jPanel41 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        sabFilm3 = new javax.swing.JComboBox();
        sabFilm4 = new javax.swing.JComboBox();
        jLabel42 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jPanel44 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        domFilm1 = new javax.swing.JComboBox();
        domFilm2 = new javax.swing.JComboBox();
        jLabel44 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jPanel45 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        domFilm3 = new javax.swing.JComboBox();
        domFilm4 = new javax.swing.JComboBox();
        jLabel46 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTabbedPane2.setToolTipText("");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel1.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        jLabel1.setText("Aggiungi Un Nuovo Film");

        jLabel2.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel2.setText("Titolo ");

        jLabel3.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel3.setText("Anno");

        jLabel4.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel4.setText("Sceneggiatore");

        jLabel5.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel5.setText("Regista");

        add.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        add.setText("Aggiungi");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        anno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annoActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel13.setText("Url Immagine");

        clean.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        clean.setText("Svuota");
        clean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cleanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(48, 48, 48)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(anno)
                                    .addComponent(titolo)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(clean, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(26, 26, 26))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(scene, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(regista, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                            .addComponent(photolink))
                        .addGap(26, 26, 26))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titolo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(anno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(scene, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(regista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(photolink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(120, 120, 120)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clean)
                    .addComponent(add))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tabella dei Film", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe Script", 1, 12))); // NOI18N

        delete.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        delete.setText("Cancella");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        update.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        update.setText("Aggiorna");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        showImage.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        showImage.setText("Vedi Copertina");
        showImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showImageActionPerformed(evt);
            }
        });

        film_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        film_Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                film_TableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(film_Table);

        jScrollPane4.setViewportView(jScrollPane2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(showImage, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
                        .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(showImage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(update)
                    .addComponent(delete))
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Gestione Film", jPanel5);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabella Dipendenti"));

        delete1.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        delete1.setText("Cancella");
        delete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete1ActionPerformed(evt);
            }
        });

        update1.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        update1.setText("Aggiorna");
        update1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update1ActionPerformed(evt);
            }
        });

        dipendenti_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        dipendenti_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dipendenti_tableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(dipendenti_table);

        jScrollPane5.setViewportView(jScrollPane3);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(delete1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(update1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(delete1)
                    .addComponent(update1))
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Dati Dipendenti"));

        jLabel10.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N

        jPanel9.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel11.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel11.setText("User-ID");

        jLabel12.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel12.setText("Password");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(userid, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(password)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        add1.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        add1.setText("Aggiungi");
        add1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add1ActionPerformed(evt);
            }
        });

        jPanel11.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        dob.setText("yyyy-mm-dd");
        dob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dobActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel8.setText("Cognome");

        jLabel9.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel9.setText("Data Nas.");

        jLabel7.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel7.setText("Nome");

        jLabel14.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel14.setText("Codice ID.");

        idCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idCodeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(idCode, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                    .addComponent(cognome)
                    .addComponent(nome)
                    .addComponent(dob, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cognome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(dob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(idCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        clean1.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        clean1.setText("Svuota");
        clean1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clean1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(clean1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(add1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(jLabel10))
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add1)
                    .addComponent(clean1))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane2.addTab("Gestione Dipendenti", jPanel3);

        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Sala 1"));

        jLabel19.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel19.setText("Primo Film : ");

        lunFilm1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Scegli..." }));
        lunFilm1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lunFilm1ActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel20.setText("Secondo Film : ");

        lunFilm2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Scegli..." }));

        jLabel6.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel6.setText("Ore 19:30");

        jLabel49.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel49.setText("Ore 16:30");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                        .addComponent(lunFilm2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lunFilm1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel49))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel49)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(lunFilm1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(lunFilm2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder("Sala 2"));

        jLabel21.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel21.setText("Primo Film : ");

        jLabel22.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel22.setText("Secondo Film : ");

        lunFilm4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Scegli..." }));

        lunFilm3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Scegli..." }));

        jLabel47.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel47.setText("Ore 19:30");

        jLabel48.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel48.setText("Ore 16:30");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lunFilm3, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                        .addComponent(lunFilm4, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel47)
                            .addComponent(jLabel48))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel48)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(lunFilm3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel47)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(lunFilm4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Lunedi", jPanel8);

        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder("Sala 1"));

        jLabel23.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel23.setText("Primo Film :");

        marFilm1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Scegli..." }));

        marFilm2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Scegli..." }));

        jLabel24.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel24.setText("Secondo Film : ");

        jLabel50.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel50.setText("Ore 19:30");

        jLabel51.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel51.setText("Ore 16:30");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(marFilm1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                        .addComponent(marFilm2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel51)
                            .addComponent(jLabel50))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel51)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(marFilm1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(jLabel50)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(marFilm2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder("Sala 2"));

        jLabel25.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel25.setText("Primo Film :");

        marFilm3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Scegli..." }));

        marFilm4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Scegli..." }));

        jLabel26.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel26.setText("Secondo Film : ");

        jLabel52.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel52.setText("Ore 19:30");

        jLabel53.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel53.setText("Ore 16:30");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(jLabel53))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(marFilm3, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                        .addComponent(marFilm4, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jLabel52)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel53)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(marFilm3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel52)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(marFilm4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Martedi", jPanel18);

        jPanel28.setBorder(javax.swing.BorderFactory.createTitledBorder("Sala 1"));

        jLabel27.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel27.setText("Primo Film :");

        merFilm1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Scegli..." }));

        jLabel28.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel28.setText("Secondo Film : ");

        merFilm2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Scegli..." }));

        jLabel54.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel54.setText("Ore 19:30");

        jLabel55.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel55.setText("Ore 16:30");

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(merFilm1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                        .addComponent(merFilm2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel54)
                            .addComponent(jLabel55))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel55)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(merFilm1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(jLabel54)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(merFilm2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addContainerGap())
        );

        jPanel29.setBorder(javax.swing.BorderFactory.createTitledBorder("Sala 2"));

        jLabel29.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel29.setText("Primo Film :");

        merFilm4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Scegli..." }));

        jLabel30.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel30.setText("Secondo Film : ");

        merFilm3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Scegli..." }));

        jLabel56.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel56.setText("Ore 19:30");

        jLabel57.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel57.setText("Ore 16:30");

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(merFilm3, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                        .addComponent(merFilm4, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel57)
                            .addComponent(jLabel56))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel57)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(merFilm3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel56)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(merFilm4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Mercoledi", jPanel19);

        jPanel32.setBorder(javax.swing.BorderFactory.createTitledBorder("Sala 1"));

        jLabel31.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel31.setText("Primo Film :");

        gioFilm1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Scegli..." }));

        gioFilm2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Scegli..." }));

        jLabel32.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel32.setText("Secondo Film : ");

        jLabel58.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel58.setText("Ore 19:30");

        jLabel59.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel59.setText("Ore 16:30");

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel32Layout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(gioFilm1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                                .addComponent(jLabel32)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                                .addComponent(gioFilm2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel59)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel58)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel59)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(gioFilm1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(jLabel58)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gioFilm2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32))
                .addContainerGap())
        );

        jPanel33.setBorder(javax.swing.BorderFactory.createTitledBorder("Sala 2"));

        jLabel33.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel33.setText("Primo Film :");

        gioFilm3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Scegli..." }));

        gioFilm4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Scegli..." }));

        jLabel34.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel34.setText("Secondo Film : ");

        jLabel60.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel60.setText("Ore 19:30");

        jLabel61.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel61.setText("Ore 16:30");

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(gioFilm3, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel33Layout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                        .addComponent(gioFilm4, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel61)
                            .addComponent(jLabel60))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel61)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(gioFilm3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel60)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gioFilm4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Giovedi", jPanel20);

        jPanel36.setBorder(javax.swing.BorderFactory.createTitledBorder("Sala 1"));

        jLabel35.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel35.setText("Primo Film :");

        venFilm1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Scegli..." }));

        venFilm2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Scegli..." }));

        jLabel36.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel36.setText("Secondo Film : ");

        jLabel62.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel62.setText("Ore 19:30");

        jLabel63.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel63.setText("Ore 16:30");

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel36Layout.createSequentialGroup()
                                .addComponent(jLabel35)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(venFilm1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel36Layout.createSequentialGroup()
                                .addComponent(jLabel36)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                                .addComponent(venFilm2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel63)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel62)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel63)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(venFilm1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(jLabel62)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(venFilm2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36))
                .addContainerGap())
        );

        jPanel37.setBorder(javax.swing.BorderFactory.createTitledBorder("Sala 2"));

        jLabel37.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel37.setText("Primo Film :");

        venFilm3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Scegli..." }));

        venFilm4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Scegli..." }));

        jLabel38.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel38.setText("Secondo Film : ");

        jLabel64.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel64.setText("Ore 19:30");

        jLabel65.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel65.setText("Ore 16:30");

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                        .addComponent(venFilm4, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37)
                            .addComponent(jLabel64)
                            .addComponent(jLabel65))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(venFilm3, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel65)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(venFilm3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel64)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(venFilm4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Venerdi", jPanel21);

        jPanel40.setBorder(javax.swing.BorderFactory.createTitledBorder("Sala 1"));

        jLabel39.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel39.setText("Primo Film :");

        sabFilm1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Scegli..." }));

        sabFilm2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Scegli..." }));

        jLabel40.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel40.setText("Secondo Film : ");

        jLabel66.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel66.setText("Ore 19:30");

        jLabel67.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel67.setText("Ore 16:30");

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel39)
                            .addComponent(jLabel67))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sabFilm1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addComponent(jLabel40)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                        .addComponent(sabFilm2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addComponent(jLabel66)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel67)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(sabFilm1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(jLabel66)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sabFilm2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40))
                .addContainerGap())
        );

        jPanel41.setBorder(javax.swing.BorderFactory.createTitledBorder("Sala 2"));

        jLabel41.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel41.setText("Primo Film :");

        sabFilm3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Scegli..." }));

        sabFilm4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Scegli..." }));

        jLabel42.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel42.setText("Secondo Film : ");

        jLabel68.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel68.setText("Ore 19:30");

        jLabel69.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel69.setText("Ore 16:30");

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel41Layout.createSequentialGroup()
                        .addComponent(jLabel42)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                        .addComponent(sabFilm4, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel41)
                            .addComponent(jLabel69))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sabFilm3, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addComponent(jLabel68)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel69)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(sabFilm3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel68)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sabFilm4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Sabato", jPanel22);

        jPanel44.setBorder(javax.swing.BorderFactory.createTitledBorder("Sala 1"));

        jLabel43.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel43.setText("Primo Film :");

        domFilm1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Scegli..." }));

        domFilm2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Scegli..." }));

        jLabel44.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel44.setText("Secondo Film : ");

        jLabel70.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel70.setText("Ore 19:30");

        jLabel71.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel71.setText("Ore 16:30");

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel44Layout.createSequentialGroup()
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(domFilm2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel44Layout.createSequentialGroup()
                        .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel44Layout.createSequentialGroup()
                                .addComponent(jLabel71)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel44Layout.createSequentialGroup()
                                .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                                .addGap(75, 75, 75)))
                        .addComponent(domFilm1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel44Layout.createSequentialGroup()
                        .addComponent(jLabel70)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel71)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(domFilm1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(jLabel70)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(domFilm2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44))
                .addContainerGap())
        );

        jPanel45.setBorder(javax.swing.BorderFactory.createTitledBorder("Sala 2"));

        jLabel45.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel45.setText("Primo Film :");

        domFilm3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Scegli..." }));

        domFilm4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Scegli..." }));

        jLabel46.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel46.setText("Secondo Film : ");

        jLabel72.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel72.setText("Ore 19:30");

        jLabel73.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel73.setText("Ore 16:30");

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel45Layout.createSequentialGroup()
                        .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel73)
                            .addComponent(jLabel72))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel45Layout.createSequentialGroup()
                        .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                            .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                        .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(domFilm3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(domFilm4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel73)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(domFilm3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel72)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(domFilm4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Domenica", jPanel23);

        jButton1.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jButton1.setText("Aggiorna l'Orario");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jButton2.setText("Orario PDF");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane2.addTab("Gestione Orari Film", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 941, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clean1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clean1ActionPerformed
        nome.setText("");
        cognome.setText("");
        dob.setText("yyyy-mm-dd");
        idCode.setText("");
        userid.setText("");
        password.setText("");
    }//GEN-LAST:event_clean1ActionPerformed

    private void idCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idCodeActionPerformed

    private void dobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dobActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dobActionPerformed

    private void add1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add1ActionPerformed

        String nom = nome.getText();
        String cognom = cognome.getText();
        String DOB = dob.getText();
        String idCod = idCode.getText();
        String user = userid.getText();
        String pass = password.getText();

        dbmn.newDipendente(nom,cognom,DOB,idCod,user, pass);
        update_tableD();
    }//GEN-LAST:event_add1ActionPerformed

    private void dipendenti_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dipendenti_tableMouseClicked
        try {
            //estrapolare il valore dalla tabella
            int row = dipendenti_table.getSelectedRow();
            String idDip =(dipendenti_table.getModel().getValueAt(row,6).toString());
            //sql
            String sql = "SELECT * FROM Dipendenti D LEFT JOIN User U ON D.ID_Dip=U.ID_Dipendente WHERE ID_Dipendente='"+idDip+"'";
            st = cn.prepareStatement(sql);
            rs = st.executeQuery();
            if (rs.next()){
                nome.setText(rs.getString("Nome"));
                cognome.setText(rs.getString("Cognome"));
                dob.setText(rs.getString("DataN"));
                userid.setText(rs.getString("userID"));
                password.setText(rs.getString("pass"));
                idCode.setText(rs.getString("ID_Dip"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_dipendenti_tableMouseClicked

    private void update1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update1ActionPerformed
        //update1 Dipendenti
        try {
            //estrapolare il valore dalla tabella
            int row = dipendenti_table.getSelectedRow();
            System.out.println("row no : "+row);
            String id =(dipendenti_table.getModel().getValueAt(row,0).toString());
            String nome =(dipendenti_table.getModel().getValueAt(row,1).toString());
            String cognome = (dipendenti_table.getModel().getValueAt(row,2).toString());
            String dob = (dipendenti_table.getModel().getValueAt(row,3).toString());
            String cat = (dipendenti_table.getModel().getValueAt(row,4).toString());
            String user = (dipendenti_table.getModel().getValueAt(row,5).toString());
            String pass = (dipendenti_table.getModel().getValueAt(row,6).toString());
            String id1 = (dipendenti_table.getModel().getValueAt(row,7).toString());
            //sql
            String comando ="UPDATE dipendenti SET ID_Dip='"+id+"',Nome='"+nome+"',Cognome='"+cognome+"',DataN='"+java.sql.Date.valueOf(dob)+"',Category ='"+cat+"' WHERE ID_Dip='"+id+"' OR Nome='"+nome+"' OR Cognome='"+cognome+"' OR DataN='"+java.sql.Date.valueOf(dob)+"'";
            st =cn.prepareStatement(comando);
            st.executeUpdate();
            if ((user!="")||(pass!="")||(id1.equalsIgnoreCase(id))){
                String comand1 ="UPDATE user SET userID='"+user+"',pass='"+pass+"',ID_Dipendente='"+id+"' WHERE userID='"+user+"' OR pass='"+pass+"' OR ID_Dipendente='"+id+"'";
                System.out.println(comand1);
                st =cn.prepareStatement(comand1);
                st.executeUpdate();
            }else {
                String deletec = "DELETE FROM User WHERE userID='"+user+"' OR pass='"+pass+"' OR ID_Dipendente='"+id+"'";
                st =cn.prepareStatement(deletec);
                st.executeUpdate();
            }
            update_tableD();                             //Richiamo la funzione per Aggiornare la tabella
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_update1ActionPerformed

    private void delete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete1ActionPerformed
        //Cancellare un istanza
        try {
            //estrapolare il valore dalla tabella
            int row = dipendenti_table.getSelectedRow();
            String ID =(dipendenti_table.getModel().getValueAt(row,0).toString());
            //sql
            String comando ="DELETE FROM dipendenti Where ID_Dip ='"+ID+"'";
            st = cn.prepareStatement(comando);
            st.executeUpdate();
            String comando1 ="DELETE FROM user Where ID_Dipendente ='"+ID+"'";
            st = cn.prepareStatement(comando1);
            st.executeUpdate();
            update_tableD();                             //Richiamo la funzione per Aggiornare la tabella
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_delete1ActionPerformed

    private void film_TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_film_TableMouseClicked
        try {
            //estrapolare il valore dalla tabella
            int row = film_Table.getSelectedRow();
            String casella =(film_Table.getModel().getValueAt(row,1).toString());
            //sql
            String comando ="Select * FROM films Where titolo ='"+casella+"'";
            st = cn.prepareStatement(comando);
            rs = st.executeQuery();
            if (rs.next()){
                titolo.setText(rs.getString("titolo"));
                anno.setText(rs.getString("anno"));
                scene.setText(rs.getString("scene"));
                regista.setText(rs.getString("regista"));
                photolink.setText(rs.getString("image"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_film_TableMouseClicked

    private void showImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showImageActionPerformed

        try {
            String img = photolink.getText();
            String title = titolo.getText();
            //Creaiamo una nuova finestra
            JFrame posterF = new JFrame(title);

            java.net.URL url = new URL(img);
            ImageIcon image = new ImageIcon(url);

            JLabel picLabel = new JLabel();
            picLabel.setIcon(image);
            posterF.add(picLabel);
            posterF.pack();
            posterF.setVisible(true);

            //finiamo con la nuova finestra
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_showImageActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        //update **********************************************************
        try {
            //estrapolare il valore dalla tabella
            int row = film_Table.getSelectedRow();
            System.out.println("row no : "+row);
            String id =(film_Table.getModel().getValueAt(row,0).toString());
            String titolo =(film_Table.getModel().getValueAt(row,1).toString());
            String anno = (film_Table.getModel().getValueAt(row,2).toString());
            String scene = (film_Table.getModel().getValueAt(row,3).toString());
            String regista = (film_Table.getModel().getValueAt(row,4).toString());
            
            //sql
            String comando ="UPDATE films SET titolo='"+titolo+"',anno="+Integer.parseInt(anno)+",scene='"+scene+"',regista='"+regista+"' WHERE ID="+id;
            System.out.println(comando);
            st =cn.prepareStatement(comando);
            st.executeUpdate();

            update_table();                             //Richiamo la funzione per Aggiornare la tabella
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_updateActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        int reply = JOptionPane.showConfirmDialog(null, "Sei Sicuro di Cancellare la istanza ?\nSe Procedi anche i Film inseriti nell'orario verranno cancellati.", "Conferma per cancellazione", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            //Cancellare un istanza
            try {
                //estrapolare il valore dalla tabella
                int row = film_Table.getSelectedRow();
                String casella =(film_Table.getModel().getValueAt(row,0).toString());
                String tit = (film_Table.getModel().getValueAt(row,1).toString());
                //sql
                String comando ="DELETE FROM films Where ID ='"+casella+"'";
                st = cn.prepareStatement(comando);
                st.executeUpdate();
                update_table();                             //Richiamo la funzione per Aggiornare la tabella
                //Cancelliamo le istanze con questo titolo nella tabella Orario.
                String que = "UPDATE orari SET titolo=null,sala=null WHERE titolo='"+tit+"'";
                
                st = cn.prepareStatement(que);
                st.executeUpdate();
                
                
                //Cancelliamo il titolo anche dalle combobox Dell'Orario
                int count =0;
                int N = lunFilm1.getItemCount();
                String T ="";
                while (count<N){
                    T = (String) lunFilm1.getSelectedItem();
                    if (T.equalsIgnoreCase(tit)){
                        break;
                    }
                    count++;
                }
                
                //dopo aver trovato l'index del titolo lo cancelliamo in tutte le
                lunFilm1.removeItemAt(count-1);
                lunFilm2.removeItemAt(count-1);
                lunFilm3.removeItemAt(count-1);
                lunFilm4.removeItemAt(count-1);
                marFilm1.removeItemAt(count-1);
                marFilm2.removeItemAt(count-1);
                marFilm3.removeItemAt(count-1);
                marFilm4.removeItemAt(count-1);
                merFilm1.removeItemAt(count-1);
                merFilm2.removeItemAt(count-1);
                merFilm3.removeItemAt(count-1);
                merFilm4.removeItemAt(count-1);
                gioFilm1.removeItemAt(count-1);
                gioFilm2.removeItemAt(count-1);
                gioFilm3.removeItemAt(count-1);
                gioFilm4.removeItemAt(count-1);
                venFilm1.removeItemAt(count-1);
                venFilm2.removeItemAt(count-1);
                venFilm3.removeItemAt(count-1);
                venFilm4.removeItemAt(count-1);
                sabFilm1.removeItemAt(count-1);
                sabFilm2.removeItemAt(count-1);
                sabFilm3.removeItemAt(count-1);
                sabFilm4.removeItemAt(count-1);
                domFilm1.removeItemAt(count-1);
                domFilm2.removeItemAt(count-1);
                domFilm3.removeItemAt(count-1);
                domFilm4.removeItemAt(count-1);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else {
                //niente.
        }
        
        
    }//GEN-LAST:event_deleteActionPerformed

    private void cleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cleanActionPerformed
        titolo.setText("");
        anno.setText("");
        scene.setText("");
        regista.setText("");
        photolink.setText("");
    }//GEN-LAST:event_cleanActionPerformed

    private void annoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_annoActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed

        String tit = titolo.getText();
        int Anno = Integer.parseInt(anno.getText());
        String sc = scene.getText();
        String reg = regista.getText();
        String imageUrl =photolink.getText();
        System.out.println(tit+" "+Anno+" "+sc+" "+reg+"  "+imageUrl);

        dbmn.newFilm(tit, Anno, sc, reg,imageUrl);
        update_table();
        lunFilm1.addItem(tit);
        lunFilm2.addItem(tit);
        lunFilm3.addItem(tit);
        lunFilm4.addItem(tit);
        marFilm1.addItem(tit);
        marFilm2.addItem(tit);
        marFilm3.addItem(tit);
        marFilm4.addItem(tit);
        merFilm1.addItem(tit);
        merFilm2.addItem(tit);
        merFilm3.addItem(tit);
        merFilm4.addItem(tit);
        gioFilm1.addItem(tit);
        gioFilm2.addItem(tit);
        gioFilm3.addItem(tit);
        gioFilm4.addItem(tit);
        venFilm1.addItem(tit);
        venFilm2.addItem(tit);
        venFilm3.addItem(tit);
        venFilm4.addItem(tit);
        sabFilm1.addItem(tit);
        sabFilm2.addItem(tit);
        sabFilm3.addItem(tit);
        sabFilm4.addItem(tit);
        domFilm1.addItem(tit);
        domFilm2.addItem(tit);
        domFilm3.addItem(tit);
        domFilm4.addItem(tit);
    }//GEN-LAST:event_addActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //Variabili temporanee per Lunedi
        String filmL1 =(String)lunFilm1.getSelectedItem();
        String filmL2 =(String)lunFilm2.getSelectedItem();
        String filmL3 =(String)lunFilm3.getSelectedItem();
        String filmL4 =(String)lunFilm4.getSelectedItem();
        //Variabili temporanee per Martedi
        String filmMa1 =(String)marFilm1.getSelectedItem();
        String filmMa2 =(String)marFilm2.getSelectedItem();
        String filmMa3 =(String)marFilm3.getSelectedItem();
        String filmMa4 =(String)marFilm4.getSelectedItem();
        //Variabili temporanee per Mercoledi
        String filmMe1 =(String)merFilm1.getSelectedItem();
        String filmMe2 =(String)merFilm2.getSelectedItem();
        String filmMe3 =(String)merFilm3.getSelectedItem();
        String filmMe4 =(String)merFilm4.getSelectedItem();
        //Variabili temporanee per Giovedi
        String filmG1 =(String)gioFilm1.getSelectedItem();
        String filmG2 =(String)gioFilm2.getSelectedItem();
        String filmG3 =(String)gioFilm3.getSelectedItem();
        String filmG4 =(String)gioFilm4.getSelectedItem();
        //Variabili temporanee per Venerdi
        String filmV1 =(String)venFilm1.getSelectedItem();
        String filmV2 =(String)venFilm2.getSelectedItem();
        String filmV3 =(String)venFilm3.getSelectedItem();
        String filmV4 =(String)venFilm4.getSelectedItem();
        //Variabili temporanee per Sabato
        String filmS1 =(String)sabFilm1.getSelectedItem();
        String filmS2 =(String)sabFilm2.getSelectedItem();
        String filmS3 =(String)sabFilm3.getSelectedItem();
        String filmS4 =(String)sabFilm4.getSelectedItem();
        //Variabili temporanee per Domenica
        String filmD1 =(String)domFilm1.getSelectedItem();
        String filmD2 =(String)domFilm2.getSelectedItem();
        String filmD3 =(String)domFilm3.getSelectedItem();
        String filmD4 =(String)domFilm4.getSelectedItem();
        
        
        //Lunedi
        update_orari(filmL1,"1","lunedi","16:30");
        update_orari(filmL2,"1","lunedi","19:30");
        update_orari(filmL3,"2","lunedi","16:30");
        update_orari(filmL4,"2","lunedi","19:30");
        //Martedi
        update_orari(filmL1,"1","martedi","16:30");
        update_orari(filmL2,"1","martedi","19:30");
        update_orari(filmL3,"2","martedi","16:30");
        update_orari(filmL4,"2","martedi","19:30");
        //Mercoledi
        update_orari(filmL1,"1","mercoledi","16:30");
        update_orari(filmL2,"1","mercoledi","19:30");
        update_orari(filmL3,"2","mercoledi","16:30");
        update_orari(filmL4,"2","mercoledi","19:30");
        //Giovedi
        update_orari(filmL1,"1","giovedi","16:30");
        update_orari(filmL2,"1","giovedi","19:30");
        update_orari(filmL3,"2","giovedi","16:30");
        update_orari(filmL4,"2","giovedi","19:30");
        //venerdi
        update_orari(filmL1,"1","venerdi","16:30");
        update_orari(filmL2,"1","venerdi","19:30");
        update_orari(filmL3,"2","venerdi","16:30");
        update_orari(filmL4,"2","venerdi","19:30");
        //Sabato
        update_orari(filmL1,"1","sabato","16:30");
        update_orari(filmL2,"1","sabato","19:30");
        update_orari(filmL3,"2","sabato","16:30");
        update_orari(filmL4,"2","sabato","19:30");
        //Domenica
        update_orari(filmL1,"1","domenica","16:30");
        update_orari(filmL2,"1","domenica","19:30");
        update_orari(filmL3,"2","domenica","16:30");
        update_orari(filmL4,"2","domenica","19:30");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JTable tbl = new JTable();
        
        String str = "SELECT ora,giorno,titolo,sala FROM orari";
        try {
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream("orario.pdf"));
            doc.open();
            doc.add(new Paragraph("Orario Settimanale"));
            doc.add(new Paragraph("                            "));
            doc.add(new Paragraph("                            "));
            PdfPTable table = new PdfPTable(4);
            table.addCell("Ora");
            table.addCell("Giorno");
            table.addCell("Titolo");
            table.addCell("Sala");
            
            st = cn.prepareCall(str);
            rs = st.executeQuery();
            
            while (rs.next()) {
                String or = rs.getString("ora");
                String gio = rs.getString("giorno");
                String tit = rs.getString("titolo");
                String sal = rs.getString("sala");
                table.addCell(or);
                table.addCell(gio);
                table.addCell(tit);
                table.addCell(sal);
            }
            doc.add(table);
            doc.close();
            Desktop.getDesktop().open(new File("orario.pdf"));
        } catch (Exception e) {
            System.out.println(e);
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void lunFilm1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lunFilm1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lunFilm1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Mng.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mng.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mng.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mng.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Mng().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JButton add1;
    private javax.swing.JTextField anno;
    private javax.swing.JButton clean;
    private javax.swing.JButton clean1;
    private javax.swing.JTextField cognome;
    private javax.swing.JButton delete;
    private javax.swing.JButton delete1;
    private javax.swing.JTable dipendenti_table;
    private javax.swing.JTextField dob;
    private javax.swing.JComboBox domFilm1;
    private javax.swing.JComboBox domFilm2;
    private javax.swing.JComboBox domFilm3;
    private javax.swing.JComboBox domFilm4;
    private javax.swing.JTable film_Table;
    private javax.swing.JComboBox gioFilm1;
    private javax.swing.JComboBox gioFilm2;
    private javax.swing.JComboBox gioFilm3;
    private javax.swing.JComboBox gioFilm4;
    private javax.swing.JTextField idCode;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JComboBox lunFilm1;
    private javax.swing.JComboBox lunFilm2;
    private javax.swing.JComboBox lunFilm3;
    private javax.swing.JComboBox lunFilm4;
    private javax.swing.JComboBox marFilm1;
    private javax.swing.JComboBox marFilm2;
    private javax.swing.JComboBox marFilm3;
    private javax.swing.JComboBox marFilm4;
    private javax.swing.JComboBox merFilm1;
    private javax.swing.JComboBox merFilm2;
    private javax.swing.JComboBox merFilm3;
    private javax.swing.JComboBox merFilm4;
    private javax.swing.JTextField nome;
    private javax.swing.JTextField password;
    private javax.swing.JTextField photolink;
    private javax.swing.JTextField regista;
    private javax.swing.JComboBox sabFilm1;
    private javax.swing.JComboBox sabFilm2;
    private javax.swing.JComboBox sabFilm3;
    private javax.swing.JComboBox sabFilm4;
    private javax.swing.JTextField scene;
    private javax.swing.JButton showImage;
    private javax.swing.JTextField titolo;
    private javax.swing.JButton update;
    private javax.swing.JButton update1;
    private javax.swing.JTextField userid;
    private javax.swing.JComboBox venFilm1;
    private javax.swing.JComboBox venFilm2;
    private javax.swing.JComboBox venFilm3;
    private javax.swing.JComboBox venFilm4;
    // End of variables declaration//GEN-END:variables
    
}


