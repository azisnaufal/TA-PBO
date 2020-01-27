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
public class RekapIuranAnggota {
    private String id_anggota;
    private String nama;
    private int simpanan_wajib;

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
    
}
