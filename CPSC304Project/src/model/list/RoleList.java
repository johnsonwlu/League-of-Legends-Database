package model.list;

import model.Role;

import java.util.ArrayList;
import java.util.List;

public class RoleList {
    private List<Role> roles;

    public RoleList() {
        this.roles = new ArrayList<>();
        roles.add(new Role("roleName1", "Aatrox"));
        roles.add(new Role("roleName2", "Aatrox"));
        roles.add(new Role("roleName3", "Aatrox"));
        roles.add(new Role("roleName4", "Aatrox"));
        roles.add(new Role("roleName5", "Anivia"));

    }

    public List<Role> getRoles() {
        return roles;
    }

    public void printRoleData() {
        System.out.printf("%20s %20s\n", "ChampionName", "RoleName");
        for (int i = 0; i < roles.size(); i++) {
            System.out.printf("%20s %20s\n", roles.get(i).getChampionName(), roles.get(i).getRoleName());
        }
    }

    public void add(Role[] rolesArrayFromDatabase) {
        for (int i = 0; i < rolesArrayFromDatabase.length; i++) {
            this.roles.add(rolesArrayFromDatabase[i]);
        }
    }
}
