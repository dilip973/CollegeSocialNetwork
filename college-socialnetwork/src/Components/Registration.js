// import React, { useState } from "react";
// import axios from "axios";
// import img from "../../src/Components/Resources/login_image.jpg";

// const Registration = () => {
//   const [userName, setuserName] = useState("");
//   const [emailId, setemailId] = useState("");
//   const [password, setPassword] = useState("");
//   const [userrole, setuserrole] = useState("");
//   const [message, setMessage] = useState("");

//   const handleuserNameChange = (e) => {
//     setuserName(e.target.value);
//   };

//   const handleemailIdChange = (e) => {
//     setemailId(e.target.value);
//   };

//   const handlePasswordChange = (e) => {
//     setPassword(e.target.value);
//   };
//   const handleuserRoleChange = (e) => {
//     setuserrole(e.target.value);
//   };

//   const handleSubmit = async (e) => {
//     e.preventDefault();
//     alert("You successfuly registered in College Social Network !!!");
//     setuserName("");
//     setemailId("");
//     setPassword("");
//     setuserrole("User Type");
//     try {
//       const response = await axios.post(
//         "http://localhost:8080/api/user/register",
//         {
//           userName,
//           emailId,
//           password,
//           userrole,
//         }
//       );
//       setMessage(response.data.msg);
//       setuserName("");
//       setemailId("");
//       setPassword("");
//       setuserrole("");
//     } catch (error) {
//       setMessage(error.response.data.msg);
//     }
//   };

//   return (
//     <>
//       <div className="login_container">
//         <div className="wrapper">
//           <div className="logo">
//             <section
//               className="h-100 gradient-form"
//               style={{
//                 backgroundColor: "#eee",
//               }}
//             >
//               <div className="container py-5 h-100">
//                 <div className="row d-flex justify-content-center align-items-center h-100">
//                   <div className="col-xl-10">
//                     <div className="card rounded-3 text-black">
//                       <div className="row g-0">
//                         <div className="col-lg-6">
//                           <div className="card-body p-md-5 mx-md-4">
//                             <div className="text-center">
//                               <h3>
//                                 {" "}
//                                 COLLEGE
//                                 <span
//                                   style={{
//                                     color: "#03A9F4",
//                                     fontWeight: "bolder",
//                                   }}
//                                 >
//                                   {" "}
//                                   SOCIAL NETWORK
//                                 </span>
//                               </h3>
//                             </div>
//                             &nbsp;
//                             <form onSubmit={handleSubmit}>
//                               <div className="form-group d-flex align-items-center">
//                                 <span className="far fa-user me-3"> </span>
//                                 <div>
//                                   {/* <label htmlFor="userName"> Name: </label> */}
//                                   <input
//                                     type="text"
//                                     id="userName"
//                                     placeholder="Name"
//                                     value={userName}
//                                     onChange={handleuserNameChange}
//                                   />
//                                 </div>
//                               </div>
//                               &nbsp;
//                               <div className="form-group d-flex align-items-center">
//                                 <span className="far fa-lock me-3"> </span>
//                                 <div>
//                                   {/* <label htmlFor="emailId"> Email: </label> */}
//                                   <input
//                                     type="emailId"
//                                     id="emailId"
//                                     placeholder="emailId"
//                                     value={emailId}
//                                     onChange={handleemailIdChange}
//                                   />
//                                 </div>
//                               </div>
//                               &nbsp;
//                               <div className="form-group d-flex align-items-center">
//                                 <span className="far fa-lock me-3"> </span>
//                                 <div>
//                                   {/* <label htmlFor="password"> Password: </label> */}
//                                   <input
//                                     type="password"
//                                     id="password"
//                                     placeholder="password"
//                                     value={password}
//                                     onChange={handlePasswordChange}
//                                   />
//                                 </div>
//                               </div>
//                               &nbsp;
//                               <div className="form-group d-flex align-items-center">
//                                 <span className="far fa-lock me-3"> </span>
//                                 <div className="form-group">
//                                   <select
//                                     className="form-select"
//                                     name="userrole"
//                                     id="userrole"
//                                     onChange={handleuserRoleChange}
//                                   >
//                                     <option value=""> UserType </option>
//                                     <option value="STUDENT"> STUDENT </option>
//                                     <option value="FACULTY"> FACULTY </option>
//                                     <option value="PO">
//                                       PLACEMENT OFFICER
//                                     </option>
//                                     <option value="STAFF"> STAFF </option>
//                                   </select>
//                                 </div>
//                               </div>
//                               <br />
//                               <button
//                                 className="btn btn-primary btn-block gradient-custom-2"
//                                 type="submit"
//                               >
//                                 Register
//                               </button>
//                             </form>
//                           </div>
//                         </div>
//                         <div
//                           className="col-lg-6 d-flex align-items-center justify-content-center gradient-custom-2"
//                           style={{
//                             marginTop: "10px",
//                             height: "500px",
//                             width: "450px",
//                           }}
//                         >
//                           <img src={img} alt="img not found" />
//                         </div>
//                       </div>
//                     </div>
//                   </div>
//                 </div>
//               </div>
//             </section>
//           </div>
//         </div>
//       </div>
//     </>
//   );
// };

// export default Registration;
