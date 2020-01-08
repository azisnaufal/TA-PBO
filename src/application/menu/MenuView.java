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
        System.out.println("1. Pilihan 1");
        System.out.println("2. Pilihan 2");
        System.out.print("Pilihan Anda : ");
        return scanner.nextInt();
    }
}
