import React, { useState } from "react";
import "./style.css";
import img from "../../Resources/login_image.jpg"

const Login = () => {
  const [isCreatNewClicked, setisCreatNewClicked] = useState(false)
  const createNewButtonHandler =()=>{
    setisCreatNewClicked(true)
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
                            <form>
                              <div className="form-group d-flex align-items-center">
                                <span className="far fa-user me-3"></span>
                                <input
                                  type="text"
                                  className="form-control"
                                  name="userName"
                                  id="userName"
                                  placeholder="Username"
                                />
                              </div>&nbsp;
                              <div className="form-group d-flex align-items-center">
                                <span className="far fa-lock me-3"></span>
                                <input
                                  type="password"
                                  className="form-control"
                                  name="password"
                                  id="password"
                                  placeholder="Password"
                                />
                              </div>&nbsp;
                              <div className="form-group">
                               
                                <select
                                  className="form-select"
                                  name="designation"
                                  id="designation"
                                >
                                  <option value="">Select Designation</option>
                                  <option value="STUDENT">STUDENT</option>
                                  <option value="FACULTY">FACULTY</option>
                                  <option value="PO">PLACEMENT OFFICER</option>
                                  <option value="ADMIN">ADMIN</option>
                                </select>
                              </div>
                              <div className="text-center pt-3">
                                <button
                                  className="btn btn-primary btn-block gradient-custom-2"
                                  type="button"
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
                                  <button
                                    className="btn btn-outline-danger"
                                    type="button"
                                  >
                                    Create new
                                  </button>
                                </div>
                              </div>
                            </form>
                          </div>
                        </div>
                        <div className="col-lg-6 d-flex align-items-center justify-content-center gradient-custom-2" style={
                            {marginTop:'10px', height:'500px', width:'450px'}
                            }>
                            <img src={img} alt="img not found"/>
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
