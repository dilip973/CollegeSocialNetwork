import React, { useState } from 'react';
import './PostForm.css';

function PostForm() {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [imageUrl, setImageUrl] = useState('');

  const handleSubmit = (event) => {
    event.preventDefault();
    // Send the post data to the server using fetch or axios
    console.log({title, description, imageUrl});
  }

  return (
    <div className="post-form-container">
      <h2>Create a new post</h2>
      <form onSubmit={handleSubmit}>
        <label>
          Title:
          <input type="text" value={title} onChange={(event) => setTitle(event.target.value)} />
        </label>
        <label>
          Description:
          <textarea value={description} onChange={(event) => setDescription(event.target.value)} />
        </label>
        <label>
          Image URL:
          <input type="text" value={imageUrl} onChange={(event) => setImageUrl(event.target.value)} />
        </label>
        <button type="submit">Submit</button>
      </form>
    </div>
  );
}

export default PostForm;
