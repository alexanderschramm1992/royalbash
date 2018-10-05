import React from "react";

class GamePicker extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            gameId: "",
            gameIds: []
        };
        this.handleClick = this.handleClick.bind(this);
        this.setGameId = this.setGameId.bind(this);
        props.store.dispatch({type: "LOAD_GAME_IDS_REQUESTED"});
        props.store.subscribe(() => this.setState({gameIds: props.store.getState().gameIds}))
    }

    handleClick() {
        this.props.store.dispatch({type: "LOAD_GAME_REQUESTED", gameId: this.state.gameId});
    }

    setGameId(event) {
        this.setState({gameId: event.target.value});
    }

    render() {
        return (
            <div className="GamePicker">
                <input type="text" value={this.state.inputValue} onChange={this.setGameId}/>
                <select value={this.state.gameId} onChange={this.setGameId}>
                    {this.state.gameIds.map((gameId) => <li>{gameId}</li>)}
                </select>
                <button onClick={this.handleClick}>
                    Load
                </button>
            </div>
        );
    }
}

export default GamePicker;