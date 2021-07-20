package cz.uhk.fim.cellar.diplang.classes;

/**
 * @author Štěpán Cellar - FIM UHK
 * Třída reprezentující frázi
 */
public class Phrase {


    private String englishPhrase;
    private String czechPhrase;
    private String created;

    public Phrase(){
    }

    public Phrase(String englishPhrase, String czechPhrase, String created) {
        this.englishPhrase = englishPhrase;
        this.czechPhrase = czechPhrase;
        this.created = created;
    }

    public Phrase(String englishPhrase, String czechPhrase) {
        this.englishPhrase = englishPhrase;
        this.czechPhrase = czechPhrase;
    }


    public String getEnglishPhrase() {
        return englishPhrase;
    }

    public void setEnglishPhrase(String englishPhrase) {
        this.englishPhrase = englishPhrase;
    }

    public String getCzechPhrase() {
        return czechPhrase;
    }

    public void setCzechPhrase(String czechPhrase) {
        this.czechPhrase = czechPhrase;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
