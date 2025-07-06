package org.example.primeselectionsystem;

import java.util.ArrayList;

public class Ranker {

        static double weightageOfRuns = 0.3;
        static double weightageOfAverage = 0.2;
        static double weightageOfStrikeRate = 0.15;
        static double weightageOfFifties = 0.1;
        static double weightageOfCenturies = 0.1;

        static double weightageOfTotalWickets = 0.4;
        static double weightageOfEconomy = 0.5;

        public static double rankBatsmen(BattingStats b){

            double points = 0;

            double runPoints = b.getTotalRuns() * weightageOfRuns;
            double averagePoints = b.getAverage() * weightageOfAverage;
            double strikeRatePoints = b.getStrikeRate() * weightageOfStrikeRate;
            double fiftiesPoints = b.getNoOfFifties() * weightageOfFifties;
            double centuriesPoints = b.getNoOfCenturies() * weightageOfCenturies;


            points = runPoints + averagePoints + strikeRatePoints + fiftiesPoints + centuriesPoints;

            return Math.round(points * 100.0) / 100.0;
        }

        public static double rankBowler(BowlerStats b){

            double points = 0;

            double wicketsPerMatch = b.noOfWickets / b.getNoOfMatches();
            double economyPoints = b.economy * weightageOfEconomy;
            double wicketPoints = wicketsPerMatch * 100;


            points = wicketPoints + (200 - economyPoints);

            return Math.round(points * 100.0) / 100.0;
        }

}
