import React from "react";

class GamePicker extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            gameId: "",
            chosenPlayer: "player1",
            gameIds: []
        };
        this.handleClick = this.handleClick.bind(this);
        this.setGameId = this.setGameId.bind(this);
        this.store = props.store;
        this.store.subscribe(() => this.setState({
            gameId: props.store.getState().gameIds[0],
            gameIds: props.store.getState().gameIds
        }))
    }

    componentDidMount() {
        this.store.dispatch({type: "LOAD_GAME_IDS_REQUESTED"});
    }

    handleClick() {
        this.store.dispatch({type: "PLAYER_CHOSEN", player: this.state.chosenPlayer});
        this.store.dispatch({type: "LOAD_GAME_REQUESTED", gameId: this.state.gameId});
    }

    setGameId(event) {
        this.setState({gameId: event.target.value});
    }

    setPlayer(event) {
        this.setState({chosenPlayer: event.target.value});
    }

    render() {
        return (
            <div className="GamePicker">
                <h1>Game:</h1>
                <select value={this.state.gameId} onChange={this.setGameId}>
                    {this.state.gameIds.map((gameId) => <option value={gameId} key={gameId}>{gameId}</option>)}
                </select>
                <h1>Player:</h1>
                <select value={this.state.chosenPlayer} onChange={this.setPlayer}>
                    <option value="player1" key="player1">Player 1</option>
                    <option value="player2" key="player2">Player 2</option>
                </select>
                <br/>
                <button onClick={this.handleClick}>
                    Load
                </button>
            </div>
        );
    }
}

export default GamePicker;