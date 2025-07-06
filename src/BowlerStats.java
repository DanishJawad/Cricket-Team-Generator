package org.example.primeselectionsystem;

public class BowlerStats extends Statistics{

    double economy;
    int noOfWickets;
    double totalOvers;

    public BowlerStats(int noOfMatches , double economy , int noOfWickets , double totalOvers) {
        super(noOfMatches);
        this.economy = economy;
        this.noOfWickets = noOfWickets;
        this.totalOvers = totalOvers;
    }

    public BowlerStats(BowlerStats b){
        this(b.getNoOfMatches(), b.economy , b.noOfWickets , b.totalOvers);
    }
}
