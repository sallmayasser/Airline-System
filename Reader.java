package os;
import os.MainFrame;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import static os.MainFrame.resources;
/**
 *
 * @author Salma
 */

public class Reader implements Runnable{
    //   STARVE-FREE READER CODE
    //MainFrame c = new MainFrame();
     @Override
     public void run () {
    
   
     
         try {
          
            MainFrame.entry_mutex.acquire();
             
             ++MainFrame.in_count;
             MainFrame.entry_mutex.release();  
             
             // ***** CRITICAL SECTION ***** //
             System.out.println("reader"+ "  : "+ MainFrame.resources);
             
             MainFrame.jTextArea1.append("Seats Number Viewed by Customer :   " + Thread.currentThread().getName() + "           Seats Number is:   " + resources +"\n");
            
             MainFrame.out_mutex.acquire();
             
             ++MainFrame.out_count;
             
             if(MainFrame.writer_waiting == true && (MainFrame.in_count == MainFrame.out_count)){
                 
                 MainFrame.rw_mutex.release();
                 
             } 
             MainFrame.out_mutex.release();
             
     
         } catch (InterruptedException ex) {
             Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
         }
    
  



}
}
