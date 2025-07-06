package org.example.primeselectionsystem;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class TeamProfile {

    private String teamName;
    private String password;

    ArrayList<TopOrderBatsman> topOrderPlayers;
    ArrayList<MiddleOrderBatsman> middleOrderPlayers;
    ArrayList<SpinBowler> spinners;
    ArrayList<Pacer> pacers;

    public TeamProfile(String teamName, String password) {
        this.teamName = teamName;
        this.password = password;
        topOrderPlayers = new ArrayList<>();
        middleOrderPlayers = new ArrayList<>();
        spinners = new ArrayList<>();
        pacers = new ArrayList<>();
    }

    public String getTeamName() {
        return teamName;
    }

    public static void addAccount(String teamName, String password) {

        try {
            File file = new File("AccountInformation.txt");
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(teamName + ",");
            bufferedWriter.write(password);
            bufferedWriter.newLine();
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Couldn't add account");
        }

    }

    public static Boolean loginAccount(String teamName, String password) {

        TeamProfile account = new TeamProfile(teamName, password);

        try {
            File file = new File("AccountInformation.txt");
            String accountInfo = readFromFile(file);
            String[] accounts = accountInfo.split("\n");

            for (int i = 0; i < accounts.length; i++) {
                teamName = accounts[i].split(",")[0];
                password = accounts[i].split(",")[1];
                TeamProfile temp = new TeamProfile(teamName, password);
                if (temp.equals(account)) {
                    return true;
                }
            }

        } catch (FileNotFoundException e) {

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;

    }

    public static String readFromFile(File f) throws Exception {

        String text = "";
        String temp = "";
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        temp = br.readLine();

        while (temp != null) {
            text += temp + "\n";
            temp = br.readLine();
        }

        return text;

    }

    public static void addBatsman(TeamProfile t, Batsman batsman) throws IOException {

        String path = "";
        if (batsman instanceof MiddleOrderBatsman)
            path = t.teamName + ".MiddleOrder.txt";
        else if (batsman instanceof TopOrderBatsman)
            path = t.teamName + ".TopOrder.txt";

        if (!path.equals("")) {
            File file = new File(path);
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(batsman.getId() + ",");
            bufferedWriter.write(batsman.getName() + ",");
            bufferedWriter.write(batsman.getBattingStats().getNoOfMatches() + ",");
            bufferedWriter.write(batsman.getBattingStats().getTotalRuns() + ",");
            bufferedWriter.write(batsman.getBattingStats().getAverage() + ",");
            bufferedWriter.write(batsman.getBattingStats().getStrikeRate() + ",");
            bufferedWriter.write(batsman.getBattingStats().getNoOfFifties() + ",");
            bufferedWriter.write(batsman.getBattingStats().getNoOfCenturies() + ",");
            bufferedWriter.write(String.valueOf(batsman.getPoints()));
            bufferedWriter.newLine();
            bufferedWriter.close();
            fileWriter.close();
        }


    }

    public static void addBowler(TeamProfile t, Bowler bowler) throws IOException {

        String path = "";
        if (bowler instanceof Pacer)
            path = t.teamName + ".Pacers.txt";
        else if (bowler instanceof SpinBowler)
            path = t.teamName + ".Spinners.txt";

        if (!(t.pacers.contains(bowler) | t.spinners.contains(bowler))) {
            if (!path.equals("")) {
                File file = new File(path);
                FileWriter fileWriter = new FileWriter(file, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(bowler.getId() + ",");
                bufferedWriter.write(bowler.getName() + ",");
                bufferedWriter.write(bowler.getBowlerStats().getNoOfMatches() + ",");
                bufferedWriter.write(bowler.getBowlerStats().economy + ",");
                bufferedWriter.write(bowler.getBowlerStats().noOfWickets + ",");
                bufferedWriter.write(bowler.getBowlerStats().totalOvers + ",");
                bufferedWriter.write(String.valueOf(bowler.getPoints()));
                bufferedWriter.newLine();
                bufferedWriter.close();
                fileWriter.close();
            }
        }



    }

    public static ArrayList<Batsman> getAllBatsmen(TeamProfile t, String category) throws FileNotFoundException {

        String path = "";
        ArrayList<Batsman> batsmen = new ArrayList<>();
        path = t.teamName + category;

        try {
            if (!path.equals("")) {
                File file = new File(path);
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String allInfo = readFromFile(file);
                String[] allBatsmen = allInfo.split("\n");

                for (int i = 0; i < allBatsmen.length; i++) {

                    int id = Integer.parseInt(allBatsmen[i].split(",")[0]);
                    String name = allBatsmen[i].split(",")[1];
                    int matches = Integer.parseInt(allBatsmen[i].split(",")[2]);
                    int runs = Integer.parseInt(allBatsmen[i].split(",")[3]);
                    double average = Double.parseDouble(allBatsmen[i].split(",")[4]);
                    double strikeRate = Double.parseDouble(allBatsmen[i].split(",")[5]);
                    int fifties = Integer.parseInt(allBatsmen[i].split(",")[6]);
                    int centuries = Integer.parseInt(allBatsmen[i].split(",")[7]);

                    if (category.equals(".TopOrder.txt")) {
                        batsmen.add(new TopOrderBatsman(id, name, new BattingStats(matches, runs, average, strikeRate, fifties, centuries)));
                    } else if (category.equals(".MiddleOrder.txt")) {
                        batsmen.add(new MiddleOrderBatsman(id, name, new BattingStats(matches, runs, average, strikeRate, fifties, centuries)));
                    }

                }
            }
        } catch (Exception e) {

        }

        return batsmen;
    }

    public static ArrayList<Bowler> getAllBowlers(TeamProfile t, String category) throws FileNotFoundException {

        String path = "";
        ArrayList<Bowler> bowlers = new ArrayList<>();
        path = t.teamName + category;

        try {
                File file = new File(path);
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String allInfo = readFromFile(file);
                String[] allBowlers = allInfo.split("\n");

                for (int i = 0; i < allBowlers.length; i++) {

                    int id = Integer.parseInt(allBowlers[i].split(",")[0]);
                    String name = allBowlers[i].split(",")[1];
                    int matches = Integer.parseInt(allBowlers[i].split(",")[2]);
                    double economy = Double.parseDouble(allBowlers[i].split(",")[3]);
                    int wickets = Integer.parseInt(allBowlers[i].split(",")[4]);
                    double overs = Double.parseDouble(allBowlers[i].split(",")[5]);

                    if (category.equals(".Pacers.txt")) {
                        bowlers.add(new Pacer(id, name, new BowlerStats(matches, economy, wickets, overs)));
                    } else if (category.equals(".Spinners.txt")) {
                        bowlers.add(new SpinBowler(id, name, new BowlerStats(matches, economy, wickets, overs)));
                    }

                }

        } catch (Exception e) {

        }
        return bowlers;
    }

    public static ArrayList<Player> getPrimeXI(TeamProfile t , int noOfTopOrder , int noOfMiddleOrder ,
                                               int noOfPacers , int noOfSpinners ) {

        ArrayList<Player> primePlayers = new ArrayList<>();

        try {
            ArrayList<Batsman> topOrder = getAllBatsmen(t, ".TopOrder.txt");
            ArrayList<Batsman> middleOrder = getAllBatsmen(t, ".MiddleOrder.txt");
            ArrayList<Bowler> pacers = getAllBowlers(t, ".Pacers.txt");
            ArrayList<Bowler> spinners = getAllBowlers(t, ".Spinners.txt");

            for(int i = 0 ; i < noOfTopOrder ; i++) {
                primePlayers.add(topOrder.get(i));
            }

            for(int i = 0 ; i < noOfMiddleOrder ; i++) {
                primePlayers.add(middleOrder.get(i));
            }

            for(int i = 0 ; i < noOfSpinners ; i++) {
                primePlayers.add(spinners.get(i));
            }

            for(int i = 0 ; i < noOfPacers ; i++) {
                primePlayers.add(pacers.get(i));
            }


            for (int i = 0 ; i < primePlayers.size() ; i++){
                primePlayers.get(i).setRank(i+1);
            }

        }
        catch (Exception e){

        }

        return primePlayers;
    }

    public static void rankPlayers(TeamProfile t , String path){

        File file = new File(path);

        try{
        if(path.equals(t.teamName + ".TopOrder.txt")){

            ArrayList<Batsman> topOrder = getAllBatsmen(t, ".TopOrder.txt");
            Collections.sort(topOrder);
            clearFile(file);
            for(Batsman tob : topOrder){
                addBatsman(t , tob);
            }
        }
        else if(path.equals(t.teamName + ".MiddleOrder.txt")){
            ArrayList<Batsman> middleOrder = getAllBatsmen(t, ".MiddleOrder.txt");
            Collections.sort(middleOrder);
            clearFile(file);
            for(Batsman mob : middleOrder){
                addBatsman(t , mob);
            }
        }
        else if(path.equals(t.teamName + ".Spinners.txt")){
            ArrayList<Bowler> spinners = getAllBowlers(t, ".Spinners.txt");
            Collections.sort(spinners);
            clearFile(file);
            for(Bowler sp : spinners){
                addBowler(t , sp);
            }
        }
        else if(path.equals(t.teamName + ".Pacers.txt")){
            ArrayList<Bowler> pacers = getAllBowlers(t, ".Pacers.txt");
            Collections.sort(pacers);
            clearFile(file);
            for(Bowler pacer : pacers){
                addBowler(t , pacer);
            }
        }
        }
        catch (Exception e){}
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamProfile that = (TeamProfile) o;
        return Objects.equals(teamName, that.teamName) && Objects.equals(password, that.password);
    }

    public ArrayList<TopOrderBatsman> getTopOrderPlayers() {
        return topOrderPlayers;
    }

    public void setTopOrderPlayers(ArrayList<TopOrderBatsman> topOrderPlayers) {
        this.topOrderPlayers = topOrderPlayers;
    }

    public ArrayList<MiddleOrderBatsman> getMiddleOrderPlayers() {
        return middleOrderPlayers;
    }

    public void setMiddleOrderPlayers(ArrayList<MiddleOrderBatsman> middleOrderPlayers) {
        this.middleOrderPlayers = middleOrderPlayers;
    }

    public ArrayList<SpinBowler> getSpinners() {
        return spinners;
    }

    public void setSpinners(ArrayList<SpinBowler> spinners) {
        this.spinners = spinners;
    }

    public ArrayList<Pacer> getPacers() {
        return pacers;
    }

    public void setPacers(ArrayList<Pacer> pacers) {
        this.pacers = pacers;
    }

    public static void clearFile(File file){

        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}




