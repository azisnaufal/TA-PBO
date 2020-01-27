/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.laporan;

import application.base.BaseView;

public class LaporanView extends BaseView{

    public int menu(){
        header("Cetak Laporan");
        printLn("1) Kartu Anggota");
        printLn("2) Rekapitulasi Poin Tahunan");
        printLn("3) Rekapitulasi Iuran Anggota");
        printLn(" ");
        printLn("0) Keluar");
        print("Masukkan Pilihan Anda : ");
        int choice = scanner.nextInt();
        
        return choice;
    }
    
    public int frmTahun(){
        System.out.print("\tMasukkan Tahun Laporan: ");
        int year = scanner.nextInt();
        
        return year;
    }
    
    public void alertDataNotSaved() {
        super.alertDataNotSaved("Laporan gagal disimpan!");
    }
    
    public void alertDataSaved(){
        super.alertDataSaved("Laporan berhasil disimpan di Desktop Anda!");
    }
    
}