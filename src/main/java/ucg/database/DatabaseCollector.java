package ucg.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ucg.entity.Teams;
import ucg.repository.TeamsRepository;

@Component
public class DatabaseCollector {

	private final TeamsRepository teamRepository;

	@Autowired
	public DatabaseCollector(TeamsRepository teamRepository) {
		this.teamRepository = teamRepository;
	}

	public List<Teams> collectData() {
		return teamRepository.findAll();
	}

	public Teams getTeamByName(String name) {
		return teamRepository.findByNameLike(name);
	}

	public Teams getTeamById(Integer id) {
		return teamRepository.findById(id);
	}

	public void saveTeam(String name) {
		teamRepository.save(new Teams(name));
	}

	public void deleteTeam(String name) {
		teamRepository.delete(teamRepository.findByNameLike(name));
	}
}
