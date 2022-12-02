package KasuP3Subscriber;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KasuP3Sender extends Thread {

    public KasuP3Sender() {
    }

    private DataOutputStream out = null;
    private volatile boolean shouldRun = true;

    public void run() {
        try {
            Scanner in = new Scanner(System.in);
            String msg = "";
            out = new DataOutputStream(KasuP3Subscriber.socket.getOutputStream());
            while (this.shouldRun) {
                msg = in.nextLine();
                out.writeUTF(msg);
            }
            
            System.out.println("sender is off");
            
        } catch (IOException ex) {
            Logger.getLogger(KasuP3Sender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //send messages/news
    public void sendMsg(String msg) {
        try {
            out = new DataOutputStream(KasuP3Subscriber.socket.getOutputStream());
            out.writeUTF(msg);
        } catch (IOException ex) {
            System.out.println("problem connecting to the server.");
        }
    }
    //stop and disconnect the sender
    public void stopSender() throws IOException {
        sendMsg("/disconnected");
        this.shouldRun = false;
        out.close();
        System.out.println("sender closed");
    }

}
