package KasuP3Publisher;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class KasuP3Receiver extends Thread {
    private DataInputStream in =  null; 
    String msg = "";
    private volatile boolean shouldRun = true;
    @Override
    public void run(){
        while(shouldRun){
            try {
                msg = in.readUTF();
                System.out.println(msg);
                KasuP3Publisher.msgUi.updateMsg(msg);
            } catch (IOException ex) {
                try {
                    System.out.println("Server left.");
                    break;
                } catch (Exception ex1) {
                    Logger.getLogger(KasuP3Receiver.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
    }
    
    public KasuP3Receiver() throws IOException{
        in = new DataInputStream( 
                new BufferedInputStream(KasuP3Publisher.socket.getInputStream())); 
    }
  
    //read the message/news
   public String readMsg() {
	return msg; 
   }
    
   //stop running the receiver 
   public void stopReceiver() throws IOException {
        shouldRun = false;
        in.close();
    }
}
