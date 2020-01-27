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
            run = true;
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
    protected Thread thread = null;

    
    public Anggota getSelectedAnggota(List<Anggota> daftarAnggota ){
        int index_anggota = -1;

        System.out.println("");
        System.out.println("\t\t[Daftar Anggota] \n");
        System.out.println("\t\tNo.\tId Anggota      |  Nama Lengkap");
        for(int i = 0 ; i < daftarAnggota.size();i++)
            System.out.println("\t\t" + (i+1) + ".\t" +
                    daftarAnggota.get(i).getId_anggota() + "  |  " +
                    daftarAnggota.get(i).getNama_lengkap());
        
        System.out.print("\tMasukkan Nomor Anggota Berdasarkan Daftar Di Atas: ");
        
        while((index_anggota < 0) || (index_anggota > daftarAnggota.size()-1)){
            index_anggota = scanner.nextInt()-1;  // data pertama => nomor 1 => index == 0 ,
            if (index_anggota < 0 || index_anggota > daftarAnggota.size()-1){
                System.out.println("\n\tMaksukan Nomor Dari 1 sampai " + daftarAnggota.size()+"!\n");
                System.out.print("\tMasukkan Nomor Anggota Berdasarkan Daftar Di Atas: ");
            }
        }
        System.out.println("\tAnda memilih : " + (index_anggota + 1) + ". " +
                daftarAnggota.get(index_anggota).getId_anggota() + "  |  " +
                daftarAnggota.get(index_anggota).getNama_lengkap()+"\n");
        
        
        return daftarAnggota.get(index_anggota);
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
        
        if (thread == null){
            thread = new Thread(task);
            thread.start();
        }
        else {
            task.run();
        }
        
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
    
    protected void alertDataSaved(String message){
        System.out.println("");
        System.out.println("\t"+message);
        System.out.println("");
        System.out.println("\tTekan enter untuk melanjutkan...");
        scanner.nextLine();
    }
    
    protected void alertDataNotSaved(String message) {
        System.out.println("");
        System.out.println("\t"+message);
        System.out.println("");
        System.out.println("\tTekan enter untuk melanjutkan...");
        scanner.nextLine();
    }
    
}
