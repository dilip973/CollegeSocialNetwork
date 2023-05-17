import axios from "axios";
import './ViewAllStudent.css';
import { useState, useEffect } from "react";


const ViewAllStudent = () => {
  const [students, setStudents] = useState([]);

  useEffect(() => {
    axios
      .get("http://localhost:8080/students/allstudents")

      .then((response) => setStudents(response.data))

      .catch((error) => console.log(error));
  }, []);

  

  return (
    <div className="all-Student">
      <center>
        <h1>Student Details</h1>{" "}
      </center>

      <table>
        <thead>
          <tr>
            <th>Student ID</th>
            <th>Name</th>
            <th>Department</th>
            <th>Backlogs</th>
            <th>Percentage</th> {/* <th>Password</th> */}
          </tr>
        </thead>

        <tbody>
          {students.map((student) => (
            <tr key={student.id}>
              <td>{student.id}</td>

              <td>{student.name}</td>

              <td>{student.department}</td>

              <td>{student.backlogs}</td>

              <td>{student.percentage}</td>

              {/* <td>{student.customer.email}</td> */}

              {/* <td>{student.customer.password}</td> */}
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default ViewAllStudent;
