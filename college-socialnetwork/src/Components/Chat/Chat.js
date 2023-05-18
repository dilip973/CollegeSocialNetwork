import React, { useState, useEffect } from "react";
import axios from "axios";
import "./Chat.css";
import MessageList from "./ChatBox";


const Chat = () => {
  const [messages, setMessages] = useState([]);
  const [recipientId, setRecipientId] = useState("");
  const [content, setContent] = useState("");

  useEffect(() => {
    fetchMessages();
  }, []);

  const fetchMessages = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/chat/messages`
      );
      setMessages(response.data);
    } catch (error) {
      console.log(error);
    }
  };

  const sendMessage = async () => {
    try {
      const response = await axios.post("http://localhost:8080/chat/send", {
        recipientIds: [recipientId],
        content: content,
      });
      console.log(response.data);
      // Clear input fields and fetch messages again
      setContent("");
      fetchMessages();
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <div className="chat-container">
      <h1 className="chat-title">Start a new Chat</h1>
      <div className="input-container">
        <label htmlFor="recipientId">Recipient ID:</label>
        <input
          type="text"
          id="recipientId"
          value={recipientId}
          onChange={(e) => setRecipientId(e.target.value)}
        />
      </div>
      <div className="input-container">
        <label htmlFor="messageContent">Message:</label>
        <input
          type="text"
          id="messageContent"
          value={content}
          onChange={(e) => setContent(e.target.value)}
        />
      </div>
      <button className="send-button" onClick={sendMessage}>
        Send Message
      </button>
      <MessageList messages={messages} />
    </div>
  );
};

export default Chat;
