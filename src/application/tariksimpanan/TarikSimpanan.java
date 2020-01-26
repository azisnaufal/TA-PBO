/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.tariksimpanan;

import java.sql.Timestamp;

/**
 *
 * @author Ary
 */
public class TarikSimpanan {
    private String id_anggota;
    private int poin_ss;
    private int poin_sukarela;
    private int jumlah_uang;
    private Timestamp tanggal;
    private String Tampilan_id_anggota;

    public String getTampilan_id_anggota() {
        return Tampilan_id_anggota;
    }

    public void setTampilan_id_anggota(String Tampilan_id_anggota) {
        this.Tampilan_id_anggota = Tampilan_id_anggota;
    }

    public int getPoin_sukarela() {
        return poin_sukarela;
    }

    public void setPoin_sukarela(int poin_sukarela) {
        this.poin_sukarela = poin_sukarela;
    }
   
    public Timestamp getTanggal() {
        return tanggal;
    }

    public void setTanggal(Timestamp tanggal) {
        this.tanggal = tanggal;
    }

    public String getId_anggota() {
        return id_anggota;
    }

    public void setId_anggota(String id_anggota) {
        this.id_anggota = id_anggota;
    }

    public int getPoin_ss() {
        return -poin_ss;
    }

    public void setPoin_ss(int poin) {
        this.poin_ss = poin;
    }

    public int getJumlah_uang() {
        jumlah_uang = poin_ss * 25000;
        return -jumlah_uang;
    }

    public void setJumlah_uang(int jumlah_uang) {
        this.jumlah_uang = jumlah_uang;
    }    
}
