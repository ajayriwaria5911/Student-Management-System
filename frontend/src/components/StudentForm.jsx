import { useState } from "react";

function StudentForm() {
  const [name, setName] = useState("");
  const [course, setCourse] = useState("");

  const addStudent = async () => {
    const token =
      "your_token";

    const res = await fetch("http://localhost:8080/students", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },

      body: JSON.stringify({
        name,
        course,
      }),
    });

    alert("Student Added Successfully");
  };

  return (
    <div>
      <input placeholder="Name" onChange={(e) => setName(e.target.value)} />

      <br />
      <br />

      <input placeholder="Course" onChange={(e) => setCourse(e.target.value)} />

      <br />
      <br />

      <br />
      <br />

      <button onClick={addStudent}>Add Student</button>
    </div>
  );
}

export default StudentForm;
