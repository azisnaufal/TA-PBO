/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.anggota;

import java.text.SimpleDateFormat;
import java.util.Date;
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
                case 0:
                        System.exit(0);
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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        
        String finalid;
        String tahun;
        String bulan;
        String finallagi;
        String id = Integer.toString(repos.getId_anggota());
        
        if (id.length() == 1) {
            id = "00" + id;
        } else if (id.length() == 2) {
            id = "0" + id;
        }
        
        finalid = simpleDateFormat.format(date);
        System.out.println(finalid);
        tahun = finalid.substring(6, 10);
        bulan = finalid.substring(3, 5);
        
        finallagi = "KSB/" + tahun + bulan + "/" + id;
        
        anggota.setId_anggota(finallagi);
        
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
