import React from 'react';
import Navbar from './Navbar';
import PostList from './Post/PostList';

const NewsFeed = () => {
  return (
    <div style={{ width: '100vw', position: 'fixed', top: 0, left: 0, backgroundColor: '#F0F0F0', padding: '10px', borderRadius: '5px', textAlign: 'center' }} className="newsfeed-section">
    <div>
    <Navbar/>
    </div>
      {/* Create post */}
      <div className="create-post">
        <textarea placeholder="What's on your mind?" rows="3"></textarea>
        <button>Post</button>
      </div>

      {/* Posts */}
      <div className="post-list">
        <PostList />
      </div>
    </div>
    
  );
}

export default NewsFeed;
