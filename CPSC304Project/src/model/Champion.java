package model;

public class Champion {
    private String championName;
    private String championType;
    private int championStats;

    public Champion(String championName, String championType, int championStats) {
        this.championName = championName;
        this.championType = championType;
        this.championStats = championStats;
    }

    public String getChampionName() {
        return championName;
    }

    public void setChampionName(String championName) {
        this.championName = championName;
    }

    public String getChampionType() {
        return championType;
    }

    public void setChampionType(String championType) {
        this.championType = championType;
    }

    public int getChampionStats() {
        return championStats;
    }

    public void setChampionStats(int championStats) {
        this.championStats = championStats;
    }

    @Override
    public String toString() {
        return "Champion{" +
                "championName='" + championName + '\'' +
                ", championType='" + championType + '\'' +
                ", championStats=" + championStats +
                '}';
    }
}
