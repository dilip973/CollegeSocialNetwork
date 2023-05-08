import React, { useState } from 'react';

const RegistrationForm = () => {
    const [email, setEmail] = useState('');
    const [firstName, setFirstName] = useState();
    const [lastName, setLastName] = useState();

    const handleSubmit = (event) =>{
        event.preventDefault();

        

    }
    return (
        <div>
            <div className='registration_form'>
            <section className="vh-100 bg-image">
        <div className="mask d-flex align-items-center h-100 gradient-custom-3">
          <div className="container h-100">
            <div className="row d-flex justify-content-center align-items-center h-100">
              <div className="col-12 col-md-9 col-lg-7 col-xl-6">
                <div className="card" style={{borderRadius: '15px'}}>
                  <div className="card-body p-5">
                    <h2 className="text-uppercase text-center mb-5">Create an account</h2>
                    <form>
                      <div className="form-outline mb-4">
                        <input type="text" id="name" className="form-control form-control-lg" />
                        <label className="form-label" htmlFor="1cg">Your Name</label>
                      </div>
                      <div className="form-outline mb-4">
                        <input type="email" id="email" className="form-control form-control-lg" />
                        <label className="form-label" htmlFor="3cg" placeholder='Your Email'>Your Email</label>
                      </div>
                      <div className="form-outline mb-4">
                        <input type="password" id="password" className="form-control form-control-lg" />
                        <label className="form-label" htmlFor="4cg" placeholder='Enter Password'>Password</label>
                      </div>
                      <div className="form-outline mb-4">
                        <input type="password" id="confirmpassword" className="form-control form-control-lg" />
                        <label className="form-label" htmlFor="4cdg" placeholder='confirm password'>Confirm password</label>
                      </div>
                      <div className="form-check d-flex justify-content-center mb-5">
                        <input className="form-check-input me-2" type="checkbox" defaultValue id="form2Example3cg" />
                        <label className="form-check-label" htmlFor="form2Example3g">
                          I agree all statements in <a href="#!" className="text-body"><u>Terms of service</u></a>
                        </label>
                      </div>
                      <div className="d-flex justify-content-center">
                        <button type="button" className="btn btn-success btn-block btn-lg gradient-custom-4 text-body">Register</button>
                      </div>
                      <p className="text-center text-muted mt-5 mb-0">Have already an account? <a href="#!" className="fw-bold text-body"><u>Login here</u></a></p>
                    </form>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
            </div>
        </div>
    );
};

export default RegistrationForm;