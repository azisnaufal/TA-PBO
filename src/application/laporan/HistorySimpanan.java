/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.laporan;

import java.sql.Timestamp;

/**
 *
 * @author oazisn
 */
public class HistorySimpanan {
    private int simpanan_sukarela;
    private int simpanan_wajib;
    private Timestamp created_at;

    public int getSimpanan_sukarela() {
        return simpanan_sukarela;
    }

    public void setSimpanan_sukarela(int simpanan_sukarela) {
        this.simpanan_sukarela = simpanan_sukarela;
    }

    public int getSimpanan_wajib() {
        return simpanan_wajib;
    }

    public void setSimpanan_wajib(int simpanan_wajib) {
        this.simpanan_wajib = simpanan_wajib;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
    
}
