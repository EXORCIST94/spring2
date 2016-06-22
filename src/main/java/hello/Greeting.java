package hello;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Greeting {
    @Id
    private int id;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}