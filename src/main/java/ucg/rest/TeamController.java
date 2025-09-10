package ucg.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ucg.entity.Teams;
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
    public List<Teams> all() {
        return repository.findAll();
    }
}
