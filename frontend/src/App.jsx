import { useEffect, useState } from "react";

function App() {

  const [students, setStudents] = useState([]);

  const getStudents = async () => {

    const response = await fetch(
      "http://localhost:8080/students/bca"
    );

    const data = await response.json();

    setStudents(data);
  };

  useEffect(() => {
    getStudents();
  }, []);

  return (
    <div style={{ padding: "40px" }}>

      <h1>BCA Students</h1>

      {
        students.map(student => (
          <div key={student.id}>
            <h3>{student.name}</h3>
          </div>
        ))
      }

    </div>
  );
}

export default App;