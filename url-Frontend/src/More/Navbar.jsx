import React from 'react'
import { useNavigate } from 'react-router-dom';
import { Link } from 'react-router-dom';

function Navbar() {

  const navigate = useNavigate();

  function Logout() {
    localStorage.clear();
    navigate("/");
  }

  return (
    <header style={styles.header}>
      <nav style={styles.nav}>
        <Link to="/Home" style={styles.link}>Home</Link>
        <Link to="/About" style={styles.link}>About</Link>
        <Link to="/Profile" style={styles.link}>Profile</Link>
      </nav>

      <button style={styles.logoutBtn} onClick={Logout}>
        Logout
      </button>
    </header>
  )
}

const styles = {
  header: {
    backgroundColor: '#0f172a',
    borderBottom: '1px solid #334155',
    padding: '16px 24px',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'space-between',
    boxShadow: '0 2px 8px rgba(0, 0, 0, 0.3)',
  },
  nav: {
    display: 'flex',
    gap: '24px',
  },
  link: {
    color: '#cbd5e1',
    textDecoration: 'none',
    fontWeight: 500,
    fontSize: '0.95rem',
  },
  logoutBtn: {
    backgroundColor: '#ef4444',
    color: '#ffffff',
    border: 'none',
    padding: '8px 16px',
    borderRadius: '8px',
    fontSize: '0.9rem',
    fontWeight: 600,
    cursor: 'pointer',
  },
}

export default Navbar