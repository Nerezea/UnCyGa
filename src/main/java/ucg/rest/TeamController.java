package ucg.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ucg.dto.TeamsDTO;
import ucg.entity.Teams;
import ucg.mapper.TeamsMapper;
import ucg.repository.TeamsRepository;

import java.net.URI;
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

    @PostMapping
    public ResponseEntity<TeamsDTO> createTeam(@RequestBody CreateTeamRequest body) {
        Teams toSave = new Teams(body.getName());
        Teams saved = repository.save(toSave);

        TeamsDTO dto = TeamsMapper.toDto(saved);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(location).body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Integer id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
