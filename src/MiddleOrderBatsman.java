package org.example.primeselectionsystem;

public class MiddleOrderBatsman extends Batsman{
    public MiddleOrderBatsman(int id , String name, BattingStats battingStats) {
        super(id , name, battingStats);
        category = "MIDDLE ORDER BATSMAN";
    }

    @Override
    public int compare(Player o1, Player o2) {
        return 0;
    }
}
