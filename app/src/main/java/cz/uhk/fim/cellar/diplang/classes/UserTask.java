package cz.uhk.fim.cellar.diplang.classes;

public class UserTask {

    private String answer;
    private int points;
    private String dateTime;

    public UserTask(String answer, int points, String dateTime) {
        this.answer = answer;
        this.points = points;
        this.dateTime = dateTime;
    }

    public UserTask(String answer, int points) {
        this.answer = answer;
        this.points = points;
    }

    public UserTask(){

    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
