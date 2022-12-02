package KasuP3DealingRoom;

public class KasuP3messages {//Circular data structure that saves the last 100 messages.
	    String[] entries = new String[100];
	    int start = 0;
	    int size = 0;
	    int index = 0;
	    boolean k = false;
	    
	    //method to add messages/news
	    public void addMsg(String msg){
	        entries[index] = msg;
	        index++;
	        index = index%100;
	        size++;
	        if(size>100){
	            start++;
	            start = start%100;
	        }
	    }
	    
	    //method to retrive messages/news
	    public String getMsg(int i){
	        if(entries[i%100] == null){
	            return "";
	        }
	        return entries[i%100];
	    }
	    public int size(){
	        return size;
	    }
	    public int start(){
	        return start;
	    }
	    
	    public KasuP3messages(){}
	}
