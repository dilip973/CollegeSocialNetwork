import React, { useState } from 'react';
import './Chat.css';

const Chat = () => {
  const [messages, setMessages] = useState([]);
  const [inputText, setInputText] = useState('');

  const handleMessageSubmit = (e) => {
    e.preventDefault();
    setMessages([...messages, inputText]);
    setInputText('');
  };

  return (
    <div className="chat-container">
      <h1 className="chat-header">Chat </h1>
      <div className="message-list">
        {messages.map((message, index) => (
          <div key={index} className={`message ${index % 2 === 0 ? '' : 'other-message'}`}>
            {message}
          </div>
        ))}
      </div>
      <form onSubmit={handleMessageSubmit} className="input-form">
        <input type="text" value={inputText} onChange={(e) => setInputText(e.target.value)} className="input-field" />
        <button type="submit" className="send-button">Send</button>
      </form>
    </div>
  );
};

export default Chat;
