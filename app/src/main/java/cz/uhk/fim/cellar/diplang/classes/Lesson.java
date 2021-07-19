package cz.uhk.fim.cellar.diplang.classes;

/**
 * Třída reprezentující lekci
 */
public class Lesson {

    private int pointsTotal;
    private String name;
    private String level;
    private int dipsGained;
    private int number;
    private String created;

    public Lesson(int pointsTotal, String name, String level, int dipsGained, int number, String created) {
        this.pointsTotal = pointsTotal;
        this.name = name;
        this.level = level;
        this.dipsGained = dipsGained;
        this.number = number;
        this.created = created;
    }

    public Lesson(int pointsTotal, String name, String level, int dipsGained, int number) {
        this.pointsTotal = pointsTotal;
        this.name = name;
        this.level = level;
        this.dipsGained = dipsGained;
        this.number = number;
    }

    public Lesson(int pointsTotal, String name, String level, int number) {
        this.pointsTotal = pointsTotal;
        this.name = name;
        this.level = level;
        this.number = number;
    }

    public Lesson() {
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getDipsGained() {
        return dipsGained;
    }

    public void setDipsGained(int dipsGained) {
        this.dipsGained = dipsGained;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPointsTotal() {
        return pointsTotal;
    }

    public void setPointsTotal(int pointsTotal) {
        this.pointsTotal = pointsTotal;
    }


}
