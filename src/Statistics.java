package org.example.primeselectionsystem;

public abstract class Statistics {

    private int noOfMatches;

    public Statistics(int noOfMatches) {
        this.noOfMatches = noOfMatches;
    }

    public int getNoOfMatches() {
        return noOfMatches;
    }

    public void setNoOfMatches(int noOfMatches) {
        this.noOfMatches = noOfMatches;
    }
}
