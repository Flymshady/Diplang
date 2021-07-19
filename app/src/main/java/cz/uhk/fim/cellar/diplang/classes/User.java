package cz.uhk.fim.cellar.diplang.classes;

/**
 * Třída reprezentující uživatele
 */
public class User {

    public String name, email, uid;

    public User(){}

    public User(String uid, String name, String email){
        this.uid=uid;
        this.name=name;
        this.email=email;
    }
    public User(String name, String email){
        this.name=name;
        this.email=email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}

