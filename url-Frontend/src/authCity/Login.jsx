import React, { useState } from "react";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import "./Login.css";

function Login() {
  const [email, setEmail] = useState("");
  const [pwd, setPwd] = useState("");
  const [error , setError] = useState("");
  const navigate = useNavigate();

  function handleEmailChange(e) {
    setEmail(e.target.value);
  }

  function handlePwdChange(e) {
    setPwd(e.target.value);
  }

  async function handleSubmit(e) {
    e.preventDefault();

    console.log("Email:", email);
    console.log("Password:", pwd);

    const r = await fetch("https://url-shortener-server-h5gn.onrender.com/api/Login", {
    method: "POST",
    credentials: "include",  // credential - bohot lambi kahani hai.
    headers: {
        "Content-Type": "application/json"
    },
    body: JSON.stringify({ email, pwd })
});

    const data = await r.text();
    if(data === "what the heck mail doesnt exist?Sign Up First with this mail!")
    {
      setError("Bro this mail does not exist!!! , be honest :3");
    }
    else if(data === "gtg")
    {
      // setError("")
      localStorage.setItem("email",email);
      // localStorage.setItem("email",);
      navigate("/home");
    }
    else
    {
      setError("Wrong Credential !! Enter Correct pASSword")
    }
  }

  return (
    <div className="login-container">
      <form className="login-form" onSubmit={handleSubmit}>
        <h2>Login</h2>

        <label>Email</label>
        <input
          type="email"
          name="email"
          value={email}
          onChange={handleEmailChange}
          required
        />

        <label>Password</label>
        <input
          type="password"
          name="pwd"
          value={pwd}
          onChange={handlePwdChange}
          required
          autoComplete=""
        />

        <button type="submit">Login</button>
        <p>
          {/* if error occured.. */}
          {error}
        </p>
        <p>
          Don't have an account?{" "}
          <Link to="/signup">Sign Up</Link>
        </p>
      </form>
    </div>
  );
}

export default Login;