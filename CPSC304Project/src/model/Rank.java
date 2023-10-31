package model;

public class Rank {
    private String rankNumber;
    private String rankTier;

    public Rank(String rankNumber, String rankTier) {
        this.rankNumber = rankNumber;
        this.rankTier = rankTier;
    }

    public String getRankNumber() {
        return rankNumber;
    }

    public void setRankNumber(String rankNumber) {
        this.rankNumber = rankNumber;
    }

    public String getRankTier() {
        return rankTier;
    }

    public void setRankTier(String rankTier) {
        this.rankTier = rankTier;
    }

    @Override
    public String toString() {
        return "Rank{" +
                "rankNumber='" + rankNumber + '\'' +
                ", rankTier='" + rankTier + '\'' +
                '}';
    }
}
