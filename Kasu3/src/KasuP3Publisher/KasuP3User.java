package KasuP3Publisher;

public class KasuP3User {

    String name;
    String password;
    String type;

    public KasuP3User(String name, String password, String type) {
        this.name = name;
        this.password = password;
        this.type = type;
    }
    //setter and getter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
    	return type;
    }
    
    public void setType(String type) {
    	this.type = type;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
