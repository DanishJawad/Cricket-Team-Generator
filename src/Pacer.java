package org.example.primeselectionsystem;

public class Pacer extends Bowler{
    public Pacer(int id , String name, BowlerStats bowlerStats) {
        super(id , name, bowlerStats);
        category = "PACER";
    }

    @Override
    public int compare(Player o1, Player o2) {
        return 0;
    }
}
