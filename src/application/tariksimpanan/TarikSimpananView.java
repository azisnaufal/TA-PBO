/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.tariksimpanan;

import static application.base.BaseView.scanner;
/**
 *
 * @author Ary
 */
public class TarikSimpananView {
    public TarikSimpanan form(){
        TarikSimpanan tarikSimpanan = new TarikSimpanan();
        
        String id_anggota;
        int poin_ss;
        
        scanner.nextLine();
        
        System.out.println("");
        System.out.println("\tTarik Simpanan Sukarela");
        System.out.println("\t=======================");
        
        System.out.print("\tMasukkan ID Anggota (KSB/YYYYMM/NNN) : ");
        id_anggota = scanner.nextLine();
        tarikSimpanan.setId_anggota(id_anggota);
        
        //System.out.println("\tID Anggota :");
        //System.out.println("\t\t"+tarikSimpanan.getTampilan_id_anggota());
        //System.out.print("\tPilihan : ");
                
        System.out.print("\tMasukkan Jumlah Poin = Rp25.000 * ");
        poin_ss = scanner.nextInt();
        tarikSimpanan.setPoin_ss(poin_ss);
        tarikSimpanan.setPoin_sukarela(poin_ss);
        
        return tarikSimpanan;
    }
    public int menu(){
        System.out.println("");
        System.out.println("\tTarik Simpanan");
        System.out.println("\t===============");
        System.out.println("\t1) Tarik Simpanan Sukarela");
        System.out.println("\t0) Keluar");
        System.out.println(" ");
        System.out.print("\tMasukkan Pilihan Anda : ");
        int choice = scanner.nextInt();
        
        return choice;
    }
    
    public void alertId_anggotaNotExist(){
        System.out.println("\tId Anggota tidak terdaftar");
        System.out.println("");
        System.out.println("\tTekan enter untuk melanjutkn");
        scanner.nextLine();
    }
    
     public void alertDataSaved(){
        System.out.println("\tPenarikan Berhasil, Data Tersimpan.");
        System.out.println("");
        System.out.println("\tTekan enter untuk melanjutkan...");
        scanner.nextLine();
    }
    
    public void alertDataNotSaved() {
        System.out.println("\tPenarikan Gagal.");
        System.out.println("");
        System.out.println("\tTekan enter untuk melanjutkan...");
        scanner.nextLine();
    }

    void alertLoading() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
