/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.base;

import java.util.Scanner;

/**
 *
 * @author oazisn
 */
public class BaseView {
    public static Scanner scanner = new Scanner(System.in); 
    
    public void alertLoading(){
        System.out.println("Sedang memproses...");
    }
}
