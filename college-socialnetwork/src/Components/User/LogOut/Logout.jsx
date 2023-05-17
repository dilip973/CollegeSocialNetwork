import React, { useState } from 'react';
import axios from 'axios';

function Logout() {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [message, setMessage] = useState(null);

  const handleLogout = () => {
    setLoading(true);
    axios.post('http://localhost:8080/api/user/logout')
      .then(response => {
        setMessage(response.data.msg);
      })
      .catch(error => {
        setError(error.message);
      })
      .finally(() => {
        setLoading(false);
      });
  };

  return (
    <div>
      <button onClick={handleLogout}>Log out</button>
      {loading && <p>Loading...</p>}
      {error && <p>Error: {error}</p>}
      {message && <p>{message}</p>}
    </div>
  );
}

export default Logout;
