import React, { useState } from "react";
import { PostMapping } from "./axios.js";
import Navbar from "../More/Navbar.jsx";
// import About from "../More/About.jsx";
import { Link } from "react-router-dom";

function OneAndOnlyPage() {
  const [url , setUrl] =useState("");
  const [displayNewUrl,setDisplay] = useState("");
  async function addUrl(e)
  {
    e.preventDefault();

    console.log(url);
    const r=await PostMapping(url);
    setDisplay("http://localhost:8080/api/show/"+r.data);
    console.log(r);
    console.log("http://localhost:8080/api/show/"+r.data);
  }
  function changeEvent(e)
  {
    setUrl(
     e.target.value
    );
    console.log(e.target.value);
  }
  
  return (
<>
<Navbar/>
    <div
    style={{
      minHeight: "100vh",
      background: "#f4f6f8",
      display: "flex",
      justifyContent: "center",
      alignItems: "center",
    }}
    >
      <div
        style={{
          width: "450px",
          background: "#fff",
          padding: "30px",
          borderRadius: "15px",
          boxShadow: "0 10px 25px rgba(0,0,0,0.1)",
          textAlign: "center",
        }}
        >
        <marquee>Url get Delete after 5hrs. Check <Link to="/About">About</Link> Section for more.</marquee>
        <h1 style={{ color: "#333" }}>URL Shortener</h1>

        <p style={{ color: "#666", marginBottom: "30px" }}>
          Welcome Master.
          <br />
          mijikai(short) your URL
        </p>
        <form >

        <input
          type="text"
          placeholder="Enter your long URL..."
          style={{
            width: "100%",
            padding: "12px",
            fontSize: "16px",
            border: "1px solid #ccc",
            borderRadius: "8px",
            marginBottom: "20px",
            boxSizing: "border-box",
          }}
          onChange={changeEvent}
          />

        <button
          style={{
            width: "100%",
            padding: "12px",
            background: "#007BFF",
            color: "white",
            border: "none",
            borderRadius: "8px",
            fontSize: "16px",
            cursor: "pointer",
          }}
          
          onClick={addUrl}
          >
          Generate Short URL
        </button>
          </form>

        <div
          style={{
            marginTop: "30px",
            padding: "15px",
            background: "#f8f9fa",
            borderRadius: "8px",
          }}
          >
          <strong>Shortened URL</strong>

          {displayNewUrl ? (
            <a
            href={displayNewUrl}
            target="_blank"
            rel="noopener noreferrer"
            >
                  {displayNewUrl}
              </a>
          ) : (
            <p>Small-Url will appear here.</p>
          )}
        </div>
      </div>
    </div>
</>
  );
}

export default OneAndOnlyPage;