import React, { useEffect, useState } from 'react';
import './PostList.css';
import axios from 'axios';
import { FaTrash, FaEdit } from 'react-icons/fa';
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';

// const PostList = () => {
//   const [posts, setPosts] = useState([]);

//   useEffect(() => {
//     fetchPosts();
//   }, []);

//   const fetchPosts = async () => {
//     try {
//       const response = await fetch('http://localhost:8080/posts/all');
//       const data = await response.json();
//       setPosts(data);
//     } catch (error) {
//       console.log('Error fetching posts:', error);
//     }
//   };

//   return (
//     <div className="posts-container">
//       <h1>Posts</h1>
//       {posts.length === 0 ? (
//         <p>No posts available.</p>
//       ) : (
//         <div>
//           {posts.map((post) => (
//             <div key={post.id} className="post">
//               <h2 className="post-title">{post.title}</h2>
//               <p className="post-content">{post.content}</p>
//               <img src={`data:image/jpeg;base64,${post.imageData}`} alt="Post Image" className="post-image" />
//               <p>Posted on: {new Date().toLocaleString()}</p>
//             </div>
//           ))}
//         </div>
//       )}
//     </div>
//   );
// };

// export default PostList;



const PostList=()=>{
    const [posts, setPosts] = useState([]);
  
    // Fetch all posts
    useEffect(() => {
      axios.get('http://localhost:8080/posts/all', { withCredentials: true })
        .then(response => {
          setPosts(response.data);
        })
        .catch(error => {
          console.error(error);
        });
    }, []);
  
    // Delete a post
    const deletePost = (postId) => {
      axios.delete(`http://localhost:8080/posts/${postId}`)
        .then(response => {
          console.log(response.data);
          // Remove the deleted post from the state
          setPosts(posts.filter(post => post.id !== postId));
        })
        .catch(error => {
          console.error(error);
        });
    };
  
    // Update a post
    const updatePost = (postId, updatedPost) => {
      axios.put(`http://localhost:8080/posts/${postId}`, updatedPost)
        .then(response => {
          console.log(response.data);
          // Update the state with the updated post
          setPosts(posts.map(post => post.id === postId ? response.data : post));
        })
        .catch(error => {
          console.error(error);
        });
    };
  
    return (
      <div>
        {posts.map(post => (
          <Card key={post.id} className="mb-3">
            <Card.Body>
            <Card.Text>{post.userName}</Card.Text>
              <Card.Title>{post.title}</Card.Title>
              <Card.Text>{post.content}</Card.Text>
              {post.imageData && (
            <Card.Img variant="top" src={`data:image/jpeg;base64,${post.imageData}`} alt="Post Image" />
          )}
              <Card.Text>Posted on: {new Date(post.postedDate).toLocaleString()}</Card.Text>
              <div className="d-flex justify-content-end">
                <Button variant="danger" className="mr-2" onClick={() => deletePost(post.id)}>
                  <FaTrash /> Delete
                </Button>
                <Button variant="primary" onClick={() => updatePost(post.id, { ...post, title: 'Updated Title' })}>
                  <FaEdit /> Edit
                </Button>
              </div>
            </Card.Body>
          </Card>
        ))}
      </div>
    );
  };

export default PostList;


