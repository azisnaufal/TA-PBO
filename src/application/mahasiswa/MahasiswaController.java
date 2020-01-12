/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package application.mahasiswa;

import application.menu.MenuController;
import java.util.List;

/**
 *
 * @author oazisn
 */
public class MahasiswaController {
    private static MahasiswaView view = null;
    private static MahasiswaController instance = null;
    private static MahasiswaRepository repo = null;
    private MahasiswaController() {
        this.view = new MahasiswaView();
        this.repo = MahasiswaRepository.getInstance();
    }
    
    public static MahasiswaController getInstance(){
        if (instance == null)
            instance = new MahasiswaController();
        
        return instance;
    }
    
    public void index(){
        boolean loop = true;
        while(loop){
            int menu = view.menu();
            switch(menu){
                case 1 : {
                    this.tambah();
                    break;
                }
                case 2 : {
                    MenuController.getInstance().index();
                    loop = false;
                    break;
                }
                default: {
                    System.out.println("Pilihan tidak tersedia!");
                    break;
                }
            }
        }
    }
    
    private void tambah(){
        Mahasiswa mahasiswa = view.form();
        view.alertLoading();
        List<Mahasiswa> mahasiswas = repo.get(mahasiswa.getNim());
        
        if (mahasiswas.size() > 0){
            view.alertNimExist();
        }
        else {
            boolean saved = repo.insert(mahasiswa);
            if (saved){
                view.alertDataSaved();
            }
            else {
                view.alertDataNotSaved();
            }
        }
    }
}
