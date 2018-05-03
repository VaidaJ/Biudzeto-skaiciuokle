/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Biudzeto_skaiciuokle;

import static Biudzeto_skaiciuokle.IslaidosTOP3.hci;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Vaidos
 */
public class Islaidos {
    static DB db;
    private int id;
    private double suma;
    private String islaidu_kategorija;
    private Date islaidu_data;
    private String komentaras;

    public Islaidos(int id, double suma, String kategorija, Date data, String komentaras) {
        this.id = id;
        this.suma = suma;
        this.islaidu_kategorija = kategorija;
        this.islaidu_data = data;
        this.komentaras = komentaras;
    }

    public static DB getDb() {
        return db;
    }

    public int getId() {
        return id;
    }

    public double getSuma() {
        return suma;
    }

    public String getIslaidu_kategorija() {
        return islaidu_kategorija;
    }

    public Date getIslaidu_data() {
        return islaidu_data;
    }

    public String getKomentaras() {
        return komentaras;
    }


    
    public static ArrayList<Islaidos> islaidosList(){
        ArrayList<Islaidos> islaidosList = new ArrayList<Islaidos>();
        try{
        Connection connection = db.getConnection();
            Statement stmt = (Statement) connection.createStatement();
            String sql = "SELECT * FROM islaidos ORDER BY islaidu_data DESC";
            ResultSet rs = stmt.executeQuery(sql);   
            while( rs.next()){
                Islaidos islaidos = new Islaidos(rs.getInt("islaidu_ID"), rs.getDouble("suma"), rs.getString("kategorija"), rs.getDate("islaidu_data"), rs.getString("komentaras"));
                islaidosList.add(islaidos);
            }    
            rs.close();
            stmt.close();
            connection.close();
        } catch (Exception ex) {
                        ex.printStackTrace();
        }
        return islaidosList;
    }  
    
    public static ArrayList<Islaidos> filtredIslaidosList(Date nuo, Date iki){
        ArrayList<Islaidos> filtredIslaidosList = new ArrayList<Islaidos>();
        try{
            Connection conn = db.getConnection();
            String sql = "SELECT * FROM islaidos WHERE islaidu_data BETWEEN ?  AND ? ORDER BY islaidu_data DESC";
            PreparedStatement stmt = conn.prepareStatement(sql);
            java.sql.Date sqlData1 = new java.sql.Date(nuo.getTime());
            java.sql.Date sqlData2 = new java.sql.Date(iki.getTime());
            stmt.setDate(1, sqlData1);
            stmt.setDate(2, sqlData2);
            ResultSet rs = stmt.executeQuery();
            while( rs.next()){
                Islaidos islaidos = new Islaidos(rs.getInt("islaidu_ID"), rs.getDouble("suma"), rs.getString("kategorija"), rs.getDate("islaidu_data"), rs.getString("komentaras"));
                filtredIslaidosList.add(islaidos);
            }    
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Netinkamai įvesta (arba neįvesta) data!" + "\n" + "Datos formatas: YYYY-MM-DD", "Klaida", JOptionPane.ERROR_MESSAGE);
        }
        return filtredIslaidosList;
    }      
 
    public static void insertIslaidos(double suma, String kategorija, String data, String komentaras){
        try {
            Connection conn = db.getConnection();
            String SQL = "INSERT INTO islaidos(suma, kategorija, islaidu_data, komentaras) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            stmt.getGeneratedKeys();
            stmt.setDouble(1, suma);
            stmt.setString(2, kategorija);
            stmt.setString(3, data);
            stmt.setString(4, komentaras);
            stmt.executeUpdate(); 
            ResultSet rs = stmt.getGeneratedKeys();
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }  
    
    public static double islaiduSuma(){
        double islaiduSuma = 0;   
        try{           
            Connection connection = db.getConnection();
            Statement stmt = (Statement) connection.createStatement();
            String sql = "SELECT SUM(suma) FROM islaidos";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                islaiduSuma = Double.parseDouble(rs.getString("SUM(suma)"));
            }           
            rs.close();
            stmt.close();
            connection.close();
            return islaiduSuma;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return islaiduSuma;
    }
    
    public static double filtredIslaiduSuma(Date nuo, Date iki){
        double islaiduSuma = 0;   
        try{           
            Connection conn = db.getConnection();
            String sql = "SELECT SUM(suma) FROM islaidos WHERE islaidu_data BETWEEN ?  AND ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            java.sql.Date sqlData1 = new java.sql.Date(nuo.getTime());
            java.sql.Date sqlData2 = new java.sql.Date(iki.getTime());
            stmt.setDate(1, sqlData1);
            stmt.setDate(2, sqlData2);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                islaiduSuma = Double.parseDouble(rs.getString("SUM(suma)"));
            }     
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Netinkamai įvesta (arba neįvesta) data!" + "\n" + "Datos formatas: YYYY-MM-DD", "Klaida", JOptionPane.ERROR_MESSAGE);
        }
        return islaiduSuma;
    }
    
    public static void updateIslaidos(int ID, double suma, String kategorija, String data, String komentaras){
        try{
            Connection conn = db.getConnection();
            String sql = "UPDATE islaidos SET suma = ?, kategorija = ?, islaidu_data = ?, komentaras = ? WHERE islaidu_ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, suma);
            stmt.setString(2, kategorija);
            stmt.setString(3, data);
            stmt.setString(4, komentaras);
            stmt.setInt(5, ID);
            stmt.executeUpdate(); 
            stmt.close();
            conn.close();
        }catch (Exception ex) {
            ex.printStackTrace();
        } 
    } 
    
    public static void deleteIslaidos(int ID){
        try{
            Connection conn = db.getConnection();
            String sql = "DELETE FROM islaidos WHERE islaidu_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, ID);
            stmt.executeUpdate();
            stmt.close();
            conn.close();  
        }catch (Exception ex) {
            ex.printStackTrace();
        } 
    }

    public static ArrayList<Islaidos> top3Islaidos(){
                ArrayList<Islaidos> islaidosTOP = new ArrayList<Islaidos>();
        try{
        Connection connection = hci.getConnection();
            Statement stmt = (Statement) connection.createStatement();
            String sql = "SELECT kategorija, SUM(suma) FROM islaidos GROUP BY kategorija ORDER BY SUM(suma) DESC LIMIT 3;";
            ResultSet rs = stmt.executeQuery(sql);   
            while( rs.next()){
                Islaidos islaidos = new Islaidos(rs.getInt("islaidu_ID"), rs.getDouble("SUM(suma)"), rs.getString("kategorija"), rs.getDate("islaidu_data"), rs.getString("komentaras"));
                islaidosTOP.add(islaidos);
            }    
            rs.close();
            stmt.close();
            connection.close();
        } catch (Exception ex) {
                        ex.printStackTrace();
        }
        return islaidosTOP;
    }

    public static ArrayList<Islaidos> filtredTop3Islaidos(Date nuo, Date iki){
        ArrayList<Islaidos> filtredTop3Islaidos = new ArrayList<Islaidos>();
        try{
            Connection conn = hci.getConnection();
            String sql = "SELECT kategorija, SUM(suma) FROM islaidos WHERE islaidu_data BETWEEN ?  AND ? GROUP BY kategorija ORDER BY SUM(suma) DESC LIMIT 3;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            java.sql.Date sqlData1 = new java.sql.Date(nuo.getTime());
            java.sql.Date sqlData2 = new java.sql.Date(iki.getTime());
            stmt.setDate(1, sqlData1);
            stmt.setDate(2, sqlData2);
            ResultSet rs = stmt.executeQuery();
            while( rs.next()){
                Islaidos islaidos = new Islaidos(rs.getInt("islaidu_ID"), rs.getDouble("SUM(suma)"), rs.getString("kategorija"), rs.getDate("islaidu_data"), rs.getString("komentaras"));
                filtredTop3Islaidos.add(islaidos);
            }    
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return filtredTop3Islaidos;
    }         
    

}
