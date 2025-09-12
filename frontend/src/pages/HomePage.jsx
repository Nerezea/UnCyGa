import { useEffect, useState } from 'react';

const HomePage = () => {
  const [teams, setTeams] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8080/api/teams')
      .then((res) => res.json())
      .then((data) => {
        data.sort((a, b) => b.points - a.points);
        setTeams(data);
      });
  }, []);

  return (
    <main className="homepage">
      <section className="start-section">
        <h2>Welcome to the Universe Cyber Games</h2>
      </section>
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

export default HomePage;
