import { useState } from 'react';

const Administration = () => {
  const [name, setName] = useState('');

  const handleSave = async () => {
    try {
      const res = await fetch('http://localhost:8080/api/teams', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name }),
      });

      if (!res.ok) {
        const text = await res.text();
        console.error('POST failed:', res.status, text);
        return;
      }

      setName('');
    } catch (err) {
      console.error('Network error:', err);
    }
  };

  return (
    <main className="administration">
      <section className="create_team">
        <input
          type="text"
          value={name}
          placeholder="Teamname"
          onChange={(event) => setName(event.target.value)}
        />
        <button onClick={handleSave}>Save team</button>
      </section>
    </main>
  );
};

export default Administration;
