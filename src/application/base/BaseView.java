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
    
    static class Loading implements Runnable{
        
        private boolean run = true;
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                if (run){
                    try {
                        System.out.print(".");
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException ex) {
                        
                    }
                }
                else {
                    break;
                }
            }
        }
        
        public void stop(){
            run = false;
        }
        
    }
    
    protected static Loading task = new Loading();
    public static Scanner scanner = new Scanner(System.in);
    
    public void printLn(String params){
        System.out.println("\t" + params);
    }
    
    public void print(String params){
        System.out.print("\t" + params);
    }
    
    public void alertLoading(){
        System.out.print("\tSedang memproses");
        scanner.nextLine();
                
        Thread thread = new Thread(task);
        thread.start();
                        
    }
    
    public void stopLoading(){
        task.stop();
    }
    
    
}
