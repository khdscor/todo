import React from "react";
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Chatting from "./Chatting";
import File from "./file/File";
import ReadFile from "./file/ReadFile";
function App() {   
  return (
  <Router>
    <Routes>
      <Route path="/chatting" element={<Chatting />} />
      <Route path="/file" element={<File />} />
      <Route path="/readfile" element={<ReadFile />} />
    </Routes>
  </Router>
  );
}

export default App;
