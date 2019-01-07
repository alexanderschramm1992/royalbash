import React, { Component } from "react";
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
