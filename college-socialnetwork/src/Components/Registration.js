import React, { useState } from "react";
import axios from "axios";

const Registration = () => {
  const [userName, setuserName] = useState("");
  const [emailId, setemailId] = useState("");
  const [password, setPassword] = useState("");
  const [userrole, setuserrole] = useState("");
  const [message, setMessage] = useState("");

  const handleuserNameChange = (e) => {
    setuserName(e.target.value);
  };

  const handleemailIdChange = (e) => {
    setemailId(e.target.value);
  };

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
  };
  const handleuserRoleChange = (e) => {
    setuserrole(e.target.value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post("http://localhost:8080/api/user/register", {
        userName,
        emailId,
        password,
        userrole,
      });
      setMessage(response.data.msg);
      setuserName("");
      setemailId("");
      setPassword("");
      setuserrole("");
    } catch (error) {
      setMessage(error.response.data.msg);
    }
  };

  return (
    <div>
      <h1>Registration</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="userName">Name:</label>
          <input
            type="text"
            id="userName"
            value={userName}
            onChange={handleuserNameChange}
          />
        </div>
        <div>
          <label htmlFor="emailId">Email:</label>
          <input
            type="emailId"
            id="emailId"
            value={emailId}
            onChange={handleemailIdChange}
          />
        </div>
        <div>
          <label htmlFor="password">Password:</label>
          <input
            type="password"
            id="password"
            value={password}
            onChange={handlePasswordChange}
          />
        </div>
        <div className="form-group">
          <select
            className="form-select"
            name="userrole"
            id="userrole"
            onChange={handleuserRoleChange}
          >
            <option value="">UserType</option>
            <option value="STUDENT">STUDENT</option>
            <option value="FACULTY">FACULTY</option>
            <option value="PO">PLACEMENT OFFICER</option>
            <option value="STAFF">STAFF</option>
          </select>
        </div>
        <button type="submit">Register</button>
      </form>
      {message && <p>{message}</p>}
    </div>
  );
};

export default Registration;
