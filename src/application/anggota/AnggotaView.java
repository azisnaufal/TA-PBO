/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.anggota;

import application.base.BaseView;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author Farhan Rafly Fahrezi
 */
public class AnggotaView extends BaseView{
    
    public Anggota form(){
        Anggota anggota = new Anggota();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        
        String no_ktp;
        String nama;
        String tempat_lahir;
        String tanggal_lahir;
        String ttl;
        String no_telepon;
        String alamat;
        
        scanner.nextLine();
        
        
        System.out.println("");
        System.out.println("\tDaftar Anggota");
        System.out.println("\t======================");
        System.out.print("\tMasukan NO KTP : ");
        no_ktp = scanner.nextLine();
        anggota.setNo_KTP(no_ktp);
        
        System.out.print("\tMasukan Nama Lengkap : ");
        nama = scanner.nextLine();
        anggota.setNama_lengkap(nama);
        
        System.out.print("\tMasukan Alamat : ");
        alamat = scanner.nextLine();
        anggota.setAlamat(alamat);
        
        System.out.print("\tMasukan Tempat Lahir : ");
        tempat_lahir = scanner.nextLine();
        
        System.out.print("\tMasukan Tanggal Lahir (DD/MM/YYYY) : ");
        tanggal_lahir = scanner.nextLine();
        
        ttl = tempat_lahir.concat(", " + tanggal_lahir);
        anggota.setTtl(ttl);
        
        System.out.print("\tMasukan No Telepon : ");
        no_telepon = scanner.next();
        anggota.setNomor_telepon(no_telepon);
        
        return anggota;
    }
    
    public int menu() {
        System.out.println("");
        System.out.println("\tAnggota");
        System.out.println("\t======================");
        System.out.println("\t1) Daftar Menjadi Anggota");
        System.out.println("\t2) Update Data Anggota");
        System.out.println(" ");
        System.out.println("\t0) Keluar");
        System.out.print("\tMasukan Pilihan Anda : ");
        int choice = scanner.nextInt();
        
        return choice;
    }
    
    public void alertKTPExist() {
        System.out.println("\tNo KTP telah terdaftar.");
        System.out.println("");
        System.out.println("\tTekan enter untuk melanjutkan...");
        scanner.nextLine();
    }
    
    public void alertDataSaved(){
        System.out.println("\tData telah tersimpan.");
        System.out.println("");
        System.out.println("\tTekan enter untuk melanjutkan...");
        scanner.nextLine();
    }
    
    public void alertDataNotSaved() {
        System.out.println("\tError, Data gagal disimpan.");
        System.out.println("");
        System.out.println("\tTekan enter untuk melanjutkan...");
        scanner.nextLine();
    }
    
}
