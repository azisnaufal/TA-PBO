/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.bayar;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author binta
 */
public class BayarController {
    private static BayarView view = null;
    private static BayarController instance = null;
    private static BayarRepository repos = null;
    
    private BayarController(){
        BayarController.view = new BayarView();
        BayarController.repos = BayarRepository.getInstance();
    }
    
    public static BayarController getInstance(){
        if (instance == null){
            instance = new BayarController();
        }
        
        return instance;
    }
    
    public void index(){
        boolean loop = true;
        while (loop){
            int menu = view.menu();
            switch (menu){
                case 1:
                        this.bayar();
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
    
    public void bayar(){
        Bayar bayar = view.form();
       
        Date date = new Date();
        
        long tanggal = date.getTime();
        Timestamp ts = new Timestamp(tanggal);
        
        
        bayar.setTanggal(ts);
  
        boolean saved = repos.insert(bayar);
        if (saved){
            view.alertDataSaved();
        }else{
            view.alertDataNotSaved();
        }
    }

}
