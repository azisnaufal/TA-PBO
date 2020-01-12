/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package application.mahasiswa;

import application.base.BaseView;

/**
 *
 * @author oazisn
 */
public class MahasiswaView extends BaseView{
    public Mahasiswa form(){
        Mahasiswa mahasiswa = new Mahasiswa();
        
        scanner.nextLine();
        System.out.print("Masukan NIM : ");
        String nim = scanner.nextLine();
        mahasiswa.setNim(nim);
        
        System.out.print("Masukan Nama : ");
        String nama = scanner.nextLine();
        mahasiswa.setNama(nama);
        
        return mahasiswa;
    }
    
    public int menu() {
        System.out.println("1. Tambah");
        System.out.println("2. Kembali");
        System.out.print("Pilihan Anda : ");
        return scanner.nextInt();
    }
    
    public void alertNimExist(){
        System.out.println("NIM telah terdaftar!");
        System.out.println("Tekan enter untuk melanjutkan.");
        scanner.nextLine();
    }
    
    public void alertDataSaved(){
        System.out.println("Data Tersimpan!");
        System.out.println("Tekan enter untuk melanjutkan.");
        scanner.nextLine();
    }
    
    public void alertDataNotSaved(){
        System.out.println("Data Gagal Tersimpan!");
        System.out.println("Tekan enter untuk melanjutkan.");
        scanner.nextLine();
    }
}
