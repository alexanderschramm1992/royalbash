import React from "react";
import ReactDOM from "react-dom";
import Player from "./Player";

describe("Player Component", () => {

    const store = {
        dispatch: function(action) {this.actions.push(action);},
        subscribe: function(methodToTrigger) {},
        getState: function() {return {gameLoaded: true, chosenPlayer: "player1", game: {player1: {id: "Owner Id"}}}},
        actions: []
    };

    it("renders without crashing", () => {
        const div = document.createElement('div');
        ReactDOM.render(<Player store={store} id="Player Id"/>, div);
        ReactDOM.unmountComponentAtNode(div);
    });

    it("does not trigger PLAY_CARD_ON_PLAYER_REQUESTED action", () => {

        // Given
        const testee = ReactDOM.render(<Player store={store} id="Player Id"/>, document.createElement('div'));
        const event = {
            dataTransfer: {
                getData: () => {return "banana";}
            }
        };

        // When
        testee.handleDrop(event);

        // Then
        expect(store.actions).toHaveLength(0);
    });

    it("triggers PLAY_CARD_ON_PLAYER_REQUESTED action", () => {

        // Given
        const testee = ReactDOM.render(<Player store={store} id="Player Id"/>, document.createElement('div'));
        const event = {
            dataTransfer: {
                getData: (key) => {
                    if(key === "type") {return "card";}
                    if(key === "payload") {return {id: "Card Id"}}
                }
            }
        };

        // When
        testee.handleDrop(event);

        // Then
        expect(store.actions).toContainEqual({
            type: "PLAY_CARD_ON_PLAYER_REQUESTED",
            cardId: "Card Id",
            ownerId: "Owner Id",
            playerId: "Player Id"
        });
    });
});
