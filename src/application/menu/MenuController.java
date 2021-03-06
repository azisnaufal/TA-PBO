/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.menu;

import application.anggota.AnggotaController;
import application.bayar.BayarController;
import application.laporan.LaporanController;
import application.tariksimpanan.TarikSimpananController;

/**
 *
 * @author oazisn
 */
public class MenuController{
    private static MenuView view = null;
    private static MenuController instance = null;
    
    private MenuController() {
        this.view = new MenuView();
    }
    
    public static MenuController getInstance(){
        if (instance == null)
            instance = new MenuController();
        
        return instance;
    }
    
    public void init(){
        view.initialization();
    }
    
    public void index(){
        
        boolean loop = true;
        while(loop){
            int menu = view.menu();
            switch(menu){
                case 1 : {
                    loop = false;
                    AnggotaController.getInstance().index();
                    break;
                }
                case 2 : {
                    loop = false;
                    TarikSimpananController.getInstance().index();
                    break;
                }
                case 3 : {
                    loop = false;
                    LaporanController.getInstance().index();
                    break;
                }
                case 4 : {
                    loop = false;
                    BayarController.getInstance().index();
                    break;
                }
                case 0 : {
                    System.exit(0);
                    break;
                }
                default: {
                    view.alert("Pilihan tidak tersedia");
                    break;
                }
            }
        }
    }
}
