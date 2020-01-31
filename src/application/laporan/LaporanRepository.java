/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package application.laporan;

import application.base.BaseRepository;
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
public class LaporanRepository extends BaseRepository{
    private static LaporanRepository instance = null;
    
    private LaporanRepository(){
        this.db = MySQLConnection.getInstance();
    }
    
    public static LaporanRepository getInstance(){
        if(instance == null){
            instance = new LaporanRepository();
        }
        return instance;
    }
    
    public List<HistorySimpanan> getSimpananByIdAnggota(String id_anggota, int year){
        List<HistorySimpanan> history = new ArrayList<>();
        String sql = "select poin_simpanan_wajib, poin_simpanan_sukarela, created_at from HistorySimpanan where id_anggota = ? and year(created_at) = ?";
        try {
            Connection con = db.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, id_anggota);
            preparedStatement.setInt(2, year);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                HistorySimpanan historySimpanan = new HistorySimpanan();
                historySimpanan.setCreated_at(resultSet.getTimestamp("created_at"));
                historySimpanan.setSimpanan_sukarela(resultSet.getInt("poin_simpanan_sukarela"));
                historySimpanan.setSimpanan_wajib(resultSet.getInt("poin_simpanan_wajib"));
                
                history.add(historySimpanan);
            }
            db.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return history;
    }
    
    private SumSimpanan getSumSimpanan(int year, String id_anggota){
        String sql = "select sum(poin_simpanan_wajib) as simpanan_wajib, sum(poin_simpanan_sukarela) as simpanan_sukarela\n" +
                "from HistorySimpanan\n" +
                "where year(created_at) = ? and id_anggota = ? \n";
        
        SumSimpanan item = new SumSimpanan();
        try {
            Connection con = db.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, year);
            preparedStatement.setString(2, id_anggota);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                item.setSimpanan_sukarela(resultSet.getInt("simpanan_sukarela"));
                item.setSimpanan_wajib(resultSet.getInt("simpanan_wajib"));
            }
            db.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        return item;
    };
    
    public List<RekapPoinTahunan> getRekapPoinTahunan(int year){
        String sql = "select id_anggota, nama_lengkap from Anggota";
        List<RekapPoinTahunan> rekaps = new ArrayList<>();
        
        try {
            Connection con = db.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                RekapPoinTahunan rekap = new RekapPoinTahunan();
                rekap.setId_anggota(resultSet.getString("id_anggota"));
                rekap.setNama(resultSet.getString("nama_lengkap"));
                
                SumSimpanan sum = this.getSumSimpanan(year, rekap.getId_anggota());
                
                rekap.setSimpanan_wajib((sum.getSimpanan_wajib() * 25000) + 100000);
                rekap.setSimpanan_sukarela(sum.getSimpanan_sukarela() * 25000);
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
    
    private int getSumSimpananWajib(int year, String id_anggota){
        String sql = "select sum(poin_simpanan_wajib) as simpanan_wajib, year(created_at) as year\n" +
                "from HistorySimpanan\n" +
                "where year(created_at) <= ? and id_anggota = ?\n" +
                "group by year(created_at)";
        
        int sum = 0;
        List<SumSimpananWajib> sums = new ArrayList<>();
        try {
            Connection con = db.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, year);
            preparedStatement.setString(2, id_anggota);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SumSimpananWajib item = new SumSimpananWajib();
                item.setPoin(resultSet.getInt("simpanan_wajib"));
                item.setYear(resultSet.getInt("year"));
                sums.add(item);
            }
            db.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        if (!sums.isEmpty()){
            int range = year - sums.get(0).getYear();
            
            for (int i = 0; i <= range; i++){
                sum += sums.get(i).getPoin();
                if (sum > 12){
                    sum -= 12;
                }
            }
        }
        
        return sum;
    };
    
    public List<RekapIuranAnggota> getRekapIuranAnggota(int year){
        String sql = "select id_anggota, nama_lengkap\n" +
                "from Anggota";
        
        List<RekapIuranAnggota> rekaps = new ArrayList<>();
        
        try {
            Connection con = db.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                RekapIuranAnggota rekap = new RekapIuranAnggota();
                rekap.setId_anggota(resultSet.getString("id_anggota"));
                rekap.setNama(resultSet.getString("nama_lengkap"));
                
                rekaps.add(rekap);
            }
            db.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        for (RekapIuranAnggota rekap : rekaps) {
            int poin = this.getSumSimpananWajib(year, rekap.getId_anggota());
            rekap.setSimpanan_wajib(poin);
        }
        
        return rekaps;
        
    }
    
}
