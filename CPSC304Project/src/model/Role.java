package model;

public class Role {
    private String roleName;
    private String championName;

    public Role(String roleName, String championName) {
        this.roleName = roleName;
        this.championName = championName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getChampionName() {
        return championName;
    }

    public void setChampionName(String championName) {
        this.championName = championName;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleName='" + roleName + '\'' +
                ", championName='" + championName + '\'' +
                '}';
    }
}
