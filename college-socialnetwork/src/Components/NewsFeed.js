import React, { useEffect, useState } from "react";
import axios from "axios";

const NewsFeed = () => {
  const [posts, setPosts] = useState([]);

  useEffect(() => {
    const fetchPosts = async () => {
      try {
        const response = await axios.get("/api/posts");
        setPosts(response.data);
      } catch (error) {
        console.log(error);
      }
    };
    fetchPosts();
  }, []);

  return (
    <div>
      <h2>News Feed</h2>
      {posts.map((post) => (
        <div key={post.id}>
          <h4>{post.title}</h4>
          <p>{post.body}</p>
          <img src={post.imageUrl} alt="Post Image" />
          <hr />
        </div>
      ))}
    </div>
  );
};

export default NewsFeed;
