package ucg.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ucg.entity.Teams;
import ucg.repository.TeamsRepository;

@Component
public class DatabaseMatching {

	private DatabaseCollector	collector;
	private TeamsRepository		repository;

	@Autowired
	public DatabaseMatching(DatabaseCollector collector, TeamsRepository repository) {
		this.collector = collector;
		this.repository = repository;
	}

	public void matchday(Integer mapsWonHome, Integer mapsWonAway) {
		Teams teamHome = collector.getTeamById(1);
		Teams teamAway = collector.getTeamById(2);

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
