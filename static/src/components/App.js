import React, { Component } from "react";
import logo from "../images/logo.svg";
import "./App.css";
import GamePicker from "./GamePicker";

class App extends Component {
  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo"/>
          <h1 className="App-title">Welcome to React</h1>
        </header>
        <GamePicker store={this.props.store}/>
      </div>
    );
  }
}

export default App;
