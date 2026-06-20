import { useContext, useState, useEffect, useMemo, useCallback } from "react";
import { AuthContext } from "./context/AuthContext";
import StudentCard from "./components/StudentCard";
import StudentForm from "./components/StudentForm";

const API_BASE = "http://localhost:8081";

function App() {
  const { user, token, login, logout } = useContext(AuthContext);

  // ---- Login form state ----
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [loginError, setLoginError] = useState("");

  // ---- Student data state ----
  const [students, setStudents] = useState([]);
  const [search, setSearch] = useState("");

  const handleLogin = async (e) => {
    e.preventDefault();
    setLoginError("");

    try {
      const response = await fetch(`${API_BASE}/auth/login`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ username, password }),
      });

      const data = await response.text();

      if (data === "Invalid Credentials") {
        setLoginError("Invalid username or password");
        return;
      }

      login(username, data);
    } catch (err) {
      setLoginError("Could not reach server");
    }
  };

  const fetchStudents = useCallback(async () => {
    if (!token) return;

    const response = await fetch(`${API_BASE}/students`, {
      method: "GET",
      headers: {
        Authorization: `Bearer ${token}`,
        "Content-Type": "application/json",
      },
    });

    const data = await response.json();
    setStudents(data);
  }, [token]);

  useEffect(() => {
    if (user && token) {
      fetchStudents();
    }
  }, [user, token, fetchStudents]);

  const filteredStudents = useMemo(() => {
    return students.filter((student) =>
      student.name.toLowerCase().includes(search.toLowerCase())
    );
  }, [students, search]);

  if (!user) {
    return (
      <div style={{ padding: "20px", maxWidth: "320px" }}>
        <h1>Login</h1>
        <form onSubmit={handleLogin}>
          <div style={{ marginBottom: "10px" }}>
            <input
              type="text"
              placeholder="Username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
            />
          </div>
          <div style={{ marginBottom: "10px" }}>
            <input
              type="password"
              placeholder="Password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </div>
          <button type="submit">Login</button>
          {loginError && <p style={{ color: "red" }}>{loginError}</p>}
        </form>
      </div>
    );
  }

  return (
    <div style={{ padding: "20px" }}>
      <h1>
        Welcome {user}
        <button style={{ marginLeft: "20px" }} onClick={logout}>
          Logout
        </button>
      </h1>

      <h2>Student Management System</h2>

      <StudentForm refreshStudents={fetchStudents} token={token} />

      <hr />

      <input
        type="text"
        placeholder="Search Student..."
        value={search}
        onChange={(e) => setSearch(e.target.value)}
      />

      <hr />

      {filteredStudents.map((student) => (
        <StudentCard
          key={student.id}
          name={student.name}
          course={student.course}
          email={student.email}
        />
      ))}
    </div>
  );
}

export default App;