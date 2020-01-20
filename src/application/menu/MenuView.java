/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.menu;

import application.base.BaseView;

/**
 *
 * @author oazisn
 */
public class MenuView extends BaseView{
    public int menu(){
        System.out.println("\tManu Utama");
        System.out.println("\t======================");
        System.out.println("\t1) Anggota");
        System.out.println("");
        System.out.println("\t0) Keluar");
        System.out.print("\tPilihan Anda : ");
        return scanner.nextInt();
    }
}
