import React from 'react';
import { Outlet, Link } from 'react-router-dom';
import './Layout.css';
export default function Layout() {
  return (
    <div>
      <header>
        <h1>
          <Link to="/" style={{ textDecoration: 'none', color: 'inherit' }}>
            Car Finder
          </Link>
        </h1>
      </header>

      <main>
        <div className="container">
          <Outlet />
        </div>
      </main>

      <footer>Abe Myatt - Car Selection Website</footer>
    </div>
  );
}
