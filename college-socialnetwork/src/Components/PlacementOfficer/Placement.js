import React, { useState } from "react";

import axios from "axios";

import { useNavigate } from "react-router-dom";



function AddStudent() {
  const [studentData, setStudentData] = useState({
    id: "",

    name: "",

    department: "",

    backlogs: "",

    percentage: "",
  });

  const navigate = useNavigate();

  const { id, name, department, backlogs, percentage } = studentData;

  const handleChange = (e) => {
    setStudentData({
      ...studentData,

      [e.target.name]: e.target.value,
    });
  };

  const submitHandler = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post(
        "http://localhost:8080/students",
        studentData
      );

      console.log(response.data);

      navigate("/addsuccess"); // Success message from the server

      // Optionally, you can perform additional actions here, such as displaying a success message or navigating to another page.
    } catch (error) {
      console.error("Error adding student:", error.response.data); // Error message from the server

      // You can handle the error here, such as displaying an error message to the user.
    }
  };

  return (
    <div>
      <form onSubmit={submitHandler}>
        <label>Id:</label>

        <input type="number" name="id" value={id} onChange={handleChange} />

        <br />

        <label>Name:</label>

        <input type="text" name="name" value={name} onChange={handleChange} />

        <br />

        <label>Department:</label>

        <input
          type="text"
          name="department"
          value={department}
          onChange={handleChange}
        />

        <br />

        <label>Backlogs:</label>

        <input
          type="number"
          name="backlogs"
          value={backlogs}
          onChange={handleChange}
        />

        <br />

        <label>Percentage:</label>

        <input
          type="number"
          name="percentage"
          value={percentage}
          onChange={handleChange}
        />

        <br />

        <button type="submit">Submit</button>
      </form>
    </div>
  );
}

export default AddStudent;
