package org.example.primeselectionsystem;

public class SpinBowler extends Bowler{
    public SpinBowler(int id , String name, BowlerStats bowlerStats) {
        super(id , name, bowlerStats);
        category = "SPINNER";
    }

    @Override
    public int compare(Player o1, Player o2) {
        return 0;
    }
}
