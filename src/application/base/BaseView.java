/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.base;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
/**
 *
 * @author oazisn
 */
public class BaseView {
    public static Scanner scanner = new Scanner(System.in); 
    
    public void printLn(String params){
        System.out.println("\t" + params);
    }
    
    public void print(String params){
        System.out.print("\t" + params);
    }
    
    public void alertLoading(){
        System.out.print("Sedang memproses");
        for (int i = 0; i < 5; i++) {
            try {
                System.out.print(".");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
     
            }
        }
    }
}
