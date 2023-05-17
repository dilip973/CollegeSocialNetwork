import React, { useState, useEffect, useRef } from 'react';
import { axios } from 'react-axios';
import Chat from '../Chat/Chat';
import Navbar from '../Navbar';
import PostForm from '../Post/PostForm';
import PostList from '../Post/PostList';
import Profile from '../Profile/Profile';
import './Home.css';

const Home = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [showChat, setShowChat] = useState(false); // state to toggle chat section
  const [showPostForm, setShowPostForm] = useState(false);
  const [posts, setPosts] = useState([]);
  const postListRef = useRef();
  const interval = 5000; // 5 seconds

  // useEffect(() => {
  //   const fetchPosts = async () => {
  //     const response = await axios.get('http://localhost:8080/posts/getall');
  //     setPosts(response.data);
  //   };
  //   fetchPosts();

  //   const intervalId = setInterval(() => {
  //     fetchPosts();
  //   }, interval);

  //   return () => clearInterval(intervalId);
  // }, []);

  useEffect(() => {
    postListRef.current.scrollTop = postListRef.current.scrollHeight;
  }, [posts]);

  const handlePostFormClose = () => setShowPostForm(false);

  console.log(localStorage.getItem('userName'))

  
  return (
    <div>
      <Navbar />
      <div className="college-social-network-container">
        {/* Main content */}
        <main>
          {/* Sidebar */}
          <aside>
            {/* Profile section */}
            <div className="profile-section">
              {/* Render Profile component */}
              <Profile />
            </div>

            {/* Chat section */}
            <div className="chat-section">
              {/* Chat header */}
              <div className="chat-header">
                <h3>Chat</h3>
                <button onClick={() => setShowChat(!showChat)}>Start new chat</button>
              </div>

              {/* Chat list */}
              {showChat && <Chat />} {/* Render Chat component when showChat is true */}
            </div>
          </aside>

          {/* Newsfeed */}
          <section className="newsfeed-section">
            {/* Create post */}
            <div className="create-post">
              {showPostForm ? (
                <PostForm onClose={handlePostFormClose} />
              ) : (
                <button onClick={() => setShowPostForm(true)}>Create Post</button>
              )}
            </div>

            {/* Posts */}
            <div className="post-list" ref={postListRef}>
              <PostList posts={posts} />
            </div>
          </section>
        </main>

        {/* Footer */}
        <footer>
          <p>© 2023 College Social Network</p>
        </footer>
      </div>
    </div>
  );
};

export default Home;
