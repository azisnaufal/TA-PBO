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
        
        tampilDaftarAnggota(daftarAnggota);
        System.out.println();
        
        System.out.print("\tMasukkan Nomor Anggota Berdasarkan Daftar Di Atas: ");
        index_anggota = scanner.nextInt()-1;  // data pertama => nomor 1 => index == 0 ,
        bayar.setId_anggota(daftarAnggota.get(index_anggota).getId_anggota());
        System.out.println("\tAnda memilih : " + (index_anggota + 1) + ". " +
                daftarAnggota.get(index_anggota).getId_anggota() + "  |  " +
                daftarAnggota.get(index_anggota).getNama_lengkap()+"\n");
        
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

}