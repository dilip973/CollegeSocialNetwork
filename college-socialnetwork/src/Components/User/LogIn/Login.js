import React, { useState } from "react";
import "./style.css";
import img from "../../Resources/login_image.jpg";

import axios from "axios";
import Home from "../../HomePage/Home";


const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [role, setRole] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const [isLoggedIn, setIsLoggedIn] = useState(false); // added state variable

  const handleEmailChange = (event) => {
    setUsername(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const handleRoleChange = (event) => {
    setRole(event.target.value);
  };


  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      const response = await axios.post("http://localhost:8080/api/user/login", {
  username: username,
  password: password,
  role: role,
});
      console.log(response.data);
      if (response.status === 200) {
        setIsLoggedIn(true);
      } else {
        setErrorMessage("Login failed");
      }
    } catch (error) {
      if (error.response) {
        console.log(error.response.data);
        setErrorMessage(error.response.data.msg);
      } else {
        console.log(error);
        setErrorMessage("An error occurred");
      }
    }
  };
  

  //if user is already logged in, redirect to Home component
  if (isLoggedIn) {
    return <Home />;
  }

  return (
   
    <>
      <div className="login_container">
        <div className="wrapper">
          <div className="logo">
            <section
              className="h-100 gradient-form"
              style={{ backgroundColor: "#eee" }}
            >
              <div className="container py-5 h-100">
                <div className="row d-flex justify-content-center align-items-center h-100">
                  <div className="col-xl-10">
                    <div className="card rounded-3 text-black">
                      <div className="row g-0">
                        <div className="col-lg-6">
                          <div className="card-body p-md-5 mx-md-4">
                            <div className="text-center">
                              <h3>
                                {" "}
                                COLLEGE{" "}
                                <span
                                  style={{
                                    color: "#03A9F4",
                                    fontWeight: "bolder",
                                  }}
                                >
                                  SOCIAL NETWORK
                                </span>
                              </h3>
                            </div>
                            &nbsp;
                            <form onSubmit={handleSubmit}>
                              <div className="form-group d-flex align-items-center">
                                <span className="far fa-envelope me-3"></span>
                                <input
                                  type="email"
                                  className="form-control"
                                  name="emailId"
                                  id="emailId"
                                  placeholder="Email address"
                                  value={username}
                                  onChange={handleEmailChange}
                                  required
                                />
                              </div>
                              &nbsp;
                              <div className="form-group d-flex align-items-center">
                                <span className="far fa-lock me-3"></span>
                                <input
                                  type="password"
                                  className="form-control"
                                  name="password"
                                  id="password"
                                  placeholder="Password"
                                  value={password}
                                  onChange={handlePasswordChange}
                                  required
                                />
                              </div>
                              &nbsp;
                              <div className="form-group d-flex align-items-center">
                                <span className="far fa-lock me-3"></span>
                                <select
                                    className="form-select"
                                    name="role"
                                    id="role"
                                    onChange={handleRoleChange}
                                  >
                                    <option value=""> UserType </option>
                                    <option value="admin"> ADMIN</option>
                                    <option value="student"> STUDENT </option>
                                    <option value="faculty"> FACULTY </option>
                                    <option value="placementofficer">
                                      PLACEMENT OFFICER
                                    </option>
                                    <option value="STAFF"> STAFF </option>
                                  </select>
                              </div>
                              <div className="text-center pt-3">
                                <button
                                  className="btn btn-primary btn-block gradient-custom-2"
                                  type="submit"
                                >
                                  Log in
                                </button>
                                <div className="my-3">
                                  <a href="#!" className="text-muted">
                                    Forgot password?
                                  </a>
                                </div>
                                <div className="d-flex justify-content-center">
                                  <p className="me-2">Don't have an account?</p>
                                  <span>
                                    {" "}
                                    <a href="/register">REGISTER NOW</a>
                                  </span>
                                </div>
                              </div>
                            </form>
                          </div>
                        </div>
                        <div
                          className="col-lg-6 d-flex align-items-center justify-content-center gradient-custom-2"
                          style={{
                            marginTop: "10px",
                            height: "500px",
                            width: "450px",
                          }}
                        >
                          <img src={img} alt="img not found" />
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

export default Login;
