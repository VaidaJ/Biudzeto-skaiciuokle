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
public class IslaidosTOP3 {
    static DB hci;
    private String kategorija;
    private double suma;

    public IslaidosTOP3(String kategorija, double suma) {
        this.kategorija = kategorija;
        this.suma = suma;
    }

    public String getKategorija() {
        return kategorija;
    }

    public double getSuma() {
        return suma;
    }
    
    public static ArrayList<IslaidosTOP3> top3Islaidos(){
                ArrayList<IslaidosTOP3> islaidosTOP = new ArrayList<IslaidosTOP3>();
        try{
        Connection connection = hci.getConnection();
            Statement stmt = (Statement) connection.createStatement();
            String sql = "SELECT kategorija, SUM(suma) FROM islaidos GROUP BY kategorija ORDER BY SUM(suma) DESC LIMIT 3;";
            ResultSet rs = stmt.executeQuery(sql);   
            while( rs.next()){
                IslaidosTOP3 islaidos = new IslaidosTOP3(rs.getString("kategorija"), rs.getDouble("SUM(suma)"));
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

    public static ArrayList<IslaidosTOP3> filtredTop3Islaidos(Date nuo, Date iki){
        ArrayList<IslaidosTOP3> filtredTop3Islaidos = new ArrayList<IslaidosTOP3>();
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
                IslaidosTOP3 islaidos = new IslaidosTOP3(rs.getString("kategorija"), rs.getDouble("SUM(suma)"));
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
