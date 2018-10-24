import React, { Component } from "react";
import logo from "../images/logo.svg";
import "./App.css";
import GamePicker from "./GamePicker";
import Board from "./Board";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            gameLoaded: false
        };
        props.store.subscribe(() => this.setState({gameLoaded: this.props.store.getState().gameLoaded}));
    }

  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo"/>
          <h1 className="App-title">Welcome to React</h1>
        </header>
        {!this.state.gameLoaded &&
            <GamePicker store={this.props.store}/>
        }
        {this.state.gameLoaded &&
            <Board store={this.props.store}/>
        }
      </div>
    );
  }
}

export default App;
