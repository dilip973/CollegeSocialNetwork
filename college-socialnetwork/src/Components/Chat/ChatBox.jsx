import React from "react";
// import "./MessageList.css";

const MessageList = ({ messages }) => {
  return (
    <div className="message-container">
      {messages.map((message) => (
        <div key={message.id} className="message-item">
          <p className="message-content">{message.content}</p>
          <p className="sender-info">Sender: {message.sender.firstName}</p>
        </div>
      ))}
    </div>
  );
};

export default MessageList;
