/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.bayar;

import java.sql.Timestamp;

/**
 *
 * @author binta
 */
public class Bayar  {
    private String id_anggota;
    private int poin_wajib;
    private int poin_sukarela;
    private Timestamp tanggal;
   
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

    public int getPoin_wajib() {
        return poin_wajib;
    }

    public void setPoin_wajib(int poin) {
        this.poin_wajib = poin;
    }
   
    public int getPoin_sukarela() {
        return poin_sukarela;
    }

    public void setPoin_sukarela(int poin_sukarela) {
        this.poin_sukarela = poin_sukarela;
    }
}

