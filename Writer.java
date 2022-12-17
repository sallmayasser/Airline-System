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
           MainFrame.entry_mutex.acquire();   //  this has same significance as in above
             
             MainFrame.out_mutex.acquire();
             //  this ensures that out_mutex is not being changed while it executes
             
             if(MainFrame.in_count == MainFrame.out_count) {
                 // means no reader is currently executing in the critical section
                 // so it simply releases the out_mutex and enters the critical section
                 
                 MainFrame.out_mutex.release();
                 
             }
             else {
                 // means some reader process is in critical section
                 
                 MainFrame.writer_waiting = true;  // the writer process would have to wait
                 MainFrame.out_mutex.release();   // this was only to ensure sync of out_count
                 
                 MainFrame.rw_mutex.acquire();
                 // now the writer process enters the blockedQueue of the rw_semaphore
                 // after acquiring it, the proces is now ready to enter the critical section
                 
                 MainFrame.writer_waiting = false;
             }
             
                // ***** CRITICAL SECTION ***** // 
             
             MainFrame.resources = MainFrame.resources - 1 ;
             System.out.println("writer"+ "  : "+ MainFrame.resources );
                MainFrame.jTextArea1.append("Seat reserved by Customer : " + Thread.currentThread().getName() + "             Seats Number Now is:    " + resources +"\n");
         
             
        
            MainFrame.entry_mutex.release();
         } catch (InterruptedException ex) {
             Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
         }

}


    
}
