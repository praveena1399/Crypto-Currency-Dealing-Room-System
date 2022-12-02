package KasuP3DealingRoom;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KasuP3Registrar implements Runnable { //Thread that waits for users to connect to respective groups

    MulticastSocket socket;
    InetAddress multicastGroup;
    private volatile boolean shouldRun = true;
    static int count=1;
    static int count1 = 0;
    @SuppressWarnings("deprecation")
	@Override
    public void run() {

        try {
            multicastGroup = InetAddress.getByName(KasuP3DealingRoom.ADR);
            socket = new MulticastSocket(KasuP3DealingRoom.PORT);
            socket.joinGroup(multicastGroup);

            while (shouldRun) {
                System.out.println("Server Running..");
                byte[] data = new byte[1000];
                DatagramPacket packet = new DatagramPacket(data, data.length);
                try {
                    socket.receive(packet);
                } catch (IOException ex) {
                }

                String message = new String(packet.getData(), 0, packet.getLength());
                System.out.println(message);

                if (!message.isEmpty()) {
                    String[] arr = message.split(","); //whenever a request is received, a username and password is sent with it. 
                 //   Server.addUser(arr[0], arr[1],arr[2], arr[4], Integer.parseInt(arr[3])); //registering user to the group.
                    if(arr[2].equals("publisher")) {
                    	//registering publisher to the group
                    	KasuP3DealingRoom.addPub(arr[0], arr[1], arr[2], arr[4], Integer.parseInt(arr[3]),count);
                    	 byte[] data1 = new byte[1000];
                         DatagramPacket packet1 = new DatagramPacket(data1, data1.length);
                         try {
                             socket.receive(packet1);
                         } catch (IOException ex) {
                         }
                         //Reading topics from publisher
                         String message1 = new String(packet1.getData(), 0, packet1.getLength());
                         if(!message1.isEmpty()) {
                        	 KasuP3DealingRoom.addTopic(message1, count);
                        	// System.out.println(count);
                        	 }
                         count++;
                       //  msgFunc();
                    	}
                   // System.out.println(Server.usersPub);
                    else  if(arr[2].equals("subscriber")) {
                    	//registering subscriber to the group
                    	KasuP3User sub = KasuP3DealingRoom.addSub(arr[0], arr[1], arr[2], arr[4], Integer.parseInt(arr[3]), count1);
                    	count1++;
                    	KasuP3DealingRoom.getTopics(count1);
                    	byte[] data2 = new byte[1000];
                        DatagramPacket packet2 = new DatagramPacket(data2, data2.length);
                        try {
                            socket.receive(packet2);
                        } catch (IOException ex) {
                        }
                        //reading topics for subscription from the subscribers
                        String message2 = new String(packet2.getData(), 0, packet2.getLength());
                        if(!message2.isEmpty()) {
                        	KasuP3DealingRoom.getSubTopics(message2, sub);
                       	 }
                        
                        
                        byte[] data3 = new byte[1000];
                        DatagramPacket packet3 = new DatagramPacket(data3, data3.length);
                        try {
                            socket.receive(packet3);
                        } catch (IOException ex) {
                        }

                        String message3 = new String(packet3.getData(), 0, packet3.getLength());
                        if(!message3.isEmpty()) {
                        	KasuP3DealingRoom.getSubTopics(message3, sub);
                       	 }
                    	
                        
                        byte[] data4 = new byte[1000];
                        DatagramPacket packet4 = new DatagramPacket(data4, data4.length);
                        try {
                            socket.receive(packet4);
                        } catch (IOException ex) {
                        }

                        String message4 = new String(packet4.getData(), 0, packet4.getLength());
                        if(!message4.isEmpty()) {
                        	KasuP3DealingRoom.getSubTopics(message4, sub);
                       	 }
                        
                        
                        byte[] data5 = new byte[1000];
                        DatagramPacket packet5 = new DatagramPacket(data5, data5.length);
                        try {
                            socket.receive(packet5);
                        } catch (IOException ex) {
                        }

                        String message5 = new String(packet5.getData(), 0, packet5.getLength());
                        if(!message5.isEmpty()) {
                        	KasuP3DealingRoom.getSubTopics(message5, sub);
                       	 }
                        
                        
                        byte[] data6 = new byte[1000];
                        DatagramPacket packet6 = new DatagramPacket(data6, data6.length);
                        try {
                            socket.receive(packet6);
                        } catch (IOException ex) {
                        }

                        String message6 = new String(packet6.getData(), 0, packet6.getLength());
                        if(!message6.isEmpty()) {
                        	KasuP3DealingRoom.getSubTopics(message6, sub);
                       	 }
                    	
                    	}
                } System.out.println();
            }
            System.out.println("server is off");
        } catch (UnknownHostException ex) {
            Logger.getLogger(KasuP3Registrar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(KasuP3Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
    //function to stop the writer thread.it is called from main method
    public void stopRegistrar() throws IOException {
        shouldRun = false;
        socket.leaveGroup(multicastGroup);
        socket.close();
    }

}

