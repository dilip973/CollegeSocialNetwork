import React from 'react';
import Chat from '../Chat/Chat';
import './Home.css';

const Home = () => {
  return (
    <div className="college-social-network-container">
     
      {/* Main content */}
      <main>
        {/* Sidebar */}
        <aside>
          {/* Profile section */}
          <div className="profile-section">
            {/* Profile image */}
            <img src="https://via.placeholder.com/150" alt="Profile" />

            {/* Profile name */}
            <h2>Name</h2>

            {/* Profile description */}
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam non magna at quam malesuada dignissim.</p>
          </div>

          {/* Chat section */}
          <div className="chat-section">
            {/* Chat header */}
            <div className="chat-header">
              <h3>Chat</h3>
              <button>Start new chat</button>
            </div>

            {/* Chat list */}
            <ul className="chat-list">
              <li>
                <img src="https://via.placeholder.com/50" alt="Profile" />
                <div className="chat-info">
                  <h4>Name</h4>
                  <p>Last message</p>
                </div>
              </li>
              <li>
                <img src="https://via.placeholder.com/50" alt="Profile" />
                <div className="chat-info">
                  <h4>Bob Smith</h4>
                  <p>Last message</p>
                </div>
              </li>
            </ul>
          </div>
        </aside>

        {/* Newsfeed */}
        <section className="newsfeed-section">
          {/* Create post */}
          <div className="create-post">
            <textarea placeholder="What's on your mind?" rows="3"></textarea>
            <button>Post</button>
          </div>

          {/* Posts */}
          <div className="post-list">
            <div className="post">
              {/* Post author */}
              <div className="post-author">
                <img src="https://via.placeholder.com/50" alt="Profile" />
                <div className="author-info">
                  <h4>Name</h4>
                  <p>2 hours ago</p>
                </div>
              </div>

              {/* Post content */}
              <div className="post-content">
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam non magna at quam malesuada dignissim.</p>
                <img src="https://via.placeholder.com/350x150" alt="Post image" />
              </div>

              {/* Post actions */}
              <div className="post-actions">
                <button>Like</button>
                <button>Comment</button>
                <button>Share</button>
              </div>
            </div>

            {/* More posts */}
            <div className="post">
              {/* Post author */}
              <div className="post-author">
                <img src="https://via.placeholder.com/50" alt="Profile" />
                <div className="author-info">
                  <h4>Name</h4>
                  <p>1 day ago</p>
                </div>
              </div>

              {/* Post content */}
              <div className="post-content">
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam non magna at quam malesuada dignissim.</p>
              </div>

              {/* Post actions */}
              <div className="post-actions">
                <button>Like</button>
                <button>Comment</button>
                <button>Share</button>
              </div>
            </div>
          </div>
        </section>
      </main>

      {/* Footer */}
      <footer>
        <p>© 2023 College Social Network</p>
      </footer>
    </div>
    
  );
};

export default Home;
