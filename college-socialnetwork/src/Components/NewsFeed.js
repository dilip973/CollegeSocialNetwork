import React from 'react';

const NewsFeed = () => {
  return (
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
  );
}

export default NewsFeed;
