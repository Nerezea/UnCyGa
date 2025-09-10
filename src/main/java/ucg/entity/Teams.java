package ucg.entity;

import javax.persistence.*;

@Entity
@Table(name = "teams")
public class Teams {

	@Id
	@GeneratedValue
	private Integer id;
	@Column(name = "TeamName")
	private String	name;
	@Column(name = "Points")
	private Integer	points;
	@Column(name = "Wins")
	private Integer	gameswon;
	@Column(name = "Losses")
	private Integer	gameslost;
	@Column(name = "MapWins")
	private Integer	mapwins;
	@Column(name = "MapLosses")
	private Integer	maplosses;

	public Teams() {
		// ...
	}

	public Teams(String name) {
		this.name = name;
		this.points = 0;
		this.gameswon = 0;
		this.gameslost = 0;
		this.mapwins = 0;
		this.maplosses = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Integer getGameswon() {
		return gameswon;
	}

	public void setGameswon(Integer gameswon) {
		this.gameswon = gameswon;
	}

	public Integer getGameslost() {
		return gameslost;
	}

	public void setGameslost(Integer gameslost) {
		this.gameslost = gameslost;
	}

	public Integer getMapwins() {
		return mapwins;
	}

	public void setMapwins(Integer mapwins) {
		this.mapwins = mapwins;
	}

	public Integer getMaplosses() {
		return maplosses;
	}

	public void setMaplosses(Integer maplosses) {
		this.maplosses = maplosses;
	}
}
