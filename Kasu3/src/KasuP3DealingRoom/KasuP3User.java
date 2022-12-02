package KasuP3DealingRoom;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class KasuP3User extends Thread{ //User Thread class.
    String name;
    String password;
    String ip;
    int port;
    String type;
    int count;
    Socket socket = null; //connected socket has to be saved
    private DataInputStream in =  null; 
    private DataOutputStream out     = null; 
    private volatile boolean shouldRun = true;
    
    @Override
    public void run(){
        while(shouldRun){
            try {
                String msg;
                if(this.type.equals("publisher")) {
                msg = in.readUTF();
                
                if(msg.equals("/disconnected")){
                    KasuP3DealingRoom.removeUser(this);
                    break;
                }
                System.out.println(this.count);
                System.out.println(this.name + ": " + msg);
               // Server.sendToAll(this.name + ": " + msg);
               KasuP3DealingRoom.sendToSubGrp(this.name + ": "+ msg, this.count);
                }   
            } catch (IOException ex) {
                System.out.println(this.getName() + " disconnected."); //when an exception is found, the user is immediately disconnected.
                try {
                    KasuP3DealingRoom.removeUser(this);
                } catch (IOException ex1) {
                    Logger.getLogger(KasuP3User.class.getName()).log(Level.SEVERE, null, ex1);
                }
                break;
            }
            
        }
    }
   //constructor 
    public KasuP3User(String name, String password, String type, String ip, int port, int count) {
        this.name = name;
        this.password = password;
        this.ip = ip;
        this.port = port;
        this.type = type;
        this.count = count;

        try {
            socket = new Socket(this.ip, this.port);
            in = new DataInputStream( 
                new BufferedInputStream(socket.getInputStream())); 
            out = new DataOutputStream(socket.getOutputStream()); 
        } catch (IOException ex) {
            
        }
        
    }
    
    //setter and getter methods
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getCount() {
    	return count;
    }
    
    public void setCount(int count) {
    	this.count = count;
    }
    
    public KasuP3User(String name, String password, String type, int count) {
        this.name = name;
        this.password = password;
        this.type = type;
        this.count = count;
    }

    public String getUName() {
        return name;
    }

    public void setUName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getType() {
    	return type;
    }
    
    public void setType(String type) {
    	this.type = type;
    }
    
    //method to send message/news to the subscribers
    void sendMsg(String msg) throws IOException{
        try{
            out.writeUTF(msg); //sending message to the connected socket.
            
        }catch (IOException ex) {
                System.out.println(this.getName() + " disconnected.");
                KasuP3DealingRoom.removeUser(this);
        }
    }
    
    //to stop the user/cliennt and disconnect
     public void stopUser() throws IOException {
        shouldRun = false;
        in.close();
        out.close();
        socket.close();
    }
     

}
