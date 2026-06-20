import { useNavigate } from "react-router-dom";

function Login() {
  const navigate = useNavigate();

  const login = () => {
    navigate("/students");
  };

  return (
    <div>
      <h1>Login Page</h1>

      <button onClick={login}>Login</button>
    </div>
  );
}

export default Login;