package KasuP3Subscriber;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KasuP3Subscriber {

    static final int PORT = 10200;
    static final String ADR = "228.5.6.7";
    static ServerSocket ss = null;
    static Socket socket = null;
    static KasuP3User u = null;
    static KasuP3messageCL msgUi = null;
    static KasuP3Sender sender;
    static KasuP3Receiver receiver ;
    
    
    public static void main(String[] args) throws IOException {        
        ss = new ServerSocket(0);
        sendConnectionDetails(ss.getLocalPort(), ss.getInetAddress().getHostName());
        socket = ss.accept();
        System.out.println("Connected!");
        sender = new KasuP3Sender();
        Thread sT = new Thread(sender);
        sT.start();
        receiver = new KasuP3Receiver();
        Thread rT = new Thread(receiver);
        rT.start();
    }
  //method to connect with dealing room server
    public static void sendConnectionDetails(int port, String hostname) throws IOException{
        try {
            InetAddress multicastGroup = null;
            DatagramSocket datagramSocket = new DatagramSocket();
            multicastGroup = InetAddress.getByName(ADR);
            String msg = u.getName() + "," + u.getPassword() + "," + u.getType() + "," +port + "," + hostname;
            byte[] data = msg.getBytes();
            DatagramPacket datagram = new DatagramPacket(data, data.length);
            datagram.setAddress(multicastGroup);
            datagram.setPort(PORT);            
            datagramSocket.send(datagram);
        } catch (UnknownHostException ex) {
            Logger.getLogger(KasuP3Subscriber.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //method to send participant type to the dealing room server
    public static void sendTopicType(String topic) throws IOException{
    	try {
    		InetAddress multicastGroup = null;
            DatagramSocket datagramSocket = new DatagramSocket();
            multicastGroup = InetAddress.getByName(ADR);
            String msg = topic;
            byte[] data = msg.getBytes();
            DatagramPacket datagram = new DatagramPacket(data, data.length);
            datagram.setAddress(multicastGroup);
            datagram.setPort(PORT);            
            datagramSocket.send(datagram);
    	}
    	catch(UnknownHostException ex) {
    		Logger.getLogger(KasuP3Subscriber.class.getName()).log(Level.SEVERE, null, ex);
    	}
    }
    
    public static void sendSubTopics(String topic) throws IOException{
    	try {
    		InetAddress multicastGroup = null;
            DatagramSocket datagramSocket = new DatagramSocket();
            multicastGroup = InetAddress.getByName(ADR);
            String msg = topic;
            byte[] data = msg.getBytes();
            DatagramPacket datagram = new DatagramPacket(data, data.length);
            datagram.setAddress(multicastGroup);
            datagram.setPort(PORT);            
            datagramSocket.send(datagram);
    	}
    	catch(UnknownHostException ex) {
    		Logger.getLogger(KasuP3Subscriber.class.getName()).log(Level.SEVERE, null, ex);
    	}
    }
    
    public static void sendSubTopics1(String topic) throws IOException{
    	try {
    		InetAddress multicastGroup = null;
            DatagramSocket datagramSocket = new DatagramSocket();
            multicastGroup = InetAddress.getByName(ADR);
            String msg = topic;
            byte[] data = msg.getBytes();
            DatagramPacket datagram = new DatagramPacket(data, data.length);
            datagram.setAddress(multicastGroup);
            datagram.setPort(PORT);            
            datagramSocket.send(datagram);
    	}
    	catch(UnknownHostException ex) {
    		Logger.getLogger(KasuP3Subscriber.class.getName()).log(Level.SEVERE, null, ex);
    	}
    }
    
    public static void sendSubTopics2(String topic) throws IOException{
    	try {
    		InetAddress multicastGroup = null;
            DatagramSocket datagramSocket = new DatagramSocket();
            multicastGroup = InetAddress.getByName(ADR);
            String msg = topic;
            byte[] data = msg.getBytes();
            DatagramPacket datagram = new DatagramPacket(data, data.length);
            datagram.setAddress(multicastGroup);
            datagram.setPort(PORT);            
            datagramSocket.send(datagram);
    	}
    	catch(UnknownHostException ex) {
    		Logger.getLogger(KasuP3Subscriber.class.getName()).log(Level.SEVERE, null, ex);
    	}
    }
    
    public static void sendSubTopics3(String topic) throws IOException{
    	try {
    		InetAddress multicastGroup = null;
            DatagramSocket datagramSocket = new DatagramSocket();
            multicastGroup = InetAddress.getByName(ADR);
            String msg = topic;
            byte[] data = msg.getBytes();
            DatagramPacket datagram = new DatagramPacket(data, data.length);
            datagram.setAddress(multicastGroup);
            datagram.setPort(PORT);            
            datagramSocket.send(datagram);
    	}
    	catch(UnknownHostException ex) {
    		Logger.getLogger(KasuP3Subscriber.class.getName()).log(Level.SEVERE, null, ex);
    	}
    }
    
    public static void sendSubTopics4(String topic) throws IOException{
    	try {
    		InetAddress multicastGroup = null;
            DatagramSocket datagramSocket = new DatagramSocket();
            multicastGroup = InetAddress.getByName(ADR);
            String msg = topic;
            byte[] data = msg.getBytes();
            DatagramPacket datagram = new DatagramPacket(data, data.length);
            datagram.setAddress(multicastGroup);
            datagram.setPort(PORT);            
            datagramSocket.send(datagram);
    	}
    	catch(UnknownHostException ex) {
    		Logger.getLogger(KasuP3Subscriber.class.getName()).log(Level.SEVERE, null, ex);
    	}
    }
}
