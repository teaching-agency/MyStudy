package jframe;

public class Course {

    private String id;
    private String name;
    private Double credit;
    private int timeTheory;
    private int timeExperiment;
    private Double scoreCall;
    private Double scoreJob;
    private Double scoreExp;
    private Double scoreExm;
    private Double scoreWord;
    private Double scorePro;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public int getTimeTheory() {
        return timeTheory;
    }

    public void setTimeTheory(int timeTheory) {
        this.timeTheory = timeTheory;
    }

    public int getTimeExperiment() {
        return timeExperiment;
    }

    public void setTimeExperiment(int timeExperiment) {
        this.timeExperiment = timeExperiment;
    }

    public Double getScoreCall() {
        return scoreCall;
    }

    public void setScoreCall(Double scoreCall) {
        this.scoreCall = scoreCall;
    }

    public Double getScoreJob() {
        return scoreJob;
    }

    public void setScoreJob(Double scoreJob) {
        this.scoreJob = scoreJob;
    }

    public Double getScoreExp() {
        return scoreExp;
    }

    public void setScoreExp(Double scoreExp) {
        this.scoreExp = scoreExp;
    }

    public Double getScoreExm() {
        return scoreExm;
    }

    public void setScoreExm(Double scoreExm) {
        this.scoreExm = scoreExm;
    }

    public Double getScoreWord() {
        return scoreWord;
    }

    public void setScoreWord(Double scoreWord) {
        this.scoreWord = scoreWord;
    }

    public Double getScorePro() {
        return scorePro;
    }

    public void setScorePro(Double scorePro) {
        this.scorePro = scorePro;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", credit=" + credit +
                ", timeTheory=" + timeTheory +
                ", timeExperiment=" + timeExperiment +
                ", scoreCall=" + scoreCall +
                ", scoreJob=" + scoreJob +
                ", scoreExp=" + scoreExp +
                ", scoreExm=" + scoreExm +
                ", scoreWord=" + scoreWord +
                ", scorePro=" + scorePro +
                '}';
    }
}
