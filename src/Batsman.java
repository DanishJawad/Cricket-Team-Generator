package org.example.primeselectionsystem;

public abstract class Batsman extends Player{

    BattingStats battingStats;
    public Batsman(int id ,String name, BattingStats battingStats) {
        super(id , name);
        this.battingStats = battingStats;
        setPoints(Ranker.rankBatsmen(battingStats));
    }

    public BattingStats getBattingStats() {
        return battingStats;
    }

    public void setBattingStats(BattingStats battingStats) {
        this.battingStats = battingStats;
    }

    @Override
    public boolean equals(Object o){

        Batsman b = (Batsman) o;

        return this.id == b.id;
    }
}
