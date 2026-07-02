import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./SignUp.css";

function SignUp() {
  const [name, setName] = useState("");
  const [pwd, setPwd] = useState("");
  const [email, setEmail] = useState("");
  const [otp, setOtp] = useState("");
  const [otpSent, setOtpSent] = useState(false);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const navigate = useNavigate();

  async function handleSubmit(e) {
    e.preventDefault();
    setLoading(true);
    setError("");

    try {
      const res = await fetch("http://localhost:8080/api/SignUp", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ name, pwd, email }),
      });

      if (res.ok) {
        setOtpSent(true);
      } else {
        const msg = await res.text();
        setError(msg || "Something went wrong, try again.");
      }
    } catch (err) {
      setError("Server not reachable.");
    } finally {
      setLoading(false);
    }
  }

  async function handleOtpVerify(e) {
    e.preventDefault();
    setLoading(true);
    setError("");

    try {
      const res = await fetch("http://localhost:8080/api/Otp", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, otp }),
      });

      if (res.ok) {
        alert("Account created successfully!");
        navigate("/");
      } else {
        const msg = await res.text();
        setError(msg || "Invalid or expired OTP.");
      }
    } catch (err) {
      setError("Server not reachable.");
    } finally {
      setLoading(false);
    }
  }

  return (
    <div className="signup-container">
      <form className="signup-form" onSubmit={otpSent ? handleOtpVerify : handleSubmit}>
        <h2>Create Account</h2>

        <label>Name</label>
        <input
          type="text"
          value={name}
          onChange={(e) => setName(e.target.value)}
          required
          disabled={otpSent}
        />

        <label>Password</label>
        <input
          type="password"
          value={pwd}
          onChange={(e) => setPwd(e.target.value)}
          placeholder="Mera toh Bicycle@123 tha."
          required
          disabled={otpSent}
        />

        <label>Email</label>
        <input
          type="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
          disabled={otpSent}
        />

        {otpSent && (
          <div className="otp-section">
            <p className="otp-info">OTP sent to <strong>{email}</strong></p>
            <label>Enter OTP</label>
            <input
              type="text"
              value={otp}
              onChange={(e) => setOtp(e.target.value)}
              placeholder="Enter 6-digit OTP"
              maxLength={6}
              required
              autoFocus
            />
          </div>
        )}

        {error && <p className="error-msg">{error}</p>}

        <button type="submit" disabled={loading}>
          {loading ? "Please wait..." : otpSent ? "Verify OTP" : "Click to Get OTP"}
        </button>

        {otpSent && (
          <button
            type="button"
            className="resend-btn"
            onClick={() => { setOtpSent(false); setOtp(""); setError(""); }}
          >
            Wrong email? Go back
          </button>
        )}

        <p>
          Already have an Account? <Link to="/">Login</Link>
        </p>
      </form>
    </div>
  );
}

export default SignUp;