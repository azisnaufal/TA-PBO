/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.base;

import application.anggota.Anggota;
import application.util.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author oazisn
 */
public class BaseRepository {
    protected MySQLConnection db = null; 

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
                anggota.setCreated_at(resultSet.getTimestamp("created_at"));
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
