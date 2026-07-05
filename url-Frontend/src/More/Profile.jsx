import React, { useEffect, useState } from 'react';
import Navbar from './Navbar';
import { MyAllUrls } from '../component/axios';

function Profile() {
  const [urls, setUrls] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  async function fetchUrls() {
    try {
      const r = await MyAllUrls();
      console.log(r.data); // Checking what the backend returns
      setUrls(r.data);
    } catch (err) {
      console.error(err);
      setError('Failed to fetch URLs');
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => {
    fetchUrls();
  }, []);

  function copyToClipboard(shortUrl) {
    navigator.clipboard.writeText(shortUrl);
    // alert('Copied to clipboard!');
  }

  return (
    <div style={styles.container}>
      <Navbar />

      <h1 style={styles.heading}>Your URLs</h1>

      {loading && <p style={styles.message}>Loading...</p>}

      {!loading && error && (
        <p style={styles.errorMessage}>{error}</p>
      )}

      {!loading && !error && urls.length === 0 && (
        <p style={styles.message}>You haven't created any URLs yet.</p>
      )}

      {!loading && !error && (
        <div style={styles.list}>
          {urls.map((url) => (
            <div key={url.id || url._id} style={styles.card}>
              <p style={styles.label}>
                Original:
                <span style={styles.originalUrl}> {url.url}</span>
              </p>

              <p style={styles.label}>
                Short:
                <a
                  href={`https://url-shortener-server-h5gn.onrender.com/api/show/${url.smallHashCode}`}
                  target="_blank"
                  rel="noopener noreferrer"
                  style={styles.shortUrl}
                >
                  {`https://url-shortener-server-h5gn.onrender.com/api/show/${url.smallHashCode}`}
                </a>
              </p>

              <div style={styles.footer}>
                <span style={styles.expiry}>
                  Expires: {new Date(url.expireAt).toLocaleString()}
                </span>

                <button
                  style={styles.copyBtn}
                  onClick={() => copyToClipboard(`https://url-shortener-server-h5gn.onrender.com/api/show/${url.smallHashCode}`)}
                >
                  Copy
                </button>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

const styles = {
  container: {
    minHeight: '100vh',
    background: 'linear-gradient(135deg, #1e1e2f, #2a2a40)',
    padding: '32px 20px',
    fontFamily: "'Segoe UI', sans-serif",
  },
  heading: {
    color: '#ffffff',
    fontSize: '1.8rem',
    marginBottom: '24px',
    textAlign: 'center',
  },
  message: {
    color: '#cbd5e1',
    textAlign: 'center',
    fontSize: '1rem',
  },
  errorMessage: {
    color: '#f87171',
    textAlign: 'center',
    fontSize: '1rem',
  },
  list: {
    display: 'flex',
    flexDirection: 'column',
    gap: '16px',
    maxWidth: '600px',
    margin: '0 auto',
  },
  card: {
    backgroundColor: '#2c2c3e',
    borderRadius: '12px',
    padding: '18px 20px',
    boxShadow: '0 4px 12px rgba(0, 0, 0, 0.3)',
  },
  label: {
    color: '#94a3b8',
    fontSize: '0.85rem',
    marginBottom: '6px',
  },
  originalUrl: {
    color: '#e2e8f0',
    fontSize: '0.95rem',
    wordBreak: 'break-all',
  },
  shortUrl: {
    color: '#7f9cf5',
    fontSize: '0.95rem',
    textDecoration: 'none',
    fontWeight: 600,
  },
  footer: {
    display: 'flex',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginTop: '10px',
  },
  expiry: {
    color: '#64748b',
    fontSize: '0.8rem',
  },
  copyBtn: {
    backgroundColor: '#334155',
    color: '#e2e8f0',
    border: 'none',
    padding: '6px 12px',
    borderRadius: '6px',
    fontSize: '0.8rem',
    cursor: 'pointer',
  },
};

export default Profile;