package model.list;

import model.InTeamUse;
import model.SummonerSpell;
import model.TeamParticipation;

import java.util.ArrayList;
import java.util.List;

public class InTeamUseList {
    private List<InTeamUse> inTeamUseList;

    public InTeamUseList(TeamParticipationList teamParticipations, ItemList items, SummonerSpellList summonerSpells, RunesList runes) {
        inTeamUseList = new ArrayList<>();
        inTeamUseList.add(new InTeamUse(teamParticipations.getTeamParticipations().get(0).getMatchID(),
                teamParticipations.getTeamParticipations().get(0).getTeamColor(),
                teamParticipations.getTeamParticipations().get(0).getChampionName(),
                items.getItems().get(0).getItemName(),
                summonerSpells.getSummonerSpells().get(0).getSummonerSpellName(),
                runes.getRunes().get(0).getRunesPrimaryPath(),
                runes.getRunes().get(0).getRunesSecondaryPath()));
        inTeamUseList.add(new InTeamUse(teamParticipations.getTeamParticipations().get(1).getMatchID(),
                teamParticipations.getTeamParticipations().get(1).getTeamColor(),
                teamParticipations.getTeamParticipations().get(1).getChampionName(),
                items.getItems().get(1).getItemName(),
                summonerSpells.getSummonerSpells().get(1).getSummonerSpellName(),
                runes.getRunes().get(4).getRunesPrimaryPath(),
                runes.getRunes().get(4).getRunesSecondaryPath()));
        inTeamUseList.add(new InTeamUse(teamParticipations.getTeamParticipations().get(1).getMatchID(),
                teamParticipations.getTeamParticipations().get(1).getTeamColor(),
                teamParticipations.getTeamParticipations().get(1).getChampionName(),
                items.getItems().get(1).getItemName(),
                summonerSpells.getSummonerSpells().get(2).getSummonerSpellName(),
                runes.getRunes().get(1).getRunesPrimaryPath(),
                runes.getRunes().get(1).getRunesSecondaryPath()));
        inTeamUseList.add(new InTeamUse(teamParticipations.getTeamParticipations().get(2).getMatchID(),
                teamParticipations.getTeamParticipations().get(2).getTeamColor(),
                teamParticipations.getTeamParticipations().get(2).getChampionName(),
                items.getItems().get(2).getItemName(),
                summonerSpells.getSummonerSpells().get(3).getSummonerSpellName(),
                runes.getRunes().get(2).getRunesPrimaryPath(),
                runes.getRunes().get(2).getRunesSecondaryPath()));
        inTeamUseList.add(new InTeamUse(teamParticipations.getTeamParticipations().get(3).getMatchID(),
                teamParticipations.getTeamParticipations().get(3).getTeamColor(),
                teamParticipations.getTeamParticipations().get(3).getChampionName(),
                items.getItems().get(0).getItemName(),
                summonerSpells.getSummonerSpells().get(4).getSummonerSpellName(),
                runes.getRunes().get(4).getRunesPrimaryPath(),
                runes.getRunes().get(4).getRunesSecondaryPath()));
    }

    public List<InTeamUse> getInTeamUseList() {
        return inTeamUseList;
    }


//    public void printInTeamUseAccountPlayData() {
//        createAccountPlayData();
//        createInTeamUseData();
//        createInTeamUseAccountPlayData();
//        System.out.printf("%20s %20s %20s %20s %20s %20s %20s %20s\n", "AccountID", "MatchID", "TeamColor", "ChampionName", "ItemName", "SummonerSpellName", "RunesPrimaryPath", "RunesSecondaryPath");
//        System.out.printf("%20s %20s %20s %20s %20s %20s %20s %20s\n",
//                inTeamUseAccountPlay1.getAccountPlay().getAccount().getAccountID(),
//                inTeamUseAccountPlay1.getInTeamUse().getTeamParticipationsByID(0).getMatchesByID(0).getMatchID(),
//                inTeamUseAccountPlay1.getInTeamUse().getTeamParticipationsByID(0).getTeamColor(), inTeamUseAccountPlay1.getInTeamUse().getTeamParticipationsByID(0).getChampionsByID(0).getChampionName(),
//                inTeamUseAccountPlay1.getInTeamUse().getItemsByID(0).getItemName(),
//                inTeamUseAccountPlay1.getInTeamUse().getSummonerSpellByID(0).getSummonerSpellName(),
//                inTeamUseAccountPlay1.getInTeamUse().getRunesByID(0).getRunesPrimaryPath(),
//                inTeamUseAccountPlay1.getInTeamUse().getRunesByID(0).getRunesSecondaryPath());
//
//        System.out.printf("%20s %20s %20s %20s %20s %20s %20s %20s\n",
//                inTeamUseAccountPlay2.getAccountPlay().getAccount().getAccountID(),
//                inTeamUseAccountPlay2.getInTeamUse().getTeamParticipationsByID(0).getMatchesByID(0).getMatchID(),
//                inTeamUseAccountPlay2.getInTeamUse().getTeamParticipationsByID(0).getTeamColor(), inTeamUseAccountPlay1.getInTeamUse().getTeamParticipationsByID(0).getChampionsByID(0).getChampionName(),
//                inTeamUseAccountPlay2.getInTeamUse().getItemsByID(0).getItemName(),
//                inTeamUseAccountPlay2.getInTeamUse().getSummonerSpellByID(0).getSummonerSpellName(),
//                inTeamUseAccountPlay2.getInTeamUse().getRunesByID(0).getRunesPrimaryPath(),
//                inTeamUseAccountPlay2.getInTeamUse().getRunesByID(0).getRunesSecondaryPath());
//
//        System.out.printf("%20s %20s %20s %20s %20s %20s %20s %20s\n",
//                inTeamUseAccountPlay3.getAccountPlay().getAccount().getAccountID(),
//                inTeamUseAccountPlay3.getInTeamUse().getTeamParticipationsByID(0).getMatchesByID(0).getMatchID(),
//                inTeamUseAccountPlay3.getInTeamUse().getTeamParticipationsByID(0).getTeamColor(), inTeamUseAccountPlay1.getInTeamUse().getTeamParticipationsByID(0).getChampionsByID(0).getChampionName(),
//                inTeamUseAccountPlay3.getInTeamUse().getItemsByID(0).getItemName(),
//                inTeamUseAccountPlay3.getInTeamUse().getSummonerSpellByID(0).getSummonerSpellName(),
//                inTeamUseAccountPlay3.getInTeamUse().getRunesByID(0).getRunesPrimaryPath(),
//                inTeamUseAccountPlay3.getInTeamUse().getRunesByID(0).getRunesSecondaryPath());
//
//        System.out.printf("%20s %20s %20s %20s %20s %20s %20s %20s\n",
//                inTeamUseAccountPlay4.getAccountPlay().getAccount().getAccountID(),
//                inTeamUseAccountPlay4.getInTeamUse().getTeamParticipationsByID(0).getMatchesByID(0).getMatchID(),
//                inTeamUseAccountPlay4.getInTeamUse().getTeamParticipationsByID(0).getTeamColor(), inTeamUseAccountPlay1.getInTeamUse().getTeamParticipationsByID(0).getChampionsByID(0).getChampionName(),
//                inTeamUseAccountPlay4.getInTeamUse().getItemsByID(0).getItemName(),
//                inTeamUseAccountPlay4.getInTeamUse().getSummonerSpellByID(0).getSummonerSpellName(),
//                inTeamUseAccountPlay4.getInTeamUse().getRunesByID(0).getRunesPrimaryPath(),
//                inTeamUseAccountPlay4.getInTeamUse().getRunesByID(0).getRunesSecondaryPath());
//
//        System.out.printf("%20s %20s %20s %20s %20s %20s %20s %20s\n",
//                inTeamUseAccountPlay5.getAccountPlay().getAccount().getAccountID(),
//                inTeamUseAccountPlay5.getInTeamUse().getTeamParticipationsByID(0).getMatchesByID(0).getMatchID(),
//                inTeamUseAccountPlay5.getInTeamUse().getTeamParticipationsByID(0).getTeamColor(), inTeamUseAccountPlay1.getInTeamUse().getTeamParticipationsByID(0).getChampionsByID(0).getChampionName(),
//                inTeamUseAccountPlay5.getInTeamUse().getItemsByID(0).getItemName(),
//                inTeamUseAccountPlay5.getInTeamUse().getSummonerSpellByID(0).getSummonerSpellName(),
//                inTeamUseAccountPlay5.getInTeamUse().getRunesByID(0).getRunesPrimaryPath(),
//                inTeamUseAccountPlay5.getInTeamUse().getRunesByID(0).getRunesSecondaryPath());
//    }
//
}
