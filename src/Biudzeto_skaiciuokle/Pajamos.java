/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Biudzeto_skaiciuokle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import static Biudzeto_skaiciuokle.Islaidos.db;

/**
 *
 * @author Vaidos
 */
public class Pajamos {
    static DB hci;
    private int id;
    private double suma;
    private String kategorija;
    private Date data;
    private String komentaras;

    public Pajamos(int id, double suma, String kategorija, Date data, String komentaras) {
        this.id = id;
        this.suma = suma;
        this.kategorija = kategorija;
        this.data = data;
        this.komentaras = komentaras;
    }

    public static DB getHci() {
        return hci;
    }

    public int getId() {
        return id;
    }

    public double getSuma() {
        return suma;
    }

    public String getKategorija() {
        return kategorija;
    }

    public Date getData() {
        return data;
    }

    public String getKomentaras() {
        return komentaras;
    }
    
    public static ArrayList<Pajamos> pajamosList(){
        ArrayList<Pajamos> pajamosList = new ArrayList<Pajamos>();
        try{
        Connection connection = hci.getConnection();
            Statement stmt = (Statement) connection.createStatement();
            String sql = "SELECT * FROM pajamos ORDER BY pajamu_data DESC";
            ResultSet rs = stmt.executeQuery(sql);   
            while( rs.next()){
                Pajamos pajamos = new Pajamos(rs.getInt("pajamu_ID"), rs.getDouble("suma"), rs.getString("kategorija"), rs.getDate("pajamu_data"), rs.getString("komentaras"));
                pajamosList.add(pajamos);
            }    
            rs.close();
            stmt.close();
            connection.close();
        } catch (Exception ex) {
                        ex.printStackTrace();
        }
        return pajamosList;
    }   
    
    public static ArrayList<Pajamos> filtredPajamosList(Date nuo, Date iki){
        ArrayList<Pajamos> filtredPajamosList = new ArrayList<Pajamos>();
        try{
            Connection conn = hci.getConnection();
            String sql = "SELECT * FROM pajamos WHERE pajamu_data BETWEEN ?  AND ? ORDER BY pajamu_data DESC";
            PreparedStatement stmt = conn.prepareStatement(sql);
            java.sql.Date sqlData1 = new java.sql.Date(nuo.getTime());
            java.sql.Date sqlData2 = new java.sql.Date(iki.getTime());
            stmt.setDate(1, sqlData1);
            stmt.setDate(2, sqlData2);
            ResultSet rs = stmt.executeQuery();
            while( rs.next()){
                Pajamos pajamos = new Pajamos(rs.getInt("pajamu_ID"), rs.getDouble("suma"), rs.getString("kategorija"), rs.getDate("pajamu_data"), rs.getString("komentaras"));
                filtredPajamosList.add(pajamos);
            }    
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Netinkamai įvesta (arba neįvesta) data!" + "\n" + "Datos formatas: YYYY-MM-DD", "Klaida", JOptionPane.ERROR_MESSAGE);
        }
        return filtredPajamosList;
    }         
 
    public static void insertPajamos(double suma, String kategorija, String data, String komentaras){
        try {
            Connection conn = hci.getConnection();
            String SQL = "INSERT INTO pajamos(suma, kategorija, pajamu_data, komentaras) VALUES (?, ?, ?, ?)";
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

    public static double pajamuSuma(){
        double pajamuSuma = 0;   
        try{           
            Connection connection = hci.getConnection();
            Statement stmt = (Statement) connection.createStatement();
            String sql = "SELECT SUM(suma) FROM pajamos";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                pajamuSuma = Double.parseDouble(rs.getString("SUM(suma)"));
            }           
            rs.close();
            stmt.close();
            connection.close();
            return pajamuSuma;
        } catch (Exception ex) {
                        ex.printStackTrace();
        }
        return pajamuSuma;
    } 
    
        public static double filtredPajamuSuma(Date nuo, Date iki){
        double islaiduSuma = 0;   
        try{           
            Connection conn = hci.getConnection();
            String sql = "SELECT SUM(suma) FROM pajamos WHERE pajamu_data BETWEEN ?  AND ?";
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
            //JOptionPane.showMessageDialog(null, "Netinkamai įvesta (arba neįvesta) data!" + "\n" + "Datos formatas: YYYY-MM-DD", "Klaida", JOptionPane.ERROR_MESSAGE);
        }
        return islaiduSuma;
    }
    
    public static void updatePajamos(int ID, double suma, String kategorija, String data, String komentaras){
        try{
            Connection conn = hci.getConnection();
            String sql = "UPDATE pajamos SET suma = ?, kategorija = ?, pajamu_data = ?, komentaras = ? WHERE pajamu_ID = ?";
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
    
    public static void deletePajamos(int ID){
        try{
            Connection conn = hci.getConnection();
            String sql = "DELETE FROM pajamos WHERE pajamu_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, ID);
            stmt.executeUpdate();
            stmt.close();
            conn.close();  
        }catch (Exception ex) {
            ex.printStackTrace();
        } 
    }
    
}
