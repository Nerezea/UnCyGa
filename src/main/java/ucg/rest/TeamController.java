package ucg.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ucg.dto.TeamsDTO;
import ucg.entity.Teams;
import ucg.mapper.TeamsMapper;
import ucg.repository.TeamsRepository;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {
    private final TeamsRepository repository;

    public TeamController(TeamsRepository repo) {
        this.repository = repo;
    }

    @GetMapping
    public List<TeamsDTO> allTeams() {
        return repository.findAll().stream().map(TeamsMapper::toDto).toList();
    }
}
