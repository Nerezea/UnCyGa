package ucg.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ucg.entity.Teams;
import ucg.repository.TeamsRepository;

import java.util.List;
import java.util.Objects;

@Component
public class DatabaseMatching {

    private final DatabaseCollector collector;
    private final TeamsRepository repository;

    @Autowired
    public DatabaseMatching(DatabaseCollector collector, TeamsRepository repository) {
        this.collector = collector;
        this.repository = repository;
    }

    public List<Teams> matchingTeams() {
        List<Teams> all_teams = collector.collectData();
        int teamNumberSize = all_teams.size();
        int homeTeamNumber = 1 + (int) (Math.random() * teamNumberSize);
        int awayTeamNumber = 1 + (int) (Math.random() * teamNumberSize);

        while (awayTeamNumber == homeTeamNumber) {
            awayTeamNumber = 1 + (int) (Math.random() * teamNumberSize);
        }

        Teams teamHome = collector.getTeamById(homeTeamNumber);
        Teams teamAway = collector.getTeamById(awayTeamNumber);

        return List.of(teamHome, teamAway);
    }

    public void matchday(String homeTeam, Integer mapsWonHome, String awayTeam, Integer mapsWonAway) {
        Teams teamHome = collector.getTeamByName(homeTeam);
        Teams teamAway = collector.getTeamByName(awayTeam);

        if (mapsWonHome > mapsWonAway) {
            teamHome.setGameswon(teamHome.getGameswon() + 1);
            teamAway.setGameslost(teamAway.getGameslost() + 1);
        } else {
            teamAway.setGameswon(teamAway.getGameswon() + 1);
            teamHome.setGameslost(teamHome.getGameslost() + 1);
        }

        teamHome.setMapwins(teamHome.getMapwins() + mapsWonHome);
        teamHome.setMaplosses(teamHome.getMaplosses() + mapsWonAway);

        teamAway.setMapwins(teamAway.getMapwins() + mapsWonAway);
        teamAway.setMaplosses(teamAway.getMaplosses() + mapsWonHome);

        teamHome.setPoints(teamHome.getPoints() + (mapsWonHome - mapsWonAway));
        teamAway.setPoints(teamAway.getPoints() + (mapsWonAway - mapsWonHome));

        repository.save(teamHome);
        repository.save(teamAway);
    }
}
