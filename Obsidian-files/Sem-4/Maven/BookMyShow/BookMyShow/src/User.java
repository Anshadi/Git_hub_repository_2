import java.util.UUID;


public class User {
    private String name;
    private String email;
    public final String Id;

    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    

    public User(String name, String email) {
        this.Id = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
    }
    
    @Override
    public String toString(){
        return this.email + " " + this.name;
    }
}
