/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.menu;

import application.base.BaseView;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author oazisn
 */
public class MenuView extends BaseView{
    public int menu(){
        cls();
        printLn("Selamat Datang " + System.getProperty("user.name"));
        header("Manu Utama");
        printLn("1) Anggota");
        printLn("2) Tarik Simpanan");
        printLn("3) Cetak Laporan");
        printLn("4) Bayar Iuran");
        printLn(" ");
        printLn("0) Keluar");
        print("Pilihan Anda : ");
        return scanner.nextInt();
    }
    
    public void initialization(){
        cls();
        print("Checking System");
        for (int i = 0; i < 5; i++) {
            System.out.print(".");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                
            }
        }
        System.out.println(".");
        
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            
        }
        
        print("Checking Database");
        for (int i = 0; i < 5; i++) {
            System.out.print(".");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                
            }
        }
        System.out.println(".");
        
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
        }
    }
}
