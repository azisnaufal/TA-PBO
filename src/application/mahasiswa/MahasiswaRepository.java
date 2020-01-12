/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package application.mahasiswa;

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
public class MahasiswaRepository {
    private static MahasiswaRepository instance = null;
    private MySQLConnection db = null;
    
    private MahasiswaRepository(){
        this.db = MySQLConnection.getInstance();
    }
    
    public static MahasiswaRepository getInstance(){
        if (instance == null)
            instance = new MahasiswaRepository();
        
        return instance;
    }
    
    public List<Mahasiswa> get(String nim){
        List<Mahasiswa> mahasiswas = new ArrayList<>();
        
        String sql = "select * from mahasiswa where nim = ?";
        try {
            Connection con = db.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, nim);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setNama(resultSet.getString("nama"));
                mahasiswa.setNim(resultSet.getString("nim"));
                
                mahasiswas.add(mahasiswa);
            }
            db.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return mahasiswas;
    }
    
    public boolean insert(Mahasiswa mahasiswa){
        boolean success = false;
        String sql = "insert into mahasiswa (nim, nama) value(?,?)";
        Connection con = db.getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, mahasiswa.getNim());
            preparedStatement.setString(2, mahasiswa.getNama());
            
            preparedStatement.execute();
            success = true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return success;
    }
}
