package org.example.primeselectionsystem;

import java.util.Comparator;
import java.util.Objects;

public abstract class Player implements Comparable<Player> , Comparator<Player> {

    final protected int id;
    private String name;
    private int rank;
    private double points;
    protected String category;

    public Player(int id , String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public String getCategory() {
        return category;
    }


    @Override
    public int compareTo(Player p) {

        if(p.points == this.points)
            return 0;
        else if(p.points < this.points)
            return -1;
        else
            return 1;

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id == player.id && rank == player.rank && Double.compare(points, player.points) == 0 && Objects.equals(name, player.name);
    }

}
