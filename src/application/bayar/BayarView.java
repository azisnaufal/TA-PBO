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
        
        int poin_sw;
        int poin_ss;
        
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
    
    public void tampilDaftarAnggota(List<Anggota> daftarAnggota ){
        System.out.println("\t\t[Daftar Anggota] \n");
        System.out.println("\t\tNo.\tId Anggota      |  Nama Lengkap");
        for(int i = 0 ; i < daftarAnggota.size();i++)
            System.out.println("\t\t" + (i+1) + ".\t" +
                    daftarAnggota.get(i).getId_anggota() + "  |  " +
                    daftarAnggota.get(i).getNama_lengkap());
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

//    void alertLoading() {
//        throw new UnsupportedOperationException("Not supported yet."); 
//    }
}

