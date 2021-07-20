package cz.uhk.fim.cellar.diplang.classes;

/**
 * @author Štěpán Cellar - FIM UHK
 * Třída reprezentující odpověď uživatele v úloze
 */
public class UserTask {

    private String answer;
    private int points;
    private String created; //LocalDateTime.now();

    public UserTask(String answer, int points, String created) {
        this.answer = answer;
        this.points = points;
        this.created = created;
    }

    public UserTask(String answer, int points) {
        this.answer = answer;
        this.points = points;
    }

    public UserTask(){

    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
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

}
