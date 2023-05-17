import React from 'react';
import { Link, NavLink } from 'react-router-dom';


const Navbar = () => {
  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
      <Link className="navbar-brand" to="/" style={{ fontWeight: 'bold', fontSize: '24px' , color:'white' }}>College Social Network</Link>
      <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span className="navbar-toggler-icon"></span>
      </button>
      <div className="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div className="navbar-nav ml-auto">
          <NavLink className="nav-link" to="/Home" exact>Home</NavLink>
          <NavLink className="nav-link" to="/newsfeed">Newsfeed</NavLink>
          <NavLink className="nav-link" to="/dashboard">Dashboard</NavLink>
          <NavLink className="nav-link" to="/profile">Profile</NavLink>
          <NavLink className="nav-link" to="/post">Post</NavLink>
          <NavLink className="nav-link" to="/chat">Chat</NavLink>
          <NavLink className="nav-link" to="/placement">Placement</NavLink>
        </div>
      </div>
      <form className="form-inline my-2 my-lg-0 mr-4">
      <input className="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" />
      {/* <button className="btn btn-outline-light my-2 my-sm-0" type="submit">Search</button> */}
    </form>
      {/* <div> <NavLink className="nav-link" to="/" onClick={handleLogout} >Logout</NavLink></div> */}
    </nav>
  );
}

export default Navbar;
