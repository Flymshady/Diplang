package cz.uhk.fim.cellar.diplang.classes;

public class UserTheory {
    private String text;

    public UserTheory(){

    }

    public UserTheory(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
