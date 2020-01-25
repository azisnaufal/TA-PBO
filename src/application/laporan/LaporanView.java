/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.laporan;

import application.base.BaseView;
import application.tariksimpanan.*;
import static application.base.BaseView.scanner;

public class LaporanView extends BaseView{

    public int menu(){
        System.out.println("");
        System.out.println("\tCetak Laporan");
        System.out.println("\t===============");
        System.out.println("\t1) Kartu Anggota");
        System.out.println("\t2) Rekapitulasi Poin Tahunan");
        System.out.println("\t3) Rekapitulasi Iuran Anggota");
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
        System.out.println("\n\tLaporan berhasil disimpan di Desktop Anda!");
        System.out.println("");
        System.out.println("\tTekan enter untuk melanjutkan...");
        stopLoading();
        scanner.nextLine();
    }
    
    public void alertDataNotSaved() {
        System.out.println("\n\tLaporan gagal disimpan!");
        System.out.println("");
        System.out.println("\tTekan enter untuk melanjutkan...");
        stopLoading();
        scanner.nextLine();
    }


}
