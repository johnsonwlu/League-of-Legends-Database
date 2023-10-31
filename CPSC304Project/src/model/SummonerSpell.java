package model;

public class SummonerSpell {
    private String summonerSpellName;
    private String summonerSpellNameDescription;

    public SummonerSpell(String summonerSpellName, String summonerSpellNameDescription) {
        this.summonerSpellName = summonerSpellName;
        this.summonerSpellNameDescription = summonerSpellNameDescription;
    }

    public String getSummonerSpellName() {
        return summonerSpellName;
    }

    public void setSummonerSpellName(String summonerSpellName) {
        this.summonerSpellName = summonerSpellName;
    }

    public String getSummonerSpellNameDescription() {
        return summonerSpellNameDescription;
    }

    public void setSummonerSpellNameDescription(String summonerSpellNameDescription) {
        this.summonerSpellNameDescription = summonerSpellNameDescription;
    }

    @Override
    public String toString() {
        return "SummonerSpell{" +
                "summonerSpellName='" + summonerSpellName + '\'' +
                ", summonerSpellNameDescription='" + summonerSpellNameDescription + '\'' +
                '}';
    }
}
