package KasuP3Publisher;

import java.io.IOException;


public class KasuP3messageCL implements Runnable{
    static boolean disconnected = false;
    private volatile String messages = "";
    KasuP3Sender sender;
    KasuP3Receiver receiver;
    Thread sT; //sender thread
    Thread rT; //receiver thread
    public void run() {
       
    }

    private synchronized void addMessage(String msg) {
        if(msg.equals("/offline")){
        }else{
            this.messages += "\n" + msg;
        }
    }

    
    public void updateMsg(String msg) {
        addMessage(msg);
    }


    
    public KasuP3messageCL() throws IOException {
        sender = new KasuP3Sender();
        sT = new Thread(sender);
        sT.start();
        try {
			receiver = new KasuP3Receiver();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        rT = new Thread(receiver);
        rT.start();
    }

    
    
    public static void main(String args[]) throws IOException {

    }

   
}
