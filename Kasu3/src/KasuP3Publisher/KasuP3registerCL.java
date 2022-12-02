package KasuP3Publisher;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KasuP3registerCL{
	static int c =0;

    	public void run() {
        try {
        	System.out.println("Enter your username: ");
        	Scanner sc = new Scanner(System.in);
            String name = sc.next();
            System.out.println("Enter password: ");
            String password = sc.next();
            System.out.println("Enter participant type- publisher or subscriber: ");
            String type = sc.next();
            if(name.equals("")){
                showErr("Username cannot be empty");
                return;
            }
            if(password.equals("")){
                showErr("Password cannot be empty");
                return;
            }
            if(type.equals("")){
                showErr("Participant type cannot be empty");
                return;
            }
            KasuP3Publisher.u = new KasuP3User(name, password, type);
            try {
                
                KasuP3Publisher.ss = new ServerSocket(0);
                KasuP3Publisher.sendConnectionDetails(KasuP3Publisher.ss.getLocalPort(), KasuP3Publisher.ss.getInetAddress().getHostName());
                KasuP3Publisher.socket = KasuP3Publisher.ss.accept();
            } 
            
            catch (IOException ex) {
                Logger.getLogger(KasuP3registerCL.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(type.equals("publisher")) {
            	//Server.addPub();
            	System.out.println("Enter topic name with number Ex: 1.bitcoin - ");
            	String topic = sc.next();
            	KasuP3Publisher.sendTopicType(topic);
            	//Server.addTopic(topic,c);
            }
            else if(type.equals("subscriber")) {
            }

            
            KasuP3Publisher.msgUi = new KasuP3messageCL();
            if(type.equals("subscriber")) {
            	System.out.println("Enter the names (one by one) of topics you want to subscribe to- \n Enter DONE when you are done.");
            	String topic = "";
            	topic = sc.next();
            	if(!topic.equals("DONE")) {
            		KasuP3Publisher.sendSubTopics(topic);
        		topic = sc.next();
        		if(!topic.equals("DONE")) {
            		KasuP3Publisher.sendSubTopics1(topic);
        		topic = sc.next();
        		
        		if(!topic.equals("DONE")) {
            		KasuP3Publisher.sendSubTopics2(topic);
        		topic = sc.next();
        	
        		if(!topic.equals("DONE")) {
            		KasuP3Publisher.sendSubTopics3(topic);
        		topic = sc.next();
        	
        		if(!topic.equals("DONE")) {
            		KasuP3Publisher.sendSubTopics4(topic);
        		topic = sc.next();
        		}
        		}
        		}
        		}
        		}

            }
           
        } 
        catch(Exception e) {}
    }
    
    
    
    public static void main(String args[]) {
    	KasuP3registerCL r = new KasuP3registerCL();
       r.run();

    }
    public void showErr(String msg){
        System.out.print(msg);
        
    }
    

  
}
