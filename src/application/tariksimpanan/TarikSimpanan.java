/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.tariksimpanan;

/**
 *
 * @author Ary
 */
public class TarikSimpanan {
    private String id_anggota;
    private int poin_ss;
    private int jumlah_uang;
    private String bulan;

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
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
