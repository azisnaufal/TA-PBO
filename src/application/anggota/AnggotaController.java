/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.anggota;

import java.util.List;

/**
 *
 * @author Farhan Rafly Fahrezi
 */
public class AnggotaController {
    private static AnggotaView view = null;
    private static AnggotaController instance = null;
    private static AnggotaRepository repos = null;
    
    private AnggotaController() {
        this.view = new AnggotaView();
        this.repos = AnggotaRepository.getInstance();
    }
    
    public static AnggotaController getInstance() {
        if (instance == null) {
            instance = new AnggotaController();
        }
        
        return instance;
    }
    
    public void index() {
        boolean loop = true;
        while (loop) {
            int menu = view.menu();
            switch (menu) {
                case 1:
                        this.tambah();
                    break;
                default: {
                    System.out.println("Pilihan tidak tersedia");
                    break;
                }
            }
        }
    }
    
    public void tambah() {
        Anggota anggota = view.form();
        view.alertLoading();
        
//        List<Anggota> anggotas = repos.get(anggota.getNo_KTP());
//        
//        if (anggotas.size() > 0) {
//            view.alertKTPExist();
//        }
//        else {
//            boolean saved = repos.insert(anggota);
//            if (saved) {
//                view.alertDataSaved();
//            } else {
//                view.alertDataNotSaved();
//            }
//        }
        
        boolean saved = repos.insert(anggota);
            if (saved) {
                view.alertDataSaved();
            } else {
                view.alertDataNotSaved();
            }
    }
}
