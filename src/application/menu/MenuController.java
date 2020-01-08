/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.menu;

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
    
    public void index(){
        int menu = view.menu();
        switch(menu){
            case 1 : {
                System.out.println("Anda memilih 1");
                break;
            }
            case 2 : {
                System.out.println("Anda memilih 2");
                break;
            }
            default: {
                System.out.println("Pilihan tidak tersedia!");
                break;
            }
        }
    }
}
