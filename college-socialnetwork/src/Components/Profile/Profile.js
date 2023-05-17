import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './Profile.css';


function Profile() {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    axios.get('http://localhost:8080/api/user/login', { withCredentials: true })
      .then(response => {
        setUser(response.data);
        setLoading(false);
        console.log(response.data);
      })
      .catch(error => {
        setError(error.message);
        setLoading(false);
        console.log(error.message);
      });
  }, []);

  const handleLogout = () => {
    axios.post('http://localhost:8080/api/user/logout')
      .then(response => {
        setUser(null);
        console.log(response.data);
      })
      .catch(error => {
        console.error(error);
      });
      
  };

  if (loading) {
    return <p>Loading user details...</p>;
  }

  if (error) {
    return <p>Oops, something went wrong: {error}</p>;
  }

  if (!user) {
    return (
      <div className="profile-container">
        <h1>Welcome to your profile</h1>
        <p>Please log in to view your profile.</p>
        <a href="/">Log In</a>
      </div>
    );
  }

  return (
    <div>
   {/* <Navbar/> */}
    <div className="profile-container">
    <h1 style={{ fontFamily: 'Arial', fontSize: '28px', fontWeight: 'bold', color: '#333', marginBottom: '10px', textTransform: 'uppercase', letterSpacing: '1px', textShadow: '1px 1px 2px rgba(0, 0, 0, 0.1)' }}>
  Welcome, {user.userName}
</h1>


<div style={{ 
  backgroundColor: '#F0F0F0', 
  padding: '10px', 
  borderRadius: '5px',
  textAlign: 'center',
  margin: '10px',
}}>
  <div className="profile-details">
    <p style={{ 
      fontWeight: 'bold', 
      marginBottom: '5px',
      color: '#333',
    }}>Email: {user.emailId}</p>
    <p style={{
      color: '#666',
    }}>{user.userrole}</p>
  </div>
</div>
      <button onClick={handleLogout}>Log Out</button>
    </div>
    </div>
  );
}

export default Profile;

