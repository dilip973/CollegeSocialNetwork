import React, { useState, useEffect } from 'react';
import axios from 'axios';

const AdminDashboard = () => {
  const [students, setStudents] = useState([]);
  const [pendingPosts, setPendingPosts] = useState([]);

  useEffect(() => {
    axios.get('/api/students')
      .then(res => setStudents(res.data))
      .catch(err => console.log(err));

    axios.get('/api/posts/pending')
      .then(res => setPendingPosts(res.data))
      .catch(err => console.log(err));
  }, []);

  const handleApprove = (postId) => {
    axios.patch(`/api/posts/${postId}`, { status: 'approved' })
      .then(res => {
        setPendingPosts(pendingPosts.filter(post => post._id !== postId));
        console.log(res.data.message);
      })
      .catch(err => console.log(err));
  };

  const handleReject = (postId) => {
    axios.patch(`/api/posts/${postId}`, { status: 'rejected' })
      .then(res => {
        setPendingPosts(pendingPosts.filter(post => post._id !== postId));
        console.log(res.data.message);
      })
      .catch(err => console.log(err));
  };

  return (
    <div>
      <h2>Admin Dashboard</h2>
      <hr />
      <h3>Registered Students</h3>
      <table className="table">
        <thead>
          <tr>
            <th>#</th>
            <th>Name</th>
            <th>Email</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {students.map((student, index) => (
            <tr key={student._id}>
              <th scope="row">{index + 1}</th>
              <td>{student.name}</td>
              <td>{student.email}</td>
              <td>
                <button className="btn btn-danger">Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <hr />
      <h3>Pending Posts</h3>
      <table className="table">
        <thead>
          <tr>
            <th>#</th>
            <th>Author</th>
            <th>Content</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {pendingPosts.map((post, index) => (
            <tr key={post._id}>
              <th scope="row">{index + 1}</th>
              <td>{post.author}</td>
              <td>{post.content}</td>
              <td>
                <button className="btn btn-success mr-2" onClick={() => handleApprove(post._id)}>Approve</button>
                <button className="btn btn-danger" onClick={() => handleReject(post._id)}>Reject</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default AdminDashboard;
