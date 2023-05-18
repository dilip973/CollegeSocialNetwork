import React, { useState } from "react";
import axios from "axios";
//  import img from "../../src/Components/Resources/login_image";

const Registration = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [role, setRole] = useState("");
  const [message, setMessage] = useState("");

  const handleUsernameChange = (e) => {
    setUsername(e.target.value);
  };

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
  };

  const handleFirstNameChange = (e) => {
    setFirstName(e.target.value);
  };

  const handleLastNameChange = (e) => {
    setLastName(e.target.value);
  };

  const handleRoleChange = (e) => {
    setRole(e.target.value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    alert("You have successfully registered in the College Social Network!");
    setUsername("");
    setPassword("");
    setFirstName("");
    setLastName("");
    setRole("");

    try {
      const response = await axios.post("http://localhost:8080/api/user/register", {
        username,
        password,
        firstName,
        lastName,
        role,
      });

      setMessage(response.data.msg);
      setUsername("");
      setPassword("");
      setFirstName("");
      setLastName("");
      setRole("");
    } catch (error) {
      setMessage(error.response.data.msg);
    }
  };

  return (
    <>
      <div className="login_container">
        <div className="wrapper">
          <div className="logo">
            <section className="h-100 gradient-form" style={{ backgroundColor: "#eee" }}>
              <div className="container py-5 h-100">
                <div className="row d-flex justify-content-center align-items-center h-100">
                  <div className="col-xl-10">
                    <div className="card rounded-3 text-black">
                      <div className="row g-0">
                        <div className="col-lg-6">
                          <div className="card-body p-md-5 mx-md-4">
                            <div className="text-center">
                              <h3>
                                COLLEGE
                                <span style={{ color: "#03A9F4", fontWeight: "bolder" }}>
                                  SOCIAL NETWORK
                                </span>
                              </h3>
                            </div>
                            <form onSubmit={handleSubmit}>
                            <div className="form-group d-flex align-items-center">
                                <span className="far fa-user me-3"></span>
                                <div>
                                  <input
                                    type="text"
                                    id="firstName"
                                    placeholder="First Name"
                                    value={firstName}
                                    onChange={handleFirstNameChange}
                                  />
                                </div>
                              </div>
                              <div className="form-group d-flex align-items-center">
                                <span className="far fa-user me-3"></span>
                                <div>
                                  <input
                                    type="text"
                                    id="lastName"
                                    placeholder="Last Name"
                                    value={lastName}
                                    onChange={handleLastNameChange}
                                  />
                                </div>
                              </div>
                              <div className="form-group d-flex align-items-center">
                                <span className="far fa-user me-3"></span>
                                <div>
                                  <input
                                    type="text"
                                    id="username"
                                    placeholder="Username"
                                    value={username}
                                    onChange={handleUsernameChange}
                                  />
                                </div>
                              </div>
                              <div className="form-group d-flex align-items-center">
                                <span className="far fa-lock me-3"></span>
                                <div>
                                  <input
                                    type="password"
                                    id="password"
                                    placeholder="Password"
                                    value={password}
                                    onChange={handlePasswordChange}
                                  />
                                </div>
                              </div><br/>
                              <div className="form-group d-flex align-items-center">
                                <span className="far fa-lock me-3"></span>
                                <div className="form-group">
                                  <select
                                    className="form-select"
                                    name="role"
                                    id="role"
                                    onChange={handleRoleChange}
                                    value={role}
                                  >
                                    <option value="">User Type</option>
                                    <option value="admin"> ADMIN</option>
                                    <option value="student"> STUDENT </option>
                                    <option value="faculty"> FACULTY </option>
                                    <option value="placementofficer">
                                      PLACEMENT OFFICER
                                    </option>
                                    <option value="STAFF"> STAFF </option>
                                  </select>
                                </div>
                              </div>
                              <br />
                              <button
                                className="btn btn-primary btn-block gradient-custom-2"
                                type="submit"
                              >
                                Register
                              </button>
                            </form>
                          </div>
                        </div>
                        <div
                          className="col-lg-6 d-flex align-items-center justify-content-center gradient-custom-2"
                          style={{ marginTop: "10px", height: "500px", width: "450px" }}
                        >
                          {/* <img src={img} alt="img not found" /> */}
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </section>
          </div>
        </div>
      </div>
    </>
  );
};

export default Registration;
