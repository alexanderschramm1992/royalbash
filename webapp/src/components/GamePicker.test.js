import React from "react";
import ReactDOM from 'react-dom';
import GamePicker from "./GamePicker";

describe("Game Picker Component", () => {

    const store = {
        dispatch: function(action) {
            this.actions.push(action);
        },
        subscribe: function(methodToTrigger) {

        },
        actions: []
    };

    it("renders without crashing", () => {
        const div = document.createElement('div');
        ReactDOM.render(<GamePicker store={store}/>, div);
        ReactDOM.unmountComponentAtNode(div);
    });

    it("sets gameId", () => {

        // Given
        const testee = ReactDOM.render(<GamePicker store={store}/>, document.createElement('div'));

        // When
        testee.setGameId({target: {value: "123qwe"}});

        // Then
        expect(testee.state.gameId).toBe("123qwe");
    });

    it("sets chosenPlayer", () => {

        // Given
        const testee = ReactDOM.render(<GamePicker store={store}/>, document.createElement('div'));

        // When
        testee.setPlayer({target: {value: "player2"}});

        // Then
        expect(testee.state.chosenPlayer).toBe("player2");
    });

    it("triggers LOAD_GAME_REQUESTED action", () => {

        // Given
        const testee = ReactDOM.render(<GamePicker store={store}/>, document.createElement('div'));

        // When
        testee.handleClick();

        // Then
        expect(store.actions).toContainEqual({type: "LOAD_GAME_REQUESTED", gameId: ""});
        expect(store.actions).toContainEqual({type: "PLAYER_CHOSEN", player: "player1"})
    });
});
