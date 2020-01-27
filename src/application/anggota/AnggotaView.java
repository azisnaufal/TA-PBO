/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.anggota;

import application.base.BaseView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
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
        boolean no_ktp_validasi = false;
        String nama;
        boolean nama_validasi = false;
        String tempat_lahir;
        boolean tempat_lahir_validasi = false;
        String tanggal_lahir;
        boolean tanggal_lahir_validasi = false;
        String ttl;
        boolean ttl_validasi = false;
        String no_telepon;
        boolean no_telepon_validasi = false;
        String alamat;
        boolean alamat_validasi = false;
        
        scanner.nextLine();
        
        header("Daftar Anggota");
        
        //KTP
        do {            
            print("Masukan No KTP : ");
            no_ktp = scanner.nextLine();
            if (no_ktp.equals("")) {
                printLn("No KTP tidak boleh kosong!");
            } else if (no_ktp.length() != 16) {
                printLn("No KTP harus 16 digit");
            } else if (no_ktp.contains("^[a-zA-Z]*$")) {
                printLn("No KTP tidak boleh mengandung huruf");
            } else {
                no_ktp_validasi = true;
            }
        } while (no_ktp_validasi == false);
        anggota.setNo_KTP(no_ktp);
        
        //Nama Lengkap
        do {            
            print("Masukan Nama Lengkap : ");
            nama = scanner.nextLine();
            if (nama.equals("")) {
                printLn("Nama tidak boleh kosong!");
            } else if (nama.length() <= 2) {
                printLn("Nama minimal 3 digit");
            } else {
                nama_validasi = true;
            }
        } while (nama_validasi == false);
        anggota.setNama_lengkap(nama);
        
        //Alamat
        do {            
            print("Masukan Alamat : ");
            alamat = scanner.nextLine();
            if (alamat.equals("")) {
                printLn("Alamat tidak boleh kosong");
            } else if (alamat.length() <= 5) {
                printLn("Alamat minimal 5 digit");
            } else {
                alamat_validasi = true;
            }
        } while (alamat_validasi == false);
        anggota.setAlamat(alamat);
        
        
        //Tempat Lahir
        do {            
            print("Masukan Tempat Lahir : ");
            tempat_lahir = scanner.nextLine();
            if (tempat_lahir.equals("")) {
                printLn("Tempat lahir tidak boleh kosong");
            } else if (tempat_lahir.length() <= 4) {
                printLn("Tempat lahir minimal 5 digit");
            } else {
                tempat_lahir_validasi = true;
            }
        } while (tempat_lahir_validasi == false);
        
        
        //Tanggal Lahir
        do {            
            print("Masukan Tanggal Lahir (DD/MM/YYYY) : ");
            tanggal_lahir = scanner.nextLine();
            if (tanggal_lahir.equals("")) {
                printLn("Tanggal lahir tidak boleh kosong");
            } else if (tanggal_lahir.length() != 10) {
                printLn("Invalid Input");
            } else {
                tanggal_lahir_validasi = true;
            }
        } while (tanggal_lahir_validasi == false);
        
        
        //Tempat Tanggal Lahir
        ttl = tempat_lahir.concat(", " + tanggal_lahir);
        anggota.setTtl(ttl);
        
        
        //No Telepon
        do {            
            print("Masukan No Telepon : ");
            no_telepon = scanner.next();
            if (no_telepon.equals("")) {
                printLn("No telepon tidak boleh kosong");
            } else if (no_telepon.length() <=9) {
                printLn("No telepon minimal 10 digit");
            } else {
                no_telepon_validasi = true;
            }
        } while (no_telepon_validasi == false);
        anggota.setNomor_telepon(no_telepon);
        
        return anggota;
    }
    
    public int menu() {
        printLn("");
        printLn("Anggota");
        printLn("======================");
        printLn("1) Daftar Menjadi Anggota");
        printLn("2) Update Data Anggota");
        printLn(" ");
        printLn("0) Kembali");
        print("Masukan Pilihan Anda : ");
        int choice = scanner.nextInt();
        
        return choice;
    }
    
    public String serachID() {
        header("Update Anggota");
        print("Masukan ID Anggota : KSB/");
        String kode = scanner.nextLine();
        
        return kode;
    }
    
    public int showResult(List<String> params) {
        int choice = 0;
        
        header("Hasil Pencarian");
        printLn("1) ID Anggota             : " + params.get(0));
        printLn("2) No KTP                 : " + params.get(1));
        printLn("3) Nama Lengkap           : " + params.get(2));
        printLn("4) Alamat                 : " + params.get(3));
        printLn("5) Tempat, Tanggal Lahir  : " + params.get(4));
        printLn("6) No Telepon             : " + params.get(5));
        printLn("");
        do {            
            print("Bagian yang ingin dirubah ? ");
            choice = scanner.nextInt();    
            if (choice > 6) {
                printLn("Pilihan tidak tersedia");
            }
        } while (choice > 6);
        
        return choice;
    }
    
    public String update(String params, String params2) {
        header("Ubah " + params);
        printLn(params + " sebelumnya : " + params2);
        printLn(" ");
        print(params + " yang baru : ");
        String temp = scanner.nextLine();
        
        return temp;
    }
    
    public void alertKTPExist() {
        printLn("No KTP telah terdaftar.");
        printLn("");
        scanner.nextLine();
    }
    
    public void alertDataNotSaved() {
        super.alertDataNotSaved("Error, Data gagal disimpan.");
    }
    
    public void alertDataSaved(){
        super.alertDataSaved("Data telah tersimpan.");
    }
    
}
