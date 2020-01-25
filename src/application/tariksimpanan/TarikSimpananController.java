/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.tariksimpanan;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ary
 */
public class TarikSimpananController {
    private static TarikSimpananView view = null;
    private static TarikSimpananController instance = null;
    private static TarikSimpananRepository repos = null;
    
    private TarikSimpananController(){
        this.view = new TarikSimpananView();
        this.repos = TarikSimpananRepository.getInstance();
    }
    
    public static TarikSimpananController getInstance(){
        if (instance == null){
            instance = new TarikSimpananController();
        }
        
        return instance;
    }
    
    public void index(){
        boolean loop = true;
        while (loop){
            int menu = view.menu();
            switch (menu){
                case 1:
                        this.tambah();
                    break;
                case 0:
                        System.exit(0);
                    break;
                default:{
                    System.out.println("Pilihan tidak tersedia");
                    break;
                }
            }
        }
    }
    
    public void tambah(){
        TarikSimpanan tarikSimpanan = view.form();
       
        Date date = new Date();
        
        long tanggal = date.getTime();
        Timestamp ts = new Timestamp(tanggal);
        tarikSimpanan.setTanggal(ts);
        
        //List<TarikSimpanan> tarikSimpanans = repos.get(tarikSimpanan.getId_anggota());
        
        boolean saved = repos.getPoin_sukarela(tarikSimpanan) && repos.insert(tarikSimpanan);
        if (saved){
            view.alertDataSaved();
        }else{
            view.alertDataNotSaved();
        }
    }
}
