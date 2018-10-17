import React from "react";

class Board extends React.Component {

    constructor(props) {
        super();
        this.state = {store: props.store};
        props.store.subscribe(() => this.setState({game: props.store.getState().game}));
    }

    handleEndTurnButton() {
        this.state.store.dispatch({type: "END_TURN_REQUESTED", player: this.state.store.chosenPlayer});
    }

    handleDrawCardButton() {
        this.state.store.dispatch({type: "DRAW_CARD_REQUESTED", player: this.state.store.chosenPlayer});
    }

    render() {
        return (
            <div className="Board">

            </div>
        )
    }
}

export default Board;
