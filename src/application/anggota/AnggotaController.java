/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.anggota;

import application.menu.MenuController;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
                        this.update();
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
        
        anggota.setId_anggota(finallagi);
        
        boolean saved = repos.insert(anggota);
            if (saved) {
                view.alertDataSaved();
                System.out.println("\tID Anggota anda adalah " + finallagi);
            } else {
                view.alertDataNotSaved();
            }
    }
    
    public void update(){
        List<String> anggotas = new ArrayList<>();
        Anggota anggota = new Anggota();
        String ID = view.serachID();
        String result;
        boolean status;
        
        anggotas = repos.get(ID);
        int temp = view.showResult(anggotas);
        
        switch (temp) {
            case 1:
                    result = view.update("No KTP", anggotas.get(1));
                    status = repos.update("no_KTP", result, ID);
                    if (status) {
                        view.alertDataSaved();
                    } else {
                        view.alertDataNotSaved();
                    }
                break;
            case 2:
                    result = view.update("Nama Lengkap", anggotas.get(2));
                    status = repos.update("no_KTP", result, ID);
                    if (status) {
                        view.alertDataSaved();
                    } else {
                        view.alertDataNotSaved();
                    }
                break;
            case 3:
                    result = view.update("Alamat", anggotas.get(3));
                    status = repos.update("no_KTP", result, ID);
                    if (status) {
                        view.alertDataSaved();
                    } else {
                        view.alertDataNotSaved();
                    }
                break;
            case 4:    
                    result = view.update("Tempat, Tanggal Lahir", anggotas.get(4));
                    status = repos.update("no_KTP", result, ID);
                    if (status) {
                        view.alertDataSaved();
                    } else {
                        view.alertDataNotSaved();
                    }
                break;
            case 5:
                    result = view.update("No Telepon", anggotas.get(5));
                    status = repos.update("no_KTP", result, ID);
                    if (status) {
                        view.alertDataSaved();
                    } else {
                        view.alertDataNotSaved();
                    }
                break;
        }
    }
}
