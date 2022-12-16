/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package os;

import java.util.logging.Level;
import java.util.logging.Logger;
import static os.MainFrame.jTextArea1;
import static os.MainFrame.resources;

/**
 *
 * @author Salma
 */
public class Writer implements Runnable {
    //  FASTER STARVE-FREE WRITER CODE
     //MainFrame c = new MainFrame();
     @Override
public void run () {
    
         try {
                MainFrame.rw_mutex.acquire();
             
             MainFrame.resources = MainFrame.resources - 1 ;
             
             // ***** CRITICAL SECTION ***** // 
              System.out.println("writer"+ "  : "+ MainFrame.resources );
                MainFrame.jTextArea1.append("Seat reserved by Customer : " + Thread.currentThread().getName() + "             Seats Number Now is:    " + resources +"\n");
 
              MainFrame.rw_mutex.release();
         } catch (InterruptedException ex) {
             Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
         }

}


    
}
