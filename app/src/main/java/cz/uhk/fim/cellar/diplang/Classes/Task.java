package cz.uhk.fim.cellar.diplang.Classes;

public class Task {

    private String text;
    private String rightAnswer;
    private int points;

    public Task(String text, String rightAnswer, int points) {
        this.text = text;
        this.rightAnswer = rightAnswer;
        this.points = points;
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

    public Task(){

    }
}
