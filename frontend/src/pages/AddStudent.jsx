import { useFormik } from "formik";

import axios from "axios";

function AddStudent() {
  const formik = useFormik({
    initialValues: {
      name: "",
      course: "",
      email: "",
    },

    onSubmit: async (values) => {
      await axios.post(
        "http://localhost:8081/students",

        values,
      );

      alert("Student Added");
    },
  });

  return (
    <form onSubmit={formik.handleSubmit}>
      <input name="name" placeholder="Name" onChange={formik.handleChange} />

      <br />
      <br />

      <input
        name="course"
        placeholder="Course"
        onChange={formik.handleChange}
      />

      <br />
      <br />

      <input name="email" placeholder="Email" onChange={formik.handleChange} />

      <br />
      <br />

      <button type="submit">Save</button>
    </form>
  );
}

export default AddStudent;