import { useState, useEffect } from "react";
import StudentCard from "./components/StudentCard";
import StudentForm from "./components/StudentForm";

function App() {

  const [students, setStudents] = useState([]);
  const fetchStudents = async () => {
    const token =
      "your_token";

    const response = await fetch("http://localhost:8080/students", {
      method: "GET",
      headers: {
        Authorization: `Bearer ${token}`,
        "Content-Type": "application/json",
      },
    });

    const data = await response.json();

    setStudents(data);
  };

  useEffect(() => {
    fetchStudents();
  }, []);

  return (
    <div
      style={{
        padding: "20px",
      }}
    >
      <h1>Student Management System</h1>

      <StudentForm refreshStudents={fetchStudents} />

      <hr />

      {students.map((student) => (
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
