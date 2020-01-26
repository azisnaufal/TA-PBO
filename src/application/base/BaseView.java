/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package application.base;

import application.anggota.Anggota;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
/**
 *
 * @author oazisn
 */
public class BaseView {
    
    private static class Loading implements Runnable{
        
        private boolean run = true;
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                if (run){
                    try {
                        System.out.print(".");
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException ex) {
                        
                    }
                }
                else {
                    break;
                }
            }
        }
        
        public void stop(){
            run = false;
        }
        
    }
    
    protected static Loading task = new Loading();
    protected static Scanner scanner = new Scanner(System.in);
    
    public void tampilDaftarAnggota(List<Anggota> daftarAnggota ){
        System.out.println("\t\t[Daftar Anggota] \n");
        System.out.println("\t\tNo.\tId Anggota      |  Nama Lengkap");
        for(int i = 0 ; i < daftarAnggota.size();i++)
            System.out.println("\t\t" + (i+1) + ".\t" +
                    daftarAnggota.get(i).getId_anggota() + "  |  " +
                    daftarAnggota.get(i).getNama_lengkap());
    }
    
    public void header(String params){
        System.out.println("");
        System.out.println("\t" + params);
        System.out.println("\t======================");
    }
    
    public void printLn(String params){
        System.out.println("\t" + params);
    }
    
    public void print(String params){
        System.out.print("\t" + params);
    }
    
    public void alertLoading(){
        System.out.print("\tSedang memproses");
        scanner.nextLine();
        
        Thread thread = new Thread(task);
        thread.start();
        
    }
    
    public void stopLoading(){
        task.stop();
    }
    
    public void alertId_anggotaNotExist(){
        System.out.println("\tId Anggota tidak terdaftar");
        System.out.println("");
        System.out.println("\tTekan enter untuk melanjutkn");
        scanner.nextLine();
    }
    
    public void alertDataSaved(){
        System.out.println("\tPenarikan Berhasil, Data Tersimpan.");
        System.out.println("");
        System.out.println("\tTekan enter untuk melanjutkan...");
        scanner.nextLine();
    }
    
    public void alertDataNotSaved() {
        System.out.println("\tPenarikan Gagal.");
        System.out.println("");
        System.out.println("\tTekan enter untuk melanjutkan...");
        scanner.nextLine();
    }
    
}
