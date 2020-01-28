/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.tariksimpanan;

import application.base.BaseRepository;
import application.util.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 *
 * @author Ary
 */
public class TarikSimpananRepository extends BaseRepository{
    private static TarikSimpananRepository instance = null;
    
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
        
        String sql = "INSERT INTO HistorySimpanan (id_anggota, poin_simpanan_sukarela, created_at) Value(?,?,?)";
        
        Connection con = db.getConnection();
        
        try{
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, tarikSimpanan.getId_anggota());    
            preparedStatement.setInt(2, tarikSimpanan.getPoin_ss());
            preparedStatement.setTimestamp(3, tarikSimpanan.getTanggal());
            
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

}
