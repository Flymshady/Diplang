package cz.uhk.fim.cellar.diplang.classes;

public class FriendRequest {
    private String created;
    public FriendRequest(){}

    public FriendRequest(String created) {
        this.created = created;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
