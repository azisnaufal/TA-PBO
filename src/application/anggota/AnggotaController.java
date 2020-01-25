/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.anggota;

import application.menu.MenuController;
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
    private static MenuController menus = null;
    
    private AnggotaController() {
        this.view = new AnggotaView();
        this.repos = AnggotaRepository.getInstance();
        this.menus = MenuController.getInstance();
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
                case 2:
                        
                    break;
                case 0:
                        menus.index();
                    break;
                default: {
                    System.out.println("\tPilihan tidak tersedia");
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
        String id;        
        
        finalid = simpleDateFormat.format(date);
        tahun = finalid.substring(6, 10);
        bulan = finalid.substring(3, 5);
        
        id = Integer.toString(repos.getId_anggota(bulan, tahun) + 1);
        if (id.length() == 1) {
            id = "00" + id;
        } else if (id.length() == 2) {
            id = "0" + id;
        }
        
        finallagi = "KSB/" + tahun + bulan + "/" + id;
        
        anggota.setBulan_masuk(bulan);
        anggota.setId_anggota(finallagi);
        
        view.alertLoading();
        
        boolean saved = repos.insert(anggota);
            if (saved) {
                view.alertDataSaved();
                System.out.println("\tID Anggota anda adalah " + finallagi);
            } else {
                view.alertDataNotSaved();
            }
    }
}
