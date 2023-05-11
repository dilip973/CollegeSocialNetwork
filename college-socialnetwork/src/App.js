import { BrowserRouter, Switch, Route, Routes } from "react-router-dom";
import logo from "./logo.svg";
import "./App.css";
import Login from "./Components/User/LogIn/Login";
import SignUp from "./Components/User/SignUp/index";
import Navbar from "./Components/Navbar";
import Profile from "./Components/Profile";
import NewsFeed from "./Components/NewsFeed";
import Registration from "./Components/Registration";
import AdminDashboard from "./Components/Dashboard/AdminDashboard";
import PostForm from "./Components/Post/PostForm";
import Chat from "./Components/Chat/Chat";
import Home from "./Components/HomePage/Home";


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
          <Route exact path="/dashboard" element={<AdminDashboard/>} />
          <Route exact path="/post" element={<PostForm/>} />
          <Route exact path="/chat" element={<Chat/>} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
