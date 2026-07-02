import React from 'react'
import Navbar from './Navbar'


function About() {
  return (
    <>
    <Navbar></Navbar>
    <div style={styles.container}>
      <div style={styles.card}>
        <h1 style={styles.heading}>👋 Hello, everyone (or anyone)!</h1>

        <p style={styles.text}>
          I'm <span style={styles.highlight}>Ace</span> — the creator of this.
        </p>

        <p style={styles.text}>
          Don't worry about your credentials — since you're already logged into
          the website, they're already cooked :3
        </p>

        <p style={styles.text}>
          But jokes aside — if you're a business, or just someone who needs a
          reliable URL shortener, <strong style={styles.bold}>please don't use this website.</strong>{' '}
          Your shortened URL will only stay valid for about 5 hours, after
          which the database gets refreshed and all stored URLs are deleted.
        </p>

        <p style={styles.text}>
          This was a <span style={styles.highlight}>side project</span>, not a
          service. (I don't have the money to store URLs forever — I need to
          save resources for other projects too, TvT.)
        </p>

        <p style={styles.text}>Anyway, run along now.</p>

        <p style={styles.footerNote}>
          I don't delete your account credentials — but I do delete your
          URLs. Yeah, I'm the bad guy 😘.
        </p>
      </div>
    </div>
    </>
  )
}

const styles = {
  container: {
    minHeight: '100vh',
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    background: 'linear-gradient(135deg, #1e1e2f, #2a2a40)',
    padding: '20px',
    fontFamily: "'Segoe UI', sans-serif",
  },
  card: {
    maxWidth: '600px',
    background: '#2c2c3e',
    borderRadius: '16px',
    padding: '32px',
    boxShadow: '0 8px 24px rgba(0, 0, 0, 0.4)',
    color: '#e0e0e0',
    lineHeight: 1.6,
  },
  heading: {
    fontSize: '1.8rem',
    marginBottom: '16px',
    color: '#ffffff',
  },
  text: {
    fontSize: '1rem',
    marginBottom: '14px',
    color: '#cfcfcf',
  },
  highlight: {
    color: '#7f9cf5',
    fontWeight: 600,
  },
  bold: {
    color: '#ffffff',
  },
  footerNote: {
    fontSize: '0.9rem',
    marginTop: '20px',
    color: '#888',
    fontStyle: 'italic',
  },
}

export default About