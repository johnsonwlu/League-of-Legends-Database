package model.list;

import model.TeamParticipation;
import model.match.Match;

import java.util.ArrayList;
import java.util.List;

public class TeamParticipationList {
    private List<TeamParticipation> teamParticipations;

    public TeamParticipationList(ChampionList champions, MatchList matches) {
        teamParticipations = new ArrayList<>();
        teamParticipations.add(new TeamParticipation(matches.getMatches().get(0).getMatchID(), champions.getChampions().get(0).getChampionName(), "Blue", 60, 30, 10, "Red"));
        teamParticipations.add(new TeamParticipation(matches.getMatches().get(0).getMatchID(), champions.getChampions().get(0).getChampionName(), "Blue", 400, 500, 9, "Red"));
        teamParticipations.add(new TeamParticipation(matches.getMatches().get(0).getMatchID(), champions.getChampions().get(0).getChampionName(), "Red", 1, 4, 1, "Red"));
        teamParticipations.add(new TeamParticipation(matches.getMatches().get(1).getMatchID(), champions.getChampions().get(1).getChampionName(), "Blue", 100, 200, 4, "Blue"));
        teamParticipations.add(new TeamParticipation(matches.getMatches().get(1).getMatchID(), champions.getChampions().get(0).getChampionName(), "Red", 220, 7, 20, "Blue"));
        teamParticipations.add(new TeamParticipation(matches.getMatches().get(2).getMatchID(), champions.getChampions().get(1).getChampionName(), "Blue", 50, 3, 7, "Yellow"));
        teamParticipations.add(new TeamParticipation(matches.getMatches().get(7).getMatchID(), champions.getChampions().get(0).getChampionName(), "White", 100, 10, 30, "White"));

    }

    public TeamParticipationList() {
        teamParticipations = new ArrayList<>();
    }

    public List<TeamParticipation> getTeamParticipations() {
        return teamParticipations;
    }

    public void printTeamParticipationData() {
        System.out.printf("   %s %20s %20s %20s  %20s  %20s %20s\n", "MatchID", "ChampionName", "TeamColor", "inTeamStats", "FirstTowerTakeDown", "FirstDragon", "ParticipationWinner");
        for (int i = 0; i < teamParticipations.size(); i++) {
            System.out.printf("%10s %20s %20s %20d %20ds %20ds %20s\n",
                    teamParticipations.get(i).getMatchID(), teamParticipations.get(i).getChampionName(),
                    teamParticipations.get(i).getTeamColor(), teamParticipations.get(i).getInTeamStats(),
                    teamParticipations.get(i).getFirstTowerTakeDown(), teamParticipations.get(i).getFirstDragonInSecond(),
                    teamParticipations.get(i).getParticipationWinner());
        }
    }

    public void add(TeamParticipation[] teamParticipationArrayFromDatabase) {
        for(int i = 0; i < teamParticipationArrayFromDatabase.length; i++) {
            this.teamParticipations.add(teamParticipationArrayFromDatabase[i]);
        }
    }
}
