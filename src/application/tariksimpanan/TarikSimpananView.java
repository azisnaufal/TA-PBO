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
        
        int poin_ss;
        
        System.out.println("");
        printLn("Tarik Simpanan Sukarela");
        printLn("=======================");
        
        tarikSimpanan.setId_anggota(getSelectedAnggota(daftarAnggota).getId_anggota());
        System.out.println("");
        
        
        print("Masukkan Jumlah Simpanan Sukarela = Rp25.000 * ");
        poin_ss = scanner.nextInt();
        tarikSimpanan.setPoin_ss(poin_ss);
        tarikSimpanan.setPoin_sukarela(poin_ss);
        
        return tarikSimpanan;
    }
    public int menu(){
        System.out.println("");
        printLn("Tarik Simpanan");
        printLn("===============");
        printLn("1) Tarik Simpanan Sukarela");
        printLn("0) Kembali");
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
        super.alertEnterContinue("Penarikan Gagal.");
    }
    
    public void alertDataSaved(){
        super.alertEnterContinue("Penarikan Berhasil, Data Tersimpan.");
    }
    
}
