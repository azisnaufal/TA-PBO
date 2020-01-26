/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.bayar;

import static application.base.BaseView.scanner;

/**
 *
 * @author binta
 */
public class BayarView {
    public Bayar form(){
        Bayar bayar = new Bayar();
        
        String id_anggota;
        int poin_sw;
        int poin_ss;
        
        scanner.nextLine();
        
        System.out.println("");
        System.out.println("\tBayar Iuarn");
        System.out.println("\t=======================");
        
        System.out.print("\tMasukkan ID Anggota (KSB/YYYYMM/NNN) : ");
        id_anggota = scanner.nextLine();
        bayar.setId_anggota(id_anggota);
        
        System.out.print("\tMasukkan Jumlah Simpanan Wajib = Rp25.000 * ");
        poin_sw = scanner.nextInt();
        bayar.setPoin_wajib(poin_sw);
        
        System.out.print("\tMasukkan Jumlah Simpanan Sukarela = Rp25.000 * ");
        poin_ss = scanner.nextInt();
        bayar.setPoin_sukarela(poin_sw);
        
        return bayar;
    }
    public int menu(){
        System.out.println("");
        System.out.println("\tIuran Anggota");
        System.out.println("\t===============");
        System.out.println("\t1) Bayar Iuran");
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
        System.out.println("\tData telah tersimpan.");
        System.out.println("");
        System.out.println("\tTekan enter untuk melanjutkan...");
        scanner.nextLine();
    }
    
    public void alertDataNotSaved() {
        System.out.println("\tError, Data gagal disimpan.");
        System.out.println("");
        System.out.println("\tTekan enter untuk melanjutkan...");
        scanner.nextLine();
    }

    void alertLoading() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}

