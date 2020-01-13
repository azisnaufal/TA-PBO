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
        
        String sql = "";
        
        
        
        return anggotas;
    }
}
