import { BrowserRouter, Switch, Route, Routes } from "react-router-dom";
import logo from "./logo.svg";
import "./App.css";
import Login from "./Components/User/LogIn/Login";
import SignUp from "./Components/User/SignUp/index";
import Navbar from "./Components/Navbar";
import NewsFeed from "./Components/NewsFeed";
import Registration from "./Components/Registration";
import AdminDashboard from "./Components/Dashboard/AdminDashboard";
import PostForm from "./Components/Post/PostForm";
import Chat from "./Components/Chat/Chat";
import Home from "./Components/HomePage/Home";
import Profile from "./Components/Profile/Profile";
import AddStudent from "./Components/PlacementOfficer/Placement";
import PlacementHome from "./Components/PlacementOfficer/PlacementHome";
import ViewAllStudent from "./Components/PlacementOfficer/ViewAllStudent";


function App() {
  return (
    <>
      <BrowserRouter>
      {/* <Navbar></Navbar> */}
        <Routes>
          <Route exact path="/" element={<Login/>} />
          <Route exact path="/Home" element={<Home/>} />
          <Route exact path="/register" element={<Registration/>} />
          <Route exact path="/newsfeed" element={<NewsFeed/>} />
          <Route exact path="/dashboard" element={<AdminDashboard/>} />
          <Route exact path="/post" element={<PostForm/>} />
          <Route exact path="/profile" element={<Profile/>} />
          <Route exact path="/chat" element={<Chat/>} />
          <Route exact path="/placement" element={<AddStudent/>} />
          <Route exact path="/student/allstudents" element={<ViewAllStudent/>} />
          <Route exact path="/placementHome" element={<PlacementHome/>} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
