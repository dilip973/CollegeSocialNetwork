import React from 'react';
import { Link, NavLink } from 'react-router-dom';

const Navbar = () => {
  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-light" style={{ borderBottom: '1px solid #ccc' }}>
  <NavLink className="navbar-brand" to="/" style={{ fontWeight: 'bold', fontSize: '24px' }}>College Social Network</NavLink>
  <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
    <span className="navbar-toggler-icon" style={{ backgroundColor: '#333' }}></span>
  </button>
  <div className="collapse navbar-collapse" id="navbarNavAltMarkup">
    <div className="navbar-nav" style={{ marginLeft: 'auto' }}>
      <NavLink className="nav-item nav-link" activeClassName="active" to="/login" style={{ color: '#333', marginRight: '10px' }}>Login</NavLink>
      <NavLink className="nav-item nav-link"  to="/register" style={{ color: '#333', marginRight: '10px' }}>Register</NavLink>
      <NavLink className="nav-item nav-link"  to="/newsfeed" style={{ color: '#333', marginRight: '10px' }}>Newsfeed</NavLink>
      <NavLink className="nav-item nav-link"  to="/profile/:id" style={{ color: '#333', marginRight: '10px' }}>Profile</NavLink>
    </div>
    <form className="d-flex" style={{ marginLeft: 'auto' }}>
      <input className="form-control me-2" type="search" placeholder="Search" aria-label="Search" style={{ width: '200px', marginRight: '10px' }} />
      <button className="btn btn-outline-success" type="submit" style={{ fontSize: '14px' }}>Search</button>
    </form>
  </div>
</nav>

              
  );
}

export default Navbar;
