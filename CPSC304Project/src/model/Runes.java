package model;

public class Runes {
    private String runesPrimaryPath;
    private String runesSecondaryPath;
    private String keystone;

    public Runes(String runesPrimaryPath, String runesSecondaryPath, String keystone) {
        this.runesPrimaryPath = runesPrimaryPath;
        this.runesSecondaryPath = runesSecondaryPath;
        this.keystone = keystone;
    }

    public String getRunesPrimaryPath() {
        return runesPrimaryPath;
    }

    public void setRunesPrimaryPath(String runesPrimaryPath) {
        this.runesPrimaryPath = runesPrimaryPath;
    }

    public String getRunesSecondaryPath() {
        return runesSecondaryPath;
    }

    public void setRunesSecondaryPath(String runesSecondaryPath) {
        this.runesSecondaryPath = runesSecondaryPath;
    }

    public String getKeystone() {
        return keystone;
    }

    public void setKeystone(String keystone) {
        this.keystone = keystone;
    }

    @Override
    public String toString() {
        return "Runes{" +
                "runesPrimaryPath='" + runesPrimaryPath + '\'' +
                ", runesSecondaryPath='" + runesSecondaryPath + '\'' +
                ", keystone='" + keystone + '\'' +
                '}';
    }
}
