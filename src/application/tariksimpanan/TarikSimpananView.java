/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.tariksimpanan;

import application.anggota.Anggota;
import application.base.BaseView;
import java.util.List;

/**
 *
 * @author Ary
 */
public class TarikSimpananView extends BaseView{
    public TarikSimpanan form(List<Anggota> daftarAnggota){
        TarikSimpanan tarikSimpanan = new TarikSimpanan();
        
        String id_anggota;
        int poin_ss;
        
        scanner.nextLine();
        
        System.out.println("");
        System.out.println("\tTarik Simpanan Sukarela");
        System.out.println("\t=======================");
      
        tarikSimpanan.setId_anggota(getSelectedAnggota(daftarAnggota).getId_anggota());
        System.out.println("");     
       
        
        System.out.print("\tMasukkan Jumlah Simpanan Sukarela = Rp25.000 * ");
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
    public void tampilDaftarAnggota(List<Anggota> daftarAnggota ){
        System.out.println("\t\t[Daftar Anggota] \n");
        System.out.println("\t\tNo.\tId Anggota      |  Nama Lengkap");
        for(int i = 0 ; i < daftarAnggota.size();i++)
            System.out.println("\t\t" + (i+1) + ".\t" +
                    daftarAnggota.get(i).getId_anggota() + "  |  " +
                    daftarAnggota.get(i).getNama_lengkap());
    }

    public void alertDataNotSaved() {
        super.alertDataNotSaved("Penarikan Gagal.");
    }
    
    public void alertDataSaved(){
        super.alertDataSaved("Penarikan Berhasil, Data Tersimpan.");
    }

}
