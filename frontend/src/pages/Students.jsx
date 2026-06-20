import { useEffect, useState } from "react";

import axios from "axios";

function Students() {
  const [students, setStudents] = useState([]);

  useEffect(() => {
    axios

      .get("http://localhost:8080/students")

      .then((response) => setStudents(response.data));
  }, []);

  return (
    <div>
      <h1>Students</h1>

      {students.map((student) => (
        <div key={student.id}>{student.name}</div>
      ))}
    </div>
  );
}

export default Students;