package KasuP3DealingRoom;

import java.io.IOException;

public interface KasuP3DealingRoomInterface {
	//There a number of static methods in the present implementation, which can be definned either in the interface or class.
	//As static methods cannot be overridden once defined in the interface, they are defined directly in the class.
	//public void addTopic(String topic, int c);
	//public void getTopics(int count1) throws IOException;
	//public void getSubTopics(String message2, KasuP3User sub) throws IOException;
	public void addUser(String name, String password, String type, String ip, int port, int count) throws IOException;
	//public void addPub(String name, String password, String type, String ip, int port, int count) throws IOException;
	//public Boolean pubAuthentication(String name, String password) throws FileNotFoundException;
	//public KasuP3User addSub(String name, String password, String type, String ip, int port, int count) throws IOException;
	//public Boolean subAuthentication(String name, String password) throws FileNotFoundException;
	//public void removeUser(KasuP3User u) throws IOException;
}
