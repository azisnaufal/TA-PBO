/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import application.anggota.AnggotaController;
import application.menu.MenuController;

/**
 *
 * @author oazisn
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        AnggotaController.getInstance().index();
    }
    
}
