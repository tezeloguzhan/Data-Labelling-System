public class User {
    Long id;
    String name;
    String type;
    String target;
    String value;

    public User(Long id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Long getId() {
        return this.id;
    }

    public User(String target, String value) {
        super();
        this.target = target;
        this.value = value;
    }

    public void labelInstance() {

    }
}