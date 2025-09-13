import { useState } from 'react';

const Administration = () => {
  const [createName, setCreateName] = useState('');
  const [deleteName, setDeleteName] = useState('');

  const handleSave = async () => {
    try {
      const res = await fetch('http://localhost:8080/api/teams', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name: createName }),
      });
      if (!res.ok) {
        console.error('POST failed:', res.status);
        return;
      }
      setCreateName('');
      console.log('Team gespeichert:', createName);
    } catch (err) {
      console.error('Network error (POST):', err);
    }
  };

  const handleDelete = async () => {
    try {
      const allRes = await fetch('http://localhost:8080/api/teams');
      if (!allRes.ok) {
        console.error('GET /api/teams failed:', allRes.status);
        return;
      }
      const teams = await allRes.json();

      const match = teams.find(
        (team) => team.name?.toLowerCase() === deleteName.trim().toLowerCase()
      );
      if (!match) {
        console.error('Could not find a team with this name');
        return;
      }

      const delRes = await fetch(`http://localhost:8080/api/teams/${match.dto_id}`, {
        method: 'DELETE',
      });
      if (!delRes.ok) {
        console.error('DELETE failed:', delRes.status);
        return;
      }

      setDeleteName('');
      console.log('Team gelöscht:', match.name);
    } catch (err) {
      console.error('Network error (DELETE):', err);
    }
  };

  return (
    <main className="administration">
      <section className="create_team">
        <input
          type="text"
          value={createName}
          placeholder="Teamname anlegen"
          onChange={(e) => setCreateName(e.target.value)}
        />
        <button onClick={handleSave}>Save team</button>
      </section>

      <section className="delete_team">
        <input
          type="text"
          value={deleteName}
          placeholder="Teamname löschen"
          onChange={(e) => setDeleteName(e.target.value)}
        />
        <button onClick={handleDelete}>Delete team</button>
      </section>
    </main>
  );
};

export default Administration;
