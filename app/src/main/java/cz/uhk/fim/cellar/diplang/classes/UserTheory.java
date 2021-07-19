package cz.uhk.fim.cellar.diplang.classes;

/**
 * Třída reprezentující uživatelovu teoretickou poznámku
 */
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
