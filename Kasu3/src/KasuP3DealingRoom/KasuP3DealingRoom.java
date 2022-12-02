package KasuP3DealingRoom;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KasuP3DealingRoom implements KasuP3DealingRoomInterface {

    static final int PORT = 10200;
    static final String ADR = "228.5.6.7";
    //ArrayLists to store objects of different users
    public static ArrayList<KasuP3User> users = new ArrayList<>(); //list for all client-publishers and subscribers
    public static ArrayList<KasuP3User> usersPub = new ArrayList<>(); //list for publishers
    public static ArrayList<KasuP3User> usersSub = new ArrayList<>(); //list for subscribers
    //Creating instances to messages class
    public static KasuP3messages MSGs = new KasuP3messages();
    public static KasuP3messages MSGAll = new KasuP3messages(); //operates on messages to be sent to all subscribers
    public static KasuP3messages MSGsub = new KasuP3messages(); //operates on messages to be sent to subscriber list
    static Thread regT;
    static KasuP3Admin admin;
    KasuP3User user;
    static KasuP3Registrar registrar;
    //HashMap to store the Publisher-Subscriber list
    static HashMap<String, List<KasuP3User>> map = new HashMap<String, List<KasuP3User>>();
    static ArrayList<KasuP3User>[] l = (ArrayList<KasuP3User>[]) new ArrayList[6];

    
    public static void main(String[] args) {
        registrar = new KasuP3Registrar();
        regT = new Thread(registrar); //Registration thread waiting for new connections
        regT.start();
        admin = new KasuP3Admin();
        Thread admT = new Thread(admin); //Admin thread writing 100 messages on the webpage: http://localhost:9090/
        admT.start();
        try {
            listenForInput();

        } catch (Exception ex) {
            Logger.getLogger(KasuP3DealingRoom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //method to add topics by publisher
    public static void addTopic(String topic, int c) {
    	l[c] = new ArrayList<KasuP3User>(10);
    	map.put(topic, l[c]);
    	System.out.println(map);
    	System.out.println("Topic " + (c) + " added");
    }
    
    //method to display available topics to the subscriber
    public static void getTopics(int count1) throws IOException{
    	Set<String> s = new HashSet<>();
    	s = map.keySet();
    	KasuP3DealingRoom.sendToSub("Topics available to subscribe are- ", count1);
    	s.forEach(key -> {
    		try {
				KasuP3DealingRoom.sendToSub(key, count1);
			} catch (IOException e) {
				e.printStackTrace();
			}
    	});
	//	return map;
    }
    
    //method to read the topics for subscription from each subscriber
    public static void getSubTopics(String message2, KasuP3User sub) throws IOException{
    	try {
    			if(map.containsKey(message2)) {
    				map.get(message2).add(sub);
    			}
    		System.out.println(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    //method to add users/clients to the list
    public void addUser(String name, String password, String type, String ip, int port, int count) throws IOException { //adding user to the group
        KasuP3User u = new KasuP3User(name, password, type, ip, port, count);
        Thread uT = new Thread(u);
        KasuP3DealingRoom.users.add(u);
        uT.start(); //each user has a thread.
        KasuP3DealingRoom.sendToAll(name + " has connected.");
    }
    
    //adding new publisher to list
    public static void addPub(String name, String password, String type, String ip, int port, int count) throws IOException { //adding user to the group
    	Boolean check = pubAuthentication(name, password);
    	if(check==true) {
		System.out.println("Publisher " + name + " authentication complete.");
    	KasuP3User u = new KasuP3User(name, password, type, ip, port, count);
        Thread uT = new Thread(u);
        KasuP3DealingRoom.usersPub.add(u);
        KasuP3DealingRoom.users.add(u);
        uT.start(); //each user has a thread.
       // KasuP3DealingRoom.sendToAll(name + " has connected.");
        System.out.println(name+" has connected.");
    	}
    }
    
    //checking for valid publisher
    public static Boolean pubAuthentication(String name, String password) throws FileNotFoundException {
    	Scanner sc2 = new Scanner(new FileInputStream("E:\\eclipse\\Kasu3\\src\\KasuP3DealingRoom\\pubList"));
    	int lineNum =1;
		while(sc2.hasNextLine()) {
		   String l = sc2.nextLine();  
		   while(l.indexOf(name)!=-1) {
			   int lineNum1 =1;
			   Scanner sc3 = new Scanner(new FileInputStream("E:\\eclipse\\Kasu3\\src\\KasuP3DealingRoom\\pubList"));
			   while(sc3.hasNextLine()) {
				   String ll = sc3.nextLine();
				   while(ll.indexOf(password)!=-1) {
					   if(lineNum==lineNum1) {
						   return true;	
				   }
					   else {
						   System.out.println("Invalid credentials");
						   return false; }
		   }lineNum1++;
			   }
		   }lineNum++;
		}
		return null;
		
    }
    
    //add a new subscriber to list
    public static KasuP3User addSub(String name, String password, String type, String ip, int port, int count) throws IOException { //adding user to the group
    	Boolean check = subAuthentication(name, password);
    	KasuP3User u = null;
    	if(check==true) {
		System.out.println("Subscriber " + name + " authentication complete.");
    	u = new KasuP3User(name, password, type, ip, port, count);
        Thread uT = new Thread(u);
        KasuP3DealingRoom.usersSub.add(u);
        KasuP3DealingRoom.users.add(u);
        uT.start(); //each user has a thread.
       // KasuP3DealingRoom.sendToAll(name + " has connected.");
        System.out.println(name + " has connected.");
    	}return u;
    }
    
    //Checking for valid subscriber
    public static Boolean subAuthentication(String name, String password) throws FileNotFoundException {
    	Scanner sc2 = new Scanner(new FileInputStream("E:\\eclipse\\Kasu3\\src\\KasuP3DealingRoom\\subList"));
    	int lineNum =1;
		while(sc2.hasNextLine()) {
		   String l = sc2.nextLine();  
		   while(l.indexOf(name)!=-1) {
			   int lineNum1 =1;
			   Scanner sc3 = new Scanner(new FileInputStream("E:\\eclipse\\Kasu3\\src\\KasuP3DealingRoom\\subList"));
			   while(sc3.hasNextLine()) {
				   String ll = sc3.nextLine();
				   while(ll.indexOf(password)!=-1) {
					   if(lineNum==lineNum1) {
						   return true;	
				   }
					   else {
						   System.out.println("Invalid credentials");
						   return false; }
		   }lineNum1++;
			   }
		   }lineNum++;
		}
		return null;
    }
    
    //method to send news to all users/clients
    public static void sendToAll(String msg) throws IOException {
        MSGs.addMsg(msg);
        for (int i = 0; i < users.size(); i++) {
            users.get(i).sendMsg(msg); //sending messages to each connected user
        }
    }
    
    //method to send news to all subscribers
    public static void sendToAllSub(String msg) throws IOException{
    	MSGAll.addMsg(msg);
    	for(int  i=0; i<usersSub.size(); i++) {
    		usersSub.get(i).sendMsg(msg);
    	}
    }
    
    //method to send news to respective subscriber groups
    public static void sendToSubGrp(String msg, int v) throws IOException{
    	MSGsub.addMsg(msg);
    	for(int i=0;i<l[v].size();i++) {
    		l[v].get(i).sendMsg(msg);
    	}
    }

    //method to send news to individual subscribers
    public static void sendToSub(String msg, int count1) throws IOException {
    	MSGAll.addMsg(msg);
    	usersSub.get(count1-1).sendMsg(msg);
    }
    
    //method to remove a user/client
    public static void removeUser(KasuP3User u) throws IOException {
        String name = u.getUName();
       // KasuP3DealingRoom.sendToAll(name + " unsubscribed.");
        System.out.println(name + " unsubscribed.");
        u.stopUser();
        users.remove(u); //when a user is disconnected, its reference has to be deleted.
    }

    
    public static void listenForInput() throws Exception {
        Scanner console = new Scanner(System.in);
        while (true) {
            while (!console.hasNextLine()) {
                Thread.sleep(1);
            }
            String input = console.nextLine();

            if (input.equalsIgnoreCase("quit")) {
                break;
            }
        }
        console.close();
        shutDownKasuP3DealingRoom();
    }

    //method to turn off the dealing room server
    public static void shutDownKasuP3DealingRoom() {
        try {
            KasuP3DealingRoom.sendToAll("/offline");
            registrar.stopRegistrar();
            
            for (int i = 0; i < users.size(); i++) {
                users.get(i).stopUser();
            }
            users.clear();
            admin.stopRegistrar();
            System.out.println("KasuP3DealingRoom is off");
            //System.exit(0);
        } catch (IOException ex) {
            Logger.getLogger(KasuP3DealingRoom.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
