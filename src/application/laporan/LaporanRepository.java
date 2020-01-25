/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package application.laporan;

import application.tariksimpanan.*;
import application.util.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LaporanRepository {
    private static LaporanRepository instance = null;
    private MySQLConnection db = null;
    
    private LaporanRepository(){
        this.db = MySQLConnection.getInstance();
    }
    
    public static LaporanRepository getInstance(){
        if(instance == null){
            instance = new LaporanRepository();
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
    
    public List<RekapPoinTahunan> getRekapPoinTahunan(){
        String sql = "select Anggota.id_anggota, nama_lengkap, sum(poin_simpanan_sukarela) as simpanan_wajib, sum(poin_simpanan_sukarela) as simpanan_sukarela\n" +
                "from HistorySimpanan\n" +
                "join Anggota  on HistorySimpanan.id_anggota = Anggota.id_anggota\n" +
                "where year(created_at) = year(now())" + 
                "group by id_anggota";
        
        List<RekapPoinTahunan> rekaps = new ArrayList<>();
        
        try {
            Connection con = db.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                RekapPoinTahunan rekap = new RekapPoinTahunan();
                rekap.setId_anggota(resultSet.getString("id_anggota"));
                rekap.setNama(resultSet.getString("nama_lengkap"));
                rekap.setSimpanan_wajib((resultSet.getInt("simpanan_wajib") * 25000) + 100000);
                rekap.setSimpanan_sukarela(resultSet.getInt("simpanan_sukarela") * 25000);
                rekap.setTotal(rekap.getSimpanan_sukarela() + rekap.getSimpanan_wajib());
                rekap.setTotalPoin(rekap.getTotal()/25000);
                
                rekaps.add(rekap);
            }
            db.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        return rekaps;
       
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
