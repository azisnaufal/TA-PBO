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
        printLn("Bayar Iuran");
        printLn("=======================");
        System.out.println();
        
        bayar.setId_anggota(getSelectedAnggota(daftarAnggota).getId_anggota());

        print("Masukkan Jumlah Simpanan Wajib = Rp25.000 * ");
        poin_sw = scanner.nextInt();
        bayar.setPoin_wajib(poin_sw);
        
        print("Masukkan Jumlah Simpanan Sukarela = Rp25.000 * ");
        poin_ss = scanner.nextInt();
        bayar.setPoin_sukarela(poin_ss);
        
        return bayar;
    }
    public int menu(){
        System.out.println("");
        printLn("Iuran Anggota");
        printLn("===============");
        printLn("1) Bayar Iuran");
        printLn("0) Keluar");
        System.out.println(" ");
        print("Masukkan Pilihan Anda : ");
        int choice = scanner.nextInt();
        
        return choice;
    }
    
    public void tampilDaftarAnggota(List<Anggota> daftarAnggota ){
        printLn("\t[Daftar Anggota] \n");
        printLn("\tNo.\tId Anggota      |  Nama Lengkap");
        for(int i = 0 ; i < daftarAnggota.size();i++)
            printLn("\t" + (i+1) + ".\t" +
                    daftarAnggota.get(i).getId_anggota() + "  |  " +
                    daftarAnggota.get(i).getNama_lengkap());
    }
    
    public void alertDataNotSaved() {
        super.alertEnterContinue("Error, Data gagal disimpan.");
    }
    
    public void alertDataSaved(){
        super.alertEnterContinue("Data telah tersimpan.");
    }

}

