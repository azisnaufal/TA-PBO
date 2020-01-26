/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.anggota;

import application.util.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Farhan Rafly Fahrezi
 */
public class AnggotaRepository {
    private static AnggotaRepository instance = null;
    private MySQLConnection db = null;
    
    private AnggotaRepository() {
        this.db = MySQLConnection.getInstance();
    }
    
    public static AnggotaRepository getInstance() {
        if (instance == null) {
            instance = new AnggotaRepository();
        } 
        
        return instance;
    }
    
    public boolean insert(Anggota anggota) {
        boolean success = false;
        String sql = "INSERT INTO Anggota (id_anggota, no_KTP, nama_lengkap, alamat, ttl, nomor_telepon) value(?,?,?,?,?,?)";
        Connection con = db.getConnection();
        
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, anggota.getId_anggota());
            preparedStatement.setString(2, anggota.getNo_KTP());
            preparedStatement.setString(3, anggota.getNama_lengkap());
            preparedStatement.setString(4, anggota.getAlamat());
            preparedStatement.setString(5, anggota.getTtl());
            preparedStatement.setString(6, anggota.getNomor_telepon());
            
            preparedStatement.execute();
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }
    
    public int getId_anggota(String nama_bulan, String tahun) {
        int temp = 0;
        
        String query = "KSB/" + tahun + nama_bulan + "/%";
        
        String sql = "SELECT COUNT(no_KTP) AS countResult FROM Anggota WHERE id_anggota LIKE ?";
        
        try {
            Connection con = db.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, query);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                temp = resultSet.getInt("countResult");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return temp;
    }
    
    public List<String> get(String params) {
        List<String> anggotas = new ArrayList<>();
        params = "KSB/" + params;
        
        String sql = "SELECT * FROM Anggota WHERE id_anggota = ?";
        try {
            Connection con = db.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, params);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
//                Anggota anggota = new Anggota();
//                anggota.setNama_lengkap(resultSet.getString("no_KTP"));
//                anggota.setAlamat(resultSet.getString("alamat"));
//                anggota.setTtl(resultSet.getString("ttl"));
//                anggota.setNomor_telepon(resultSet.getString("nomor_telepon"));
                
                anggotas.add(resultSet.getString("id_anggota"));
                anggotas.add(resultSet.getString("no_KTP"));
                anggotas.add(resultSet.getString("nama_lengkap"));
                anggotas.add(resultSet.getString("alamat"));
                anggotas.add(resultSet.getString("ttl"));
                anggotas.add(resultSet.getString("nomor_telepon"));
            }
            db.close();
        } catch (Exception e) {
            System.out.println("\tData tidak ditemukan.");
        }
        return anggotas;
    }
}
