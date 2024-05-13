import logo from './logo.svg';
import './App.css';
import axios from "axios";
import React, { useEffect, useState, useRef } from "react";
import { useParams } from "react-router-dom";
import { Stomp } from "@stomp/stompjs";

function App() {
   
  const stompClient = useRef(null);
  const [messages, setMessages] = new useState();
  //  useEffect(() => {
  //   connect();
  //   fetchMessages();
  //   // 컴포넌트 언마운트 시 웹소켓 연결 해제
  //   return () => disconnect();
  // }, [roomId]);

  // 웹소켓 연결 설정
  const connect = () => {
    const socket = new WebSocket("ws://localhost:8080/ws");
    stompClient.current = Stomp.over(socket);
    stompClient.current.connect({}, () => {
      // stompClient.current.subscribe(`/sub/chatroom/${roomId}`, (message) => {
      //   const newMessage = JSON.parse(message.body);
      //   setMessages((prevMessages) => [...prevMessages, newMessage]);

      //   if (newMessage.senderSeq !== currentUser.userSeq) {
      //     setCustomerSeq(newMessage.senderSeq);
      //   }
      // });
    });
    console.log("방 번호", 1);
  };
  // 웹소켓 연결 해제
  // const disconnect = () => {
  //   if (stompClient.current) {
  //     stompClient.current.disconnect();
  //   }
  // };
  // 기존 채팅 메시지를 서버로부터 가져오는 함수
  const fetchMessages = () => {
    return axios.get("http://localhost:8080/chat/1" )
           .then(response => {setMessages(response.data)});
    
  };

   useEffect(() => {
    // connect();
    fetchMessages();
    // // 컴포넌트 언마운트 시 웹소켓 연결 해제
    // return () => disconnect();
  }, []);

  console.log(messages)
  return (
    <div>
      테스트입니다.!
    </div>
  );
}

export default App;
