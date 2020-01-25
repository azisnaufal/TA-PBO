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
        
        String sql = "SELECT SUM(poin_simpanan_sukarela) "
                + "FROM HistorySimpanan "
                + "WHERE id_anggota = ? LIMIT 1";

        try {
            Connection con = db.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, tarikSimpanan.getId_anggota());
            preparedStatement.execute();
            
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                int sql1 = rs.getInt("SUM(poin_simpanan_sukarela)"); 
                if(tarikSimpanan.getPoin_sukarela() > sql1){
                    System.out.println("\tPoin Anda Tidak Cukup");
                    System.out.println("\tTotal Poin Simpanan Sukarela Anda : "+sql1);
                    success = false;
                }else{
                    int sql2 = sql1 - tarikSimpanan.getPoin_sukarela();
                    System.out.println("\tSisa Poin Simpanan Sukarela Anda : "+sql2);
                    
                    success = true;
                }
            } 
        } catch (Exception e) {
            System.out.println(e);
        }
        return success;
    }
    
    //public List<TarikSimpanan> get(String id_anggota){
        //List<TarikSimpanan> tarikSimpanans = new ArrayList<>();
        
        //String sql = "SELECT DISTINC id_anggota FROM HistorySimpanan";
        
        //try{
            //Connection con = db.getConnection();
            //PreparedStatement preparedStatement = con.prepareStatement(sql);
            //preparedStatement.execute();
            //ResultSet rs = preparedStatement.executeQuery();
            //while(rs.next()){
                //TarikSimpanan tarikSimpanan = new TarikSimpanan();
                //tarikSimpanan.setTampilan_id_anggota(rs.getString("id_anggota"));
                
                //tarikSimpanans.add(tarikSimpanan);
            //}
            
        //}catch (Exception e){
            //e.printStackTrace();
        //}
        
        //return tarikSimpanans;
    //}  
}
