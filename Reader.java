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
          
             
            ++MainFrame.read_count ;
            if(MainFrame.read_count == 1) {
                    MainFrame.rw_mutex.acquire();
                        }
            
             // ***** CRITICAL SECTION ***** //
             System.out.println("reader"+ "  : "+ MainFrame.resources);
             
             MainFrame.jTextArea1.append("Seats Number Viewed by Customer :   " + Thread.currentThread().getName() + "           Seats Number is:   " + resources +"\n");
          
            --MainFrame.read_count;
          
            if(MainFrame.read_count == 0){
           MainFrame.rw_mutex.release();
            }
             
     
         } catch (InterruptedException ex) {
             Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
         }
    
  



}
}
