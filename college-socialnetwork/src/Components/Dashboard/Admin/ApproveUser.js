import React, { useState, useEffect } from 'react';
import axios from 'axios';

const ApproveUser = ({ userId, onUserApproved }) => {
  const [loggedIn, setLoggedIn] = useState(false);
  const [admin, setAdmin] = useState(null);

  useEffect(() => {
    checkAuthStatus();
  }, []);

  const checkAuthStatus = async () => {
    try {
      const response = await axios.get('/admin/auth/status');
      if (response.status === 200) {
        setLoggedIn(true);
        setAdmin(response.data);
      }
    } catch (error) {
      console.error('Error checking authentication status:', error);
    }
  };

  const approveUser = async () => {
    try {
      const response = await axios.post(`/admin/users/${userId}/approve`);
      if (response.status === 200) {
        onUserApproved(response.data);
      }
    } catch (error) {
      console.error('Error approving user:', error);
    }
  };

  if (!loggedIn || !admin) {
    return null; // Render nothing if not logged in or admin data not available
  }

  return (
    <div>
      <h3>Approve User</h3>
      <p>Logged in as: {admin.username}</p>
      <button onClick={approveUser}>Approve User {userId}</button>
    </div>
  );
};

export default ApproveUser;
