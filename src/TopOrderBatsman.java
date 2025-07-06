package org.example.primeselectionsystem;

public class TopOrderBatsman extends Batsman{
    public TopOrderBatsman(int id , String name, BattingStats battingStats) {
        super(id , name, battingStats);
        category = "TOP ORDER BATSMAN";
    }

    public TopOrderBatsman(TopOrderBatsman t) {
        this(t.id , t.getName(), t.battingStats);
    }

//    @Override
//    public int getId() {
//        return super.getId();
//    }
//
//    @Override
//    public String getCategory() {
//        return super.getCategory();
//    }
//
//    @Override
//    public String getName() {
//        return super.getName();
//    }

    @Override
    public int compare(Player o1, Player o2) {
        return 0;
    }
}
