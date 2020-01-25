/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.laporan;

/**
 *
 * @author oazisn
 */
public class RekapPoinTahunan {
    private String id_anggota;
    private String nama;
    private int simpanan_wajib;
    private int simpanan_sukarela;
    private int total;
    private int totalPoin;

    public String getId_anggota() {
        return id_anggota;
    }

    public void setId_anggota(String id_anggota) {
        this.id_anggota = id_anggota;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getSimpanan_wajib() {
        return simpanan_wajib;
    }

    public void setSimpanan_wajib(int simpanan_wajib) {
        this.simpanan_wajib = simpanan_wajib;
    }

    public int getSimpanan_sukarela() {
        return simpanan_sukarela;
    }

    public void setSimpanan_sukarela(int simpanan_sukarela) {
        this.simpanan_sukarela = simpanan_sukarela;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalPoin() {
        return totalPoin;
    }

    public void setTotalPoin(int totalPoin) {
        this.totalPoin = totalPoin;
    }
    
    
}
