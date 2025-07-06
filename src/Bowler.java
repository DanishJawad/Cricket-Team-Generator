package org.example.primeselectionsystem;

public abstract class Bowler extends Player{

    BowlerStats bowlerStats;
    public Bowler(int id , String name, BowlerStats bowlerStats) {
        super(id , name);
        this.bowlerStats = new BowlerStats(bowlerStats);
        setPoints(Ranker.rankBowler(bowlerStats));
    }

    public BowlerStats getBowlerStats() {
        return bowlerStats;
    }

    public void setBowlerStats(BowlerStats bowlerStats) {
        this.bowlerStats = bowlerStats;
    }

    @Override
    public boolean equals(Object o){

        Bowler b = (Bowler) o;

        return this.id == b.id;
    }
}
