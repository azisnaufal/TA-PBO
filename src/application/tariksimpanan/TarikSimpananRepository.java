/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.tariksimpanan;

import application.util.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ary
 */
public class TarikSimpananRepository {
    private static TarikSimpananRepository instance = null;
    private MySQLConnection db = null; 
    
    private TarikSimpananRepository(){
        this.db = MySQLConnection.getInstance();
    }
    
    public static TarikSimpananRepository getInstance(){
        if(instance == null){
            instance = new TarikSimpananRepository();
        }
        return instance;
    }
    
    public boolean insert(TarikSimpanan tarikSimpanan){
        boolean success = false;
        
        String sql = "INSERT INTO HistorySimpanan (id_anggota, poin_simpanan_sukarela, jumlah_uang, created_at) Value(?,?,?,?)";
        
        Connection con = db.getConnection();
        
        try{
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, tarikSimpanan.getId_anggota());    
            preparedStatement.setInt(2, tarikSimpanan.getPoin_ss());
            preparedStatement.setInt(3, tarikSimpanan.getJumlah_uang());
            preparedStatement.setTimestamp(4, tarikSimpanan.getTanggal());
            
            preparedStatement.execute();
            
            success = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return success;
    }
    
    public boolean getPoin_sukarela(TarikSimpanan tarikSimpanan) {
        boolean success = false;
        
        String sql = "SELECT * FROM HistorySimpanan WHERE id_simpanan = 1"; //masih salah
 
        try {
            Connection con = db.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                int sql2 = rs.getInt("poin_simpanan_sukarela"); //masih salah
                System.out.println(sql2);
                System.out.println(tarikSimpanan.getPoin_sukarela());
                if(tarikSimpanan.getPoin_sukarela() >= sql2){
                    System.out.println("\tUang Tidak Cukup");
                    success = false;
                }else{
                    System.out.println("\tBerhasil !!!");
                    success = true;
                }
            } 
        } catch (Exception e) {
            System.out.println(e);
        }
        return success;
    }
    
}
