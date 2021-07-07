package cz.uhk.fim.cellar.diplang.Classes;

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
}

