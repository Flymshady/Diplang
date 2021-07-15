package cz.uhk.fim.cellar.diplang.classes;

public class FriendRequest {
    private String created;
    private String senderEmail;
    private String receiverEmail;
    private String senderId;
    private String receiverId;
    private String senderName;
    private String receiverName;
    public FriendRequest(){}

    public FriendRequest(String created, String senderEmail, String receiverEmail, String senderId, String receiverId, String senderName, String receiverName) {
        this.created = created;
        this.senderEmail = senderEmail;
        this.receiverEmail = receiverEmail;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.senderName = senderName;
        this.receiverName = receiverName;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
