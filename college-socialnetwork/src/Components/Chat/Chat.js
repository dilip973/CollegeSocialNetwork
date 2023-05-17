import React, { useState, useEffect } from 'react';
import axios from 'axios';

const Chat = () => {
  const [content, setContent] = useState('');
  const [receiverId, setReceiverId] = useState('');
  const [messages, setMessages] = useState([]);
  const [loggedInUser, setLoggedInUser] = useState(null);

  // useEffect(() => {
  //   // Fetch chat history with the other user
  //   const fetchMessages = async () => {
  //     try {
  //       const response = await axios.get(`http://localhost:8080/api/chat/messages/${receiverId}`);
  //       setMessages(response.data);
  //     } catch (error) {
  //       console.error(error);
  //     }
  //   };
  //   fetchMessages();
  // }, [receiverId]);

  useEffect(() => {
    // Fetch logged in user
    const fetchLoggedInUser = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/user/login');
        setLoggedInUser(response.data);
      } catch (error) {
        console.error(error);
      }
    };
    fetchLoggedInUser();
  }, []);

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/api/chat/send', { content, receiverId });
      const newMessage = response.data;
      setMessages([...messages, newMessage]);
      setContent('');
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="chat-container">
      <div className="chat-header">
        <h1>Chat with User {receiverId}</h1>
      </div>
      <div className="chat-body">
        <div className="message-list">
          {messages.map((message) => (
            <div key={message.id} className={`message-item ${message.sender.id === loggedInUser.id ? 'sent' : 'received'}`}>
              <div className="message-sender">{message.sender.id === loggedInUser.id ? 'You' : message.sender.name}</div>
              <div className="message-content">{message.message}</div>
            </div>
          ))}
        </div>
        <form onSubmit={handleSubmit}>
          <div className="message-input">
            <input type="text" id="receiverId" value={receiverId} onChange={(event) => setReceiverId(event.target.value)} placeholder="Enter User ID" />
            <textarea id="content" value={content} onChange={(event) => setContent(event.target.value)} placeholder="Enter Message" />
            <button type="submit">Send</button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default Chat;
