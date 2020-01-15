/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.anggota;

import application.base.BaseView;

/**
 *
 * @author Farhan Rafly Fahrezi
 */
public class AnggotaView extends BaseView{
    public Anggota form(){
        Anggota anggota = new Anggota();
        
        String no_ktp;
        String nama;
        String tempat_lahir;
        String tanggal_lahir;
        String ttl;
        String no_telepon;
        String alamat;
        String id;
        
        scanner.nextLine();
        
        System.out.print("Masukan iD : ");
        id = scanner.nextLine();
        anggota.setId_anggota(id);
        
        System.out.print("Masukan NO KTP : ");
        no_ktp = scanner.nextLine();
        anggota.setNo_KTP(no_ktp);
        
        System.out.print("Masukan Nama Lengkap : ");
        nama = scanner.nextLine();
        anggota.setNama_lengkap(nama);
        
        System.out.print("Masukan Alamat : ");
        alamat = scanner.nextLine();
        anggota.setAlamat(alamat);
        
        System.out.print("Masukan Tempat Lahir : ");
        tempat_lahir = scanner.nextLine();
        
        System.out.print("Masukan Tanggal Lahir (DD/MM/YYYY) : ");
        tanggal_lahir = scanner.nextLine();
        
        ttl = tempat_lahir.concat(", " + tanggal_lahir);
        anggota.setTtl(ttl);
        
        System.out.print("Masukan No Telepon : ");
        no_telepon = scanner.next();
        anggota.setNomor_telepon(no_telepon);
        
        return anggota;
    }
    
    public int menu() {
        System.out.println("1) Anggota");
        System.out.println(" ");
        System.out.print("Masukan Pilihan Anda : ");
        int choice = scanner.nextInt();
        
        return choice;
    }
    
    public void alertKTPExist() {
        System.out.println("No KTP telah terdaftar.");
        System.out.println("");
        System.out.println("Tekan enter untuk melanjutkan...");
        scanner.nextLine();
    }
    
    public void alertDataSaved(){
        System.out.println("Data telah tersimpan.");
        System.out.println("");
        System.out.println("Tekan enter untuk melanjutkan...");
        scanner.nextLine();
    }
    
    public void alertDataNotSaved() {
        System.out.println("Error, Data gagal disimpan.");
        System.out.println("");
        System.out.println("Tekan enter untuk melanjutkan...");
        scanner.nextLine();
    }
}
