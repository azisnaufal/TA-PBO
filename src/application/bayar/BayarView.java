/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.bayar;

import java.util.List;
import application.anggota.Anggota;
import application.base.BaseView;
/**
 *
 * @author binta
 */
public class BayarView extends BaseView{
    public Bayar form(List<Anggota> daftarAnggota){
        Bayar bayar = new Bayar();
        
        int index_anggota;
        int poin_sw;
        int poin_ss;
        
        scanner.nextLine();
        
        System.out.println("");
        System.out.println("\tBayar Iuran");
        System.out.println("\t=======================");
        System.out.println();
        
        bayar.setId_anggota(getSelectedAnggota(daftarAnggota).getId_anggota());
        
        System.out.print("\tMasukkan Jumlah Simpanan Wajib = Rp25.000 * ");
        poin_sw = scanner.nextInt();
        bayar.setPoin_wajib(poin_sw);
        
        System.out.print("\tMasukkan Jumlah Simpanan Sukarela = Rp25.000 * ");
        poin_ss = scanner.nextInt();
        bayar.setPoin_sukarela(poin_ss);
        
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

    public void alertDataNotSaved() {
        super.alertDataNotSaved("Error, Data gagal disimpan.");
    }
    
    public void alertDataSaved(){
        super.alertDataSaved("Data telah tersimpan.");
    }
}