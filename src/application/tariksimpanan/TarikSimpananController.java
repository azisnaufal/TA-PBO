/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.tariksimpanan;

import java.text.SimpleDateFormat;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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
       
        String bulan;
        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        bulan = simpleDateFormat.format(date).substring(3, 5);
        
        tarikSimpanan.setBulan(bulan);
  
        boolean saved = repos.insert(tarikSimpanan);
        if (saved){
            view.alertDataSaved();
        }else{
            view.alertDataNotSaved();
        }
    }
}
