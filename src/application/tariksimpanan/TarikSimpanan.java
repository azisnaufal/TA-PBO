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
    private Timestamp tanggal;

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
}
