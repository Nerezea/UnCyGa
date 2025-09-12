import { useEffect, useState } from 'react';

const Leaderboard = () => {
  const [teams, setTeams] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8080/api/teams')
      .then((res) => res.json())
      .then((data) => {
        data.sort((team1, team2) => team2.points - team1.points);
        setTeams(data);
      });
  }, []);

  return (
    <main className="leaderboard">
      <section className="teams-section">
        <ol>
          {teams.map((team) => (
            <li key={team.id}>
              {team.name} — Points: {team.points}, Wins: {team.gameswon}, Losses: {team.gameslost}
            </li>
          ))}
        </ol>
      </section>
    </main>
  );
};

export default Leaderboard;
