/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.bayar;

import application.util.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import application.anggota.Anggota;

/**
 *
 * @author binta
 */
public class BayarRepository  {
    private static BayarRepository instance = null;
    private MySQLConnection db = null; 
    
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
    
    public List<Anggota> getDaftarAnggota(){
        String sql = "SELECT * FROM `Anggota`";
        
        List<Anggota> daftarAnggota = new ArrayList<>();
        
        try {
            Connection con = db.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Anggota anggota = new Anggota();
                anggota.setId_anggota(resultSet.getString("id_anggota"));
                anggota.setNo_KTP(resultSet.getString("no_KTP"));
                anggota.setNama_lengkap(resultSet.getString("nama_lengkap"));
                anggota.setAlamat(resultSet.getString("alamat"));
                anggota.setTtl(resultSet.getString("ttl"));
                anggota.setNomor_telepon(resultSet.getString("nomor_telepon"));
                daftarAnggota.add(anggota);
            }
            db.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        return daftarAnggota;
       
    }
    
   
}
