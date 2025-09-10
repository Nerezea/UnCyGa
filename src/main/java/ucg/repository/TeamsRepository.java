package ucg.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import ucg.entity.Teams;

public interface TeamsRepository
		extends
		Repository<Teams, Integer> {

	Teams findByNameLike(String name);

	Teams findById(Integer id);

	List<Teams> findAll();

	void delete(Teams name);

	Teams save(Teams name);
}
