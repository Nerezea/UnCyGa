import { Route, Routes } from 'react-router-dom';
import Layout from './Layout';
import HomePage from '../pages/HomePage';
import Leaderboard from '../pages/Leaderboard';
import NotFoundPage from '../pages/NotFoundPage';
import Administration from '../pages/Administration';

const App = () => {
  return (
    <Layout>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/leaderboard" element={<Leaderboard />} />
        <Route path="/administration" element={<Administration />} />
        <Route path="*" element={<NotFoundPage />} />
      </Routes>
    </Layout>
  );
};

export default App;
