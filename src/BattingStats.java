package org.example.primeselectionsystem;

public class BattingStats extends Statistics{

    private int totalRuns;
    private double average;
    private double strikeRate;
    private int noOfFifties;
    private int noOfCenturies;

    public BattingStats(int noOfMatches , int totalRuns, double average, double strikeRate, int noOfFifties, int noOfCenturies) {
        super(noOfMatches);
        this.totalRuns = totalRuns;
        this.average = average;
        this.strikeRate = strikeRate;
        this.noOfFifties = noOfFifties;
        this.noOfCenturies = noOfCenturies;
    }

    public BattingStats(BattingStats s){

        this(s.getNoOfMatches(), s.totalRuns , s.average , s.strikeRate , s.noOfFifties , s.noOfCenturies);

    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public int getNoOfFifties() {
        return noOfFifties;
    }

    public void setNoOfFifties(int noOfFifties) {
        this.noOfFifties = noOfFifties;
    }

    public int getNoOfCenturies() {
        return noOfCenturies;
    }

    public void setNoOfCenturies(int noOfCenturies) {
        this.noOfCenturies = noOfCenturies;
    }

    public int getTotalRuns() {
        return totalRuns;
    }

    public void setTotalRuns(int totalRuns) {
        this.totalRuns = totalRuns;
    }

    public double getStrikeRate() {
        return strikeRate;
    }

    public void setStrikeRate(double strikeRate) {
        this.strikeRate = strikeRate;
    }
}
