/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.bayar;

import application.util.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import application.base.BaseRepository;

/**
 *
 * @author binta
 */
public class BayarRepository  extends BaseRepository{
    private static BayarRepository instance = null;
    
    private BayarRepository(){
        this.db = MySQLConnection.getInstance();
    }
    
    public static BayarRepository getInstance(){
        if(instance == null){
            instance = new BayarRepository();
        }
        return instance;
    }
    
    public boolean insert(Bayar bayar){
        boolean success = false;
        
        String sql = "INSERT INTO HistorySimpanan (id_anggota, poin_simpanan_wajib, poin_simpanan_sukarela, created_at) Value(?,?,?,?)";
        
        Connection con = db.getConnection();
        
        try{
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, bayar.getId_anggota());    
            preparedStatement.setInt(2, bayar.getPoin_wajib());
            preparedStatement.setInt(3, bayar.getPoin_sukarela());
            preparedStatement.setTimestamp(4, bayar.getTanggal());
            
            preparedStatement.execute();
            
            success = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return success;
    }
    
}
