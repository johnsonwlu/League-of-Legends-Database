package model.list;

import model.Runes;

import java.util.ArrayList;
import java.util.List;

public class RunesList {
    private List<Runes> runes;

    public RunesList() {
        runes = new ArrayList<>();
        runes.add(new Runes("Precision", "Domination", "Keystone1"));
        runes.add(new Runes("Resolve", "Inspiration", "Keystone2"));
        runes.add(new Runes("Sorcery", "Domination", "Keystone3"));
        runes.add(new Runes("Resolve", "Sorcery", "Keystone4"));
        runes.add(new Runes("Inspiration", "Precision", "Keystone5"));
    }

    public List<Runes> getRunes() {
        return runes;
    }



    public void printRunesData() {
        System.out.printf("%20s %20s %30s\n", "RunesPrimaryPath", "RunesSecondaryPath", "Keystone");
        for (int i = 0; i < runes.size(); i++) {
            System.out.printf("%20s %20s %30s\n", runes.get(i).getRunesPrimaryPath(), runes.get(i).getRunesSecondaryPath(), runes.get(i).getKeystone());
        }
    }

    public void add(Runes[] runesArrayFromDatabase) {
        for (int i = 0; i < runesArrayFromDatabase.length; i++) {
            this.runes.add(runesArrayFromDatabase[i]);
        }
    }
}
