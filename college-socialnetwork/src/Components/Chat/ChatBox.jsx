import React from 'react';
import PropTypes from 'prop-types';
import './Chat.css';

function ChatBox({ messages, loggedInUser }) {
  return (
    <div className="chat-box">
      {messages.map((message) => (
        <div key={message.id} className={`message ${message.sender.id === loggedInUser.id ? 'sent' : 'received'}`}>
          <span className="sender-name">{message.sender.name}</span>
          <span className="message-text">{message.message}</span>
        </div>
      ))}
    </div>
  );
}

ChatBox.propTypes = {
  messages: PropTypes.array.isRequired,
  loggedInUser: PropTypes.object.isRequired,
};

export default ChatBox;
