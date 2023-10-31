package model.list;

import model.Champion;

import java.util.ArrayList;
import java.util.List;

public class ChampionList {
    private List<Champion> champions;
    public ChampionList() {
        champions = new ArrayList<>();
        champions.add(new Champion("Aatrox", "Fighter", 1));
        champions.add(new Champion("Anivia", "Mage", 1));
        champions.add(new Champion("Bard", "Support", 1));
        champions.add(new Champion("Blitzcrank", "Tank", 10));
        champions.add(new Champion("Caitlyn", "Marksman", 5));
    }

    public List<Champion> getChampions() {
        return champions;
    }

    public void printChampionData() {
        System.out.printf("%s %20s %15s\n", "Champion Name", "Champion Type", "Champion Stats");
        for (int i = 0; i < champions.size(); i++) {
            System.out.printf("%10s %20s %15d\n", champions.get(i).getChampionName(), champions.get(i).getChampionType(), champions.get(i).getChampionStats());
        }
    }

    public void add(Champion[] championArrayFromDatabase) {
        for (int i = 0; i < championArrayFromDatabase.length; i++) {
            this.champions.add(championArrayFromDatabase[i]);
        }
    }

}
