package cz.uhk.fim.cellar.diplang.classes;

/**
 * @author Štěpán Cellar - FIM UHK
 * Třída reprezentující úlohu cvičení, která nabízí více možnosti pro odpověď ale pouze jednu správnou
 */
public class OptionsTask {

    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private int points;
    private String rightAnswer;
    private String text;
    private String hint;

    public OptionsTask(String optionA, String optionB, String optionC, String optionD, int points, String rightAnswer, String text, String hint) {
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.points = points;
        this.rightAnswer = rightAnswer;
        this.text = text;
        this.hint = hint;
    }

    public OptionsTask(String optionA, String optionB, String optionC, String optionD, int points, String rightAnswer, String text) {
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.points = points;
        this.rightAnswer = rightAnswer;
        this.text = text;
    }

    public OptionsTask(){

    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
