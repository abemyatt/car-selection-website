import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Layout from './components/Layout/Layout';
import Home from './pages/Home';
import Refine from './pages/Refine';
import Results from './pages/Results';

export default function App() {
  return (
    <Routes>
      <Route path="/" element={<Layout />}>
        <Route index element={<Home />} />
        <Route path="refine" element={<Refine />} />
        <Route path="results" element={<Results />} />
      </Route>
    </Routes>
  );
}
