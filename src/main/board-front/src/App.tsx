import React from "react";
import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Board from "./components/board";
import WriteForm from "./components/WriteForm";

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <Routes>
          <Route path="/" element={<Board />}></Route>
          <Route path="/write" element={<WriteForm />}></Route>
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
