import logo from './logo.svg';
import './App.css';
import axios from "axios";
import React, { useEffect, useState, useRef } from "react";
import { useParams } from "react-router-dom";
import { Stomp } from "@stomp/stompjs";

function App() {
   
  const stompClient = useRef(null);
  const [messages, setMessages] = new useState();
  const [message, setMessage] = useState("");

  // 웹소켓 연결 설정
  const connect = () => {
    const socket = new WebSocket("ws://localhost:8080/ws-stomp");
    stompClient.current = Stomp.over(socket);
    stompClient.current.connect({}, () => {
      stompClient.current.subscribe(`/sub/chatroom/1`, (message) => {
        console.log("성공!")
         const newMessage = JSON.parse(message.body);
        setMessages((prevMessages) => [...prevMessages, newMessage]);

        console.log(newMessage.senderSeq)
        // if (newMessage.senderSeq !== currentUser.userSeq) {
        //   // setCustomerSeq(newMessage.senderSeq);
        // }
      });
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
    connect();
    fetchMessages();
    // // 컴포넌트 언마운트 시 웹소켓 연결 해제
    // return () => disconnect();
  }, []);

  const sendMessage = () => {
    if (stompClient.current) {
      const body = {
        id : 1,
        name : "테스트1",
        message : "테스트입니다."
      };
      stompClient.current.send(`/pub/message`, {}, JSON.stringify(body));
      console.log("11111111111111111")
      setMessage(""); // 입력 필드 초기화
    }
  };



  console.log(messages)
  return (
    <div>
      테스트입니다.!
      <button onClick={()=>{sendMessage()}}>버튼</button>
    </div>
  );
}

export default App;
