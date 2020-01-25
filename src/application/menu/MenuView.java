/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.menu;

import application.base.BaseView;

/**
 *
 * @author oazisn
 */
public class MenuView extends BaseView{
    public int menu(){
        header("Manu Utama");
        printLn("1) Anggota");
        printLn("3) Tarik Simpanan");
        printLn("4) Cetak Laporan");
        printLn(" ");
        printLn("0) Keluar");
        print("Pilihan Anda : ");
        return scanner.nextInt();
    }
}
