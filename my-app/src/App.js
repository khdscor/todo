import React from "react";
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Chatting from "./Chatting";
import File from "./File";
function App() {   
  return (
  <Router>
    <Routes>
      <Route path="/chatting" element={<Chatting />} />
      <Route path="/file" element={<File />} />
    </Routes>
  </Router>
  );
}

export default App;
