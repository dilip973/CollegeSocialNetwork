import React from "react";

const Home = () => {
  return (
    <div className="container mt-3">
      <h1>Welcome to the College Social Network</h1>
      <p className="lead">
        The College Social Network is a platform for students, faculty members,
        and placement officers to interact with each other and stay connected.
      </p>
      <div className="row">
        <div className="col-md-6">
          <h2>Features for Students</h2>
          <ul>
            <li>Register and login to the system</li>
            <li>Post articles on various topics of their choice</li>
            <li>Upload images with their posts</li>
            <li>Chat with other students</li>
            <li>View news feed posted by other students</li>
            <li>View their own profiles</li>
          </ul>
        </div>
        <div className="col-md-6">
          <h2>Features for Faculty Members</h2>
          <ul>
            <li>Add or delete faculty members</li>
            <li>View student profiles</li>
            <li>Post events which will be sent via SMS to students</li>
          </ul>
        </div>
      </div>
      <div className="row">
        <div className="col-md-6">
          <h2>Features for Placement Officers</h2>
          <ul>
            <li>Create a list of students by specifying the department and
              criteria</li>
            <li>View student profiles</li>
            <li>Add posts which will be sent for approval to the admin</li>
          </ul>
        </div>
        <div className="col-md-6">
          <h2>Features for Admin</h2>
          <ul>
            <li>Approve or reject student registrations and posts</li>
            <li>Add or delete faculty members</li>
          </ul>
        </div>
      </div>
    </div>
  );
};

export default Home;
