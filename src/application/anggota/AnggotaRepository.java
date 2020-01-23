/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.anggota;


import application.mahasiswa.MahasiswaRepository;
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
    
    public List<Anggota> get(String params) {
        List<Anggota> anggotas = new ArrayList<>();
        
        String sql = "INSERT INTO Anggota (no_KTP, nama_lengkap, alamat, ttl, nomor_telepon)";
        
        try {
            
        } catch (Exception e) {
            
        }
        
        return anggotas;
    }
    
    public boolean insert(Anggota anggota) {
        boolean success = false;
        String sql = "INSERT INTO Anggota (id_anggota, no_KTP, nama_lengkap, alamat, ttl, nomor_telepon, bulan_daftar) value(?,?,?,?,?,?,?)";
        Connection con = db.getConnection();
        
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, anggota.getId_anggota());
            preparedStatement.setString(2, anggota.getNo_KTP());
            preparedStatement.setString(3, anggota.getNama_lengkap());
            preparedStatement.setString(4, anggota.getAlamat());
            preparedStatement.setString(5, anggota.getTtl());
            preparedStatement.setString(6, anggota.getNomor_telepon());
            preparedStatement.setString(7, anggota.getBulan_masuk());
            
            preparedStatement.execute();
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }
    
    public int getId_anggota(String nama_bulan, String tahun) {
        int temp = 0;

        // ksb/202001/001 
        // where id_anggota = "ksb/202001/%" 
        
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
    
    
}
