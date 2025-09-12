package ucg.mapper;

import ucg.dto.TeamsDTO;
import ucg.entity.Teams;

public final class TeamsMapper {
    private TeamsMapper() {
    }

    public static TeamsDTO toDto(Teams team) {
        TeamsDTO dto = new TeamsDTO();
        dto.dto_id = team.getId();
        dto.name = team.getName();
        dto.points = team.getPoints();
        dto.gameswon = team.getGameswon();
        dto.gameslost = team.getGameslost();
        dto.mapwins = team.getMapwins();
        dto.maplosses = team.getMaplosses();
        return dto;
    }
}
