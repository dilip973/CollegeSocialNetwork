import { BrowserRouter, Switch, Route, Routes } from "react-router-dom";
import logo from "./logo.svg";
import "./App.css";
import Login from "./Components/User/LogIn/Login";
import SignUp from "./Components/User/SignUp/index";
import Navbar from "./Components/Navbar";
import Profile from "./Components/Profile";
import NewsFeed from "./Components/NewsFeed";
import Home from "./Components/Home";
import Registration from "./Components/Registration";


function App() {
  return (
    <>
      <BrowserRouter>
      <Navbar/>
        <Routes>
          <Route exact path="/" element={<Home/>} />
          <Route exact path="/login" element={<Login/>} />
          <Route exact path="/register" element={<Registration/>} />
          <Route exact path="/newsfeed" element={<NewsFeed/>} />
          <Route exact path="/profile/:id" element={<Profile/>} />
          {/* <Route exact path="/dashboard" element={<Dashboard/>} /> */}
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
