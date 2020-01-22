/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.tariksimpanan;

import application.util.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
        String sql = "INSERT INTO Simpanan (id_anggota, poin_ss, jumlah_uang, bulan) Value(?,?,?,?)";
        Connection con = db.getConnection();
        
        try{
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, tarikSimpanan.getId_anggota());
            preparedStatement.setInt(2, tarikSimpanan.getPoin_ss());
            preparedStatement.setInt(3, tarikSimpanan.getJumlah_uang());
            preparedStatement.setString(4, tarikSimpanan.getBulan());
            
            preparedStatement.execute();
            success = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return success;
        
    }
}
