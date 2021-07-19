package cz.uhk.fim.cellar.diplang.classes;

/**
 * Třída reprezentující úlohu cvičení
 */
public class PageTask {

    private String text;
    private String rightAnswer;
    private String rightAnswer2;
    private int points;
    private String link;

    public PageTask(String text, String rightAnswer, String rightAnswer2, int points, String link) {
        this.text = text;
        this.rightAnswer = rightAnswer;
        this.rightAnswer2 = rightAnswer2;
        this.points = points;
        this.link = link;
    }

    public PageTask(String text, String rightAnswer, int points, String link) {
        this.text = text;
        this.rightAnswer = rightAnswer;
        this.points = points;
        this.link = link;
    }

    public PageTask(String text, String link) {
        this.text = text;
        this.link = link;
    }

    public PageTask(String text, String rightAnswer, int points) {
        this.text = text;
        this.rightAnswer = rightAnswer;
        this.points = points;
    }

    public PageTask(String text, String rightAnswer, String rightAnswer2, int points) {
        this.text = text;
        this.rightAnswer = rightAnswer;
        this.rightAnswer2 = rightAnswer2;
        this.points = points;
    }

    public String getRightAnswer2() {
        return rightAnswer2;
    }

    public void setRightAnswer2(String rightAnswer2) {
        this.rightAnswer2 = rightAnswer2;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public PageTask(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public PageTask(){

    }
}
