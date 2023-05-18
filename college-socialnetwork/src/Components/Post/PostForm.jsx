import React, { useState } from "react";
import axios from "axios";
import './PostForm.css';

const PostForm = () => {
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [imageData, setImageData] = useState(null);

  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
      const formData = new FormData();

      formData.append("title", title);
      formData.append("content", content);
      formData.append("image", imageData);

      const response = await axios.post(
        "http://localhost:8080/posts/create",
        formData,
        {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        }
      );

      console.log("Success:", response.data);

      // Reset the form fields
      setTitle("");
      setContent("");
      setImageData(null);
    } catch (error) {
      console.error("Error:", error);
    }
  };

  const handleImageUpload = (event) => {
    const file = event.target.files[0];
    setImageData(file);
  };

  return (
    <div className="post-form-container">
      <h2>Create a New Post</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <input
            id="title"
            type="text"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            className="form-input"
            placeholder="Title"
          />
        </div>
        <div className="form-group">
          <textarea
            id="content"
            placeholder="What's on your mind"
            value={content}
            onChange={(e) => setContent(e.target.value)}
            className="form-input"
          ></textarea>
        </div>
        <div className="form-group">
          <label htmlFor="image" className="file-label">
            <span>Upload Image</span>
            <input
              id="image"
              type="file"
              accept="image/*"
              onChange={handleImageUpload}
              className="file-input"
            />
          </label>
        </div>
        <button type="submit" className="submit-btn">
          Post
        </button>
      </form>
    </div>
  );
};

export default PostForm;
