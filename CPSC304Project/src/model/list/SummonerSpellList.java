package model.list;

import model.SummonerSpell;

import java.util.ArrayList;
import java.util.List;

public class SummonerSpellList {
    private List<SummonerSpell> summonerSpells;

    public SummonerSpellList() {
        this.summonerSpells = new ArrayList<>();
        summonerSpells.add(new SummonerSpell("Heal", "SummonerSpellNameDescription1"));
        summonerSpells.add(new SummonerSpell("Barrier", "SummonerSpellNameDescription2"));
        summonerSpells.add(new SummonerSpell("Teleport", "SummonerSpellNameDescription3"));
        summonerSpells.add(new SummonerSpell("Dash", "SummonerSpellNameDescription4"));
        summonerSpells.add(new SummonerSpell("Exhaust", "SummonerSpellNameDescription5"));

    }

    public List<SummonerSpell> getSummonerSpells() {
        return summonerSpells;
    }

    public void printSummonerSpellData() {
        System.out.printf("%20s %30s\n", "SummonerSpellName", "SummonerSpellDescription");
        for (int i = 0; i < summonerSpells.size(); i++) {
            System.out.printf("%20s %30s\n", summonerSpells.get(i).getSummonerSpellName(), summonerSpells.get(i).getSummonerSpellNameDescription());
        }
    }

    public void add(SummonerSpell[] summonerSpellArrayFromDatabase) {
        for (int i = 0; i < summonerSpellArrayFromDatabase.length; i++) {
            this.summonerSpells.add(summonerSpellArrayFromDatabase[i]);
        }
    }
}
