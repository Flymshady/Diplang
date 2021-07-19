package cz.uhk.fim.cellar.diplang.classes;

/**
 * Třída reprezentující stránku lekce - cvičení
 */
public class LessonPage {

    private int pagePoints;
    private String info;

    public LessonPage(int pagePoints, String info) {
        this.pagePoints = pagePoints;
        this.info = info;
    }

    public LessonPage(){

    }

    public int getPagePoints() {
        return pagePoints;
    }

    public void setPagePoints(int pagePoints) {
        this.pagePoints = pagePoints;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
